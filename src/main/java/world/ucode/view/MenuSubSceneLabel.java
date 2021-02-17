package world.ucode.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MenuSubSceneLabel extends Label {

    public final static String FONT_PATH = "src/main/resources/TTF/kenvector_future.ttf";

    public MenuSubSceneLabel(String text) {
        setPrefSize(380, 49);
        setPadding(new Insets(40, 40, 40, 40));
        setText(text);
        setWrapText(true);
        setLabelFont();
        setAlignment(Pos.CENTER);
        setStyle("-fx-text-fill: blueviolet;");
    }

    private void setLabelFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
        }
    }

}