package game.enemy;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.player.Player;
import physic.BoxCollider;
import physic.PhysicBody;
import renderer.ImageRenderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Enemy extends GameObject implements PhysicBody {

    public Vector2D velocity;
    private List<BulletEnemy> bulletEnemies;
    public BoxCollider boxCollider;

    public Enemy() {
        this.velocity = new Vector2D();
        this.bulletEnemies = new ArrayList<>();
        this.renderer = new ImageRenderer("resources/images/circle.png", 20, 20);
        this.boxCollider = new BoxCollider(20, 20);
    }

    @Override
    public void run() {
        super.run();

//        if (this.count == 30) {
//            for (double angle = 0.0; angle < 360.0; angle += 360.0 / 10.0) {
//                game.enemy.BulletEnemy bulletEnemy = new game.enemy.BulletEnemy();
//                bulletEnemy.position.set(this.position);
//                bulletEnemy.velocity.set(
//                        (new base.Vector2D(3.0f, 0.0f)).rotate(angle)
//                );
//                this.bulletEnemies.add(bulletEnemy);
//            }
//            this.count = 0;
//        } else {
//            this.count += 1;
//        }
        Player player = GameObjectManager.instance.findPlayer();
        if (player != null) {
            Vector2D velocity = player.position
                    .subtract(this.position)
                    .normalize()
                    .multiply(1.5f);
            this.velocity.set(velocity);
        }
        this.position.addUp(this.velocity);
        this.boxCollider.position.set(this.position.x - 10, this.position.y - 10);
        this.bulletEnemies.forEach(bulletEnemy -> bulletEnemy.run());

    }

    @Override
    public void render(Graphics graphics) {
        super.render(graphics);
        this.bulletEnemies.forEach(bulletEnemy -> bulletEnemy.render(graphics));
    }
    
    @Override
    public void getHit(GameObject gameObject)	{
    	this.isAlive= false;
    }
    
    @Override
    public BoxCollider getBoxCollider()	{
    	return this.boxCollider;
    }
}
