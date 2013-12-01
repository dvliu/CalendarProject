<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="resources/js/jquery-1.10.2.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/bootstrap/css/bootstrap.min.css" />
<script src="resources/js/script.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/styles.css"/>

<html>
<head>
<title>add event || schedutary</title>
</head>

<body>
	<div class="well">
		<form class="form-horizontal">
			<div class="control-group">
				<label class="control-label" for="date">date</label>
				<div class="controls">
					<input type="date" id="date" placeholder="..." />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="name">name</label>
				<div class="controls">
					<input type="text" id="name" placeholder="..." />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="label">label</label>
				<div class="controls">
					<input type="text" id="label" placeholder="..." />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="priority">priority</label>
				<div class="controls">
					<select id="priority">
						<option disabled selected>select...</option>
						<c:forEach var="priority" items="${priorities}">
							<option>${priority}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="time">time</label>
				<div class="controls">
					<input type="time" id="time" placeholder="..." />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="estimate">estimate</label>
				<div class="controls">
					<input type="text" id="estimate" placeholder="minutes" />
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="notes">notes</label>
				<div class="controls">
					<input type="text" id="notes" placeholder="..." />
				</div>
			</div>
			<a id="save">save</a> <br /> <a id="back">back to init page</a>
		</form>
	</div>
</body>

<script type="text/javascript">
	$(document).ready(function() {
		$('a#back').bind('click', function() {
			$('a#back').attr('href', '/');
		});

		$('a#save').bind('click', function() {
			$.ajax({
				type : "POST",
				url : "save",
				data : {
					date : $('#date').val(),
					name : $('#name').val(),
					label : $('#label').val(),
					priority : $('#priority').val(),
					time : $('#time').val(),
					estimate : $('#estimate').val(),
					notes : $('#notes').val()
				}
			}).done(function() {
				alert("saved!");
			});
		});
	});
</script>
</html>
