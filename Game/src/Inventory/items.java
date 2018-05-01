package Inventory;

import java.awt.image.BufferedImage;

public class items {
    private String itemName;
    private String itemType;
    private int ID;
    private BufferedImage itemImage;

    public items(String itemName, int ID, String itemType, String itemImage){

    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public BufferedImage getItemImage() {
        return itemImage;
    }

    public void setItemImage(BufferedImage itemImage) {
        this.itemImage = itemImage;
    }
}
