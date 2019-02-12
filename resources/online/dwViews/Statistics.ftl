<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    	<div class="container">
    		 <header>
      			<h1 style="background-color:#330066; font-size:40px; color:#FFFFFF; text-align:center; padding:20px">Top Trumps Game</h1>
	  			<h2 style="background-color:#0066cc; font-size:30px; color:#FFFFFF; text-align:center; padding:20px">Statistics</h2>
			</header>
	  
	    	<div style="float:left; margin: 30px; padding: 20px; height: 400px; width: 300px;background: #99ccff; class="StatTable">
				<p><h4 style="Font-size:20px; text-align:left;"> Your game statistics are:</h4></p>
		 		<p><h5 id="gNumber"></h5></p>
		 		<p><h5 id="hWin"></h5></p>
		 		<p><h5 id="aiWin"></h5></p>
		 		<p><h5 id="draws"></h5></p>
		 		<p><h5 id="maxGame"></h5></p></br>
		 		<p><h5>Return to main menu</h5></p>
		 		<input type="button" value="Menu" onclick="location.href='http://localhost:7777/toptrumps/'">
			</div>
	
		</div>
	
		
		<script type="text/javascript">
		
			var db;
		
			// ------call getStats when page loads
			function initalize() {
				getStats();
			}
			
			//-----prints attributes to html. Called from getStats (below) 
			function printStats() {
				document.getElementById("gNumber").innerHTML = "Number of games played: " + db.gameNumber;
				document.getElementById("hWin").innerHTML = "Number of human wins: " + db.gameHuWins;
				document.getElementById("aiWin").innerHTML = "Number of ai wins: " + db.gameAiWins;
				document.getElementById("draws").innerHTML = "Adverage number of draws: " + db.gameDraws;
				document.getElementById("maxGame").innerHTML = "Longest game played: " + db.gameLen;
			}
			
			
			// This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}
		
		</script>
		
		
		<script type="text/javascript">
		//-------Get database obj from REST API as JSON and parse to Javascript obj
			function getStats() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getStatistics");
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					console.log(xhr.response)
					db = JSON.parse(xhr.response);
					printStats();
				};
				xhr.send();
			}
		
		</script>
		
		</body>
</html>