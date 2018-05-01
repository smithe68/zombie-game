package Inventory;

public class ItemManager {
   private items[] inventory =  new items[25];
   private int numberOfEntries=0;


    public boolean add (items newEntry){
        items items = newEntry;
        for(int i=0;i<inventory.length;i++){
            Inventory.items oldItem =  inventory[i];
            if (items.getID()==(oldItem.getID())){
                return false;
            }
        }
        inventory[numberOfEntries] = newEntry;
        numberOfEntries++;
        return false;

    }


}
