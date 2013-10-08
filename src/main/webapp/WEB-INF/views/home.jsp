<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="resources/js/jquery-1.10.2.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.min.css" />
<script src="resources/js/handlebars.js"></script>
<script src="resources/js/script.js"></script>
<html>
<head>
<title>${title}</title>
</head>

<body>
	<div class="well" style="display: none">
		<span id="today_month">${today_month}</span> <span
			id="today_int_month">${today_int_month}</span> <span id="today_year">${today_year}</span>
		<span id="today_date">${today_day}</span> <span id="today_date">${today_date }</span>
		<span>First day of month is -> ${first_day_month}</span> <span>First
			int day of month is -> ${first_int_day_month}</span> <span>Last day
			of the month is -> ${last_day_month}</span> <span>Week of Month ->
			${week_of_month}</span>
	</div>

	<div class="well">
		<p>monthly</p>
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
										<td><a href="#" id="date">${sum - first_int_day_month}</a></td>
									</c:if>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
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
		<p>weekly</p>
		<table border="1" class="table">
			<thead>
				<tr>
					<td colspan="8">week of ${today_month} ${today_date}</td>
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

			<tbody>
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
										<td><a href="#" id="date">${i - first_int_day_month}</a></td>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${today_date > 7 - first_int_day_month}">
						<td><a href="#" id="date">${i + (7 * week_of_month) - first_int_day_month}</a></td>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
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
