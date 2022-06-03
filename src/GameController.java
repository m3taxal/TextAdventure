import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class GameController implements Initializable {

    public ArrayList<String> gameText;

    @FXML
    private TextArea gameDialogue; //game window where the scrolling text is shown

    @FXML
    private TextField playerInput;

    private PlayerLines playerLines;

    private int letter; //used for scrolling

    /**
     * Get game text from given filepath.
     */
    private void getAllGameLines(){
        gameText = new ArrayList<>();
        try{
            File gameLines = new File(Main.dialogueFilePath);
            Scanner reader = new Scanner(gameLines);
            while (reader.hasNextLine()){
                gameText.add(reader.nextLine());
            }
            reader.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Wrong filepath!");
            //e.printStackTrace();
        }
    }

    /**
     * Used to scroll a line.
     *
     * @param line  line to be scrolled. Actually a multi-line
     *              String combined into one for ease of use.
     */
    private void scroll(String line){
        playerInput.setEditable(false);
        this.letter = 0;

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.03), e -> {
            gameDialogue.appendText(String.valueOf(line.charAt(letter)));
            this.letter += 1;
        }));

        //play for amount of characters in line
        timeline.setCycleCount(line.length());

        timeline.play();
        timeline.setOnFinished(actionEvent -> playerInput.setEditable(true));
    }

    /**
     * Combine a list of String objects into one single String object.
     * list.join(n) would not work in this case
     * because there are special characters ([, ], ;)
     * that need to be removed.
     *
     * @param linesToCombine    lines to be combined
     * @return                  combined String
     */
    private String combineLines(List<String> linesToCombine){
        StringBuilder temp = new StringBuilder();
        for (String line:
             linesToCombine) {
            if(line.contains("[")){
                temp.append(line, 0, line.indexOf("["));
            }
            else {
                temp.append(line);
            }
            temp.append("\n");
        }
        return temp.toString();
    }

    /**
     * Called whenever player enters a new line.
     * Scrolls corresponding game text.
     */
    public void scrollToGameDialogue(){
        if(playerInput.isEditable()){
            String enteredText = playerInput.getText();
            if(playerLines.playerInputPermissible(enteredText)){
                gameDialogue.appendText("> " + enteredText.strip().toLowerCase() + "\n");
                playerInput.clear();
                int[] newLineNumbers = playerLines.getCorrLineNumber(enteredText);
                String newStartingText;
                if(newLineNumbers[0] == newLineNumbers[1]){
                    newStartingText = gameText.get(newLineNumbers[0]-1);
                }
                else {
                    newStartingText = combineLines(gameText.subList(newLineNumbers[0]-1, newLineNumbers[1]));
                }
                playerLines.setCurrentPlayerLines(newStartingText);
                scroll(newStartingText);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameDialogue.setEditable(false);
        getAllGameLines();

        playerLines = new PlayerLines(gameText);

        //create a starting point
        String startingText =  combineLines(gameText.subList(0, Main.startEndLine));
        playerLines.setCurrentPlayerLines(startingText);
        scroll(startingText);
    }
}
