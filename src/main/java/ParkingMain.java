import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class ParkingMain
{
    public static void main(String[] args) throws FileNotFoundException {

        String fName = getTestCase();

        String fs = File.separator; //This is required to make this work on both Windows and Linux. (Might even work on Mac, but I can't test that.)

        String testFile = new File("").getAbsolutePath() + fs + "TestCases" + fs + fName;

        Scanner scan;

        //When I ran this in IntelliJ, the string in testFile2 worked just fine, but when I ran it from cmd testFile2
        //did not work because it added an extra /target/ for some reason. So I added this try/catch bit that makes it
        //where it works for both... On Windows at least. Should work on Linux too.
        try
        {
            scan = new Scanner(new File(testFile));
        }
        catch(FileNotFoundException e)
        {
            String testFile2 = new File("").getAbsolutePath() + fs + "target" + fs + "TestCases" + fs + fName;
            scan = new Scanner(new File(testFile2));
        }



        String firstLine = scan.nextLine(); //Gets the first line of the file which contains the capacityA.
        String array[] = firstLine.split(" ");

        String secondLine = scan.nextLine(); //Gets the second line of the file which contains the discounts
        String array2[] = secondLine.split(" ");

        String thirdLine = scan.nextLine(); //Gets the third line of the file which contains the discounts
        String array3[] = thirdLine.split("/");

        Groups.setDiscountA(Integer.parseInt(array2[1]));
        Groups.setDiscountB(Integer.parseInt(array2[2]));
        Groups.setDiscountC(Integer.parseInt(array2[3]));

        Groups.setPolicyA(array3[1]);
        Groups.setPolicyB(array3[2]);
        Groups.setPolicyC(array3[3]);

        System.out.println("This is Group A's policies: " + Groups.getPolicyA());
        System.out.println("This is Group B's policies: " + Groups.getPolicyB());
        System.out.println("This is Group C's policies: " + Groups.getPolicyC());


        int capacityA = Integer.parseInt(array[1]);
        int capacityB = Integer.parseInt(array[2]);
        int capacityC = Integer.parseInt(array[3]);


        ParkingLot ltA = new ParkingLot(capacityA, "A"); //Uses that capacity to instantiate a ParkingLot object.
        ParkingLot ltB = new ParkingLot(capacityB, "B"); //Uses that capacity to instantiate a ParkingLot object.
        ParkingLot ltC = new ParkingLot(capacityC, "C"); //Uses that capacity to instantiate a ParkingLot object.

        ArrayList<Car> carList = new ArrayList<Car>();

        int i = 0; //Car number counter

        while (scan.hasNextLine()) //Searches file for Enter/Exit commands
        {
            String line = scan.nextLine();

            String arr[] = line.split(" ");


            if(arr[0].contains("Enter"))
            {
                //Check to see if all lots are full.
                if(!ltA.isAvailable() && !ltB.isAvailable() && !ltC.isAvailable())
                {
                    System.out.println("***********All lots are full, please try again later.*************");
                    continue;
                }

                Car cr = new Car(i); //Initialize a car with the current car/ticket number.
                i++;

                String groupChoice = cr.pickGroup(Groups.names); //This car's initial group choice.

                ArrayList<String> temp = new ArrayList(Arrays.asList(Groups.names));

                enterLot(groupChoice, cr, temp, ltA, ltB, ltC); //Used to check if initial group choice is available and
                //picks a new one if it is not.

                carList.add(cr);

            }
            else if(arr[0].contains("Exit"))
            {
                int num = Integer.parseInt(arr[1]);

                int carNum = ParkingLot.searchCarList(carList, num);

                Car cr = carList.get(carNum);
                String grp = cr.getGroup();

                if(grp == "A")
                    ltA.exitLot(cr);
                else if(grp == "B")
                    ltB.exitLot(cr);
                else if(grp == "C")
                    ltC.exitLot(cr);

                carList.remove(carNum);
            }

        }
        //This part shows the earnings of each group and also how many cars are left in each lot.
        System.out.println("Group A's lot's earnings: $" + ltA.getEarnings() + ". Cars left in group A's lot: " + ltA.getNumCars());
        System.out.println("Group B's lot's earnings: $" + ltB.getEarnings() + ". Cars left in group B's lot: " + ltB.getNumCars());
        System.out.println("Group C's lot's earnings: $" + ltC.getEarnings() + ". Cars left in group C's lot: " + ltC.getNumCars());
    }

    //This method asks the user which test case they want to use and returns the file name.
    public static String getTestCase()
    {
        String fileName;

        Scanner scnr = new Scanner(System.in);

        System.out.println("Please enter which test case you would like to run. \nYour choices are: Test01.txt, Test02.txt," +
                " Test03.txt, Test04.txt, and Test05.txt");
        System.out.print("Enter your choice: ");
        fileName = scnr.nextLine();

        //List of possible file names
        String[] files = {"Test01.txt", "Test02.txt", "Test03.txt", "Test04.txt", "Test05.txt"};

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

    //This function will let the car enter the cheapest lot IF there is a spot available. If there is not one available
    //in that lot it will check the next cheapest and perform the same process on that one that it did on the first one.
    public static void enterLot(String group, Car cr, ArrayList<String> al, ParkingLot a, ParkingLot b, ParkingLot c)
    {
        if(group == "A")
            if(cr.checkAvailable(a))
                a.entrance(cr);
            else
            {
                al.remove("A");
                System.out.println("Group A's lot is full, picking next cheapest...");
                group = cr.pickGroup(al.toArray(new String[0]));
                enterLot(group, cr, al, a, b, c);
            }
        else if(group == "B")
            if(cr.checkAvailable(b))
                b.entrance(cr);
            else
            {
                al.remove("B");
                System.out.println("Group B's lot is full, picking next cheapest...");
                group = cr.pickGroup(al.toArray(new String[0]));
                enterLot(group, cr, al, a, b, c);
            }
        else if(group == "C")
            if(cr.checkAvailable(c))
                c.entrance(cr);
            else
            {
                al.remove("C");
                System.out.println("Group C's lot is full, picking next cheapest...");
                group = cr.pickGroup(al.toArray(new String[0]));
                enterLot(group, cr, al, a, b, c);
            }
    }
}
