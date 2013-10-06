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

	private ObjectMapper mapper;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String init(Model model) {
		cacheService.init();
		model.addAttribute("title", "calendar");

		String firstDayOfMonth = cDate.getFirstDayOfMonth();
		int firstIntDayOfMonth = cDate.getIntFirstDayOfMonth(firstDayOfMonth);

		model.addAttribute("today_month", cDate.getStringMonth(cDate.getMonth()));
		model.addAttribute("today_int_month", cDate.getMonth());
		model.addAttribute("today_year", cDate.getYear());
		model.addAttribute("today_date", cDate.getDate());
		model.addAttribute("last_day_month", cDate.getLastDayOfMonth(cDate.getMonth()));
		model.addAttribute("first_day_month", firstDayOfMonth);
		model.addAttribute("first_int_day_month", firstIntDayOfMonth);
		model.addAttribute("today_day", cDate.getStringDayOfWeek());
		model.addAttribute("week_of_month", cDate.getWeekOfMonth());

		List<HashMap<String, String>> activities = Lists.newArrayList();

		return "home";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("title", "add");
		List<String> prioritiesList = processPriorities();
		model.addAttribute("priorities", prioritiesList);

		return "add";
	}

	private List<String> processPriorities() {
		List<String> prioritiesList = Lists.newArrayList();
		for (Priority p : Priority.values()) {
			String priority = p.toString().toLowerCase();
			prioritiesList.add(priority);
		}
		return prioritiesList;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(Model model, @RequestParam(value = "date") String date, @RequestParam(value = "name") String name, @RequestParam(value = "label") String label,
			@RequestParam(value = "priority") String priority, @RequestParam(value = "notes") String notes) {
		HashMap<String, Object> metadata = Maps.newHashMap();
		metadata.put("name", name);
		metadata.put("label", label);
		metadata.put("priority", priority);
		metadata.put("year", cDate.getYear(date));
		metadata.put("month", cDate.getMonth(date));
		metadata.put("date", cDate.getDate(date));
		metadata.put("notes", notes);
		cacheService.index(metadata);
		return "home";
	}
	
	
	@ResponseBody
	@RequestMapping(value="display", method = RequestMethod.GET)
	public String display (Model model, @RequestParam(value="date") String date , @RequestParam(value="month") String month) throws Throwable{
		List<HashMap<String, String>> answer = Lists.newArrayList();
		List<HashMap<String, String>> searchResults = cacheService.searchMonth(month);
		for(HashMap<String, String> result : searchResults){
			if (result.get("date").equals(date)){
				answer.add(result);
			}
		}
		return mapper.writeValueAsString(answer);
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void delete(Model model) {

	}
}
