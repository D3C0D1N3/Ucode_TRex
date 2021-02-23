package world.ucode.view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import world.ucode.controller.Buttons;
import world.ucode.model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class GameOver {

    private Pane overPane;
    private Stage overStage;

    private final Stage gameStage;

    private final Player player;
    private final Enemy enemy;
    private final Ground ground;
    private final Score score;
    private final Collision collision;
    private final Cloud cloud;
    private final Screamer screamer;

    private Menu menu;

    private List<Buttons> buttonsList;

    private MediaPlayer overSound;
    private MediaPlayer secondOverSound;

    public GameOver(Stage gameStage, Player player, Enemy enemy,
                    Ground ground, Score score, Collision collision,
                    Cloud cloud, Screamer screamer) {
        this.gameStage = gameStage;
        this.player = player;
        this.enemy = enemy;
        this.ground = ground;
        this.score = score;
        this.collision = collision;
        this.cloud = cloud;
        this.screamer = screamer;
    }

    public void initGameOver() {

        menu = new Menu();
        gameStage.close();
        player.animationTimer.stop();
        enemy.animationTimer.stop();
        ground.animationTimer.stop();
        score.animationTimer.stop();
        collision.animationTimer.stop();
        cloud.animationTimer.stop();
        screamer.time.cancel();

        overPane = new AnchorPane();
        Scene menuScene = new Scene(overPane, 900, 720);
        overStage = new Stage();
        overStage.setScene(menuScene);

        buttonsList = new ArrayList<>();

        overStage.setTitle("Ucode_TRex");
        overStage.setResizable(false);
        overStage.show();

        createBackGroundImage();
        createButtons();
        createLogo();

        overSound = new MediaPlayer(
                new Media(
                        new File("src/main/resources/game_over_sound.mp3").toURI().toString()));
        secondOverSound = new MediaPlayer(
                new Media(
                        new File("src/main/resources/monster_game_over.mp3").toURI().toString()));
        overSound.setCycleCount(-1);
        secondOverSound.play();
        overSound.play();
    }

    private void initButtonOnScreen(Buttons button) {
        button.setLayoutX(250);
        button.setLayoutY(250 + buttonsList.size() * 100);
        buttonsList.add(button);
        overPane.getChildren().add(button);
    }

    private void createButtons() {
        Buttons start = new Buttons("RESTART");
        initButtonOnScreen(start);
        start.setOnAction(event -> {
            secondOverSound.stop();
            overSound.stop();
            Game game = new Game(overStage);
            game.initializeGameStage();
        });

        Buttons help = new Buttons("MENU");
        initButtonOnScreen(help);
        help.setOnAction(event -> {
            secondOverSound.stop();
            overSound.stop();
            overStage.close();
            menu.initMenu();
            menu.getMenuStage().show();
        });
    }

    private void createBackGroundImage() {
        try {
            Image menuBack = new Image("over_back.gif", 900, 720, false, false);
            BackgroundImage background = new BackgroundImage(menuBack, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
            overPane.setBackground(new Background(background));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createLogo() {
        Label logo = new Label("GAME OVER\nYou reached: " + score.getScore());
        try {
            logo.setStyle("-fx-text-fill: grey;");
            logo.setFont((Font.loadFont(new FileInputStream("src/main/resources/menu_font.ttf"), 36)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        logo.setLayoutX(200);
        logo.setLayoutY(50);

        logo.setOnMouseEntered(mouseEvent -> logo.setEffect(new DropShadow(40, Color.BLACK)));
        logo.setOnMouseExited(mouseEvent -> logo.setEffect(null));

        overPane.getChildren().add(logo);
    }
}
