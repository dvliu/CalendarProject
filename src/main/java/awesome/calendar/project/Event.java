package awesome.calendar.project;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Component;

@Data
@Component
public class Event {

	@Getter
	@Setter
	String name;

	@Getter
	@Setter
	String priority;

	@Getter
	@Setter
	String month;

	@Getter
	@Setter
	String date;

	@Getter
	@Setter
	String year;

	@Getter
	@Setter
	String time;

	@Getter
	@Setter
	String label;

	@Getter
	@Setter
	String notes;

	@Getter
	@Setter
	String estimate;

}
