package world.ucode.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InfoLabel extends Label {

    public InfoLabel(String text) {
        setPrefWidth(480);
        setText(text);
        setStyle("-fx-text-fill: red;");
        setWrapText(true);
        try {
            setFont(Font.loadFont(new FileInputStream("src/main/resources/menu_font.ttf"), 23));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setAlignment(Pos.CENTER);
    }

}
