$(document).ready(function() {
	
	var dataJson;
	
	$.ajax({
		type : "POST",
		url : "getDataForWeek",
		data : {
			today_date : $('#today_date').html(),
			month : $('#today_int_month').html(),
			week_of_month : $('#week_of_month').html(),
			first_int_day_of_month : $('#first_int_day_of_month').html()
		}
	
	}).done(function(data) {
		console.log('data: ', data);
		dataJson = $.parseJSON(data);
		console.log('dataJson', dataJson);
		
	});
	
	$('#weeklyCollapse').collapse('show');
	
	$('#weekly_accordion_toggle').bind('click', function(){
		$('#weeklyCollapse').collapse('show');
		$('#monthlyCollapse').collapse('hide');
	});
	
	$('#monthly_accordion_toggle').bind('click', function(){
		$('#weeklyCollapse').collapse('hide');
		$('#monthlyCollapse').collapse('show');
	});
	
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
		$('#weekly_calendar_times').animate({scrollTop: '-=20px'}, 400);
		$('.calendar_day').animate({scrollTop: '-=20px'}, 400);
	});
	
	
	$('#weekly_calendar_scroll_down').click(function(){
		$('#weekly_calendar_times').animate({scrollTop: '+=20px'}, 400);
		$('.calendar_day').animate({scrollTop: '+=20px'}, 400);
	});
	
	Handlebars.registerHelper('if_eq', function(a, b, opts) {
	    if(a == b) // Or ===
	        return opts.fn(this);
	    else
	        return opts.inverse(this);
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
