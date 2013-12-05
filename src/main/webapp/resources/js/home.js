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

		$('.calendar_day li').each(function(i) {
			var compareId;
			var indexId = $(this).attr('id');
			
			for ( var i in dataJson) {
				compareId = dataJson[i].date + "_" + dataJson[i].time;
				compareId = compareId.replace(":00", "");
				if(indexId == compareId){
					console.log(indexId);
					document.getElementById(compareId).innerHTML = dataJson[i].name;
				}
			};
			
		});

	});

	$('#weeklyCollapse').collapse('show');

	$('#weekly_accordion_toggle').bind('click', function() {
		$('#weeklyCollapse').collapse('show');
		$('#monthlyCollapse').collapse('hide');
	});

	$('#monthly_accordion_toggle').bind('click', function() {
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

	$('#weekly_calendar_scroll_up').click(function() {
		$('#weekly_calendar_times').animate({
			scrollTop : '-=20px'
		}, 400);
		$('.calendar_day').animate({
			scrollTop : '-=20px'
		}, 400);
	});

	$('#weekly_calendar_scroll_down').click(function() {
		$('#weekly_calendar_times').animate({
			scrollTop : '+=20px'
		}, 400);
		$('.calendar_day').animate({
			scrollTop : '+=20px'
		}, 400);
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
