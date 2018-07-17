package game.player;

import base.GameObject;
import base.GameObjectAttributes;
import base.Vector2D;
import input.KeyboardInput;

import java.util.Random;

public class PlayerMove implements GameObjectAttributes<Player> {
    private Random random = new Random();

    public PlayerMove() {

    }

    @Override
    public void run(Player gameObject) {
        if (KeyboardInput.instance.isLeft) {
            gameObject.angle += 2.0;
        }
        if (KeyboardInput.instance.isRight) {
            gameObject.angle -= 2.0;
        }

        gameObject.velocity.set(
                (new Vector2D(3.5f, 0)).rotate(gameObject.angle)
        );

        gameObject.position.addUp(gameObject.velocity);
        this.backToScreen(gameObject);
    }

    private void backToScreen(GameObject gameObject) {
        if (gameObject.position.x < 0) gameObject.position.set(1024, this.random.nextInt(600));

        if (gameObject.position.x > 1024) gameObject.position.set(0, this.random.nextInt(600));

        if (gameObject.position.y < 0) gameObject.position.set(this.random.nextInt(1024), 600);

        if (gameObject.position.y > 600) gameObject.position.set(this.random.nextInt(1024), 0);
    }
}
