$(document).ready(function() {
	$('a#date').bind('click', function() {
		$.ajax({
			type : "GET",
			url : "display",
			data : {
				date : $(this).html(),
				month : $('#today_int_month').html()
			}
		}).done(function(fulldata) {
			if (fulldata == null || fulldata.length < 1) {
				console.log("empty data");
				$('#emptyDateModal').modal('show');
			} else {
				fulldata = $.parseJSON(fulldata);
				console.log("fulldata : ", fulldata);
				$('#dateModal').modal('show');
				for (i in fulldata) {
					console.log(fulldata[i].name);
				}
				$('#metaJson').html(fulldata);

				var source = $("#modal-template").html();
				var template = Handlebars.compile(source);
				var html = template(fulldata);
				$("#agenda-body").html(html);
			}
		});
	});
});
