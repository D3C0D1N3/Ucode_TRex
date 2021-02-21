package world.ucode.view;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import world.ucode.model.GameLoop;

public class Game {
    private AnchorPane gamePane;

    private final Stage menuStage;

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

        new GameLoop(gameStage, gamePane);
    }

}