package Entities;

import Engine.Entity;

import java.awt.*;

public class Bullet extends Entity{
    public void start()
    {
        renderType = RenderType.Rectangle;
        renderTint = Color.WHITE;

        width = 6;
        height = 6;
    }
}
