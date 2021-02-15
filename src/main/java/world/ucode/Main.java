package world.ucode;

import javafx.application.Application;
import javafx.stage.Stage;
import world.ucode.view.MenuStage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            MenuStage menu = new MenuStage();
            primaryStage = menu.getMenuStage();
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void main(String[]args) {
        Application.launch();
    }

}
