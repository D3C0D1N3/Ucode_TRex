package world.ucode.controller;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Buttons extends Button {

    private static final String BUTTON_FREE_STYLE = "-fx-background-color: none; -fx-border-radius: 0%;" +
            "-fx-border-color: grey; -fx-border-width: 3px;" +
            "-fx-text-fill: grey;";
    private static final String BUTTON_PRESSED_STYLE = "-fx-background-color: none; -fx-border-radius: 0%;" +
            "-fx-border-color: black; -fx-border-width: 3px;" +
            "-fx-text-fill: black; -fx-rotate: 1;";

    public Buttons(String text) {
        setText(text);
        setButtonFont();
        setPrefSize(190, 49);
        setStyle(BUTTON_FREE_STYLE);
        initialiseButtonListeners();
    }

    private void setButtonFont() {
        try {
            String FONT_PATH = "src/main/resources/menu_font.ttf";
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 25));
            System.out.println("Font not found or could not be loaded. Using default \"Verdana\"");
        }
    }

    private void setButtonPressedStyle() {
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(getPrefHeight() - 4);
        setLayoutY(getLayoutY() + 4);
    }

    private void setButtonReleasedStyle() {
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }

    private void initialiseButtonListeners() {
        setOnMousePressed(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                setButtonPressedStyle();
            }
        });

        setOnMouseReleased(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                setButtonReleasedStyle();
            }
        });

        setOnMouseEntered(event -> setEffect(new DropShadow(20, Color.BLACK)));

        setOnMouseExited(event -> setEffect(null));
    }
}