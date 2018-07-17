package game.player;

import base.GameObject;
import base.Vector2D;
import base.GameObjectManager;
import game.enemy.BulletEnemy;
import game.enemy.Enemy;
import physic.BoxCollider;
import physic.PhysicBody;
import physic.RunHitObject;
import renderer.ImageRenderer;

public class BulletPlayer extends GameObject implements PhysicBody {

    public Vector2D velocity;
    public BoxCollider boxCollider;
    public RunHitObject runHitObject;

    public BulletPlayer() {
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("resources/images/circle.png", 8, 8);
        this.boxCollider= new BoxCollider(8, 8);
        this.runHitObject= new RunHitObject(
        		Enemy.class
        		);
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
        this.boxCollider.position.set(this.position.x - 4, this.position.y - 4);
        this.runHitObject.run(this);
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
