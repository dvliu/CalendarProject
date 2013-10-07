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
				console.log("fulldata : ", fulldata);
				$('#dateModal').modal('show');
				$('#metaJson').html(fulldata);
			}
		});
	});
});
