import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

public class GameControllerTest {
    GameController gameController;

    @BeforeMethod
    public void setUp() {
        gameController = new GameController();
    }

    @Test
    public void testCombineLines() {
        String expectedResult = """
                                test
                                test1
                                """;
        String[] testList = {"test", "test1"};
        //actual first, second expected
        assertEquals(gameController.combineLines(Arrays.asList(testList)), expectedResult);
    }
}