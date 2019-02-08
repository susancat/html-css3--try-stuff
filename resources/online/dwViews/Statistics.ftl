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
      <h1 style="background-color:black; font-size:40px; 
	   color:#FFFFFF; text-align:center; padding:20px">
	  TOP TRUMP GAME
	  </h1>
	  <h2 style="background-color:blue; font-size:30px; 
	   color:#FFFFFF; text-align:left; padding:20px">
	  YOUR PRVIOUS GAME STATISTICS ARE
	  </h2>
	  </header>
	  
	    <div style="float:center; margin: 30px;height: 480px; width: 600px;background: lime; class="StatTable">
		 
		
		 <p style="Font-size:20px; text-align:center"> YOUR GAME STATISTIS ARE</p></br>
		 <p>NUMBER OF GAMES</p><p id="nofGames"></p>
		 <p>NUMBER OF HUMAN WIN</p><p id="nofhumanwin"></p>
		 <p>NUMBER OF AI WIN</p><p id="nofaiwin"></p>
		 <p>AVERAGE NUMBER OF DRAWS</p><p id="aveofdraws"></p>
		 <p>LONGEST GAME</p><p id="longestgame"></p>
		 
		 
		</div>
		</div>
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				// For example, lets call our sample methods
				//<!--helloJSONList();-->
				//<!--helloWord("Student");-->
				getStatistics();
			}
			  // var NumofGames = 1;
			 //  var NumofHuWin = 2;
			 //  var NumofAiWin = 3;
			  // var AveOfDraws = 4;
			 //  var LongGame   = 5;
			  // document.getElementById("nofGames").innerHTML = NumofGames;
			 //  document.getElementById("nofhumanwin").innerHTML = NumofHuWin;
			  // document.getElementById("nofaiwin").innerHTML = NumofAiWin;
			 //  document.getElementById("aveofdraws").innerHTML = AveOfDraws;
			 //  document.getElementById("longestgame").innerHTML = LongGame;
			
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
			
			function getStatistics() {

                // First create a CORS request, this is the message we are going to send (a get request in this case)

                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getStatistics?Stat); // Request type and URL+parameters

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

               var '{"statisitics":['+ xhr ]}';

                object = JSON.parse(xhr)

          document.getElementById("demo").innerHTML =

          obj.statisitics[0] + " " + obj.statisitics[0];

           obj.statisitics[1] + " " + obj.statisitics[1];

          obj.statisitics[2] + " " + obj.statisitics[2];

          obj.statisitics[3] + " " + obj.statisitics[3];

          obj.statisitics[4] + " " + obj.statisitics[4];        

            }

		</script>
		
		</body>
</html>