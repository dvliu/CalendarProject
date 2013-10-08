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
	
	$('a#delete').bind('click', function(){
		$.ajax({
			type : "POST",
			url : "delete",
			data : {
				tokenDelete : $(this).attr("tokenDelete")
			}
		}).done(function(fulldata){
			alert("deleted");
		});
	});
});
