import java.util.ArrayList;
public class ProcessInputFile
{
    //array of objects representing jobs
    private ArrayList<Job> jobObjects;

    //initialize array which contains jobs
    public ProcessInputFile()
    {
        //initialize the arraylist
        jobObjects = new ArrayList<Job>();
    }

    //adding new elements to the array
    public void addJob(Job obj)
    {
        jobObjects.add(obj);
    }

    //GETTER, return collection of address slots
    public ArrayList<Job> getJobObjects()
    {
        return jobObjects;
    }

    //print contents of input file
    public String print()
    {
        String output = jobObjects.size() + "\n";

        //traverse the list, access all objects
        for(Job job : jobObjects)
        {
            output += job.getProcessNumber() + " " + job.getProcessSize() + "\n";
        }

        return output;
    }
}
