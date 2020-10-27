# uno-game
An implementation of the card game UNO using Java and the Java.Swing graphics package.
# Description
This project is a single player game where the user is able to play against a choice of 1-3 opponents. The game follows the basic rules of UNO where players place cards on a central stack with the objective of emptying their hand first, players can play cards of the same colour/number or play a wildcard if they have one in their hand. The opponents make decisions randomly, selecting a random value out of the cards that can be played or picking up an additional card if they are not able to play any cards in there hand.
# How to run
The game can be played by running the RunUno.java file which creates a new game instance and loads the GUI.
# How to play
Upon first running the program, you are present with a start screen which allows the user to enter their name and select the amount of opponents they wish to play against.
![image](https://user-images.githubusercontent.com/66950962/97295388-1b9dc080-1847-11eb-99fb-74622f87b6c1.png) 
After pressing start you are then presented by the game screen. Here the user will always have the first turn, they are able to click on a card from their hand to select it then click the 'play card' button to play it and move onto the next turn (cards that can be played are highlighted blue and cards that cannot are highlighted pink). To progress through opponent turns click 'play card' and the next card to be played will be shown or the player will pick up a card, the name of the player taking the current turn is shown next to the deck of played cards. 

![image](https://user-images.githubusercontent.com/66950962/97295917-d7f78680-1847-11eb-918d-eca31c77793f.png)

The winner of the game is the player that empties their hand and has no cards left, when this happens a message will displayed and the user has the option of starting a new game.

![image](https://user-images.githubusercontent.com/66950962/97296555-bd71dd00-1848-11eb-9a4a-c0e9c50f8f87.png)
