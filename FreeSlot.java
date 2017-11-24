import java.util.ArrayList;

public class FreeSlot
{
    //variables representing an available address
    private int start;
    private int end;

    //tells program what index to begin entering data into the memory
    private int pointer;

    //lists contains ArrayList of Job objects;
    private ArrayList<Job> memory;

    public FreeSlot(int start, int end)
    {
        //assign start-times and end times
        this.start = start;
        this.end = end;

        //assign the starting address to the pointer
        pointer = start;

        //instantiate memory ArrayList
        memory = new ArrayList<Job>();
    }


    //PRINT FUNCTION FOR OUTPUT
    public String getAllocationData()
    {
        //records output for users
        String output = "";

        //used to dynamically track starting job address
        int processStart = start;

        for(Job job: memory)
        {
            //building output string dynamically
            output += processStart + " " + (processStart + job.getProcessSize()) + " " + job.getProcessNumber() + "\n";

            //increase processStart so its ready to access the next job
            processStart += job.getProcessSize();
        }
        return output;
    }

    //SETTER
    public void add(Job currentJob)
    {
        //add a job into this slot
        memory.add(currentJob);


        //increase value of pointer
        pointer += currentJob.getProcessSize();

    }

    //GETTERS
    public int getStartAddress()
    {
        return start;
    }
    public int getEndAddress()
    {
        return end;
    }
    public int getPointer()
    {
        return pointer;
    }
}
