package components;

import engine.Component;
import engine.Entity;
import engine.components.Camera;
import engine.components.Transform;
import engine.enums.Anchor;
import engine.utility.Vector;

import java.awt.*;

public class EntityRenderer extends Component
{
    protected Vector renderPosition = new Vector();
    protected boolean inWorldSpace = true;
    protected Anchor screenAnchor = Anchor.TOP_LEFT;

    private Color tint = Color.white;

    public EntityRenderer(Entity parent) { super(parent); }

    @Override
    protected void update(float delta)
    {
        renderPosition = inWorldSpace ? getRenderedPosition(transform) :
                Vector.add(transform.position, offsetByAnchor());

    }

    @Override
    protected final void render(Graphics2D g)
    {
        setInternalRotation(g, transform.getRotation());
        g.setColor(tint);
        onRender(g);
        setInternalRotation(g, -transform.getRotation());
    }

    protected void onRender(Graphics2D g) { }

    private Vector offsetByAnchor()
    {
        Vector anchorOffset = new Vector();

        if(inWorldSpace) { return anchorOffset; }

        switch(screenAnchor)
        {
            case TOP_LEFT:
                anchorOffset.set(0, 0);
                break;

            case TOP_RIGHT:
                anchorOffset.set(renderResolution.width - transform.getWidth(), 0);
                break;

            case CENTER:
                anchorOffset.set((renderResolution.width / 2) - (transform.getWidth() / 2),
                        (renderResolution.height / 2) - (transform.getHeight() / 2));
                break;

            case BOTTOM_LEFT:
                anchorOffset.set(0, renderResolution.height - transform.getHeight());
                break;

            case BOTTOM_RIGHT:
                anchorOffset.set(renderResolution.width - transform.getWidth(),
                        renderResolution.height - transform.getHeight());
                break;
        }

        return anchorOffset;
    }

    private void setInternalRotation(Graphics2D g, float rotation)
    {
        g.rotate(Math.toRadians(rotation), renderPosition.getX() + transform.getWidth() / 2,
                renderPosition.getY() + transform.getHeight() / 2);
    }

    public void setTint(Color tint) { this.tint = tint; }
    public Color getTint() { return tint; }

    public void setInWorldSpace(boolean inWorldSpace) {
        this.inWorldSpace = inWorldSpace;
    }

    public void setScreenAnchor(Anchor anchor) {
        screenAnchor = anchor;
    }

    public Anchor getScreenAnchor() { return screenAnchor; }

    public static Vector getRenderedPosition(Transform t)
    {
        Vector renderPos = new Vector();
        renderPos.setX(t.position.getX() + (renderResolution.width / 2));
        renderPos.setY(-t.position.getY() + (renderResolution.height / 2));
        renderPos.sub(t.getWidth() / 2, t.getHeight() / 2);
        renderPos.add(-Camera.getPosition().getX(), Camera.getPosition().getY());
        return renderPos;
    }
}
