package game.star;

import action.*;
import base.FrameCounter;
import base.GameObject;
import base.GameObjectManager;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class CreateStar extends GameObject {

    private FrameCounter frameCounter;
    private Random random;

    public CreateStar() {
        this.frameCounter = new FrameCounter(30);
        this.random = new Random();
    }
    
    public void configAction()	{
    	List<Star> stars= new ArrayList<>();
    	
    	Action createAction = new ActionAdapter()	{
    		@Override
    		public boolean run(GameObject owner)	{
    			Star star = GameObjectManager.instance.recycle(Star.class);
    			star.position.set(1024, random.nextInt(600));
    			star.velocity.set(-(random.nextInt(3) + 1), 0);
    			stars.add(star);
    			return true;
    		}
    	};
    	
    	Action waitAction = new ActionAdapter()	{
    		@Override
    		public boolean run(GameObject owner) {
    			stars.removeIf(star -> !star.isAlive);
    			return stars.isEmpty();
    		}
    	};
    	
    	this.addAction(
    				new SequenceAction(
    						new WaitAction(25),
    						new LimitAction(
    								4,
    								new SequenceAction(
    								waitAction,
    								createAction
    								)
    						)
    			)
    	);
    }

    /*@Override
    public void run() {
        super.run();
        if (this.frameCounter.run()) {
            Star star = new Star();
            star.position.set(1024, this.random.nextInt(600));
            star.velocity.set(-(this.random.nextInt(3) + 1), 0);
            GameObjectManager.instance.add(star);
            this.frameCounter.reset();
        }
    }*/
}
