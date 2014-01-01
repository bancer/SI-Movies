$(document).ready(function() {
	
	/* Attaches onclick event listener to the toggleable element.
	 * On the click: 
	 * 1. Adds or removes "collapsable" class from this element.
	 * 2. Displays or hides the next <div> element. "Stop" call prevents
	 * multiple clicks on the same element so the content of the next division
	 * is not displayed multiple times.
	 * Ref.: http://www.webstutorial.com/simple-jquery-toggle-tutorial-css-jquery-slide-toggle/jquery */
	$(".toggleable").click(function(){
		$(this).toggleClass("collapsable");
		$(this).next("div").stop('true', 'true').slideToggle("slow");
	});
	
	
	/* Save comment form handler.
	 * 
	 * Ref.: http://stackoverflow.com/questions/5004233/jquery-ajax-post-example-with-php */
	// variable to hold request
	var request = null;
	// bind to the submit event of our form
	$("#comment_form").submit(function(event){
		// prevent default posting of form
	    event.preventDefault();

		// abort any pending request
	    if (request) {
	        request.abort();
	    }
	    
		request = $.ajax({
			type: "post",
			url: "default.jsp",
			data: $(this).serialize(),
			success: function(msg){
				alert("Data Saved: " + msg );
	            //$("#result").html('Submitted successfully');
	        },
	        error: function(jqXHR, textStatus){
	            alert("Cannot save the comment: " + textStatus);
	           // $("#result").html('There is error while submit');
	        }
		});
	});
});