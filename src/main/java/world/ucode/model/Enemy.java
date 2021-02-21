package world.ucode.model;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Enemy {

    private final AnchorPane gamePane;

    private ImageView[] zombieImage;

    public AnimationTimer animationTimer;

    public Enemy(AnchorPane gamePane) {
        this.gamePane = gamePane;
    }

    public void createEnemy() {
        zombieImage = new ImageView[3];
        for (int i = 0; i < zombieImage.length; i++) {
            zombieImage[i] = new ImageView("enemyZombie.gif");
            zombieImage[i].setLayoutY(720 - 240);
            zombieImage[i].setFitWidth(168);
            zombieImage[i].setFitHeight(168);
            gamePane.getChildren().add(zombieImage[i]);
        }
        for (ImageView imageView : zombieImage) {
            imageView.setLayoutX(random());
        }
    }

    public void moveEnemy() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (ImageView i : zombieImage) {
                    i.setLayoutX(i.getLayoutX() - 15);
                    if (i.getLayoutX() + i.getFitWidth() <= 0) {
                        i.setLayoutX(random());
                    }
                }
            }
        };
        animationTimer.start();
    }

    private double random() {
        double result = 1500 + (int) (Math.random() * 35) * 100;

        for (ImageView y : zombieImage) {
            if (Math.abs(result - y.getLayoutX()) < 400)
                result = -150;
        }
        return result;
    }

    public ImageView[] getEnemy() {
        return zombieImage;
    }

}
