import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ParkingMain
{
    public static void main(String[] args) throws FileNotFoundException {

        String testFile = "src/TestCases/" + getTestCase();
        Scanner scan = new Scanner(new File(testFile));

        boolean flag = false;

        String firstLine = scan.nextLine(); //Gets the first line of the file which contains the capacity.
        String array[] = firstLine.split(" ");

        int capacity = Integer.parseInt(array[1]);

        ParkingLot lt = new ParkingLot(capacity); //Uses that capacity to instantiate a ParkingLot object.

        while (scan.hasNextLine()) //Searches file for Enter/Exit commands
        {
            String line = scan.nextLine();

            String arr[] = line.split(" ");


            if(arr[0].contains("Enter"))
            {
                lt.entrance(); //Car enters lot
            }
            else if(arr[0].contains("Exit"))
            {
                int spotNum = Integer.parseInt(arr[1]);
                lt.exitLot(spotNum); //Car exits lot
            }

        }
        //lt.showLot(); //If you want to see what cars are in the lot at the end of the file, just uncomment this. false means spot is empty.

        System.out.println("This parking lot's total earnings were: $" + lt.getEarnings());
        scan.close();
    }

    //This method asks the user which test case they want to use and returns the file name.
    public static String getTestCase()
    {
        String fileName;

        Scanner scnr = new Scanner(System.in);

        System.out.println("Please enter which test case you would like to run. \nYour choices are: Test1.txt, Test2.txt" +
                ", Test3.txt, Test4.txt, Test5.txt, Test6.txt, and Test7.txt");
        System.out.print("Enter your choice: ");
        fileName = scnr.nextLine();

        //List of possible file names
        String[] files = {"Test1.txt", "Test2.txt", "Test3.txt", "Test4.txt", "Test5.txt", "Test6.txt", "Test7.txt"};

        while(Arrays.binarySearch(files, fileName) < 0) //Input valdation: Checking for invalid test file name.
        {
            System.out.println("The file name you entered was invalid, please try again.");
            System.out.print("Enter your choice: ");
            fileName = scnr.nextLine();
        }

        scnr.close();

        return fileName;
    }
}
