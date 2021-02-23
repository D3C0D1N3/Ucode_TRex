package world.ucode.model;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import world.ucode.view.GameOver;

import java.io.File;

public class Collision {

    private ImageView[] enemyPos;
    private double playerPos;

    private boolean isAlreadyDie = false;

    private final MediaPlayer dieSound;

    public AnimationTimer animationTimer;

    public Collision() {
        dieSound = new MediaPlayer(
                new Media(
                        new File("src/main/resources/die_sound.mp3").toURI().toString()));
    }

    public void checkCollision(Player player, Enemy enemy, GameOver gameOver, MediaPlayer gameSound) {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                playerPos = player.getPlayerYPos();
                enemyPos = enemy.getEnemy();

                for (ImageView i : enemyPos) {
                    if (i.getLayoutX() <= 85 && i.getLayoutX() >= 70 && playerPos >= 400) {
                        if (!isAlreadyDie) {
                            dieSound.play();
                            gameSound.stop();
                            gameOver.initGameOver();
                            isAlreadyDie = true;
                        }
                    }
                }
            }
        };
        animationTimer.start();
    }
}
