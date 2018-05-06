package components;

import engine.Component;
import engine.Entity;
import engine.utility.Vector;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

/**
 * Acts on a Entity's Transform to imitate
 * 'Real World' Physics.
 * @author Jakub
 */
public final class Physics extends Component
{
    public Vector velocity = new Vector();
    public Vector colliderSize = new Vector(transform.getWidth(), transform.getHeight());
    private static List<Physics> physicsObjs = new LinkedList<>();

    public Physics(Entity parent)
    {
        super(parent);
        physicsObjs.add(this);
    }

    @Override
    protected void update(float delta)
    {
        checkCollision();

        transform.position.set(transform.position.getX() + velocity.getX(),
                transform.position.getY() + velocity.getY());

        velocity.set(0, 0);
    }

    @Override
    protected void render(Graphics2D g)
    {
        if(debugActive)
        {
            var renderPosition = EntityRenderer.getRenderedPosition(transform);

            Vector sizeFactor = new Vector(transform.getWidth() / colliderSize.getX(),
                    transform.getHeight() / colliderSize.getY());

            Vector offset = new Vector(colliderSize.getX() / sizeFactor.getX(),
                    colliderSize.getY() / sizeFactor.getY());

            if(offset.getX() == transform.getWidth()) { offset.setX(0); }
            if(offset.getY() == transform.getHeight()) { offset.setY(0); }

            // Visualize Collider
            g.setColor(Color.red);
            g.draw(new Rectangle2D.Float(renderPosition.getX() + offset.getX(), renderPosition.getY() + offset.getY(),
                    colliderSize.getX(), colliderSize.getY()));
        }
    }

    @Override
    protected void cleanup() {
        physicsObjs.remove(this);
    }

    private void checkCollision()
    {
        for(int i = 0; i < physicsObjs.size(); i++)
        {
            var phy = physicsObjs.get(i);
            if(phy == this) { continue; }
            var rect = phy.getCollider();

            if(getCollider().intersects(rect))
            {
                if(phy.transform.position.getX() > transform.position.getX() & phy.velocity.getX() < 0) {
                    velocity.setX(velocity.getX() + phy.velocity.getX());
                }

                if(phy.transform.position.getX() < transform.position.getX() & phy.velocity.getX() > 0) {
                    velocity.setX(velocity.getX() + phy.velocity.getX());
                }

                if(phy.transform.position.getY() < transform.position.getY() & phy.velocity.getY() > 0) {
                    velocity.setY(velocity.getY() + phy.velocity.getY());
                }

                if(phy.transform.position.getY() > transform.position.getY() & phy.velocity.getY() < 0) {
                    velocity.setY(velocity.getY() + phy.velocity.getY());
                }
            }
        }
    }

    private Rectangle2D.Float getCollider()
    {
        var renderPosition = EntityRenderer.getRenderedPosition(transform);

        Vector sizeFactor = new Vector(transform.getWidth() / colliderSize.getX(),
                transform.getHeight() / colliderSize.getY());

        Vector offset = new Vector(colliderSize.getX() / sizeFactor.getX(),
                colliderSize.getY() / sizeFactor.getY());

        if(offset.getX() == transform.getWidth()) { offset.setX(0); }
        if(offset.getY() == transform.getHeight()) { offset.setY(0); }

        return new Rectangle2D.Float(renderPosition.getX() + offset.getX(), renderPosition.getY() + offset.getY(),
                colliderSize.getX(), colliderSize.getY());
    }
}
