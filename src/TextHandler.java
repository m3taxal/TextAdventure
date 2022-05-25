import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextHandler {
    private final ArrayList<String> allLines;

    private int rememberPlayerLineIndex;

    private int letter;

    private final gameController gameController;

    public TextHandler(String filepath){
        this.gameController = new gameController();
        this.letter = 0;
        this.allLines = new ArrayList<>();
        this.rememberPlayerLineIndex = 0;

        //fill allLines with the text in dialogue.txt
        try{
            File dialogueLines = new File(filepath);
            Scanner myReader = new Scanner(dialogueLines);
            while(myReader.hasNextLine()){
                this.allLines.add(myReader.nextLine());
            }
            myReader.close();
        }
        catch (FileNotFoundException e){
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    public int getRememberPlayerLineIndex() {
        return rememberPlayerLineIndex;
    }

    public ArrayList<String> getAllLines() {
        return allLines;
    }

    /**
     * Checks whether a line entered by the player can be shown in the game window.
     *
     * @param playerLine    line that is to be checked
     * @param gameDialogue  the game window
     * @return              true if line is permissible, false if line can't be shown
     */
    public boolean playerInputPermissible(String playerLine, TextArea gameDialogue){
        for(int i = 0; i < allLines.size(); i++){
            if(playerLine.equalsIgnoreCase(allLines.get(i))){ //check whether game lines actually contain player line
                    rememberPlayerLineIndex = i;

                    //get last two strings (actions player can take) of gameDialogue (will eventually expand to n last options)
                    String[] gameLines = gameDialogue.getText().split("\n");
                    String lastLine = gameLines[gameLines.length-1];
                    String secondToLastLine = gameLines[gameLines.length-2];

                //if playerLine actually equals either one of the last two actions
                return playerLine.equalsIgnoreCase(lastLine) || playerLine.equalsIgnoreCase(secondToLastLine);
            }
        }
        return false;
    }

    /**
     * Scrolls text in the gameDialogue window.
     *
     * @param line           line that is to be scrolled
     * @param endScrolling   determines whether scrolling ends. If true,
     *                       the program can scroll the next line of text. If false, the scrolling
     *                       will be disabled. To re-enable scrolling, manually set canWrite to true,
     */
    public void scrollText(String line, boolean endScrolling, TextArea gameDialogue){
        //to separate different lines in gameDialogue window
        gameDialogue.appendText("\n");
        this.letter = 0;

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.03), e -> {
            gameDialogue.appendText(String.valueOf(line.charAt(this.letter)));

            //append each letter
            this.letter++;
        }));

        //play for amount of characters in line
        timeline.setCycleCount(line.length());

        timeline.play();
        timeline.setOnFinished(actionEvent -> gameController.setCanWrite(endScrolling));
    }
}
