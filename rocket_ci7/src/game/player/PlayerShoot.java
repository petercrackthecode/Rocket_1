package game.player;

import base.FrameCounter;
import base.GameObjectAttributes;
import base.GameObjectManager;
import input.KeyboardInput;

public class PlayerShoot implements GameObjectAttributes<Player> {

    private FrameCounter frameCounter;

    public ShootState shootState= ShootState.SINGLE;
    
    public PlayerShoot() {
        this.frameCounter = new FrameCounter(30);
    }

    @Override
    public void run(Player gameObject) {
        if (this.frameCounter.run()) {
            BulletPlayer bulletPlayer = GameObjectManager.instance.recycle(BulletPlayer.class);
            bulletPlayer.position.set(gameObject.position);
            bulletPlayer.velocity.set(gameObject.velocity.copy().multiply(1.5f));
            this.frameCounter.reset();
        }
    }
}
