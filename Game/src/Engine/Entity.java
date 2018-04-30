package Engine;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity implements Comparable<Entity>
{
    public String tag = getClass().getSimpleName();

    public float x;
    public float y;

    public float velX;
    public float velY;

    public int width = 32;
    public int height = 32;

    public int layer;

    public boolean hasCollision;

    public Color renderTint = Color.white;
    public RenderType renderType = RenderType.None;
    public BufferedImage renderImage;

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
    public void fixedUpdate() {}
    public void update() {}

    final void render(Graphics g)
    {
        int renderX = (int) (x + (screenResolution.width * 0.5f) - (width * 0.5f) - Camera.x);
        int renderY = (int) (-y + (screenResolution.height * 0.5f) - (height * 0.5f) + Camera.y);

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

    final void collision()
    {
        if(!hasCollision) { return; }

        for(int i = 0; i < SceneManager.entities.size(); i++)
        {
            Entity entity = SceneManager.entities.get(i);
            if(!entity.hasCollision | entity == this) { continue; }
            Rectangle rect = entity.getCollider();

            if(rect.contains(getCollider()))
            {
                if(entity.x > x && velX < 0) { velX = 0; }
                if(entity.x < x && velX > 0) { velX = 0; }
                if(entity.y > y && velY < 0) { velY = 0; }
                if(entity.y < y && velY > 0) { velY = 0; }
            }
        }
    }

    final void physics()
    {
        x += velX;
        y += velY;

        velX = 0;
        velY = 0;
    }

    private Rectangle getCollider() {
        return new Rectangle((int)x, (int)y, width, height);
    }

    @Override
    public int compareTo(Entity o) {
        return layer - o.layer;
    }
}
