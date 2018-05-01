package Entities;

import Engine.Entity;
import Engine.Visual;

import java.awt.*;

public class Tile extends Entity
{
    public void start()
    {
        visual.setLayer(-1);
        visual.setRenderType(Visual.RenderType.Rectangle);
        visual.setTint(Color.GRAY);

        transform.setSize(128, 128);
    }
}
