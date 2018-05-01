package Entities;

import Engine.*;

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
        visual.setRenderType(Visual.RenderType.EllipseBorder);
        visual.setTint(Color.red);

        hero = (Hero)SceneManager.getEntity("Hero");
    }


    public void update()
    {
        randomInt = random.nextInt(10-1)+1;
        transform.setX(Mafs.lerp(transform.getX(), hero.transform.getX(), Updater.deltaTime * 0.000995f*randomInt));
        transform.setY(Mafs.lerp(transform.getY(), hero.transform.getY(), Updater.deltaTime * 0.000995f*randomInt));
    }
}
