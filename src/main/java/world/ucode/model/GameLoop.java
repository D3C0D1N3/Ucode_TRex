package world.ucode.model;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import world.ucode.view.Menu;

public class GameLoop {

    private final Stage gameStage;
    private final AnchorPane gamePane;

    private final Player player;
    private final Ground ground;
    private final Enemy enemy;
    private final Score score;

    private boolean Active = false;

    public GameLoop(Stage gameStage, AnchorPane gamePane) {
        this.gameStage = gameStage;
        this.gamePane = gamePane;

        ground = new Ground(gamePane);
        ground.createGround();
        player = new Player(gamePane);
        player.createPlayer();
        enemy = new Enemy(gamePane);
        enemy.createEnemy();
        score = new Score(gamePane);

        event();
    }

    public void event() {
        gameStage.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.SPACE)
                startGame();
            else if (key.getCode() == KeyCode.Q) {
                gameStage.close();
                Menu menu = new Menu();
                menu.getMenuStage().show();
            }
        });
    }

    private void startGame() {
        if (!Active) {
            ground.moveGround();
            enemy.moveEnemy();
            score.startScore();
            Active = true;
        }
        player.Jump();
    }

}
