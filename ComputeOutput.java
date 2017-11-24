import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class ComputeOutput
{
    //object representation of the three output files: “FFoutput.data”, “BFoutput.data” , and “WFoutput.data
    private PrintWriter FFoutputData;
    private PrintWriter BFoutputData;
    private PrintWriter WFoutputData;


    //instantiate all output file objects
    public ComputeOutput(MemoryInputFile memIP, ProcessInputFile proIP) throws FileNotFoundException, UnsupportedEncodingException
    {
        //declare default file values
        FFoutputData =  new PrintWriter("FFoutputData.data", "UTF-8");
        BFoutputData =  new PrintWriter("BFoutputData.data", "UTF-8");
        WFoutputData =  new PrintWriter("WFoutputData.data", "UTF-8");

        //call the three functions in unison: FF, BF, WF
        firstFit(memIP.copy(), proIP.getJobObjects());

        bestFit(memIP.copy(), proIP.getJobObjects());

        worstFit(memIP.copy(), proIP.getJobObjects());

    }

    //executing first fit algo, write output contents to a 'FFoutputData'
    public void firstFit(ArrayList<FreeSlot> slotObjects, ArrayList<Job> jobObjects)
    {
        //contains the jobs #'s that haven't been allocated
        String allocation = "";

        //select the processes in numerical order1
        for(Job process: jobObjects)
        {
           //traverse the collection of memory slots
            for(FreeSlot slot: slotObjects)
            {
                //check if this process "fits" into the slot
                if(( process.getProcessSize() <= (slot.getEndAddress() - slot.getPointer()) ))
                {
                     //add process to the vacant memory slot
                     slot.add(process);

                     //the process has been allocated to the slot
                     process.setAllocationStatus(true);

                     //this process has been added to a slot, proceed to the next process in the list
                     break;
                }
            }

            //check if the process hasn't been added to any of the slots
            if(!process.getAllocationStatus())
            {
                allocation += "-" + process.getProcessNumber() + " ";
            }
        }

        //append a '-0' to allocation, if all other processes were successfully allocated
        if(allocation.compareTo("") == 0)
        {
            allocation += "-0";
        }

        //print output to user files
        printOutput(FFoutputData,"\"FFoutput.data\"",slotObjects,allocation);

    }

    //executing best fit algo, write output contents to 'BFoutputData'
    public void bestFit(ArrayList<FreeSlot> slotObjects, ArrayList<Job> jobObjects)
    {
        //text representing processes that can't be allocated
        String allocation = "";

        for(Job process: jobObjects)
        {
            //the index of best fit, the best fit so far
            int bestFitIndex = Integer.MAX_VALUE;
            int bestFitValue = Integer.MAX_VALUE;

            for(FreeSlot slot: slotObjects)
            {
                //if this process fits in this memory slot,
                if(process.getProcessSize() <= slot.getEndAddress() - slot.getPointer())
                {
                    //size of fit
                    int fit = (slot.getEndAddress() - slot.getPointer()) - process.getProcessSize();

                    //is this fit the best fit we've seen so far between this process and this slot?
                    if(fit < bestFitValue)
                    {
                        //record the index + value of best fit
                        bestFitIndex = slotObjects.indexOf(slot);
                        bestFitValue = fit;
                    }

                }
            }

            //check if this process has been allocated

            if(bestFitIndex == Integer.MAX_VALUE)
            {
                //there are no available slots for this process.
                allocation += "-" + process.getProcessNumber() + " ";
            }
            else  //we've found a suitable slot
            {
                process.setAllocationStatus(true);
                slotObjects.get(bestFitIndex).add(process);
            }
        }

        //append a '-0' to allocation, if all other processes were successfully allocated
        if(allocation.compareTo("") == 0)
        {
            allocation += "-0";
        }

        //print output to user files
        printOutput(BFoutputData,"\"BFoutput.data\"",slotObjects,allocation);

    }

    //executing worst fit algo, write output contents to 'WFoutputData'
    public void worstFit(ArrayList<FreeSlot> slotObjects, ArrayList<Job> jobObjects)
    {
        //text representing processes that can't be allocated
        String allocation = "";

        for(Job process: jobObjects)
        {
            //the index of worst fit, the worst fit so far
            int worstFitIndex = Integer.MIN_VALUE;
            int worstFitValue = Integer.MIN_VALUE;

            for(FreeSlot slot: slotObjects)
            {
                //if this process fits in this memory slot,
                if(process.getProcessSize() <= slot.getEndAddress() - slot.getPointer())
                {
                    //size of fit
                    int fit = (slot.getEndAddress() - slot.getPointer()) - process.getProcessSize();

                    //is this fit the best fit we've seen so far between this process and this slot?
                    if(fit > worstFitValue)
                    {
                        //record the index + value of best fit
                        worstFitIndex = slotObjects.indexOf(slot);
                        worstFitValue = fit;
                    }

                }
            }

            //check if this process has been allocated

            if(worstFitIndex == Integer.MIN_VALUE)
            {
                //there are no available slots for this process.
                allocation += "-" + process.getProcessNumber() + " ";
            }
            else  //we've found a suitable slot
            {
                process.setAllocationStatus(true);
                slotObjects.get(worstFitIndex).add(process);
            }
        }

        //append a '-0' to allocation, if all other processes were successfully allocated
        if(allocation.compareTo("") == 0)
        {
            allocation += "-0";
        }

        //print output to user files
        printOutput(WFoutputData,"\"WFoutput.data\"",slotObjects,allocation);
    }

    //Traverse each memory [] in every slot object, retrieve output and print it to the file
    private void printOutput(PrintWriter file, String fileName, ArrayList<FreeSlot> slotObjects, String jobsAllocated)
    {
        //display the file's name to both output mediums
        System.out.println("\n" + fileName);
        file.println(fileName);

        //traverse each slot, building output file for each iteration
        for(FreeSlot slot: slotObjects)
        {
             //get information from slot object
             String allocationData = slot.getAllocationData();

             //write output command line and output file concurrently
             System.out.print(allocationData);
             file.print(allocationData);
        }

        //append string containing concatenation status
        System.out.println(jobsAllocated);
        file.println(jobsAllocated);

        //create space for next file
        System.out.println("\n");

        //close file
        file.close();
    }
}
