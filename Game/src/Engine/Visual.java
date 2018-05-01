package Engine;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.*;

public final class Visual
{
    private Color tint = Color.white;
    private RenderType type = RenderType.None;
    private BufferedImage sprite;

    private Transform transform;
    private Dimension screenResolution;
    private AffineTransform affine;

    private int layer;

    public Visual(Transform transform)
    {
        this.transform = transform;
        screenResolution = Renderer.getResolution();
        affine = new AffineTransform();
    }

    public enum RenderType
    {
        None,
        Rectangle,
        Ellipse,
        RectangleBorder,
        EllipseBorder,
        Image
    }

    public void render(Graphics2D g)
    {
        float renderX = (transform.getX() + (screenResolution.width * 0.5f) -
                (transform.getWidth() * 0.5f) - Camera.x);

        float renderY = (-transform.getY() + (screenResolution.height * 0.5f) -
                (transform.getHeight() * 0.5f) + Camera.y);

        g.setColor(tint);
        g.transform(affine);

        g.rotate(Math.toRadians(transform.getRot()),
                renderX + transform.getWidth() * 0.5f, renderY + transform.getHeight() * 0.5f);

        switch(type)
        {
            case Rectangle:
                g.fill(new Rectangle2D.Float(renderX, renderY, transform.getWidth(), transform.getHeight()));
                break;

            case Ellipse:
                g.fill(new Ellipse2D.Float(renderX, renderY, transform.getWidth(), transform.getHeight()));
                break;

            case RectangleBorder:
                g.draw(new Rectangle2D.Float(renderX, renderY, transform.getWidth(), transform.getHeight()));
                break;

            case EllipseBorder:
                g.draw(new Ellipse2D.Float(renderX, renderY, transform.getWidth(), transform.getHeight()));
                break;

            case Image:
                if(sprite == null) { return; }
                g.drawImage(sprite, (int) renderX, (int) renderY, transform.getWidth(), transform.getHeight(), null);
                break;
        }

        g.rotate(Math.toRadians(-transform.getRot()),
                renderX + transform.getWidth() * 0.5f, renderY + transform.getHeight() * 0.5f);
    }

    public Color getTint() { return tint; }

    public void setTint(Color tint) {
        this.tint = tint;
    }

    public void setRenderType(RenderType type) {
        this.type = type;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public BufferedImage getSprite() { return sprite; }

    public void setLayer(int layer)
    {
        this.layer = layer;
    }

    public int getLayer() { return layer; }
}
