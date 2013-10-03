package awesome.calendar.project;

import java.util.HashMap;
import java.util.List;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.collect.Maps;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	@Autowired
	private CurrentDate cDate;

	@Autowired
	private CacheService cacheService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String init(Model model) {
		cacheService.init();
		model.addAttribute("title", "calendar");

		String firstDayOfMonth = cDate.getFirstDayOfMonth();
		int firstIntDayOfMonth = cDate.getIntFirstDayOfMonth(firstDayOfMonth);

		model.addAttribute("today_month", cDate.getStringMonth(cDate.getMonth()));
		model.addAttribute("today_year", cDate.getYear());
//		model.addAttribute("today_date", cDate.getDate());
		model.addAttribute("last_day_month", cDate.getLastDayOfMonth(cDate.getMonth()));
		model.addAttribute("first_day_month", firstDayOfMonth);
		model.addAttribute("first_int_day_month", firstIntDayOfMonth);
		model.addAttribute("today_day_of_week", cDate.getDayOfWeek());
		model.addAttribute("today_day", cDate.getStringDayOfWeek());
		
		List<HashMap<String, String>> answer = Lists.newArrayList();

		// search ES implementation
		// for (int i = cDate.getDate(); i < cDate.getDate() + 7; i++) {
		// List<HashMap<String, String>> searchResult =
		// cacheService.search(String.valueOf(cDate.getMonth()), i);
		// for (HashMap<String, String> map : searchResult) {
		// answer.add(map);
		// }
		// }

		model.addAttribute("metadata", answer);
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
	public void save(Model model, @RequestParam(value = "date") String date, @RequestParam(value = "name") String name, @RequestParam(value = "label") String label,
			@RequestParam(value = "priority") String priority) {
		HashMap<String, Object> metadata = Maps.newHashMap();
		metadata.put("name", name);
		metadata.put("label", label);
		metadata.put("priority", priority);
		metadata.put("year", cDate.getYear(date));
		metadata.put("month", cDate.getMonth(date));
		metadata.put("date", cDate.getDate(date));
		cacheService.index(metadata);
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void delete(Model model) {

	}
}
