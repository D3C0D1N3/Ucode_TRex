package world.ucode.model;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;

public class Collision {

    private ImageView[] enemyPos;
    private double playerPos;

    public AnimationTimer animationTimer;

    public Collision() { }

    public void checkCollision(Player player, Enemy enemy) {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                playerPos = player.getPlayerYPos();
                enemyPos = enemy.getEnemy();

                for (ImageView i : enemyPos) {
                    if (i.getLayoutX() <= 85 && i.getLayoutX() >= 70 && playerPos >= 400) {
                         System.out.println("DEAD");
                    }
                }
            }
        };
        animationTimer.start();
    }
}
