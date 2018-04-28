package Engine;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity
{
    public float x;
    public float y;

    public int width = 32;
    public int height = 32;

    public RenderType renderType;
    public Color renderTint = Color.white;
    public BufferedImage renderImage;

    private int renderX;
    private int renderY;

    private Dimension screenResolution;

    public enum RenderType
    {
        None,
        Rectangle,
        Ellipse,
        Image
    }

    public Entity()
    {
        screenResolution = Renderer.getResolution();
        start();
    }

    public void start() {}
    public void update() {}

    public final void render(Graphics g)
    {
        renderX = (int)(x + (screenResolution.width * 0.5f) - (width * 0.5f));
        renderY = (int)(y + (screenResolution.height * 0.5f) - (height * 0.5f));

        g.setColor(renderTint);

        switch(renderType)
        {
            case Rectangle:
                g.fillRect(renderX, renderY, width, height);
                break;

            case Ellipse:
                g.fillOval(renderX, renderY, width, height);
                break;

            case Image:
                if(renderImage == null) { return; }
                g.drawImage(renderImage, renderX, renderY, width, height, null);
                break;
        }
    }
}
