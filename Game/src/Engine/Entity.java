package Engine;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
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


    public float rotation;

    public int layer;

    public boolean hasCollision;

    public Color renderTint = Color.white;
    public RenderType renderType = RenderType.None;
    public BufferedImage renderImage;

    private Dimension screenResolution;
    private AffineTransform transform;

    public enum RenderType
    {
        None,
        Rectangle,
        Ellipse,
        RectangleBorder,
        EllipseBorder,
        Image
    }

    public Entity()
    {
        transform = new AffineTransform();
        screenResolution = Renderer.getResolution();
        start();
    }

    public void start() {}
    public void fixedUpdate() {}
    public void update() {}

    final void render(Graphics2D g)
    {
        float renderX = (x + (screenResolution.width * 0.5f) - (width * 0.5f) - Camera.x);
        float renderY = (-y + (screenResolution.height * 0.5f) - (height * 0.5f) + Camera.y);

        g.setColor(renderTint);
        g.transform(transform);

        g.rotate(Math.toRadians(rotation),
                renderX + width * 0.5f, renderY + height * 0.5f);

        switch(renderType)
        {
            case Rectangle:
                g.fill(new Rectangle2D.Float(renderX, renderY, width, height));
                break;

            case Ellipse:
                g.fill(new Ellipse2D.Float(renderX, renderY, width, height));
                break;

            case RectangleBorder:
                g.draw(new Rectangle2D.Float(renderX, renderY, width, height));
                break;

            case EllipseBorder:
                g.draw(new Ellipse2D.Float(renderX, renderY, width, height));
                break;

            case Image:
                if(renderImage == null) { return; }
                g.drawImage(renderImage, (int)renderX, (int)renderY, width, height, null);
                break;
        }

        g.rotate(Math.toRadians(-rotation),
                renderX + width * 0.5f, renderY + height * 0.5f);
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
