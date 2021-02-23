package world.ucode.model;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.File;

public class Player {

    private Rectangle player;
    private final AnchorPane gamePane;

    private int animState = 0;

    private int gravity = 0;

    private Image playerRunImage;
    private Image playerIdleImage;

    private MediaPlayer jumpSound;

    public AnimationTimer animationTimer;

    public Player(AnchorPane gamePane) {
        this.gamePane = gamePane;
    }

    public void createPlayer() {
        Image playerIdleAnim = new Image("char_idle.gif");

        player = new Rectangle(124, 124);
        player.setFill(new ImagePattern(playerIdleAnim));
        player.setLayoutX(80);
        player.setLayoutY(720 - 186);

        gamePane.getChildren().add(player);

        playerRunImage = new Image("char_run.gif");
        playerIdleImage = new Image("char_idle.gif");

        jumpSound = new MediaPlayer(
                new Media(
                        new File("src/main/resources/jump_sound.mp3").toURI().toString()));
    }

    private void runAnimation() {
        if (animState == 0) {
            animState = 1;
            player.setFill(new ImagePattern(playerRunImage));
        }
    }

    private void idleAnimation() {
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
                    jumpSound.play();
                    idleAnimation();
                    player.setLayoutY(player.getLayoutY() - 35 + gravity);
                    gravity += 2;
                    if (yPreviousPos <= player.getLayoutY()) {
                        runAnimation();
                        jumpSound.stop();
                        animationTimer.stop();
                        gravity = 0;
                    }
                }
            };
            animationTimer.start();
        }
    }

    public double getPlayerYPos() {
        return player.getLayoutY();
    }

}
