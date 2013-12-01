<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="resources/js/jquery-1.10.2.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="resources/bootstrap/css/bootstrap.min.css" />
<script src="resources/js/handlebars.js"></script>
<script src="resources/js/script.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/home.css" />
<html>
<head>
<title>schedutary</title>
</head>

<body>
	<div class="well" style="display: none;">
		<span id="today_month">${today_month}</span> 
		<span id="today_int_month">${today_int_month}</span> 
		<span id="today_year">${today_year}</span>
		<span id="today_day">${today_day}</span> 
		<span id="today_date">${today_date }</span>
		<span id="first_day_of_month">${first_day_month}</span> 
		<span id="first_int_day_of_month">${first_int_day_month}</span> 
		<span id="last_day_of_month">${last_day_month}</span> 
		<span id="week_of_month">${week_of_month}</span>
	</div>

	<div class="accordion" id="calendarAccordion">
		<div class="accordion-group">
			<div class="accordion-heading">
				<a class="accordion-toggle" id="weekly_accordion_toggle" data-toggle="collapse" data-parent="#calendarAccordion" href="#weeklyCollapse">
					weekly
				</a>
			</div>	
		<div id="weeklyCollapse" class="accordion-body collapse">
			<div id="weekly_calendar_section" class="well">
				<div id="weekly_calendar_controls">
					<span id="weekly_calendar_scroll_up">^</span>
					<span id="weekly_calendar_scroll_down">v</span>	
					<ul id="weekly_calendar_times">
						<li>12pm</li>
						<li>1am</li>			
						<li>2am</li>			
						<li>3am</li>
						<li>4am</li>
						<li>5am</li>
						<li>6am</li>
						<li>7am</li>
						<li>8am</li>
						<li>9am</li>
						<li>10am</li>
						<li>11am</li>
						<li>12am</li>
						<li>1pm</li>
						<li>2pm</li>
						<li>3pm</li>
						<li>4pm</li>
						<li>5pm</li>
						<li>6pm</li>
						<li>7pm</li>
						<li>8pm</li>
						<li>9pm</li>
						<li>10pm</li>
						<li>11pm</li>
					</ul>
				</div>
				<table id="weekly_calendar" border="1" class="table">
					<thead>
						<tr>
							<td colspan="8">
							week of ${today_month} ${today_date}
							</td>
						</tr>
					</thead>
					<tr>
						<th>sun</th>
						<th>mon</th>
						<th>tue</th>
						<th>wed</th>
						<th>thur</th>
						<th>fri</th>
						<th>sat</th>
					</tr>
		
					<tr id="weekly_calendar_row">
						<c:forEach var="i" begin="1" end="7" step="1">
							<c:if test="${today_date <= 7 - first_int_day_month}">
								<c:choose>
									<c:when test="${today_date <= first_int_day_month}">
										<td></td>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${i - first_int_day_month <= 0}">
												<td></td>
											</c:when>
											<c:otherwise>
												<td>		
												<a href="#" id="date" style="display: block">${i - first_int_day_month}</a>
													<ul class="calendar_day">
														<li id="${i-first_int_day_month}_00" style="font-size:14px; display:block">12pm</li>
														<li id="${i-first_int_day_month}_01" style="font-size:14px; display:block">1am</li>
														<li id="${i-first_int_day_month}_02" style="font-size:14px; display:block">2am</li>
														<li id="${i-first_int_day_month}_03" style="font-size:14px; display:block">3am</li>
														<li id="${i-first_int_day_month}_04" style="font-size:14px; display:block">4am</li>
														<li id="${i-first_int_day_month}_05" style="font-size:14px; display:block">5am</li>
														<li id="${i-first_int_day_month}_06" style="font-size:14px; display:block">6am</li>
														<li id="${i-first_int_day_month}_07" style="font-size:14px; display:block">7am</li>
														<li id="${i-first_int_day_month}_08" style="font-size:14px; display:block">8am</li>
														<li id="${i-first_int_day_month}_09" style="font-size:14px; display:block">9am</li>
														<li id="${i-first_int_day_month}_10" style="font-size:14px; display:block">10am</li>
														<li id="${i-first_int_day_month}_11" style="font-size:14px; display:block">11am</li>
														<li id="${i-first_int_day_month}_12" style="font-size:14px; display:block">12am</li>
														<li id="${i-first_int_day_month}_13" style="font-size:14px; display:block">1pm</li>
														<li id="${i-first_int_day_month}_14" style="font-size:14px; display:block">2pm</li>
														<li id="${i-first_int_day_month}_15" style="font-size:14px; display:block">3pm</li>
														<li id="${i-first_int_day_month}_16" style="font-size:14px; display:block">4pm</li>
														<li id="${i-first_int_day_month}_17" style="font-size:14px; display:block">5pm</li>
														<li id="${i-first_int_day_month}_18" style="font-size:14px; display:block">6pm</li>
														<li id="${i-first_int_day_month}_19" style="font-size:14px; display:block">7pm</li>
														<li id="${i-first_int_day_month}_20" style="font-size:14px; display:block">8pm</li>
														<li id="${i-first_int_day_month}_21" style="font-size:14px; display:block">9pm</li>
														<li id="${i-first_int_day_month}_22" style="font-size:14px; display:block">10pm</li>
														<li id="${i-first_int_day_month}_23" style="font-size:14px; display:block">11pm</li>
													</ul>
												</td>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:if>
							<c:if
								test="${(today_date > 7 - first_int_day_month) and ((i + (7 * week_of_month) - first_int_day_month) <= last_day_month)}">
								<td>
									<a href="#" style="display: block" id="date">${i + (7 * week_of_month) - first_int_day_month}</a>
									<ul class="calendar_day">
										<li id="${i + (7 * week_of_month) - first_int_day_month}_00" style="font-size:14px; display:block">12pm</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_01" style="font-size:14px; display:block">1am</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_02" style="font-size:14px; display:block">2am</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_03" style="font-size:14px; display:block">3am</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_04" style="font-size:14px; display:block">4am</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_05" style="font-size:14px; display:block">5am</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_06" style="font-size:14px; display:block">6am</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_07" style="font-size:14px; display:block">7am</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_08" style="font-size:14px; display:block">8am</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_09" style="font-size:14px; display:block">9am</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_10" style="font-size:14px; display:block">10am</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_11" style="font-size:14px; display:block">11am</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_12" style="font-size:14px; display:block">12am</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_13" style="font-size:14px; display:block">1pm</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_14" style="font-size:14px; display:block">2pm</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_15" style="font-size:14px; display:block">3pm</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_16" style="font-size:14px; display:block">4pm</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_17" style="font-size:14px; display:block">5pm</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_18" style="font-size:14px; display:block">6pm</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_19" style="font-size:14px; display:block">7pm</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_20" style="font-size:14px; display:block">8pm</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_21" style="font-size:14px; display:block">9pm</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_22" style="font-size:14px; display:block">10pm</li>
										<li id="${i + (7 * week_of_month) - first_int_day_month}_23" style="font-size:14px; display:block">11pm</li>
									</ul>
								</td>						
							</c:if>
						</c:forEach>
					</tr>
				</table>
			</div>
			</div>
		</div>
		
		<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" id="monthly_accordion_toggle" data-toggle="collapse" data-parent="#calendarAccordion" href="#monthlyCollapse">monthly</a>
		</div>
		<div id="monthlyCollapse" class="accordion-body collapse">
		<div class="well">
			<table border="1" class="table">
				<thead>
					<tr>
						<td colspan="8">${today_month},${today_year}</td>
					</tr>
				</thead>
				<tr>
					<th>sun</th>
					<th>mon</th>
					<th>tue</th>
					<th>wed</th>
					<th>thur</th>
					<th>fri</th>
					<th>sat</th>
				</tr>
				<c:set var="monthDictionary" value="${monthDictionary}" />
				<tbody>
					<c:forEach var="i" begin="0" end="${last_day_month }" step="7">
						<tr>
							<c:forEach var="j" begin="1" end="7" step="1">
								<c:set var="sum" value="${i+j}" />
								<c:choose>
									<c:when test="${sum <= first_int_day_month}">
										<td></td>
									</c:when>
									<c:otherwise>
										<c:if test="${sum - first_int_day_month <= last_day_month}">
											<td><a href="#" id="date">${sum - first_int_day_month}</a>
												<ul id="entriesList">
													<c:forEach var="monthDictionary" items="${monthDictionaryList}">
														<c:if test="${monthDictionary.date == (sum - first_int_day_month)}">
															<li>${monthDictionary.npm ||e } -- ${monthDictionary.priority }</li>
														</c:if>
													</c:forEach>
												</ul></td>
										</c:if>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		</div>
		</div>
	</div>

	<div class="modal hide fade" tabindex="-1" role="dialog"
		id="emptyDateModal">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3>agenda</h3>
		</div>
		<div class="modal-body">
			<p id="empty">nothing to display for this date</p>
		</div>
	</div>

	<div class="modal hide fade" tabindex="-1" role="dialog" id="dateModal">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3>agenda</h3>
		</div>
		<div class="modal-body">
			<div id="agenda-body"></div>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">close</button>
		</div>
	</div>



	<div class="well">
		<a href="/add">add</a>
	</div>

	<script id="modal-template" type="text/x-handlebars-template">
		<table class="table" border="1"> 
			<thead>
				<tr>
					<td>name</td>
					<td>label</td>
					<td>priority</td>
					<td>date</td>
					<td>notes</td>
					<td>careful!</td>
				</tr>
			</thead>
			{{#each .}}
				<tbody>
					<tr id="entry">
						<td id="entry-name">{{name}}</td>		
						<td id="entry-label">{{label}}</td>
						<td id="entry-priority">{{priority}}</td>
						<td id="entry-date">{{month}} {{date}}, {{year}}</td>
						<td id="entry-notes">{{notes}}</td>
						<td><a id="delete" tokenDelete="{{name}}">DELETE</a></td>
					</tr>
				</tbody>
			{{/each}}
		</table>
	</script>

</body>

<script type="text/javascript" src="resources/js/home.js">
	
</script>

</html>
