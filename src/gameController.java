
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class gameController implements Initializable {

    @FXML
    private TextArea gameDialogue;

    @FXML
    private TextField playerInput;

    private static Boolean canWrite;

    private TextHandler textHandler;

    public void setCanWrite(boolean newValue) {
        canWrite = newValue;
    }

    /**
     * Adds valid player lines to gameDialogue window.
     */
    public void addToGameDialogue(){
        if(canWrite){
            String playerLine = playerInput.getText();

            if(textHandler.playerInputPermissible(playerLine, gameDialogue)){
                canWrite = false;
                gameDialogue.appendText("\n" + ">" + textHandler.getAllLines().get(textHandler.getRememberPlayerLineIndex()));
            }

            //handle line skipping and jumping (e.g.where to go after line x, ...)
            if(!canWrite){
                switch (textHandler.getRememberPlayerLineIndex()) { //handles line-skipping and game-flow (e.g. what line to go after playerLine...)
                    case 2 -> textHandler.scrollText(textHandler.getAllLines().get(4), false, gameDialogue);
                    case 3 ->
                            textHandler.scrollText(textHandler.getAllLines().get(5) + "\n" + textHandler.getAllLines().get(6) + "\n" +
                                    textHandler.getAllLines().get(7) + "\n" + textHandler.getAllLines().get(8), true, gameDialogue);
                    case 7 -> textHandler.scrollText(textHandler.getAllLines().get(9), false, gameDialogue);
                    case 8 -> textHandler.scrollText(textHandler.getAllLines().get(10) + "\n" + textHandler.getAllLines().get(11), false, gameDialogue);
                }
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.gameDialogue.setEditable(false);
        this.gameDialogue.setWrapText(true);
        canWrite = true;
        this.textHandler = new TextHandler("src/dialogue.txt");

        //begin with starting dialogue (nothing entered by player yet)
        canWrite = false;
        textHandler.scrollText("""
                You are captured in an underground cell inside a dungeon. If you don't do anything in the next few seconds, you will most likely die.
                What do you do?
                Rot in your cell
                Unlock the cell with the key next to you""", true, gameDialogue);
    }
}
