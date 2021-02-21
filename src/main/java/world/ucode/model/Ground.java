package world.ucode.model;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Ground {

    private ArrayList<Rectangle> groundList;
    private AnchorPane gamePane;
    private AnimationTimer animationTimer;

    public Ground(AnchorPane gamePane) {
        this.gamePane = gamePane;
    }

    public void createGround() {
        groundList = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Image groundImage = new Image("ground.png");
            Rectangle groundRectangle = new Rectangle(900, 124);
            groundRectangle.setFill(new ImagePattern(groundImage));
            groundRectangle.setLayoutX(i == 0 ? 0 : 900);
            groundRectangle.setLayoutY(720 - 124);
            gamePane.getChildren().add(groundRectangle);
            groundList.add(groundRectangle);
        }
    }

    public void moveGround() {
        animationTimer = new AnimationTimer(){
            @Override
            public void handle(long now) {
                for (Rectangle i : groundList) {
                    i.setLayoutX(i.getLayoutX() - 10);
                    if (i.getLayoutX() + i.getWidth() <= 0)
                        i.setLayoutX(900);
                }
            }
        };
        animationTimer.start();
    }

}
