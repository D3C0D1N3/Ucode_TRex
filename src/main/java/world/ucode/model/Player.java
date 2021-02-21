package world.ucode.model;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Player {

    private Rectangle player;
    private final AnchorPane gamePane;

    private AnimationTimer animationTimer;
    private int animState = 0;

    private int gravity = 0;

    private Image playerRunImage;
    private Image playerIdleImage;

    public Player(AnchorPane gamePane) {
        this.gamePane = gamePane;
    }

    public void createPlayer() {
        Image playerIdleAnim = new Image("charIdle.gif");

        player = new Rectangle(124, 124);
        player.setFill(new ImagePattern(playerIdleAnim));
        player.setLayoutY(60);
        player.setLayoutY(720 - 186);

        gamePane.getChildren().add(player);

        playerRunImage = new Image("charRun.gif");
        playerIdleImage = new Image("charIdle.gif");
    }

    private void runAnimation() {
        if (animState == 0) {
            animState = 1;
            player.setFill(new ImagePattern(playerRunImage));
        }
    }

    public void idleAnimation() {
        if (animState == 1) {
            animState = 0;
            player.setFill(new ImagePattern(playerIdleImage));
        }
    }

    public void Jump() {
        if (gravity == 0) {
            double yPreviousPos = player.getLayoutY();
            animationTimer = new AnimationTimer(){
                @Override
                public void handle(long now) {
                    idleAnimation();
                    player.setLayoutY(player.getLayoutY() - 30 + gravity);
                    gravity += 2;
                    if (yPreviousPos <= player.getLayoutY()) {
                        runAnimation();
                        animationTimer.stop();
                        gravity = 0;
                    }
                }
            };
            animationTimer.start();
        }
    }

}
