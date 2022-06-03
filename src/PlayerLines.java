import java.util.ArrayList;
import java.util.HashMap;

public class PlayerLines{
    private final HashMap<String, int[]> playerLinesInfo;
    public final ArrayList<String> currentPlayerLines;

    public PlayerLines(ArrayList<String> gameText){
        playerLinesInfo = new HashMap<>();
        currentPlayerLines = new ArrayList<>();
        setPlayerLinesInfo(gameText);
    }

    /**
     * Get the next line numbers that correspond to the player input.
     *
     * @param playerInput   player input to be checked
     * @return              corresponding line numbers (e.g. {start, finish})
     */
    public int[] getCorrLineNumber(String playerInput) {
        return playerLinesInfo.get(playerInput.strip().toLowerCase());
    }

    /**
     * Get all player options that are currently in the game window.
     * @param currentGameDialogue   text that was last scrolled in the game window
     */
    public void setCurrentPlayerLines(String currentGameDialogue) {
        currentPlayerLines.clear();

        String[] sentences = currentGameDialogue.split("\n");

        for (String line:
             sentences) {
            if(playerLinesInfo.containsKey(line.toLowerCase())){
                currentPlayerLines.add(line);
            }
        }
    }

    /**
     * Check if player input is permissible.
     *
     * @param playerInput   input to be checked
     * @return              true if permissible, false otherwise
     */
    public boolean playerInputPermissible(String playerInput){
        for (String line: this.currentPlayerLines) {
            if(line.strip().equalsIgnoreCase(playerInput.strip())){
                return true;
            }
        }
        return false;
    }

    /**
     * Get all player options present in the game text.
     *
     * @param gameText  accessed through filepath,
     *                  contains all game lines
     */
    private void setPlayerLinesInfo(ArrayList<String> gameText){
        for (String line:
             gameText) {
            if(line.contains("[")){
                int[] newInfo = new int[2];
                String[] newLineNumbers = line.substring(line.indexOf("[")+1, line.indexOf("]")).split(";");

                //these are the actual line numbers, so do -1 in runtime
                newInfo[0] = Integer.parseInt(newLineNumbers[0]);
                newInfo[1] = Integer.parseInt(newLineNumbers[1]);

                this.playerLinesInfo.put(line.substring(0, line.indexOf("[")).strip().toLowerCase(), newInfo);
            }
        }
    }
}
