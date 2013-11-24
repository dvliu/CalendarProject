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
				$('#dateModal').modal('show');

				var source = $("#modal-template").html();
				var template = Handlebars.compile(source);
				var html = template(fulldata);
				$("#agenda-body").html(html);
			}
		});
	});
	
	$('#weekly_calendar_scroll_up').click(function(){
	$('.calendar_day').animate({scrollTop: '-=20px'}, 400);
	});
	
	
	$('#weekly_calendar_scroll_down').click(function(){
		$('#weekly_calendar_times').animate({scrollTop: '+=20px'}, 400);
		$('.calendar_day').animate({scrollTop: '+=20px'}, 400);
	});

	$('a#delete').bind('click', function() {
		$.ajax({
			type : "POST",
			url : "delete",
			data : {
				tokenDelete : $(this).attr("tokenDelete")
			}
		}).done(function(fulldata) {
			alert("deleted");
		});
	});
});
