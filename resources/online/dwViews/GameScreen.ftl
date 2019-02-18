

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
	  	</br>
			<h5><input type="button" value="Next Round" onclick="submit"><h5>
			<h5>Active player is:</h5>
	 		<p><h5 id="activePName"></h5></p></br>
	 		<h5>Chosen category:</h5>
			<p><h5 id="selectedCategory"></h5></p></br>
	 		<p><h5>Round:</h5></p> 
			<p><h5 id="round"><h5></p></br>
	 		</br>
	  		<div class="chooseCats">
	 			<h5>Choose your category</h5>
				<input type="button" id="1" value="Size" onclick=chooseCat()></br>
				<input type="button" id="2" value="Speed" onclick=chooseCat()></br>
				<input type="button" id="3" value="Range" onclick=chooseCat()></br>
				<input type="button" id="4" value="Firepower" onclick=chooseCat()></br>
				<input type="button" id="5" value="Cargo" onclick=chooseCat()>
	 		</div>
	 		</br>
	 		<h5>Return to main menu</h5>
	 		<h5><input type="button" value="Menu" onclick="location.href='http://localhost:7777/toptrumps/'"></h5></br>
	 	</div>
	 
	 
		<div id="showByClick2" style="visibility:hidden; float:right; margin: 30px; padding: 10px; height: 300px; width: 220px;background: #cccc66; class="card"">
	 		<h4>AI Player 2</h4>
			<p><h5 id="AI2_cardName"></h5></p>
			<p><h5 id="AI2_size"></h5></p>
			<p><h5 id="AI2_speed"></h5></p>
			<p><h5 id="AI2_range"></h5></p>
			<p><h5 id="AI2_firepower"></h5></p>
			<p><h5 id="AI2_cargo"></h5></p>
			<p><h5 id="cardLeft2"></h5></p>
	 	</div>
	 
		<div id="showByClick" style="visibility:hidden; float:right; margin: 30px; padding: 10px; height: 300px; width: 220px;background: #cccc66; class="card"">
	 		<h4>AI Player 1</h4>
	  		<p><h5 id="AI1_cardName"></h5></p>
	  		<p><h5 id="AI1_size"></h5></p>
	  		<p><h5 id="AI1_speed"></h5></p>
	  		<p><h5 id="AI1_range"></h5></p>
	  		<p><h5 id="AI1_firepower"></h5></p>
	  		<p><h5 id="AI1_cargo"></h5></p>
	  		<p><h5 id="cardLeft1"></h5></p>
	 	</div>
	 
	 
		<div style="float:right; margin: 30px; padding: 10px; height: 300px; width: 220px;background: #cccc66; class="card"">
			<h4>Your card is:</h4>
			<p><h5 id="cardName"></h5></p>
	 		<p><h5 id="size"></h5></p>
	 		<p><h5 id="speed"></h5></p>
	 		<p><h5 id="range"></h5></p>
	 		<p><h5 id="firepower"></h5></p>
	 		<p><h5 id="cargo"></h5></p>
	 		<p><h5 id="cardLeft"></h5></p>
	 	</div>
	 
	 	<div id="showByClick4" style="visibility:hidden; float:right; margin: 30px; padding: 10px; height: 300px; width: 220px;background: #cccc66; class="card"">
	 		<h4>AI Player 4</h4>
	  		<p><h5 id="AI4_cardName"></h5></p>
	    	<p><h5 id="AI4_size"></h5></p>
	    	<p><h5 id="AI4_speed"></h5></p>
	    	<p><h5 id="AI4_range"></h5></p>
	    	<p><h5 id="AI4_firepower"></h5></p>
	    	<p><h5 id="AI4_cargo"></h5></p>
	    	<p><h5 id="cardLeft4"></h5></p>
	 	</div>
	 
	 	<div id="showByClick3" style="visibility:hidden; float:right; margin: 30px; padding: 10px; height: 300px; width: 220px; background: #cccc66; class="card"">
	 		<h4>AI Player 3</h4>
	  		<p><h5 id="AI3_cardName"></h5></p>
	    	<p><h5 id="AI3_size"></h5></p>
	    	<p><h5 id="AI3_speed"></h5></p>
	   		<p><h5 id="AI3_range"></h5></p>
	   	 	<p><h5 id="AI3_firepower"></h5></p>
	    	<p><h5 id="AI3_cargo"></h5></p>
	    	<p><h5 id="cardLeft3"></h5></p>
		</div>
		
	</div>
		
		
		
		
		<script type="text/javascript">
			var activeCard;
			var ai1ActiveCard;
			var ai2ActiveCard;
			var ai3ActiveCard;
			var ai4ActiveCard;
			var category;
			//var playRound;
			
			//var player;
            //var playerOut = "Player out!";
      		//var activePlayer;
			
			// Method that is called on page load
			function initalize() {
				activeCard();
				activeAI1Card();
				activeAI2Card();
				activeAI3Card();
				activeAI4Card();
				//activePlayer();
				cardInHand(0);
				cardInHand1(1);
				cardInHand2(2);
				cardInHand3(3);
				cardInHand4(4);
				//playRound();
				
			}
			
			
			function showCard() {
				document.getElementById("cardName").innerHTML = activeCard.cardName;
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
			
			function showAI2card() {
				document.getElementById("AI2_cardName").innerHTML = ai2ActiveCard.cardName;
				document.getElementById("AI2_size").innerHTML = "Size: " + ai2ActiveCard.size;
				document.getElementById("AI2_speed").innerHTML = "Speed: " + ai2ActiveCard.speed;
				document.getElementById("AI2_range").innerHTML = "Range: " + ai2ActiveCard.range;
				document.getElementById("AI2_firepower").innerHTML = "Firepower: " + ai2ActiveCard.firepower;
				document.getElementById("AI2_cargo").innerHTML = "Cargo: " + ai2ActiveCard.cargo;
			}
			
			function showAI3card() {
				document.getElementById("AI3_cardName").innerHTML = ai3ActiveCard.cardName;
				document.getElementById("AI3_size").innerHTML = "Size: " + ai3ActiveCard.size;
				document.getElementById("AI3_speed").innerHTML = "Speed: " + ai3ActiveCard.speed;
				document.getElementById("AI3_range").innerHTML = "Range: " + ai3ActiveCard.range;
				document.getElementById("AI3_firepower").innerHTML = "Firepower: " + ai3ActiveCard.firepower;
				document.getElementById("AI3_cargo").innerHTML = "Cargo: " + ai3ActiveCard.cargo;
			}
			
			function showAI4card() {
				document.getElementById("AI4_cardName").innerHTML = ai4ActiveCard.cardName;
				document.getElementById("AI4_size").innerHTML = "Size: " + ai4ActiveCard.size;
				document.getElementById("AI4_speed").innerHTML = "Speed: " + ai4ActiveCard.speed;
				document.getElementById("AI4_range").innerHTML = "Range: " + ai4ActiveCard.range;
				document.getElementById("AI4_firepower").innerHTML = "Firepower: " + ai4ActiveCard.firepower;
				document.getElementById("AI4_cargo").innerHTML = "Cargo: " + ai4ActiveCard.cargo;
			}
			
			function ShowCardInHand(cardLeft){document.getElementById("cardLeft").innerHTML = "Cards left:" + cardLeft;}
			function ShowCardInHand1(cardLeft1){document.getElementById("cardLeft1").innerHTML = "Cards left: " + cardLeft1;}
			function ShowCardInHand2(cardLeft2){document.getElementById("cardLeft2").innerHTML = "Cards left: " + cardLeft2;}
			function ShowCardInHand3(cardLeft3){document.getElementById("cardLeft3").innerHTML = "Cards left: " + cardLeft3;}
			function ShowCardInHand4(cardLeft4){document.getElementById("cardLeft4").innerHTML = "Cards left: " + cardLeft4;}
            //function playerOut() { document.getElementById("cardName").innerHTML = playerOut; }
            
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
			
			function showdiv(id) {
  				var div = document.getElementById(id);
      				  if (div.style.visibility == 'hidden') {
              				div.style.visibility = 'visible';
          			} 
          			  else {
          				div.style.visibility = 'hidden';
          			}
          	}
			
			function chooseCat(){
				document.getElementById("selectedCategory").innerHTML = event.target.value;
				var category = parseInt(event.target.id)
				showdiv("showByClick");
				showdiv("showByClick2");
				showdiv("showByClick3");
				showdiv("showByClick4");
				//playRound(category);
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
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
			function playRound(num) {
				
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/playRound");
				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					console.log(xhr.response)
					var response = JSON.parse(xhr.response);
                    playRound = response;
                    }
				
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
			
			function activeAI2Card() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/ai2ActiveCard");
				if(!xhr) {
					alert("CORS not supported")
				}
				xhr.onload = function(e) {
					console.log(xhr.response)
					ai2ActiveCard = JSON.parse(xhr.response);
					showAI2card();
				};
				xhr.send();
			}
			
			function activeAI3Card() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/ai3ActiveCard");
				if(!xhr) {
					alert("CORS not supported")
				}
				xhr.onload = function(e) {
					console.log(xhr.response)
					ai3ActiveCard = JSON.parse(xhr.response);
					showAI3card();
				};
				xhr.send();
			}
			
			function activeAI4Card() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/ai4ActiveCard");
				if(!xhr) {
					alert("CORS not supported")
				}
				xhr.onload = function(e) {
					console.log(xhr.response)
					ai4ActiveCard = JSON.parse(xhr.response);
					showAI4card();
				};
				xhr.send();
			}
			
			function cardInHand(num){
	            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/cardInHand?num="+num);
	            if(!xhr) {
	                alert("CORS not supported")
	            }
	            xhr.onload = function(e) {
	                console.log(xhr.response)
	                var cardLeft = JSON.parse(xhr.response);
	                ShowCardInHand(cardLeft);
	            };
	            xhr.send();
	        }
			function cardInHand1(num){
	            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/cardInHand?num="+num);
	            if(!xhr) {
	                alert("CORS not supported")
	            }
	            xhr.onload = function(e) {
	                console.log(xhr.response)
	                var cardLeft1 = JSON.parse(xhr.response);
	                ShowCardInHand1(cardLeft1);
	            };
	            xhr.send();
	        }
			function cardInHand2(num){
	            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/cardInHand?num="+num);
	            if(!xhr) {
	                alert("CORS not supported")
	            }
	            xhr.onload = function(e) {
	                console.log(xhr.response)
	                var cardLeft2 = JSON.parse(xhr.response);
	                ShowCardInHand2(cardLeft2);
	            };
	            xhr.send();
	        }
			function cardInHand3(num){
	            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/cardInHand?num="+num);
	            if(!xhr) {
	                alert("CORS not supported")
	            }
	            xhr.onload = function(e) {
	                console.log(xhr.response)
	                var cardLeft3 = JSON.parse(xhr.response);
	                ShowCardInHand3(cardLeft3);
	            };
	            xhr.send();
	        }
			function cardInHand4(num){
	            var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/cardInHand?num="+num);
	            if(!xhr) {
	                alert("CORS not supported")
	            }
	            xhr.onload = function(e) {
	                console.log(xhr.response)
	                var cardLeft4 = JSON.parse(xhr.response);
	                ShowCardInHand4(cardLeft4);
	            };
	            xhr.send();
	        }
			
		</script>
		
		</body>

</html>