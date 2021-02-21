package world.ucode.view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import world.ucode.controller.MenuButtons;

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

    private List<MenuButtons> menuButtons;

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
    }

    private void initButtonOnScreen(MenuButtons button) {
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
        menuPane.getChildren().add(creditsSubScene);

        helpSubScene = new MenuSubScene(menuButtons);
        helpSubScene.initSubScene();
        menuPane.getChildren().add(helpSubScene);
    }

    private void createButtons() {
        MenuButtons start = new MenuButtons("START");
        initButtonOnScreen(start);
        start.setOnAction(event -> {
            game = new Game(menuStage);
            game.initializeGameStage();
        });

        MenuButtons help = new MenuButtons("HELP");
        initButtonOnScreen(help);
        help.setOnAction(event -> showSubScene(helpSubScene));

        MenuButtons credits = new MenuButtons("CREDITS");
        initButtonOnScreen(credits);
        credits.setOnAction(event -> showSubScene(creditsSubScene) );

        MenuButtons exit = new MenuButtons("EXIT");
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
