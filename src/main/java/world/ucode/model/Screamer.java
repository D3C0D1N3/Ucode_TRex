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

    private final MediaPlayer scream_sound;
    private final MediaPlayer scream_sound1;
    private final MediaPlayer scream_sound2;

    public Timer time;

    public Screamer(Pane gamePane) {
        this.gamePane = gamePane;
        random = new Random();

        scream_sound = new MediaPlayer(
                new Media(
                        new File("src/main/resources/screamer0.mp3").toURI().toString()));
        scream_sound1 = new MediaPlayer(
                new Media(
                        new File("src/main/resources/screamer1.mp3").toURI().toString()));
        scream_sound2 = new MediaPlayer(
                new Media(
                        new File("src/main/resources/screamer2.mp3").toURI().toString()));
    }

    public void initScreamer(Enemy enemy, Score score) {
        time = new Timer();

        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    scream_sound.stop();
                    scream_sound1.stop();
                    scream_sound2.stop();
                } catch(Exception e) {
                    e.printStackTrace();
                }
                standartBack();
                if (score.getScore() > 1000 && random.nextInt(100) <= 9) {
                    ImageView[] enemyImage = enemy.getEnemy();
                    for (ImageView i : enemyImage) {
                        if (i.getLayoutX() <= 200) {
                            screamerBack();
                            int x = random.nextInt(3);
                            switch (x) {
                                case 0: scream_sound.play();
                                case 1: scream_sound1.play();
                                case 2: scream_sound2.play();
                            }
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