# TextAdventure
A simple, badly made DIY TextAdventure Game.

# Configure
You will need any [JavaFX](https://gluonhq.com/products/javafx/) version equal or above JavaFX11.
Download the compiled [JAR file](https://github.com/m3taxal/TextAdventure/releases/tag/v1.0.0).
Additionally, you need to create a text file which contains all of the game's text.

# Create your game
Here is some example game text:
```
You are captured in an underground cell inside a dungeon.
If you don't do anything in the next few seconds, you will most likely die.
What do you do?
Rot in your cell[6;6]
Unlock the cell with the key next to you[7;10]
Congratulations, you died on the first choice!
You unlocked the cell with the key. However, now you stand in an empty and dark hallway.
How will you continue?
March forward, like the brave man you are[10;10]
Stay just where you are and wait[12;13]
You march forward until you bump into something huge and sturdy - before you can even notice what's happening, you've already lost consciousness and, sadly, your life.
You wait for a while until you see a light at the end of the hallway. You think it's a good idea to approach it, and when you finally reach it, you find yourself in an open field.
Congratulations, you escaped the dungeon!
```
At certain lines, there are square brackets at the end with numbers in them, seperated by semicolons. These are the options from which your player can choose while playing your game. For example, [7;10] means that if the player writes "Unlock the cell with the key next to you", line 7 - 10 will be scrolled next.

# Run
The command for running the JAR file looks like this:
```
java -jar --module-path path/to/javafx/lib --add-modules javafx.controls,javafx.fxml TextAdventure.jar --startEndLine=5 --filepath=path/to/dialogue.txt
```
The arguments "--startEndLine" and "--filepath" must be assigned. --startEndline helps in deciding until which line should be scrolled at the start of the game, --filepath contains the path to your file which contains all of the game's texts.
