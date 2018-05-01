package Entities;

import Engine.Entity;
import Engine.Mafs;
import Engine.SceneManager;
import Engine.Updater;

import java.awt.*;
import java.util.Random;

/**
 * Created by evan on 4/29/2018.
 */
public class SmallFollowZombie extends Entity
{
    private Hero hero;

   private Random random = new Random();
    private int randomInt;

    public void start()
    {
        renderType = RenderType.EllipseBorder;
        renderTint = Color.red;

        hero = (Hero)SceneManager.getEntity("Hero");


    }


    public void update()
    {
        randomInt = random.nextInt(10-1)+1;
        x = Mafs.lerp(x, hero.x, Updater.deltaTime * 0.000995f*randomInt);
        y = Mafs.lerp(y, hero.y, Updater.deltaTime * 0.000995f*randomInt);
    }
}
