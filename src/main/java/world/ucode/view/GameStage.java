package world.ucode.view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import world.ucode.model.GameLoop;

public class GameStage {
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;

    private Rectangle player;

    private Stage menuStage;

    public GameStage() {
        initializeStage();
        createPlayer();
        GameLoop loop = new GameLoop(gameScene, gameStage, menuStage, player);
        loop.createKeyListener();
        loop.startGameLoop();
    }

    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, 900, 720);
        gameStage = new Stage();
        gameStage.setResizable(false);
        gameStage.setScene(gameScene);
    }

    public void createNewGame(Stage menuStage) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        gameStage.show();
    }

    public void createPlayer() {
        Image playerIdleAnim = new Image("charIdle.gif");
        Image playerRunAnim = new Image("charRun.gif");

        player = new Rectangle(124, 124);
        player.setFill(new ImagePattern(playerIdleAnim));
        player.setX(20);
        player.setY(720 - 124);

        gamePane.getChildren().add(player);
    }
}