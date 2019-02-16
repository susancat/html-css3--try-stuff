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
	  		<h2 style="background-color:#0066cc; font-size:30px; color:#FFFFFF; text-align:center; padding:20px">Let's Play!</h2>
	  	</header>
	  	
	  	<div style="float:left; margin: 30px;height: 660px; width: 150px; padding: 10px; background: #99ccff"> 
			<h5>Active player is:</h5>
	 		<p><h5 id="activePName"></h5></p></br>
	 		<h5>Chosen category:</h5>
			<p><h5 id="selectedCategory"></h5></p></br>
	 		<p><h5>Round:</h5></p> 
			<p><h5 id="round"><h5></p></br>
	 		</br>
	  		<div class="chooseCats">
	 			<h5>Choose your category</h5>
				<input type="button" value="Size" onclick="submit"></br>
				<input type="button" value="Speed" onclick="submit"></br>
				<input type="button" value="Range" onclick="submit"></br>
				<input type="button" value="Firepower" onclick="submit"></br>
				<input type="button" value="Cargo" onclick="submit">
	 		</div>
	 		</br>
	 		<h5>Return to main menu</h5>
	 		<input type="button" value="Menu" onclick="location.href='http://localhost:7777/toptrumps/'"></br>
			<input type="button" value="Next Round" onclick="location.href='http://localhost:7777/toptrumps/playRound'"></br>
	 	</div>
	 
	 
		<div style="float:right; margin: 30px; padding: 10px; height: 300px; width: 220px;background: #cccc66; class="card"">
	 		<h4>AI Player 2</h4>
			<p><h5 id="AI2_cardName"></h5></p>
			<p><h5 id="AI2_size"></h5></p>
			<p><h5 id="AI2_speed"></h5></p>
			<p><h5 id="AI2_range"></h5></p>
			<p><h5 id="AI2_firepower"></h5></p>
			<p><h5 id="AI2_cargo"></h5></p>
			<p><h5 id="cardAI2"></h5></p>

	 	</div>
	 
		<div style="float:right; margin: 30px; padding: 10px; height: 300px; width: 220px;background: #cccc66; class="card"">
	 		<h4>AI Player 1</h4>
	  		<p><h5 id="AI1_cardName"></h5></p>
	  		<p><h5 id="AI1_size"></h5></p>
	  		<p><h5 id="AI1_speed"></h5></p>
	  		<p><h5 id="AI1_range"></h5></p>
	  		<p><h5 id="AI1_firepower"></h5></p>
	  		<p><h5 id="AI1_cargo"></h5></p>

	 	</div>
	 
	 
		<div style="float:right; margin: 30px; padding: 10px; height: 300px; width: 220px;background: #cccc66; class="card"">
			<h4>Your card is:</h4>
			<p><h5 id="thatIsMyID"></h5></p>
	 		<p><h5 id="size"></h5></p>
	 		<p><h5 id="speed"></h5></p>
	 		<p><h5 id="range"></h5></p>
	 		<p><h5 id="firepower"></h5></p>
	 		<p><h5 id="cargo"></h5></p>
			<p><h5 id="card"></h5>cards left</p>
	 	</div>
	 
	 	<div style="float:right; margin: 30px; padding: 10px; height: 300px; width: 220px;background: #cccc66; class="card"">
	 		<h4>AI Player 4</h4>
	  		<p><h5 id="AI4_cardName"></h5></P>
	    	<p><h5 id="AI4_size"></h5></p>
	    	<p><h5 id="AI4_speed"></h5></p>
	    	<p><h5 id="AI4_range"></h5></p>
	    	<p><h5 id="AI4_firepower"></h5></P>
	    	<p><h5 id="AI4_cargo"></h5></p>
	 	</div>
	 
	 	<div style="float:right; margin: 30px; padding: 10px; height: 300px; width: 220px; background: #cccc66; class="card"">
	 		<h4>AI Player 3</h4>
	  		<p><h5 id="AI3_cardName"></h5></p>
	    	<p><h5 id="AI3_size"></h5></p>
	    	<p><h5 id="AI3_speed"></h5></p>
	   		<p><h5 id="AI3_range"></h5></p>
	   	 	<p><h5 id="AI3_firepower"></h5></p>
	    	<p><h5 id="AI3_cargo"></h5></p>
		</div>
		
	</div>
		
		
		
		
		<script type="text/javascript">
			var chosenCategory;
			var whosTurn;
			var activeCard;
			var ai1ActiveCard;
            var playerOut = "Player out!";
			
			// Method that is called on page load
			function initalize() {
				defineCategory();
				activeCard()
				activeAI1Card();
				//maybe
				//whosTurn();//function returns int category
				//displayCards();//1 or 5 depends on whos turn is
				playRound();//int category passed here
				//try here
				cardInHand(0);
				

				
			}

			//round loops
			//when you click on play game button
			var category;
			var counter = 0;
			var rand;
			var arrayList = ["player", "AI1", "AI2", "AI3", "AI4"];
			var indexRandom = Math.floor(Math.random() * 4);

			var roundWinnerName = arrayList[indexRandom];//need to get initial value!!!
			//define category including the first round
			function defineCategory(roundWinnerName){
				if(counter != 0){
					if(roundWinnerName.includes("player")){
						//category = result from the click
						category = Math.floor(Math.random() * 4);
					}else{
						category = Math.floor(Math.random() * 4);
					}
				}else{
					counter++;
					rand = Math.floor(Math.random() * 4);
				if(rand = 0){
					//category = result from the click
					category = Math.floor(Math.random() * 4);

				}else {
					category = Math.floor(Math.random() * 4);
				}
				}
			return category;	
			}



			function playRound(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/playRound?category="+category);
				if(!xhr) {
					alert("CORS not supported")
				}
				xhr.onload = function(e) {
					//console.log(xhr.response)
					var response = JSON.parse(xhr.response);
					//ShowCardInHand(response);
				};
			xhr.send();
		}
			

			function clickNext(){
				playRound();
			}

			function getActivePlayer(){
				
			}

			function getRoundWinnerName(){
				
			}

			

			

			whosTurn = function whosTurn(){
				//if -1 --> Active player >activePlayer = -1;
				// else random choice>
				
			}
			
			
			function showCard() {
				document.getElementById("thatIsMyID").innerHTML = activeCard.cardName;
				document.getElementById("size").innerHTML = "Speed: " + activeCard.size;
				document.getElementById("speed").innerHTML = "Size: " + activeCard.speed;
				document.getElementById("range").innerHTML = "Range: " + activeCard.range;
				document.getElementById("firepower").innerHTML = "Firepower: " + activeCard.firepower;
				document.getElementById("cargo").innerHTML = "Cargo: " + activeCard.cargo;
			}
			
			
			function showAI1card() {
				document.getElementById("AI1_cardName").innerHTML = ai1ActiveCard.cardName;
				document.getElementById("AI1_size").innerHTML = "Size: " + ai1ActiveCard.size;
				document.getElementById("AI1_speed").innerHTML = "Speed: " + ai1ActiveCard.speed;
				document.getElementById("AI1_range").innerHTML = "Range: " + ai1ActiveCard.range;
				document.getElementById("AI1_firepower").innerHTML = "Firepower: " + ai1ActiveCard.firepower;
				document.getElementById("AI1_cargo").innerHTML = "Cargo: " + ai1ActiveCard.cargo;
				

			}

            function playerOut() { document.getElementById("cardName").innerHTML = playerOut; }
            function AI1Out() { document.getElementById("AI1_cardName").innerHTML = playerOut; }

			function ShowCardInHand(response){
					document.getElementById("card").innerHTML = response;
				}
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
		
			function cardInHand(num){
			var num = 0;
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/cardInHand?num="+num);
			if(!xhr) {
				alert("CORS not supported")
			}
			xhr.onload = function(e) {
				console.log(xhr.response)
				var response = JSON.parse(xhr.response);
			};
			xhr.send();
		}




			function activeCard() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/playerActiveCard");
				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					console.log(xhr.response)
					//alert(responseText);
                    var response = JSON.parse(xhr.response);
                    if(response == "o") { playerOut(); }
                    else {
                    activeCard = response;
                    showCard(); }
				};
				xhr.send();
			}
			
			function activeAI1Card() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/ai1ActiveCard");
				if(!xhr) {
					alert("CORS not supported")
				}
				xhr.onload = function(e) {
					console.log(xhr.response)
                    var response = JSON.parse(xhr.response);
                    if(response == "o") { AI1Out(); }
                    else {
                    ai1ActiveCard = response;
                    showAI1card(); }
				};
				xhr.send();
			}
			
//==============================================DELETE BELOW WHEN READY==================================================================================================	
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
//============================================================DELETE ABOVE==========================================================
		</script>
		
		</body>
</html>