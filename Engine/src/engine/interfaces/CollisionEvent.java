package engine.interfaces;

import engine.Entity;

public interface CollisionEvent
{
    void Invoke(Entity other);
}
