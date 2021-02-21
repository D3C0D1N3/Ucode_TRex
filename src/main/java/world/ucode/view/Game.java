package world.ucode.view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import world.ucode.model.GameLoop;
import world.ucode.model.Ground;
import world.ucode.model.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private AnchorPane gamePane;

    private final Stage menuStage;

    private Rectangle player;

    private List<Rectangle> groundList;

    public Game(Stage menuStage) {
        this.menuStage = menuStage;

        initializeGameStage();
    }

    public void initializeGameStage() {
        gamePane = new AnchorPane();
        Scene gameScene = new Scene(gamePane, 900, 720);
        Stage gameStage = new Stage();
        gameStage.setResizable(false);
        gameStage.setScene(gameScene);
        gamePane.setStyle("-fx-background-color: black;");

        menuStage.close();
        gameStage.show();

        GameLoop loop = new GameLoop(gameStage, gamePane);
    }

}