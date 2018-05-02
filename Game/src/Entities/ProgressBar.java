package Entities;

import Engine.Utility.Mafs;
import Engine.SceneManager;
import Engine.UI;

import java.awt.*;

public class ProgressBar extends UI
{
    public float fillAmount = 1;
    public Color fillColor;

    private UI fill;

    public void start()
    {
        visual.setTint(Color.white);
        visual.setLayer(10);
        fill = (UI)SceneManager.createEntity(new UI());
        fill.visual.setLayer(11);
    }

    public void update()
    {
        fill.visual.setAnchor(visual.getAnchor());
        fill.transform.setPos(transform.getX(), transform.getY());
        fill.visual.setTint(fillColor);

        fill.transform.setHeight(transform.getHeight());
        fillAmount = Mafs.clamp(fillAmount, 0f, 1f);
        fill.transform.setWidth(transform.getWidth() * fillAmount);
    }
}
