<script type="text/javascript">
	/* Start OpenId authentication script */
	(function() {
		if (typeof window.janrain !== 'object')
			window.janrain = {};
		if (typeof window.janrain.settings !== 'object')
			window.janrain.settings = {};

		janrain.settings.tokenUrl = '<%= getServletContext().getInitParameter("auth-url") %>';

		function isReady() {
			janrain.ready = true;
		}
		;
		if (document.addEventListener) {
			document.addEventListener("DOMContentLoaded", isReady, false);
		} else {
			window.attachEvent('onload', isReady);
		}

		var e = document.createElement('script');
		e.type = 'text/javascript';
		e.id = 'janrainAuthWidget';

		if (document.location.protocol === 'https:') {
			e.src = 'https://rpxnow.com/js/lib/movie-hunter/engage.js';
		} else {
			e.src = 'http://widget-cdn.rpxnow.com/js/lib/movie-hunter/engage.js';
		}

		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(e, s);
	})();
	/* End OpenId authentication script */
</script>