<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="resources/js/jquery-1.10.2.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="resources/bootstrap/css/bootstrap.min.css" />

<html>
<head>
<title>${title}</title>
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
					priority : $('#priority').val()
				}
			}).done(alert("saved!"));
		});
	});
</script>
</html>
