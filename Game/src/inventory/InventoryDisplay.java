package inventory;
// ayy
import engine.components.Visual;

import engine.SceneManager;
import engine.UI;


import java.awt.*;

public class InventoryDisplay extends UI {
    private UI invetory;
    private Items currentitem;
    public void start()
    {
        visual.setTint(Color.white);
        visual.setLayer(10);
        visual.setRenderType(Visual.RenderType.Rectangle);
        invetory = (UI)SceneManager.createEntity(new UI());
        invetory.visual.setLayer(11);

        invetory.visual.setAnchor(Visual.Anchor.BOTTOM_RIGHT);

    }

    public void update()
    {
        invetory.visual.setAnchor(Visual.Anchor.BOTTOM_RIGHT);


        //invetory.transform.setPos(transform.getX(), transform.getY());

        invetory.transform.setHeight(50);
        invetory.transform.setWidth(50);


    }
}

