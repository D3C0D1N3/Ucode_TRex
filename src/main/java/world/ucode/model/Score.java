package world.ucode.model;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Score {

    private final AnchorPane gamePane;

    private Label label;
    private int score = 0;

    public AnimationTimer animationTimer;

    public Score(AnchorPane gamePane) {
        this.gamePane = gamePane;
    }

    public void setScoreLabel() {
        label = new Label();
        label.setLayoutX(1000);
        label.setLayoutY(10);
        try {
            label.setFont((Font.loadFont(new FileInputStream("src/main/resources/menu_font.ttf"), 23)));
            label.setTextFill(Color.WHITE);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        label.setText("score: 0");
        gamePane.getChildren().add(label);
    }

    public void startScore() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                label.setText("score: " + score);
                score++;
            }
        };
        animationTimer.start();
    }

    public int getScore() {
        return score;
    }
}
