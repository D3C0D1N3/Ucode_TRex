package world.ucode;

import javafx.application.Application;
import javafx.stage.Stage;
import world.ucode.view.Menu;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Menu menu = new Menu();
            menu.initMenu();
            menu.getMenuStage().show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void main(String[]args) {
        Application.launch();
    }

}
