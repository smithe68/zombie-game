package Entities;

import Engine.Entity;
import Engine.Input;
import Engine.Updater;

import java.awt.event.KeyEvent;

public class Hero extends Entity
{
    public void start()
    {
        renderType = RenderType.Rectangle;
    }

    public void update()
    {
        if(Input.getKey(KeyEvent.VK_D)) {
            x += 0.5f * Updater.deltaTime;
        }

        if(Input.getKey(KeyEvent.VK_A)) {
            x -= 0.5f * Updater.deltaTime;
        }

        if(Input.getKey(KeyEvent.VK_W)) {
            y += 0.5f * Updater.deltaTime;
        }

        if(Input.getKey(KeyEvent.VK_S)) {
            y -= 0.5f * Updater.deltaTime;
        }
    }
}
