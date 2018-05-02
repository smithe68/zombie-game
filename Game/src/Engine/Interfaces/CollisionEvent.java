package Engine.Interfaces;

import Engine.Entity;

public interface CollisionEvent
{
    void Invoke(Entity other);
}
