package Entities;

import Engine.*;

import java.awt.event.KeyEvent;

public class Hero extends Entity
{
    private float moveSpeed = 0.1f;

    public void start()
    {
        renderImage = Renderer.getImage("Smiley.png");
        renderType = RenderType.Image;
        layer = 1;

        hasCollision = true;
    }

    public void update()
    {
        Camera.x = Mafs.lerp(Camera.x, x, Updater.deltaTime * 0.01f);
        Camera.y = Mafs.lerp(Camera.y, y, Updater.deltaTime * 0.01f);

        if(Input.getKey(KeyEvent.VK_D)) { velX = moveSpeed * Updater.deltaTime; }
        if(Input.getKey(KeyEvent.VK_A)) { velX = -moveSpeed * Updater.deltaTime; }
        if(Input.getKey(KeyEvent.VK_W)) { velY = moveSpeed * Updater.deltaTime; }
        if(Input.getKey(KeyEvent.VK_S)) { velY = -moveSpeed * Updater.deltaTime; }
    }
}
