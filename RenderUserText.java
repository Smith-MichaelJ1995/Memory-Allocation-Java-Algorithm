import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/* Gather input and create objects representing Memory and Processes*/
public class RenderUserText
{
    //object representations of input files
    private MemoryInputFile memIP;
    private ProcessInputFile proIP;


    //constructor for creating input objects
    public RenderUserText() throws FileNotFoundException
    {
        //instantiate the two file objects
        memIP = new MemoryInputFile();
        proIP = new ProcessInputFile();

        //read text from user, generate objects for each of the two "files"
        buildInputObjects();
    }

    //all text is rendered, both objects are built
    public void buildInputObjects() throws FileNotFoundException
    {
         //read the integers from the file
        ArrayList<Integer> inputIntegers = processInputFile();

        //Determine how many memory slot objects we'll have
        int memorySlotTotal = inputIntegers.get(0);

        //build FreeSlot object collection
        //start iterating from index '1' because index '0' contains the total amount of slots
        //looping through memorySlotTotal * 2 because each slot has two values: start + end
        //hence, we'll have to process memorySlotTotal * 2 amount of integers from this linear data structure
        int inputCounter = 1;
        while( inputCounter <= (memorySlotTotal * 2) )
        {
            //gather input values from the file and generate the free slot object
            memIP.addFreeSlotObject(new FreeSlot(inputIntegers.get( inputCounter++ ), inputIntegers.get( inputCounter++ )));
        }

        //disregard total processes number, we can loop to end of list
        inputCounter++;

        //build Process object collection, each process object contains two attributes: process # and process size
        while(inputCounter < inputIntegers.size())
        {
            proIP.addJob(new Job(inputIntegers.get( inputCounter++ ) , inputIntegers.get( inputCounter++ )));
        }

        //BOTH OBJECTS SUCCESSFULLY BUILT
    }

    //processes an input file and returns its contents
    private ArrayList<Integer> processInputFile() throws FileNotFoundException
    {

        //declare scanner object, render input file
        Scanner kb = new Scanner(new File("Minput.data"));

        //ArrayList<Integer> to read integers from user
        ArrayList<Integer> input = new ArrayList<Integer>();

        //process each integer
        while (kb.hasNextInt()) {
            input.add(kb.nextInt());
        }

        //closing the scanner
        kb.close();

        //return arraylist representation of input file data
        return input;
    }

    /*ERROR HANDLING CAPABILITIES Given two integer parameters, determine whether they are valid memory address pair
    public void validateInput(int startAddress, int endAddress)
    {
        // numbers must be: (start address < end address)
        if(startAddress >= endAddress)
        {
            System.out.println("ERROR, END ADDRESS MUST BE GREATER THAN START ADDRESS!");
            System.exit(1);
        }
        else
        {
            //check existing FreeSlot objects, confirm that this slot, and other existing slots don't have common integers in their ranges.
            for(int i = 0; i < memIP.copy().size(); i++)
            {
                FreeSlot slot = memIP.copy().get(i);

                //error handling the memory address input values
                if(endAddress >= slot.getStartAddress())
                {
                    // is 'startAddress' within the range of the slot values?
                    if(startAddress <= slot.getEndAddress())
                    {
                        System.out.println("ERROR, SLOTS OF MEMORY ARE CONGRUENT, ALL FREE SLOTS MUST BE DISJOINT SETS OF NON NEGATIVE INTEGERS");
                        System.exit(1);
                    }
                }
                //the two ranges are both sets of non-negative integers, the two ranges are disjoint.
            }

        }
    }

    /* GETTERS FOR INPUT FILES */
    public MemoryInputFile getMemoryInputFile()
    {
        return memIP;
    }

    public ProcessInputFile getProcessInputFile()
    {
        return proIP;
    }
}
