# flashcard-app

## Introduction 
This is a flashcard application intended for the use of those studying a foreign language or any other topic that may benefit from that use of flashcards (latin roots, anatomy, definitions, etc). This particular application is based on the Leitner study system, which is described as follows: </br>
The game consists of three boxes that all cards may move between, and begins by placing all cards within box one. A box is chosen randomly such that the P(Box1) > P(Box2) > P (Box3). A card from within the chosen box is then chosen randomly such that P(card1) = P(card2) = ... = (cardn), where n is the total number of cards within a box. For whatever card is chosen, the front or the back of the card is randomly displayed to the user given a 50/50 chance. If the user answers the card correctly, that card may move up a box, otherwise it will move back one box. The game continues in this fashion until the user "wins" the game and all cards are placed into box 3.

## Features
Other than the game logic required to implement the Leitner system, the application also features a plethora of aesthetic features intended for enhancing user experience.
* Landing Screen </br>
  * features three buttons to navigate to game screen, settings screen, and managament screen
* Game Screen </br>
  * provides user interface for Leitner system game logic
  * user may enter and submit text to answer card
  * user may flip card if unable to answer
* Card Management Screen </br>
  * user may add new cards to system
  * user may remove existing cards from system
* Settings Screen </br>
  * user may choose between three background sound files, or may silence the music entirely
  * user may choose between three background patterns
  * user may choose between five background colors
  * user may change between English, German, or Hungarian

## Setup and Running
* Clone Source Repository from GitHub via Command Line </br>
`git clone https://github.com/alyssaromero/flashcard-app.git` </br>
* Change to the Directory in which Project was Cloned </br>
* Run the Following Commands
  * Move to Source File `cd src`
  * Compile Source Code `javac GameTester.java`
  * Execute Application `java GameTester`

## Sample Session
![](flashcard-app.gif)

## Author
_Alyssa Romero_ </br>
