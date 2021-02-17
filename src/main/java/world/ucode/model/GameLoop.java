package world.ucode.model;

import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import world.ucode.view.MenuStage;

import java.util.List;

public class GameLoop {

    private final Scene gameScene;
    private final Stage gameStage;

    private final Rectangle player;

    private final List<Rectangle> groundList;

    private int gameSpeed = 5;

    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private boolean isAlive = true;
    private boolean isGameStarted = false;

    private final Image charRun;
    private final Image charIdle;

    private int animationState = 0;

    public GameLoop(Scene gameScene, Stage gameStage, Rectangle player, List <Rectangle> groundList) {
        this.gameScene = gameScene;
        this.gameStage = gameStage;
        this.groundList = groundList;
        this.player = player;

        charRun = new Image("charRun.gif");
        charIdle = new Image("charIdle.gif");
    }

    public void startGameLoop() {
        AnimationTimer gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (isAlive) {
                    jumpPlayer();
                    if (isGameStarted) {
                        crouchPlayer();
                        moveGround();
                    }
                }
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
                MenuStage menu = new MenuStage();
                Stage menuStage = menu.getMenuStage();
                isAlive = false;
                gameStage.close();
                menuStage.show();
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
        if (isUpKeyPressed && player.getTranslateY() == 0) {
            isGameStarted = true;
            double ty = player.getTranslateY();

            Interpolator interpolator = new Interpolator() {
                @Override
                protected double curve(double t) {
                    return t * (2 - t);
                }
            };
            Timeline timeline = new Timeline(new KeyFrame(Duration.ONE,
                    new KeyValue(player.translateYProperty(), ty, interpolator)),
                    new KeyFrame(Duration.seconds(0.3), new KeyValue(player.translateYProperty(), ty - 300,
                            interpolator)));

            timeline.setCycleCount(2);
            timeline.setAutoReverse(true);

            timeline.play();
        }
    }

    private void crouchPlayer() {
        if (player.getTranslateY() == 0 && animationState == 0) {
            player.setFill(new ImagePattern(charRun));
            animationState = 1;
        }
        if (player.getTranslateY() != 0 && animationState == 1) {
            player.setFill(new ImagePattern(charIdle));
            animationState = 0;
        }
    }

    private void moveGround() {
        Rectangle element = groundList.get(0);
        Rectangle element1 = groundList.get(1);

        element.setX(element.getX() - gameSpeed);
        element1.setX(element1.getX() - gameSpeed);

        if (element1.getX() == 0) {
            element.setX(900);
            if (gameSpeed != 30)
                gameSpeed += 5;
        }
        else if (element.getX() == 0)
            element1.setX(900);
    }

}
