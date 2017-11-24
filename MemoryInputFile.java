import java.util.ArrayList;
public class MemoryInputFile
{
    //array of objects representing addresses
    private ArrayList<FreeSlot> slotObjects;

    //initialize array which contains addresses
    public MemoryInputFile()
    {
        //initialize the arraylist
        slotObjects = new ArrayList<FreeSlot>();
    }

    //adding new elements to the array
    public void addFreeSlotObject(FreeSlot obj)
    {
        slotObjects.add(obj);
    }



    //print contents of input file
    public String print()
    {
        String output = slotObjects.size() + "\n";

        //traverse the list, access all objects
        for(FreeSlot slot : slotObjects)
        {
             output += slot.getStartAddress() + " " + slot.getEndAddress() + "\n";
        }

        return output;
    }

    //returns a copy of the list of slot objects, preserves the original list copy
    public ArrayList<FreeSlot> copy()
    {
        //instantiate new object
        ArrayList<FreeSlot> newList = new ArrayList<>();

        //perform deep copy for each FreeSlot object, add it to an arrayList
        for(int index = 0; index < slotObjects.size(); index++)
        {
            //copy over all values from each FreeSlot object
            FreeSlot memGap = new FreeSlot(slotObjects.get(index).getStartAddress(),slotObjects.get(index).getEndAddress());

            newList.add(memGap);
        }

        //we've successfully created a new list, return it.
        return newList;
    }
}
