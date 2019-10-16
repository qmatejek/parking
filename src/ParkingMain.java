import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParkingMain
{
    public static void main(String[] args) throws FileNotFoundException {
//        ParkingLot lot = new ParkingLot(3);
//        lot.entrance();
//        lot.entrance();
//        lot.entrance();
//        //lot.entrance();
//        lot.exitLot(1);
//        lot.entrance();
//        lot.exitLot(3);
//        lot.exitLot(0);
//
//        System.out.println("===========================================");

        Scanner scan = new Scanner(new File("src/Test1.txt"));

        //int capacity = 0;
        boolean flag = false;

        String firstLine = scan.nextLine();
        System.out.println("This is the first line: " + firstLine);
        String array[] = firstLine.split(" ");
        int capacity = Integer.parseInt(array[1]);
        ParkingLot lt = new ParkingLot(capacity);

        while (scan.hasNextLine()) {
            String line = scan.nextLine();

            System.out.println("This is line: " + line);

            String arr[] = line.split(" ");

            //int capacity = 0;
            //ParkingLot lt;

//            if(arr[0].contains("Capacity"))
//            {
//                //System.out.println("This is inside the capacity branch");
//                capacity = Integer.parseInt(arr[1]);
//               // lt = new ParkingLot(capacity);
//               // System.out.println("This is inside the capacity branch. This is capacity: " + capacity);
//            }

            //System.out.println("This is outside the capacity branch. This is capacity: " + capacity);
           // lt = new ParkingLot(capacity);

            if(arr[0].contains("Enter"))
            {
                lt.entrance();
            }
            else if(arr[0].contains("Exit"))
            {
               // System.out.println("This is arr[1]: " + arr[1]);
                int spotNum = Integer.parseInt(arr[1]);
                lt.exitLot(spotNum);
            }

            //System.out.println(line);
        }
    }
}
