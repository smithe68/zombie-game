package Engine;

import java.awt.*;

public final class Physics
{
    private float velX;
    private float velY;

    private Transform transform;

    private boolean hasCollision;

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
            Rectangle rect = entity.physics.getCollider();

            if(rect.contains(getCollider()))
            {
                if(entity.transform.getX() > transform.getX() && velX < 0) { velX = 0; }
                if(entity.transform.getX() < transform.getX() && velX > 0) { velX = 0; }
                if(entity.transform.getY() > transform.getY() && velY < 0) { velY = 0; }
                if(entity.transform.getY() < transform.getY() && velY > 0) { velY = 0; }
            }
        }
    }

    private Rectangle getCollider()
    {
        return new Rectangle((int)transform.getX(), (int)transform.getY(),
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
}
