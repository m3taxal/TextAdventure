import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PlayerLinesTest {
    PlayerLines playerLines;
    GameController gameController;

    @BeforeMethod
    public void setUp() {
        gameController = new GameController();
        gameController.setAllGameLines("tests/testDialogue");
        playerLines = new PlayerLines(gameController.gameText);
    }

    @Test
    public void testGetCorrLineNumber() {
        int[] expectedResult = {6, 8};
        assertEquals(expectedResult, playerLines.getCorrLineNumber("setsetset"));
    }

    @Test
    public void testGetCorrLineNumberWrongLine(){
        int[] expectedResult = {8, 9};
        try{
            assertEquals(expectedResult, playerLines.getCorrLineNumber("asdfsdfasdf"));
        }
        catch (AssertionError e){
            System.out.println("Successfully caught error while using wrong line.");
        }
    }

    @Test
    public void testSetCurrentPlayerLines() {
        playerLines.setCurrentPlayerLines(gameController.combineLines(gameController.gameText.subList(5, 8)));
        assertEquals(playerLines.currentPlayerLines.get(0), "asdfasdf");
    }

    @Test
    public void testPlayerInputPermissible() {
        playerLines.setCurrentPlayerLines(gameController.combineLines(gameController.gameText.subList(5, 8)));
        assertTrue(playerLines.playerInputPermissible("asdfasdf"));
    }
}