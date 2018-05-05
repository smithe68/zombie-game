package components;

import engine.Component;
import engine.Entity;
import engine.components.Transform;
import engine.utility.Vector;

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
    private static List<Physics> physicsObjs = new LinkedList<>();
    private Transform parentTransform;

    public Physics(Entity parent)
    {
        super(parent);
        physicsObjs.add(this);
        this.parentTransform = parent.transform;
    }

    @Override
    protected void update()
    {
        checkCollision();

        parentTransform.position.set(parentTransform.position.getX() + velocity.getX(),
                parentTransform.position.getY() + velocity.getY());

        velocity.set(0, 0);
    }

    private void checkCollision()
    {
        for(int i = 0; i < physicsObjs.size(); i++)
        {
            var phy = physicsObjs.get(i);
            if(phy == this) { continue; }
            var rect = phy.getCollider();
        }
    }

    private Rectangle2D.Float getCollider()
    {
        return new Rectangle2D.Float(parentTransform.position.getX(), parentTransform.position.getY(),
                parentTransform.getWidth(), parentTransform.getHeight());
    }
}
