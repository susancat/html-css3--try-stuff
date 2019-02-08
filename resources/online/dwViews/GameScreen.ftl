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

			<!-- Add your HTML Here -->
			<header>
      <h1 style="background-color:black; font-size:40px; 
	   color:#FFFFFF; text-align:center; padding:20px">
	  TOP TRUMP GAME
	  </h1>
	  <h2 style="background-color:blue; font-size:30px; 
	   color:#FFFFFF; text-align:left; padding:20px">
	  YOUR: SELECTED RANGE
	  </h2>
	  </header>
	   
			<!-- Add your HTML Here -->
	 
     <div style="float:left; margin: 30px;height: 200px; width: 150px;background: green; 
	 
	 <table class="card">
	 <tr>
	 <th>
	 <p>Active Player Is</p>
	 <p id="Act_PlayerName"></p>
	 </th>
	 </tr>
	 <td> <p>They Selected</p> <p id="SelectedRange"></p>
	 </br>
	 <td> <p>Round Number</p> <p id="Round.NO"></p>
	 </table>
	 </div>
	 
	 <div style="float:right; margin: 40px;height: 300px; width: 220px;background: lightblue; 
	 <table class="card">
	 <tr>
	  <th style="font-size:100px">AI PLAYER 2</th>
	  </tr></br></br>
	  <tr>
	   <p id="AI2Attribute1">AI2Attribute1</p>
		</br>
	   <p id="AI2Attribute2">AI2Attribute2</p>
		</br>
	  <p id="AI2Attribute3">AI2Attribute3</p>
		</br>
	   <p id="AI2Attribute4">AI2Attribute4</p>
		</br>
	  <p id="AI2Attribute5">AI2Attribute5</p>
	  </tr>
	 </table>
	 </div>
	 
	 <div style="float:right; margin: 40px;height: 300px; width: 220px;background: lightblue; 
	 <table class="card">
	 <tr>
	  <th style="font-size:100px">AI PLAYER 1</th>
	  </tr></br></br>
	  <tr>
	  <p id="AI1Attribute1">AI1Attribute1</p>
		</br>
	  <p id="AI1Attribute1">AI1Attribute2</p>
		</br>
	  <p id="AI1Attribute1">AI1Attribute3</p>
		</br>
	  <p id="AI1Attribute1">AI1Attribute4</p>
		</br>
	  <p id="AI1Attribute1">AI1Attribute5</p>
	  </tr>
	 </table>
	 </div>
	 
	 <div style="float:right; margin: 40px;height: 300px; width: 220px;background: lightblue; 
	 <table class="card">
	 <tr>
	  <th style="font-size:100px">YOU</th>
	  </tr></br></br></br>
	  <tr>
	    <input type="button" value="YOUAttribute1" onclick="submit">
		</br></br>
	    <input type="button" value="YOUAttribute2" onclick="submit">
		</br></br>
		<input type="button" value="YOUAttribute3" onclick="submit">
		</br></br>
		<input type="button" value="YOUAttribute4" onclick="submit">
		</br></br>
	    <input type="button" value="YOUAttribute5" onclick="submit">
	  </tr>
	 </table>
	 </div>
	 
	 <div style="float:right; margin: 40px;height: 300px; width: 220px;background: lightblue; class="card">
	 <tr>
	  <th style="font-size:100px">AI Player 4</th>
	  </tr></br></br>
	  <tr>
	    <p id="AI4Attribute1">AI4Attribute1</p>
		</br>
	   <p id="AI4Attribute1">AI4Attribute2</p>
		</br>
	    <p id="AI4Attribute1">AI4Attribute3</p>
		</br>
	    <p id="AI4Attribute1">AI4Attribute4</p>
		</br>
		<p id="AI4Attribute1">AI4Attribute5</p>
	  </tr>
	 </table>
	 </div>
	 
	 <div style="float:right; margin: 40px; height: 300px; width: 220px; background: lightblue; class="card">
	 <tr>
	  <th style="font-size:100px">AI Player 3</th>
	  </tr></br></br>
	  <tr>
	   <p id="AI3Attribute1">AI3Attribute1</p>
		</br>
	   <p id="AI3Attribute2">AI3Attribute2</p>
		</br>
		<p id="AI3Attribute3">AI3Attribute3</p>
		</br>
         <p id="AI3Attribute4">AI3Attribute4</p>
		</br>
		<p id="AI3Attribute5">AI3Attribute5</p>
	  </tr>
	 </table>
	 </div>
    
		</div>
		</div>
		
		
		
		
		<script type="text/javascript">
		    
			// Method that is called on page load
			function initalize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				// For example, lets call our sample methods
				helloJSONList();
				helloWord("Student");
				
			}
			  
			 var ActivePlayerId = "Active Palyer is";
			  var ActivePlayerName = "You";
			  var SelectedRangeBox = "Range";
              document.getElementById("ActiveTitle").innerHTML = ActivePlayerId;
			  document.getElementById("Act_PlayerName").innerHTML = ActivePlayerName;
			  document.getElementById("SelectedRange").innerHTML = SelectedRangeBox;
			  
			  var youcard1 = "Yourcard1";
			  var youcard2 = "Yourcard2";
			  var youcard3 = "Yourcard3";
			  var youcard4 = "Yourcard4";
			  var youcard5 = "Yourcard5";
			  
			  document.getElementById("YOUAttribute1").innerHTML = "Yourcard1";
			  document.getElementById("YOUAttribute2").innerHTML = youcard2;
			  document.getElementById("YOUAttribute3").innerHTML = youcard3;
			  document.getElementById("YOUAttribute4").innerHTML = youcard4;
			  document.getElementById("YOUAttribute5").innerHTML = youcard5;
			  
			  var AIonecard1 = "AICARD11";
			  var AIonecard2
			  var AIonecard3
			  var AIonecard4
			  var AIonecard5
			  
			  document.getElementById("AI1Attribute1").innerHTML = AIonecard1;
			  document.getElementById("AI1Attribute2").innerHTML = AIonecard2;
			  document.getElementById("AI1Attribute3").innerHTML = AIonecard3;
			  document.getElementById("AI1Attribute4").innerHTML = AIonecard4;
			  document.getElementById("AI1Attribute5").innerHTML = AIonecard5;
			  
			  var AI2ndcard1
			  var AI2ndcard2
			  var AI2ndcard3
			  var AI2ndcard4
			  var AI2ndcard5
			  
			  document.getElementById("AI2Attribute1").innerHTML = AI2ndcard1;
			  document.getElementById("AI2Attribute2").innerHTML = AI2ndcard2;
			  document.getElementById("AI2Attribute3").innerHTML = AI2ndcard3;
			  document.getElementById("AI2Attribute4").innerHTML = AI2ndcard4;
			  document.getElementById("AI2Attribute5").innerHTML = AI2ndcard5;
			  
			  var AI3rdcard1
			  var AI3rdcard2
			  var AI3rdcard3
			  var AI3rdcard4
			  var AI3rdcard5
			  
			  document.getElementById("AI3Attribute1").innerHTML = AI3rdcard1;
			  document.getElementById("AI3Attribute2").innerHTML = AI3rdcard2;
			  document.getElementById("AI3Attribute3").innerHTML = AI3rdcard3;
			  document.getElementById("AI3Attribute4").innerHTML = AI3rdcard4;
			  document.getElementById("AI3Attribute5").innerHTML = AI3rdcard5;
			  
			  var AI4thcard1
			  var AI4thcard2
			  var AI4thcard3
			  var AI4thcard4
			  var AI4thcard5
			  
			  document.getElementById("AI4Attribute1").innerHTML = AI4thcard1;
			  document.getElementById("AI4Attribute2").innerHTML = AI4thcard2;
			  document.getElementById("AI4Attribute3").innerHTML = AI4thcard3;
			  document.getElementById("AI4Attribute4").innerHTML = AI4thcard4;
			  document.getElementById("AI4Attribute5").innerHTML = AI4thcard5;
			 
			  
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

		</script>
		
		</body>
</html>