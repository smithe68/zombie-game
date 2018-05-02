package Inventory;
// ayy
import Engine.Mafs;
import Engine.SceneManager;
import Engine.UI;
import Engine.Visual;

import java.awt.*;

public class InventoryDisplay extends UI {
    private UI invetory;
    public void start()
    {
        visual.setTint(Color.white);
        visual.setLayer(10);
        visual.setRenderType(Visual.RenderType.Rectangle);
        invetory = (UI)SceneManager.createEntity(new UI());
        invetory.visual.setLayer(11);



    }

    public void update()
    {

        invetory.visual.setAnchor(Visual.Anchor.BOTTOM_RIGHT);
        //invetory.transform.setPos(transform.getX(), transform.getY());

        invetory.transform.setHeight(5);
        invetory.transform.setWidth(5);


    }
}

