package world.ucode.model;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import world.ucode.view.GameOver;
import world.ucode.view.Menu;

import java.io.File;

public class GameLoop {

    private final Stage gameStage;
    private final AnchorPane gamePane;

    private Player player;
    private Ground ground;
    private Enemy enemy;
    private Score score;
    private Cloud cloud;
    private Collision collision;
    private GameOver gameOver;
    private Screamer screamer;

    private boolean alreadyStarted = false;

    private MediaPlayer gameSound;

    public GameLoop(Stage gameStage, AnchorPane gamePane) {
        this.gameStage = gameStage;
        this.gamePane = gamePane;
    }

    public void initGameLoop() {
        cloud = new Cloud(gamePane);
        cloud.initCloudsOnScreen();
        ground = new Ground(gamePane);
        ground.createGround();
        player = new Player(gamePane);
        player.createPlayer();
        enemy = new Enemy(gamePane);
        enemy.createEnemy();
        score = new Score(gamePane);
        score.setScoreLabel();
        collision = new Collision();
        screamer = new Screamer(gamePane);

        gameOver = new GameOver(gameStage, player, enemy,
                ground, score, collision, cloud, screamer);

        gameSound = new MediaPlayer(
                new Media(
                        new File("src/main/resources/game_sound.mp3").toURI().toString()));
        gameSound.setCycleCount(-1);
        gameSound.play();
        event();
    }

    public void event() {
        gameStage.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.W)
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
        gameStage.setOnCloseRequest(event -> stopGame());
    }

    private void startGame() {
        if (!alreadyStarted) {
            cloud.moveClouds();
            ground.moveGround();
            enemy.moveEnemy();
            score.startScore();
            screamer.initScreamer(enemy, score);
            alreadyStarted = true;
        }
        player.Jump();
        collision.checkCollision(player, enemy, gameOver, gameSound);
    }

    public void stopGame() {
        gameSound.stop();
        player.animationTimer.stop();
        ground.animationTimer.stop();
        enemy.animationTimer.stop();
        score.animationTimer.stop();
        collision.animationTimer.stop();
        cloud.animationTimer.stop();
        screamer.time.cancel();
    }

}
