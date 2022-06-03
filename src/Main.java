import  javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    public static int startEndLine; //to create a starting point, decide at which line the first scroll ends
    public static String dialogueFilePath; //path to all the game text

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameWindow.fxml")));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("gameStyle.css")).toExternalForm());

        stage.setTitle("Basic TextAdventure Game");
        stage.setResizable(false);
        stage.setScene(scene);

        stage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();

        Parameters parameters = getParameters();
        Map<String, String> namedArguments = parameters.getNamed();

        //set command line args
        startEndLine = Integer.parseInt(namedArguments.get("startEndLine"));
        dialogueFilePath = namedArguments.get("filepath");
    }
}
