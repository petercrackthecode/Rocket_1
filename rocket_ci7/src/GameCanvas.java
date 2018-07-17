import base.GameObject;
import base.GameObjectManager;
import game.background.Background;
import game.enemy.CreateEnemy;
import game.enemy.Enemy;
import game.star.CreateStar;
import game.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameCanvas extends JPanel {

    BufferedImage backBuffered;
    Graphics graphics;

    public Player player = new Player();

    public GameCanvas() {

        this.setSize(1024, 600);

        this.setupBackBuffered();

        this.setupCharacter();

        this.setVisible(true);
    }

    private void setupBackBuffered() {
        this.backBuffered = new BufferedImage(1024, 600, BufferedImage.TYPE_INT_RGB);
        this.graphics = this.backBuffered.getGraphics();
    }

    private void setupCharacter() {
        GameObjectManager.instance.add(new Background());
        CreateStar createStar = GameObjectManager.instance.recycle(CreateStar.class);
        createStar.configAction();
        CreateEnemy createEnemy = GameObjectManager.instance.recycle(CreateEnemy.class);
        createEnemy.configAction();
        this.setupPlayer();

    }

    private void setupPlayer() {
        Player player = new Player();
        player.position.set(400, 200);
        GameObjectManager.instance.add(player);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backBuffered, 0, 0, null);
    }

    public void renderAll() {
        GameObjectManager.instance.renderAll(this.graphics);
        this.repaint();
    }

    public void runAll() {
        GameObjectManager.instance.runAll();
    }

    private void runEnemy() {
//        base.Vector2D velocity = this.player.position
//                .subtract(this.enemy.position)
//                .normalize()
//                .multiply(1.5f);
//        this.enemy.velocity.set(velocity);
//        this.enemy.run();
    }
}
