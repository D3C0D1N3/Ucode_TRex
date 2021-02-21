package world.ucode.model;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Random;

public class Enemy {

    private final AnchorPane gamePane;

    private ImageView[] zombieImage;
    private ImageView[] eyeImage;

    private final Random random;

    public Enemy(AnchorPane gamePane) {
        this.gamePane = gamePane;

        random = new Random();
    }

    public void createEnemy() {
        zombieImage = new ImageView[2];
        for (int i = 0; i < zombieImage.length; i++) {
            zombieImage[i] = new ImageView("enemyZombie.gif");
            zombieImage[i].setLayoutY(720 - 240);
            zombieImage[i].setFitWidth(168);
            zombieImage[i].setFitHeight(168);
            zombieImage[i].setLayoutX(-200);
            gamePane.getChildren().add(zombieImage[i]);
        }

        eyeImage = new ImageView[2];
        for (int i = 0; i < eyeImage.length; i++) {
            eyeImage[i] = new ImageView("enemyEye.gif");
            eyeImage[i].setLayoutY(720 - 360);
            eyeImage[i].setFitWidth(64);
            eyeImage[i].setFitHeight(64);
            eyeImage[i].setLayoutX(-200);
            gamePane.getChildren().add(eyeImage[i]);
        }
    }

    public void moveEnemy() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (ImageView i : zombieImage) {
                    i.setLayoutX(i.getLayoutX() - 10);
                }
                for (ImageView i : eyeImage) {
                    i.setLayoutX(i.getLayoutX() - 10);
                }
            }
        };
        animationTimer.start();
    }

}
