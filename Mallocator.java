import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/* @author Michael Joshua Smith */
public class Mallocator
{
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
    {
        //building files from user text
        RenderUserText files = new RenderUserText();

        System.out.println("\n" + "MEMORY INPUT FILE:");
        //print contents of Minput.data
        System.out.println(files.getMemoryInputFile().print());


        System.out.println("PROCESS INPUT FILE:");
        //print contents of Pinput.data
        System.out.println(files.getProcessInputFile().print());

        //create object that performs computations, pass in both input files as parameters
        ComputeOutput output = new ComputeOutput(files.getMemoryInputFile(), files.getProcessInputFile());

    }
}
