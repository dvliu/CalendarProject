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
					console.log("data entry -> ", fulldata[i]);
					console.log("priority -> ", fulldata[i].priority);
					console.log("name -> ", fulldata[i].name);
				}
				$('#metaJson').html(fulldata);
			}
		});
	});
});
