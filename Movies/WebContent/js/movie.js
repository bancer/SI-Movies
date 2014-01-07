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
	
	
	/**
	 * Save comment form handler.
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
				prependComment(msg);
				$("#comment_text").val("");
	        },
		});
	});
	
	/**
	 * Delete comment form handler.
	 */
	$("form[name=delete_comment]").submit(function(event) {
		event.preventDefault();
		if (request) {
			request.abort();
		}
		var listItem = $(this).parent("li");
		request = $.ajax({
			type : "post",
			url : "default.jsp",
			data : $(this).serialize(),
			success : function(msg) {
				if(msg == "1") {
					listItem.remove();
				}	
			},
		});
	});
});

/**
 * Retrieves a single comment from the server and inserts it as a first item
 * into the comments list on the page.
 * 
 * @param id
 *            comment id
 */
function prependComment(id) {
	$.ajax({
		type : "get",
		url : "default.jsp?command=Comment&id=" + id,
		dataType : "json",
		success : function(comment) {
			var item = "<li><h3>" + comment.author + "</h3>" + comment.text
					+ "</li>";
			$("#comments_list").prepend(item);
		},
	});
}