public class Job
{
    //only attributes for a process
   private int processNumber;
   private int processSize;

   //indicating whether or not the process was allocated
   private boolean allocated;


   public Job(int processNumber, int processSize)
   {
       this.processNumber = processNumber;
       this.processSize = processSize;

       allocated = false;
   }

   //specify if this process has been allocated to a memory slot
    public void setAllocationStatus(boolean status)
    {
        allocated = status;
    }


   //return the process number for this object
   public int getProcessNumber()
   {
       return processNumber;
   }

   //return the process size for this object
   public int getProcessSize()
   {
       return processSize;
   }

   //has this job been allocated?
   public boolean getAllocationStatus()
   {
       return allocated;
   }

}
