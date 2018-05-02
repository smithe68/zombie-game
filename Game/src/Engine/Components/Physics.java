package Engine.Components;

import Engine.Entity;
import Engine.Interfaces.CollisionEvent;
import Engine.SceneManager;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public final class Physics
{
    private float velX;
    private float velY;

    private Transform transform;

    private boolean hasCollision;

    private CollisionEvent collisionEvent;

    public Physics(Transform transform) {
        this.transform = transform;
    }

    public void update()
    {
        checkCollision();

        transform.setPos(transform.getX() + velX,
                transform.getY() + velY);

        velX = 0;
        velY = 0;
    }

    private void checkCollision()
    {
        if(!hasCollision) { return; }

        for(int i = 0; i < SceneManager.entities.size(); i++)
        {
            Entity entity = SceneManager.entities.get(i);
            if(!entity.physics.hasCollision | entity.physics == this) { continue; }
            var rect = entity.physics.getCollider();

            if(getCollider().contains(rect)) {
                if(collisionEvent != null) { collisionEvent.Invoke(entity); }
            }
        }
    }

    public Rectangle2D.Float getCollider()
    {
        return new Rectangle.Float(transform.getX(), transform.getY(),
                transform.getWidth(), transform.getHeight());
    }

    public void setVelocity(float velX, float velY)
    {
        this.velX = velX;
        this.velY = velY;
    }

    public void setVelX(float velX) { this.velX = velX; }
    public void setVelY(float velY) { this.velY = velY; }

    public float getVelX() { return velX; }
    public float getVelY() { return velY; }

    public void setHasCollision(boolean hasCollision) {
        this.hasCollision = hasCollision;
    }

    public void setCollisionEvent(CollisionEvent e) {
        collisionEvent = e;
    }
}
