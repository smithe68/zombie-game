package Inventory;

public class ItemManager {
   private Items[] inventory =  new Items[25];
   private int numberOfEntries=0;


    public boolean add (Items newEntry){
        Items Items = newEntry;
        for(int i=0;i<inventory.length;i++){
            Items oldItem =  inventory[i];
            if (Items.getID()==(oldItem.getID())){
                return false;
            }
        }
        inventory[numberOfEntries] = newEntry;
        numberOfEntries++;
        return false;

    }

    public boolean remove(int ID){
        for(int i=0;i<inventory.length;i++){
            Items item =  inventory[i];
            if (ID==item.getID()){
                inventory[i]=null;
                numberOfEntries--;
                return true;
            }
        }
        return false;

    }

    public Items lookup(int ID){
        for(int i=0;i<inventory.length;i++) {
            if (ID == inventory[i].getID()){
                return inventory[i];
            }
        }
        return null;

    }
}
