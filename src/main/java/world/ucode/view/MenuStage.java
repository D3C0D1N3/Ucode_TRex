package world.ucode.view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MenuStage {

    private final AnchorPane menuPane;
    private final Stage menuStage;

    List<MenuButtons> menuButtons;

    public MenuStage() {
        menuPane = new AnchorPane();
        Scene menuScene = new Scene(menuPane, 900, 720);
        menuStage = new Stage();
        menuStage.setScene(menuScene);

        menuStage.setTitle("Ucode_TRex");
        menuStage.setResizable(false);
        initMenuObjects();
    }

    public Stage getMenuStage() {
        return menuStage;
    }

    public void initMenuObjects() {
        menuButtons = new ArrayList<>();

        createBackGroundImage();
        createLogo();
        createButtons();
    }

    public void initButtonOnScreen(MenuButtons button) {
        button.setLayoutX(350);
        button.setLayoutY(250 + menuButtons.size() * 100);
        menuButtons.add(button);
        menuPane.getChildren().add(button);
    }

    public void createButtons() {
        MenuButtons start = new MenuButtons("START");
        initButtonOnScreen(start);
        start.setOnAction(event -> {
            GameStage game = new GameStage();
            game.createNewGame(menuStage);
        });
        MenuButtons credits = new MenuButtons("CREDITS");
        initButtonOnScreen(credits);
        credits.setOnAction(event -> System.out.println("D3C0D1N3"));
        MenuButtons exit = new MenuButtons("EXIT");
        initButtonOnScreen(exit);
        exit.setOnAction(event -> menuStage.close());
    }

    public void createBackGroundImage() {
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
