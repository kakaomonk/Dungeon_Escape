package graphics;

import game.Game;
import game.Serialize;
import game.GameMaker;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import utils.Point2D;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Separate main file, only to be used for the graphics branch.
 */
public class GraphicsMain extends Application {

    private static Scene mainScene;
    //NOTE: Not sure if storing RenderPane as private static is clean, but it's a temp solution at least
    private static RenderPane renderPane;
    private int boardSize;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws IOException {
        GameMaker gameMaker = new GameMaker();

        int[] sizeLoad  = gameMaker.getBoardSize();

        this.boardSize = sizeLoad[0];
        int load = sizeLoad[1];

        mainStage.setTitle("1190");

        // Build scene graph
//        Group root = new Group();
//        mainScene = new Scene(root);
//        mainStage.setScene(mainScene);

        // Init RenderPane and add to scene graph
        if (load == 1) {
            Game g = Serialize.deserialize();
            int size = g.getSize();
            renderPane = new RenderPane(g, new Point2D(32 * size,
                    32 * size));
        }
        else {
            renderPane = new RenderPane(new Game(this.boardSize), new Point2D(32 * this.boardSize,
                    32 * this.boardSize));
            // TODO: Remove after testing
            this.addGoal(new Point2D(17, 17));
            this.addUpAlligatorDen(new Point2D(12, 13));
            this.addRightAlligatorDen(new Point2D(7,8));
        }



//        root.getChildren().add(renderPane.getAnchor());



        renderPane.start();

        // Load resources using FXML
        // NOTE: This method of loading doesn't work that
        URL url = new File("src/main/assets/testScene.fxml").toURI().toURL();
        Scene fxmlScene = new Scene(FXMLLoader.load(url));
        mainStage.setScene(fxmlScene);

        // TODO: Clean up scuffed code block later
        BorderPane bp = (BorderPane) fxmlScene.lookup("#layout");
        bp.setCenter(renderPane.getAnchor());
        Button playButton = (Button) fxmlScene.lookup("#playButton");
        PlayButtonController pbc = new PlayButtonController(playButton);
        playButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (pbc.isPlayMode()) {
                renderPane.stop();
            } else {
                renderPane.start();
            }
        });
        // TODO: Clean up this other scuffed code block later
        Button saveButton = (Button) fxmlScene.lookup("#saveButton");
        saveButton.setOnMouseClicked(event -> {
            Serialize.serialize(renderPane.getGame());
        });

        // Show stage
        mainStage.show();
    }

    /*private static void addObject(Element object){
        renderPane.getGame().getBoard().getObjectManager().addObject(object);
    }*/

    // Object-specific add methods to be called by gameMaker
    // TODO: add more or configure with gameMaker
    public void addGoal(Point2D pos) {
        renderPane.getGame().getBoard().getObjectManager().addGoal(pos);
    }

    public void addRightAlligatorDen(Point2D pos) {
        renderPane.getGame().getBoard().getObjectManager().addRightAlligatorDen(pos);
    }
    public void addLeftAlligatorDen(Point2D pos) {
        renderPane.getGame().getBoard().getObjectManager().addLeftAlligatorDen(pos);
    }
    public void addUpAlligatorDen(Point2D pos) {
        renderPane.getGame().getBoard().getObjectManager().addUpAlligatorDen(pos);
    }
    public void addDownAlligatorDen(Point2D pos) {
        renderPane.getGame().getBoard().getObjectManager().addDownAlligatorDen(pos);
    }


}
