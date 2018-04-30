package Entities;

import Engine.*;

import java.awt.event.KeyEvent;

public class Hero extends Entity
{
    public void start()
    {
        renderImage = Renderer.getImage("Smiley.png");
        renderType = RenderType.Image;
        layer = 1;
    }

    public void update()
    {
        Camera.x = Mafs.lerp(Camera.x, x, Updater.deltaTime * 0.01f);
        Camera.y = Mafs.lerp(Camera.y, y, Updater.deltaTime * 0.01f);

        if(Input.getKey(KeyEvent.VK_D)) { x += 0.5f * Updater.deltaTime; }
        if(Input.getKey(KeyEvent.VK_A)) { x -= 0.5f * Updater.deltaTime; }
        if(Input.getKey(KeyEvent.VK_W)) { y += 0.5f * Updater.deltaTime; }
        if(Input.getKey(KeyEvent.VK_S)) { y -= 0.5f * Updater.deltaTime; }
    }
}
