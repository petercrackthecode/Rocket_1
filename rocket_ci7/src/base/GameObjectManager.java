package base;

import game.enemy.Enemy;
import game.player.BulletPlayer;
import game.player.Player;
import physic.BoxCollider;
import physic.PhysicBody;

import java.awt.*;
import java.util.ArrayList;

public class GameObjectManager	{

    public static GameObjectManager instance = new GameObjectManager();

    private ArrayList<GameObject> list = new ArrayList<>();
    private ArrayList<GameObject> tempList = new ArrayList<>();

    private GameObjectManager() {
//        this.list = new ArrayList<>();
    }

    public void add(GameObject gameObject) {
        this.tempList.add(gameObject);
    }

    public void runAll() {
        this.list
                .stream()
                .filter(gameObject -> gameObject.isAlive)
                .forEach(gameObject -> gameObject.run());
        this.list.addAll(this.tempList);
        this.tempList.clear();
    }

    public void renderAll(Graphics graphics) {
        this.list
                .stream()
                .filter(gameObject -> gameObject.isAlive)
                .forEach(gameObject -> gameObject.render(graphics));
    }

    public Player findPlayer() {
        return (Player) this.list
                .stream()
                .filter(gameObject -> gameObject instanceof Player)
                .filter(gameObject -> gameObject.isAlive)
                .findFirst()
                .orElse(null);
    }
    
    public <T extends GameObject> T recycle(Class<T> cls)	{
    	T gameObject= (T) this.list
    			.stream()
    			.filter(object -> cls.isInstance(object))
    			.filter(object -> !object.isAlive)
    			.findFirst()
    			.orElse(null);
    	if (gameObject != null)	{
    		gameObject.isAlive= true;
    	} else	{
    		try	{
    			gameObject= cls.newInstance();
    			this.add(gameObject);
    		} catch (InstantiationException | IllegalAccessException e)	{
    			e.printStackTrace();
    			return null;
    		}
    	}
    	return gameObject;
    }
    
    public <T extends GameObject & PhysicBody> T checkCollision(BoxCollider boxCollider, Class<T> cls)	{
    	return (T) this.list
    			.stream()
    			.filter(gameObject -> gameObject.isAlive)
    			.filter(gameObject -> cls.isInstance(gameObject)).filter(gameObject -> {
    				BoxCollider other = ((T) gameObject).getBoxCollider();
    				return boxCollider.checkCollision(other);
    			})
    			.findFirst()
    			.orElse(null);
    }
    
}
