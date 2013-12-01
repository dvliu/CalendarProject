package awesome.calendar.project;

import java.util.HashMap;
import java.util.List;

import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class HomeController {

	@Autowired
	private CurrentDate cDate;

	@Autowired
	private CacheService cacheService;

	ObjectMapper mapper = new ObjectMapper();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String init(Model model) {
		cacheService.init();

		String firstDayOfMonth = cDate.getFirstDayOfMonth();
		int firstIntDayOfMonth = cDate.getIntFirstDayOfMonth(firstDayOfMonth);
		model.addAttribute("monthDictionaryList", cacheService.searchMonth(cDate.getMonth()));

		model.addAttribute("today_month", cDate.getStringMonth(cDate.getMonth()));
		model.addAttribute("today_int_month", cDate.getMonth());
		model.addAttribute("today_year", cDate.getYear());
		model.addAttribute("today_date", cDate.getDate());
		model.addAttribute("last_day_month", cDate.getLastDayOfMonth(cDate.getMonth()));
		model.addAttribute("first_day_month", firstDayOfMonth);
		model.addAttribute("first_int_day_month", firstIntDayOfMonth);
//		model.addAttribute("today_day", cDate.getStringDayOfWeek());
		model.addAttribute("week_of_month", cDate.getWeekOfMonth());
		
		return "home";
	}
	
	@ResponseBody
	@RequestMapping(value = "getDataForWeek", method = RequestMethod.POST)
	public String getDataForWeek(Model model, @RequestParam(value="today_date")long todayDate, @RequestParam(value = "month") long month, @RequestParam(value="week_of_month") long weekOfMonth, @RequestParam(value="first_int_day_of_month") long firstIntDayOfMonth) throws Throwable{
		List<HashMap<String, String>> answer = cacheService.searchWeek(month, weekOfMonth, todayDate, firstIntDayOfMonth);
		return mapper.writeValueAsString(answer);
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model) {
		List<String> prioritiesList = cacheService.processPriorities();
		model.addAttribute("priorities", prioritiesList);

		return "add";
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(Model model, @RequestParam(value = "date") String date, @RequestParam(value = "name") String name, @RequestParam(value = "label") String label, @RequestParam(value = "priority") String priority,
			@RequestParam(value = "notes") String notes, @RequestParam(value = "time") String time, @RequestParam(value = "estimate") String estimate) {
		HashMap<String, Object> metadata = Maps.newHashMap();
		metadata.put("name", name.replaceAll("\"", ""));
		metadata.put("label", label.replaceAll("\"", ""));
		metadata.put("priority", priority.replaceAll("\"", ""));
		metadata.put("year", cDate.getYear(date));
		metadata.put("month", cDate.getMonth(date));
		metadata.put("date", cDate.getDate(date));
		metadata.put("notes", notes.replaceAll("\"", ""));
		metadata.put("estimate", Integer.valueOf(estimate.replaceAll("\"", "")));
		metadata.put("time", cDate.getTime(time));
		cacheService.index(metadata);
		return "home";
	}

	@ResponseBody
	@RequestMapping(value = "display", method = RequestMethod.GET)
	public String display(Model model, @RequestParam(value = "date") String date, @RequestParam(value = "month") String month) throws Throwable {
		List<HashMap<String, String>> answer = Lists.newArrayList();
		List<HashMap<String, String>> searchResults = cacheService.searchMonth(Integer.valueOf(month));
		for (HashMap<String, String> result : searchResults) {
			if (result.get("date").equals(date)) {
				answer.add(result);
			}
		}
		if (answer == null || answer.isEmpty()) {
			return null;
		} else {
			return mapper.writeValueAsString(answer);
		}
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void delete(Model model, @RequestParam(value = "tokenDelete") String tokenDelete) {
		cacheService.remove(tokenDelete);
	}
}
