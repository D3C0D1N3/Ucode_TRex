package world.ucode.view;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.layout.*;
import javafx.util.Duration;
import world.ucode.controller.MenuButtons;

import java.util.List;

public class MenuSubScene extends SubScene {

    private boolean isHidden;

    private final List<MenuButtons> buttons;


    public MenuSubScene(List<MenuButtons> buttons) {
        super(new AnchorPane(), 600, 400);

        this.buttons = buttons;

        initSubScene();
    }

    private void initSubScene() {
        setStyle("-fx-background-color: grey; -fx-opacity: 30%;");
        isHidden = true;
        setLayoutX(1024);
        setLayoutY(235);
    }

    public void moveSubScene() {
        TranslateTransition subSceneTransition = new TranslateTransition();
        TranslateTransition buttonTransition;
        double interval = 0.3;

        subSceneTransition.setDuration(Duration.seconds(0.5));

        subSceneTransition.setNode(this);

        if (isHidden) {
            isHidden = false;
            subSceneTransition.setToX(-700);
            subSceneTransition.setToY(0);

            for (MenuButtons i : buttons) {
                buttonTransition = new TranslateTransition();
                buttonTransition.setDuration(Duration.seconds(interval));
                interval += 0.1;
                buttonTransition.setNode(i);
                buttonTransition.setToX(-250);
                buttonTransition.setToY(0);
                buttonTransition.play();
            }
        } else {
            isHidden = true;
            interval = 0.3;
            subSceneTransition.setToX(0);
            subSceneTransition.setToY(0);

            for (MenuButtons NaN: buttons) {
                buttonTransition = new TranslateTransition();
                buttonTransition.setDuration(Duration.seconds(interval));
                interval += 0.1;
                buttonTransition.setToX(0);
                buttonTransition.setToY(0);
                buttonTransition.playFromStart();
            }
        }

        subSceneTransition.play();
    }
}
