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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class Menu {

    private Game game;

    private AnchorPane menuPane;
    private Stage menuStage;

    private MenuSubScene helpSubScene;
    private MenuSubScene creditsSubScene;

    private MenuSubScene sceneToHide;

    private List<Buttons> menuButtons;

    private MediaPlayer menuSound;
    private MediaPlayer startSound;
    private MediaPlayer menuButtonSound;

    public Menu() { }

    public Stage getMenuStage() {
        return menuStage;
    }

    public void initMenu() {
        menuPane = new AnchorPane();
        Scene menuScene = new Scene(menuPane, 900, 720);
        menuStage = new Stage();
        menuStage.setScene(menuScene);

        menuStage.setTitle("Ucode_TRex");
        menuStage.setResizable(false);
        menuButtons = new ArrayList<>();

        createBackGroundImage();
        createLogo();
        createButtons();
        createSubScenes();

        menuSound = new MediaPlayer(
                new Media(
                        new File("src/main/resources/menu_sound.mp3").toURI().toString()));
        startSound = new MediaPlayer(
                new Media(
                        new File("src/main/resources/gasp_sound.mp3").toURI().toString()));
        menuButtonSound = new MediaPlayer(
                new Media(
                        new File("src/main/resources/menu_button_sound.mp3").toURI().toString()));
        menuSound.setCycleCount(-1);
        menuSound.play();
    }

    private void initButtonOnScreen(Buttons button) {
        button.setLayoutX(350);
        button.setLayoutY(250 + menuButtons.size() * 100);
        menuButtons.add(button);
        menuPane.getChildren().add(button);
    }

    private void showSubScene(MenuSubScene subScene) {
        if (sceneToHide != null)
            sceneToHide.moveSubScene();

        subScene.moveSubScene();
        sceneToHide = subScene;
    }

    private void createSubScenes() {
        creditsSubScene = new MenuSubScene(menuButtons);
        creditsSubScene.initSubScene();
        InfoLabel credits = new InfoLabel("This game is a T-rex implementation.\n" +
                "This is my first project in this language\nand I still have a lot to develop.\n" +
                "You can find me on github\nunder the login D3C0D1N3");
        creditsSubScene.getPane().getChildren().add(credits);
        menuPane.getChildren().add(creditsSubScene);

        helpSubScene = new MenuSubScene(menuButtons);
        helpSubScene.initSubScene();
        InfoLabel help = new InfoLabel("The essence of the game\n is to score a large number of points.\n" +
                "Press \"W\" to jump and dodge obstacles.\n" +
                "Beware of screamers)");
        helpSubScene.getPane().getChildren().add(help);
        menuPane.getChildren().add(helpSubScene);
    }

    private void createButtons() {
        Buttons start = new Buttons("START");
        initButtonOnScreen(start);
        start.setOnAction(event -> {
            menuSound.stop();
            startSound.play();
            game = new Game(menuStage);
            game.initializeGameStage();
        });

        Buttons help = new Buttons("HELP");
        initButtonOnScreen(help);
        help.setOnAction(event -> {
            menuButtonSound.play();
            showSubScene(helpSubScene);
        });

        Buttons credits = new Buttons("CREDITS");
        initButtonOnScreen(credits);
        credits.setOnAction(event -> {
            menuButtonSound.play();
            showSubScene(creditsSubScene);
        } );

        Buttons exit = new Buttons("EXIT");
        initButtonOnScreen(exit);
        exit.setOnAction(event -> menuStage.close());
    }

    private void createBackGroundImage() {
        try {
            Image menuBack = new Image("menu_back.jpg", 900, 720, false, false);
            BackgroundImage background = new BackgroundImage(menuBack, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
            menuPane.setBackground(new Background(background));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createLogo() {
        Label logo = new Label("Ucode-TRex");
        try {
            logo.setStyle("-fx-text-fill: grey;");
            logo.setFont((Font.loadFont(new FileInputStream("src/main/resources/menu_font.ttf"), 74)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        logo.setLayoutX(250);
        logo.setLayoutY(20);

        logo.setOnMouseEntered(mouseEvent -> logo.setEffect(new DropShadow(40, Color.BLACK)));
        logo.setOnMouseExited(mouseEvent -> logo.setEffect(null));

        menuPane.getChildren().add(logo);
    }

}
