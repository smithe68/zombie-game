package Entities;

import Engine.*;

import java.awt.event.KeyEvent;

public class Hero extends Entity
{
    private int health = 100;
    private float moveSpeed = 1f;

    public void start()
    {
        visual.setRenderType(Visual.RenderType.Image);
        visual.setSprite(Renderer.getImage("Smiley.png"));
        visual.setLayer(-1);

        physics.setHasCollision(true);
    }

    public void update()
    {
        Camera.x = Mafs.lerp(Camera.x, transform.getX(), Updater.deltaTime);
        Camera.y = Mafs.lerp(Camera.y, transform.getY(), Updater.deltaTime);

        if(Input.getKey(KeyEvent.VK_D)) { physics.setVelX(moveSpeed * Updater.deltaTime); }
        if(Input.getKey(KeyEvent.VK_A)) { physics.setVelX(-moveSpeed * Updater.deltaTime); }
        if(Input.getKey(KeyEvent.VK_W)) { physics.setVelY(moveSpeed * Updater.deltaTime); }
        if(Input.getKey(KeyEvent.VK_S)) { physics.setVelY(-moveSpeed * Updater.deltaTime); }

        if(Input.getKeyDown(KeyEvent.VK_SPACE)) {
            SceneManager.createEntity(new Bullet());
        }
    }
}
