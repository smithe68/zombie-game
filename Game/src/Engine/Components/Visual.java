package Engine.Components;

import Engine.Camera;
import Engine.Interfaces.RenderEvent;
import Engine.Internal.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.*;

public final class Visual
{
    private Color tint = Color.white;
    private RenderType type = RenderType.None;
    private BufferedImage sprite;

    private Anchor anchor = Anchor.TOP_LEFT;

    private boolean isFilled = true;
    private boolean inScreenSpace = false;

    private Transform transform;
    private Dimension screenResolution;
    private AffineTransform affine;

    private float renderX;
    private float renderY;

    private float anchorOffsetX;
    private float anchorOffsetY;

    private int layer;

    private RenderEvent renderEvent;

    public Visual(Transform transform)
    {
        this.transform = transform;
        screenResolution = Renderer.getScaledResolution();
        affine = new AffineTransform();
    }

    public enum RenderType
    {
        None,
        Rectangle,
        Ellipse,
        Image
    }

    public enum Anchor
    {
        TOP_LEFT,
        TOP_RIGHT,
        CENTER,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }

    public void render(Graphics2D g)
    {
        calculateRenderPos();

        g.setColor(tint);
        g.transform(affine);

        setInternalRotation(g, transform.getRot());

        drawEntity(g);

        if(renderEvent != null) {
            renderEvent.Invoke(g);
        }

        setInternalRotation(g, -transform.getRot());
    }

    private void calculateRenderPos()
    {
        if(!inScreenSpace)
        {
            renderX = (transform.getX() + (screenResolution.width * 0.5f) -
                    (transform.getWidth() * 0.5f) - Camera.x);

            renderY = (-transform.getY() + (screenResolution.height * 0.5f) -
                    (transform.getHeight() * 0.5f) + Camera.y);
        }
        else
        {
            switch(anchor)
            {
                case TOP_LEFT:
                    anchorOffsetX = 0;
                    anchorOffsetY = 0;
                    break;

                case TOP_RIGHT:
                    anchorOffsetX = screenResolution.width - transform.getWidth();
                    anchorOffsetY = 0;
                    break;

                case CENTER:
                    anchorOffsetX = screenResolution.width / 2 - transform.getWidth() / 2;
                    anchorOffsetY = screenResolution.height / 2 - transform.getHeight() / 2;
                    break;

                case BOTTOM_LEFT:
                    anchorOffsetX = 0;
                    anchorOffsetY = screenResolution.height - transform.getHeight();
                    break;

                case BOTTOM_RIGHT:
                    anchorOffsetX = screenResolution.width - transform.getWidth();
                    anchorOffsetY = screenResolution.height - transform.getHeight();
                    break;
            }

            renderX = transform.getX() + anchorOffsetX;
            renderY = transform.getY() + anchorOffsetY;
        }
    }

    private void setInternalRotation(Graphics2D g, float rotation)
    {
        g.rotate(Math.toRadians(rotation),
                renderX + transform.getWidth() * 0.5f,
                renderY + transform.getHeight() * 0.5f);
    }

    private void drawEntity(Graphics2D g)
    {
        switch(type)
        {
            case Rectangle:
                var rect = new Rectangle2D.Float(renderX, renderY,
                        transform.getWidth(), transform.getHeight());
                if(isFilled) { g.fill(rect); } else { g.draw(rect); }
                break;

            case Ellipse:
                var ell = new Ellipse2D.Float(renderX, renderY,
                        transform.getWidth(), transform.getHeight());
                if(isFilled) { g.fill(ell); } else { g.draw(ell); }
                break;

            case Image:
                if(sprite == null) { return; }
                g.drawImage(sprite, (int) renderX, (int) renderY,
                        (int)transform.getWidth(), (int)transform.getHeight(), null);
                break;
        }
    }

    //<editor-fold desc="> Getters and Setters">

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

    public boolean getIsFilled() { return isFilled; }

    public void setIsFilled(boolean isFilled) { this.isFilled = isFilled; }

    public boolean getInScreenSpace() { return inScreenSpace; }

    public void setInScreenSpace(boolean inScreenSpace) {
        this.inScreenSpace = inScreenSpace;
    }

    public void setAnchor(Anchor anchor) { this.anchor = anchor; }

    public Anchor getAnchor() { return anchor; }

    public void setRenderEvent(RenderEvent renderEvent) {
        this.renderEvent = renderEvent;
    }

    //</editor-fold>
}
