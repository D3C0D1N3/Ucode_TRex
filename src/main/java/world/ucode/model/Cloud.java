package world.ucode.model;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Cloud {

    private final Pane gamePane;

    private ImageView[] clouds;

    public AnimationTimer animationTimer;

    private Random random;

    public Cloud(Pane gamePane) {
        this.gamePane = gamePane;
    }

    public void initCloudsOnScreen() {
        random = new Random();
        int y = 20;
        clouds = new ImageView[3];

        for (int i = 0; i < clouds.length; i++) {
            clouds[i] = new ImageView("cloud.gif");
            clouds[i].setFitWidth(64);
            clouds[i].setFitHeight(64);
            clouds[i].setLayoutY(y);
            clouds[i].setLayoutX(1300 + random.nextInt(3000));
            gamePane.getChildren().add(clouds[i]);
            y += 10 + random.nextInt(150);
        }
    }

    public void moveClouds() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (ImageView i : clouds) {
                    i.setLayoutX(i.getLayoutX() - 2.5);
                    if (i.getLayoutX() + i.getFitWidth() <= -64)
                        i.setLayoutX(1300 + random.nextInt(3000));
                }
            }
        };
        animationTimer.start();
    }
}
