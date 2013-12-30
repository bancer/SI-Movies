$(document).ready(function() {
	/* Attaches onclick event listener to the toggleable element.
	 * On the click: 
	 * 1. Adds or removes "collapsable" class from this element.
	 * 2. Displays or hides the next <div> element. "Stop" call prevents
	 * multiple clicks on the same element so the content of the next division
	 * is not displayed multiple times. */
	$(".toggleable").click(function(){
		$(this).toggleClass("collapsable");
		$(this).next("div").stop('true', 'true').slideToggle("slow");
	});
});