package world.ucode.view;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import world.ucode.model.GameLoop;

public class Game {

    private final Stage menuStage;

    public Game(Stage menuStage) {
        this.menuStage = menuStage;
    }

    public void initializeGameStage() {
        AnchorPane gamePane = new AnchorPane();
        Scene gameScene = new Scene(gamePane, 1280, 720);
        Stage gameStage = new Stage();
        gameStage.setResizable(false);
        gameStage.setScene(gameScene);
        gamePane.setStyle("-fx-background-color: black;");

        menuStage.close();
        gameStage.show();

        GameLoop gameLoop = new GameLoop(gameStage, gamePane);
        gameLoop.initGameLoop();
    }

}