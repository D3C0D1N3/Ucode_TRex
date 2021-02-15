package world.ucode.model;

import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import world.ucode.Main;

public class GameLoop {

    private final Scene gameScene;
    private final Stage gameStage;
    private final Stage menuStage;

    private final Rectangle player;

    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;

    public GameLoop(Scene gameScene, Stage gameStage, Stage menuStage, Rectangle player) {
        this.gameScene = gameScene;
        this.gameStage = gameStage;
        this.menuStage = menuStage;
        this.player = player;
    }

    public void startGameLoop() {
        AnimationTimer gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                jumpPlayer();
            }
        };

        gameTimer.start();
    }

    public void createKeyListener() {
        gameScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.W) {
                isUpKeyPressed = true;
            } else if (event.getCode() == KeyCode.S) {
                isDownKeyPressed = true;
            } else if (event.getCode() == KeyCode.Q) {
                gameStage.close();
                Main main = new Main();
                try {
                    main.start(menuStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        gameScene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.W) {
                isUpKeyPressed = false;
            } else if (event.getCode() == KeyCode.S) {
                isDownKeyPressed = false;
            }
        });
    }

    private void jumpPlayer() {
        if (isUpKeyPressed && !isDownKeyPressed && player.getTranslateY() == 0) {
            player.setTranslateY(0);
            double ty = player.getTranslateY();

            Interpolator interpolator = new Interpolator() {
                @Override
                protected double curve(double t) {
                    return t * (2 - t);
                }
            };
            Timeline timeline = new Timeline(new KeyFrame(Duration.ONE,
                    new KeyValue(player.translateYProperty(), ty, interpolator)),
                    new KeyFrame(Duration.seconds(0.3), new KeyValue(player.translateYProperty(), ty - 100,
                            interpolator)));

            timeline.setCycleCount(2);
            timeline.setAutoReverse(true);

            timeline.play();
        }
    }

}
