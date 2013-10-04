<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="resources/js/jquery-1.10.2.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.min.css" />

<html>
<head>
<title>${title}</title>
</head>

<body>
	<div class="well">
		<p>Month is: ${today_month}</p>
		<p>Year is: ${today_year}</p>
		<p>First day of month is: ${first_day_month}</p>
		<p>First int day of month is: ${first_int_day_month}</p>
		<p>Last day of the month is: ${last_day_month}</p>
		<p>Today's day of the week: ${today_day_of_week} </p>
		<p>Day is: ${today_day}</p>
		<p>Week of Month: ${week_of_month}</p>
	</div>

	<div class="well">
		<p>monthly</p>
		<table border="1" class="table">
			<thead>
				<tr>
					<td colspan="8">${today_month}, ${today_year}</td>
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
						<c:set var="sum" value="${i+j}"/>
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
					<c:if test="${today_day_of_week <= 7}">
						<c:choose>
							<c:when test="${today_day_of_week <= first_int_day_month}">
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
					<c:if test="${today_day_of_week > 7}">
						<td><a>${first_int_day_month + (7 * week_of_month)}</a></td>
					</c:if>
					
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="well">
		<p>daily</p>
		<p>${today_month} ${today_day_of_week}, ${today_year}</p>
		<ul>
			<li></li>
		</ul>
	</div>

	<div class="well">
		<a href="/add">Add</a>

	</div>

</body>

<script type="text/javascript">
	$(document).ready(function() {
		$('a#date').bind('click', function() {
			console.log($(this).html());
		});
	});
</script>
</html>
