package engine;

import java.awt.*;
import java.util.Collections;

public class SceneManager
{
    private static Scene currentScene = new Scene();

    public static void update(float delta) {
        currentScene.update(delta);
    }

    public static void render(Graphics2D g) {
        currentScene.render(g);
    }

    static Scene getCurrentScene() {
        return currentScene;
    }

    public static void changeScene(Scene scene) {
        currentScene = scene;
    }

    public static Entity createEntity(String name)
    {
        var entity = new Entity(name);
        currentScene.entities.add(entity);
        System.out.println("Spawned: " + entity.name);
        Collections.sort(currentScene.entities);
        return entity;
    }

    public static Entity getEntity(String name)
    {
        for(int i = 0; i < currentScene.entities.size(); i++)
        {
            if(currentScene.entities.get(i).name.equals(name)) {
                return currentScene.entities.get(i);
            }
        }

        System.err.println("Entity [" + name + "] not Found!");

        return null;
    }

    public static void destroyEntity(Entity e)
    {
        if(e == null) { return; }
        e.cleanup();
        currentScene.entities.remove(e);
    }
}
