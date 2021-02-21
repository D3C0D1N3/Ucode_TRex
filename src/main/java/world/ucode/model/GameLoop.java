package world.ucode.model;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import world.ucode.view.Menu;

public class GameLoop {

    private final Stage gameStage;
    private final AnchorPane gamePane;

    private Player player;
    private Ground ground;
    private Enemy enemy;
    private Score score;
    private Collision collision;

    private boolean alreadyStarted = false;

    public GameLoop(Stage gameStage, AnchorPane gamePane) {
        this.gameStage = gameStage;
        this.gamePane = gamePane;
    }

    public void initGameLoop() {
        ground = new Ground(gamePane);
        ground.createGround();
        player = new Player(gamePane);
        player.createPlayer();
        enemy = new Enemy(gamePane);
        enemy.createEnemy();
        score = new Score(gamePane);
        score.setScoreLabel();
        collision = new Collision();

        event();
    }

    public void event() {
        gameStage.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.SPACE)
                startGame();
            else if (key.getCode() == KeyCode.Q) {
                startGame();
                stopGame();
                gameStage.close();
                Menu menu = new Menu();
                menu.initMenu();
                menu.getMenuStage().show();
            }
        });
    }

    private void startGame() {
        if (!alreadyStarted) {
            ground.moveGround();
            enemy.moveEnemy();
            score.startScore();
            alreadyStarted = true;
        }
        player.Jump();
        collision.checkCollision(player, enemy);
    }

    public void stopGame() {
        player.animationTimer.stop();
        ground.animationTimer.stop();
        enemy.animationTimer.stop();
        score.animationTimer.stop();
        collision.animationTimer.stop();
    }

}
