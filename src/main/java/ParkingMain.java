import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class ParkingMain
{
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Nee hoi manoi");
        //String testFile = new File("").getAbsolutePath() + "\\target\\TestCases\\" + getTestCase();
        String fName = getTestCase();

        String fs = File.separator; //This is required to make this work on both Windows and Linux. (Might even work on Mac, but I can't test that.)

        String testFile = new File("").getAbsolutePath() + fs + "TestCases" + fs + fName;

        System.out.println("This is testFile: " + testFile);
        System.out.println("Nee hoi manoi");

        Scanner scan;

        //When I ran this in IntelliJ, the string in testFile2 worked just fine, but when I ran it from cmd testFile2
        //did not work because it added an extra /target/ for some reason. So I added this try/catch bit that makes it
        //where it works for both... On Windows at least.
        try
        {
            scan = new Scanner(new File(testFile));
        }
        catch(FileNotFoundException e)
        {
            String testFile2 = new File("").getAbsolutePath() + fs + "target" + fs + "TestCases" + fs + fName;
            scan = new Scanner(new File(testFile2));
        }

        String str = new File("").getAbsolutePath() + fs + "target" + fs + "TestCases" + fs;
        System.out.println("File path: " + str);

        //boolean flag = false;

        String firstLine = scan.nextLine(); //Gets the first line of the file which contains the capacity.
        String array[] = firstLine.split(" ");

        String secondLine = scan.nextLine(); //Gets the first line of the file which contains the capacity.
        String array2[] = secondLine.split(" ");

        String thirdLine = scan.nextLine(); //Gets the first line of the file which contains the capacity.
        String array3[] = thirdLine.split(" ");

        System.out.println("First Line: " + firstLine);
        System.out.println("Second Line: " + secondLine);
        System.out.println("Third Line: " + thirdLine);

        int capacityA = Integer.parseInt(array[1]);
        int capacityB = Integer.parseInt(array2[1]);
        int capacityC = Integer.parseInt(array3[1]);

        ParkingLot ltA = new ParkingLot(capacityA, "A"); //Uses that capacity to instantiate a ParkingLot object.
        ParkingLot ltB = new ParkingLot(capacityB, "B"); //Uses that capacity to instantiate a ParkingLot object.
        ParkingLot ltC = new ParkingLot(capacityC, "C"); //Uses that capacity to instantiate a ParkingLot object.

        Groups A = new Groups("A", ltA);
        Groups B = new Groups("B", ltB);
        Groups C = new Groups("C", ltC);

        ArrayList<Car> carList = new ArrayList<Car>();

        int i = 0;

        while (scan.hasNextLine()) //Searches file for Enter/Exit commands
        {
            String line = scan.nextLine();

            String arr[] = line.split(" ");


            if(arr[0].contains("Enter"))
            {
                Car cr = new Car(i);
                System.out.println("This is i: " + i);
                i++;



                String groupChoice = cr.pickGroup(Groups.names);

                //String temp[] = Groups.names;
                ArrayList<String> temp = new ArrayList(Arrays.asList(Groups.names));

                enterLot(groupChoice, cr, temp, ltA, ltB, ltC);

                carList.add(cr);



                //lt.entrance(); //Car enters lot
            }
            else if(arr[0].contains("Exit"))
            {
                int num = Integer.parseInt(arr[1]);
               // lt.exitLot(spotNum); //Car exits lot
                System.out.println("This is num: " + num);
                int index = ParkingLot.searchCarList(carList, num);

                Car cr = carList.get(index);
                String grp = cr.getGroup();

                if(grp == "A")
                    ltA.exitLot2(cr);
                else if(grp == "B")
                    ltB.exitLot2(cr);
                else if(grp == "C")
                    ltC.exitLot2(cr);

                carList.remove(index);
            }

        }
       // lt.showLot(); //If you want to see what cars are in the lot at the end of the file, just uncomment this. false means spot is empty.

       // System.out.println("This parking lot's total earnings were: $" + lt.getEarnings());
        scan.close();

//        float A[] = {0.0f, 2.0f, 4.0f, 8.0f, 10.0f, 12.0f, 14.0f, 26.0f};
//        float B[] = {0.0f, 2.0f, 4.0f, 6.0f, 8.0f, 10.0f, 12.0f, 22.0f};
//
//        System.out.println("This: " + Arrays.compare(B,A));

        System.out.println("This is names: " + Arrays.toString(Groups.names));
    }

    //This method asks the user which test case they want to use and returns the file name.
    public static String getTestCase()
    {
        String fileName;

        Scanner scnr = new Scanner(System.in);

        System.out.println("Please enter which test case you would like to run. \nYour choices are: Test1.txt, Test2.txt" +
                ", Test3.txt, Test4.txt, Test5.txt, Test6.txt, Test7.txt, and Test01.txt");
        System.out.print("Enter your choice: ");
        fileName = scnr.nextLine();

        //List of possible file names
        String[] files = {"Test1.txt", "Test2.txt", "Test3.txt", "Test4.txt", "Test5.txt", "Test6.txt", "Test7.txt", "Test01.txt"};

        Arrays.sort(files);

        while(Arrays.binarySearch(files, fileName) < 0) //Input valdation: Checking for invalid test file name.
        {
            System.out.println("The file name you entered was invalid, please try again.");
            System.out.print("Enter your choice: ");
            fileName = scnr.nextLine();
        }

        scnr.close();

        return fileName;
    }

    public static void enterLot(String group, Car cr, ArrayList<String> al, ParkingLot a, ParkingLot b, ParkingLot c)
    {

        if(group == "A")
            if(a.isAvailable())
                a.entrance(cr);
            else
            {
                al.remove("A");
//                group = cr.pickGroup((String[]) al.toArray());
                group = cr.pickGroup(al.toArray(new String[0]));
                enterLot(group, cr, al, a, b, c);
            }
        else if(group == "B")
            if(b.isAvailable())
                b.entrance(cr);
            else
            {
                al.remove("B");
//                group = cr.pickGroup((String[]) al.toArray());
                group = cr.pickGroup(al.toArray(new String[0]));
                enterLot(group, cr, al, a, b, c);
            }
        else if(group == "C")
            if(c.isAvailable())
                c.entrance(cr);
            else
            {
                al.remove("C");
//                group = cr.pickGroup((String[]) al.toArray());
                group = cr.pickGroup(al.toArray(new String[0]));
                enterLot(group, cr, al, a, b, c);
            }
    }
}
