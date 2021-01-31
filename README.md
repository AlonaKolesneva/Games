# Games
This is standard snake game (no portal through the walls). You need to eat 20 apples to win. Each turn snake moves quicker. 
Graphics based on javarush.engine.cell.Game. Game field visualisation, snake body and apple signs were taken by inheritance.
I've used main OOP principeles, collections, lambda, stream.
There are 5 classes: 
  GameObject - an object with x, y coordinate
  Apple extends GameObject - allows to create and manage apple one the game field
  Snake extends GameObject - allows to create and manage snake as an ArrayList with head and body
  Directions - enum of snake directions
  SnakeGame - class to manage the game: initialization, field creating, finishing and restarting, visualisation
