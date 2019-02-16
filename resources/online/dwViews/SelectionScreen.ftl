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
      		<h1 style="background-color:#330066; font-size:40px; color:#ffffff; text-align:center; padding:20px">Top Trumps Game</h1>
	  		<h2 style="background-color:#0066cc; font-size:30px; color:#ffffff; text-align:center; padding:20px">Sci-Fi Ships Special Web Edition!</h2>
		</header>
	   
		<div>
	   		<p><h4 style="font-size:20px"> Please select your choice</h4></p>
	   		<input type="button" value="Play Game" onclick="location.href='http://localhost:7777/toptrumps/game'">
	   		<input type="button" value="Game Statistics" onclick="location.href='http://localhost:7777/toptrumps/stats'">
	   		<input type="button" value="Quit" onclick="closeCurrentTab()">
	  	</div>

	</div>
		
		<script type="text/javascript">
			
			
			//---TRY ME------TRY ME------TRY ME------TRY ME------TRY ME------TRY ME---//
			
			//-----declare variables begin----------declare variables begin----------declare variables begin-----
			
			//active-cards-begin===============
			var playerActiveCard;
			var ai1ActiveCard;
			var ai2ActiveCard;
			var ai3ActiveCard;
			var ai4ActiveCard;
			//===============active-cards-end
			
			//WHOS CHOOSES CATEGORY===============
			var whosTurn;
			//===============whos-turn-end
			
			var playRound;
			var getStatistics;
			
			//-----declare variables end----------declare variables end----------declare variables end-----
			
			// Method that is called on page load
			function initalize() {
			
				//active-cards-begin===============
				activePCard();
				activeAI1Card();
				activeAI2Card();
				activeAI3Card();
				activeAI4Card();
				//===============active-cards-end
				
				//WHOS CHOOSES CATEGORY===============
				whosTurn();
				//===============whos-turn-end
				
				playRound(int category);
				getStatistics();
			}
			
			
			//-----function pair One begin----------function pair One begin----------function pair One begin-----
			
			 function showActivePCard() {
		    	document.getElementById("playerActiveCard").innerHTML = activePCard();
		    }
			
			function activePlayer() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/playerActiveCard");
				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					console.log(xhr.response)
					//alert(responseText);
					activePlayer = JSON.parse(xhr.response);
					showActivePCard();
				};
				xhr.send();
			}
			
			//-----function pair One end----------function pair One end----------function pair One end-----
			
			
			//---TRY ME END------TRY ME END------TRY ME END------TRY ME END------TRY ME END---//
			
			
			
			
			
			
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
		
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
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
		
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloJSONList() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloWord(word) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word="+word); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}
				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
		function closeCurrentTab(){
			var conf=confirm("Are you sure, you want to quit the game?");
				if(conf==true){
					window.opener=null;
					window.open('','_self');
					window.open("about:blank","_self").close();
				}
	    }
		</script>
		
		</body>
    
</html>