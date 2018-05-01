package Entities;

import Engine.*;

import java.awt.event.KeyEvent;

public class Hero extends Entity
{
   private static int health = 100;
    private float moveSpeed = 1f;

    public void start()
    {
        renderImage = Renderer.getImage("Smiley.png");
        renderType = RenderType.Image;
        layer = 1;

        hasCollision = true;
    }

    public void update()
    {
        Camera.x = Mafs.lerp(Camera.x, x, Updater.deltaTime);
        Camera.y = Mafs.lerp(Camera.y, y, Updater.deltaTime);

        if(Input.getKey(KeyEvent.VK_D)) { velX = moveSpeed * Updater.deltaTime; }
        if(Input.getKey(KeyEvent.VK_A)) { velX = -moveSpeed * Updater.deltaTime; }
        if(Input.getKey(KeyEvent.VK_W)) { velY = moveSpeed * Updater.deltaTime; }
        if(Input.getKey(KeyEvent.VK_S)) { velY = -moveSpeed * Updater.deltaTime; }

        rotation += Updater.deltaTime;
        if(rotation > 360) { rotation = 0; }

        if(Input.getKeyDown(KeyEvent.VK_SPACE)) {
            SceneManager.createEntity(new Bullet());
        }
    }
   public static int getHeroHealth(){
        return health;
    }
}
