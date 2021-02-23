package world.ucode.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Screamer {

    private final Pane gamePane;

    private final Random random;

    private MediaPlayer scream_sound;

    public Timer time;

    public Screamer(Pane gamePane) {
        this.gamePane = gamePane;
        random = new Random();
    }

    public void initScreamer(Enemy enemy, Score score) {
        time = new Timer();

        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                standartBack();
                if (score.getScore() > 1000 && random.nextInt(100) <= 8) {
                    ImageView[] enemyImage = enemy.getEnemy();
                    for (ImageView i : enemyImage) {
                        if (i.getLayoutX() <= 200) {
                            screamerBack();
                            scream_sound = new MediaPlayer(
                                    new Media(
                                            new File("src/main/resources/screamer" +
                                                    random.nextInt(3) + ".mp3").toURI().toString()));
                            scream_sound.play();
                        }
                    }
                }
            }
        },  1000, 1000);
    }

    private void screamerBack() {
        try {
            Image menuBack = new Image("screamer_image" + random.nextInt(4) + ".gif",
                    1280, 720, false, false);
            BackgroundImage background = new BackgroundImage(menuBack, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
            gamePane.setBackground(new Background(background));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void standartBack() {
        try {
            Image menuBack = new Image("black.png", 1280, 720, false, false);
            BackgroundImage background = new BackgroundImage(menuBack, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
            gamePane.setBackground(new Background(background));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}