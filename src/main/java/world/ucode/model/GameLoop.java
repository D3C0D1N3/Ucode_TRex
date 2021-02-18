package world.ucode.model;

import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import world.ucode.view.MenuStage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLoop {

    private final Scene gameScene;
    private final Stage gameStage;
    private final Pane gamePane;

    private final Rectangle player;

    private final List<Rectangle> groundList;

    private int gameSpeed = 5;

    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private boolean isAlive = true;
    private boolean isGameActive = false;

    private final Image charRun;
    private final Image charIdle;

    private ImageView enemyZombie;
    private ImageView enemyEye;

    private int animationState = 0;

    private List<ImageView> zombieList;
    private List<ImageView> eyeList;

    Random random;

    public GameLoop(Scene gameScene, Stage gameStage, Pane gamePane, Rectangle player, List <Rectangle> groundList) {
        this.gameScene = gameScene;
        this.gameStage = gameStage;
        this.gamePane = gamePane;
        this.groundList = groundList;
        this.player = player;

        charRun = new Image("charRun.gif");
        charIdle = new Image("charIdle.gif");

//        random = new Random();

//        zombieList = new ArrayList<>();
//        eyeList = new ArrayList<>();

//        enemyZombie = new ImageView("enemyZombie.gif");
//        enemyEye = new ImageView("enemyEye.gif");

//        createEnemy();
    }

    public void startGameLoop() {
        AnimationTimer gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (isAlive) {
                    jumpPlayer();
                    if (isGameActive) {
                        crouchPlayer();
                        moveGround();
//                        moveEnemy();
//                        replaseEnemy();
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
            isGameActive = true;
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

        if (element1.getX() == 0)
            element.setX(900);
        else if (element.getX() == 0)
            element1.setX(900);
    }


//    private void createEnemy() {
//        enemyZombie.setFitHeight(168);
//        enemyZombie.setFitWidth(168);
//        enemyZombie.setLayoutY(720 - 220);
//
//        for (int i = 0; i < 3; i++) {
//            enemyZombie.setLayoutX(random.nextInt(2000));
//            zombieList.add(enemyZombie);
//        }
//        gamePane.getChildren().add(enemyZombie);
//
//        enemyEye.setFitHeight(64);
//        enemyEye.setFitWidth(64);
//        enemyEye.setLayoutY(720 - 250);
//
//        for (int i = 0; i < 3; i++) {
//            enemyEye.setLayoutX(random.nextInt(2000));
//            eyeList.add(enemyEye);
//        }
//        gamePane.getChildren().add(enemyEye);
//    }
//
//    private void replaseEnemy() {
//        for (int i = 0; i < zombieList.size(); i++) {
//            if (zombieList.get(i).getLayoutX() < -200) {
//                int pos = random.nextInt(2000);
//                pos += pos < 1000 ? 1000 : 0;
//                zombieList.get(i).setLayoutX(pos);
//            }
//        }
//        for (int i = 0; i < eyeList.size(); i++) {
//            if (eyeList.get(i).getLayoutX() < -200) {
//                int pos = random.nextInt(2000);
//                pos += pos < 1000 ? 1000 : 0;
//                eyeList.get(i).setLayoutX(pos);
//            }
//        }
//    }
//
//    private void moveEnemy() {
//        for (int i = 0; i < zombieList.size(); i++) {
//            zombieList.get(i).setLayoutX(zombieList.get(i).getLayoutX() - gameSpeed);
//        }
//        for (int i = 0; i < zombieList.size(); i++) {
//            eyeList.get(i).setLayoutX(eyeList.get(i).getLayoutX() - gameSpeed);
//        }
//    }

}
