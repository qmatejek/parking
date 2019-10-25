import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class ParkingLot
{
    private int capacity;
    private int numCars = 0; //The number of cars in the lot.
    private float earnings = 0;
    private String group;

    private boolean spots[]; //This array simulates the spots in the parking lot. true = spot taken, false = spot empty

    private ArrayList<Car> carList = new ArrayList<Car>(); //This holds the information for each car. Specifically its spot # and its timestamp.

    ParkingLot(int cap, String grp)
    {
        capacity = cap;
        group = grp;

        if(cap <= 0)//Input validation: Checking to make sure the capacity is a valid number.
        {
            System.out.println("Invalid Capacity: Capacity must be more than or equal to 0." +
                    "\nThe Capacity Provided was " + cap);
            System.exit(0);
        }
        else {

            spots = new boolean[cap]; //Initiating the parking lot with all empty spots.

            for (int i = 0; i < cap; i++) {
                spots[i] = false;
            }

        }
    }

    //Not really necessary, but here just in case. Sets the value of earnings.
    public void setEarnings(float earn)
    {
        earnings = earn;
    }

    //Not really necessary, but here just in case. Returns the value of earnings.
    public String getEarnings()
    {
        DecimalFormat df = new DecimalFormat("###,###.##"); //It's probably not even going to be close to $999, but better safe than sorry.
        return df.format(earnings);
    }

    //Not really necessary, but here just in case. Sets the value of capacity.
    public void setCapacity(int c)
    {
        capacity = c;
    }

    //Not really necessary, but here just in case. Returns the value of capacity.
    public int getCapacity()
    {
        return capacity;
    }

    //Not really necessary, but here just in case. Sets the value of numCars.
    public void setNumCars(int nc)
    {
        numCars = nc;
    }

    //Not really necessary, but here just in case. Returns the value of numCars.
    public int getNumCars()
    {
        return numCars;
    }

    //This method is basically the entrance gate. When a car enters it's ticket is added to carList.
    public void entrance(Car c)
    {
        long enterTime = 0;

        if(capacity == numCars)
            System.out.println("Sorry, lot is full! Please try again later." + "\n[Time passes...]"); //Car has attempted to enter, but the lot
        //is full so they are turned away. :(
        else
        {
            enterTime = System.nanoTime();

            for(int i = 0; i < capacity; i++)
            {
                if(spots[i] == false)
                {
                    System.out.println("Car is entering: Printing ticket...");
                    System.out.println("Car is entering group " + group + "'s Parking lot.");
                    carList.add(new Car(i, enterTime));
                    c.setTimestamp(enterTime);
                    spots[i] = true;
                    break;
                }
            }

            numCars++;
        }
    }



    //This method searches the list of cars/tickets in the lot to see which one has the matching spot number.
    public static int searchCarList(ArrayList<Car> list, int spotNum)
    {
        int index = -1;

        for(int i = 0; i < list.size(); i++)
        {
            Car cr = list.get(i);

//            if(cr.getSpotNumber() == spotNum)
//            {
//                index = i;
//            }

            if(cr.getCarNum() == spotNum)
            {
                index = i;
            }
        }

        return index;
    }

    //This method calculates the price of a cars stay based on how long they were there
    private float calcPrice(float stayTime)
    {
        float price = 0;

        if(stayTime < 0.5)
        {
            price = 0;
        }
        else if(stayTime > 0.5 && stayTime < 1)
        {
            price = 2;
        }
        else if(stayTime > 1 && stayTime < 2)
        {
            price = 4;
        }
        else if(stayTime > 2 && stayTime < 3)
        {
            price = 8;
        }
        else if(stayTime > 3 && stayTime < 4)
        {
            price = 10;
        }
        else if(stayTime > 4 && stayTime < 5)
        {
            price = 12;
        }
        else if(stayTime > 5 && stayTime < 24)
        {
            price = 14;
        }
        else if(stayTime > 24)
        {
            float temp = stayTime / 24;
            price = temp * 26;
        }

        return price;
    }


    //This method checks to see if the parking lot is empty.
    private boolean isEmpty()
    {
        boolean empty = true;

        for(int i = 0; i < spots.length; i++)
        {
            if(spots[i] == true)
                empty = false;
        }

        return empty;
    }

    //This method is basically the exit gate. It receives the spot number of the car from its ticket, gets the price from
    //calcPrice, takes payment, and says that the car has exited the lot.
    public void exitLot(int spotNum)
    {
        if(spotNum > (capacity-1) || spotNum < 0)
        {
            System.out.println("===============================================================");
            System.out.println("Invalid ticket: spot number is invalid.");
            System.out.println("Spot number should be in range 0-" + (capacity-1) + ". Your spot number was: " + spotNum);
            System.out.println("===============================================================");
        }
        else if(isEmpty())
        {
            System.out.println("=======================================================================");
            System.out.println("Invalid command: No cars can exit a parking lot if it is already empty");
            System.out.println("=======================================================================");
        }
        else {
            long exitTime = System.nanoTime();
            long stayTime = 0;

            int index = searchCarList(carList, spotNum); //Finds out which car had that spot number.

            Car c = carList.get(index);

            stayTime = exitTime - c.getTimestamp();

            float stayTimeMS = (float)stayTime / 1000000;

            float price = (float) (Math.round(calcPrice(stayTimeMS) * 100.0) / 100.0);

            DecimalFormat df = new DecimalFormat("###,###.##"); //It's probably not even going to be close to $999, but better safe than sorry.

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Car is exiting: Please insert ticket...");
            System.out.println("Car that was in spot #" + spotNum + " stayed for " + stayTimeMS + " hours.");
            System.out.println("Car that was in spot #" + spotNum + " owes:  $" + df.format(price));
            System.out.println("Car that was in spot number " + spotNum + " has paid.");

            earnings += price;

            System.out.println("Car that was in spot number " + spotNum + " has exited the lot.");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


            //Remove car from the lot.
            carList.remove(index);
            spots[spotNum] = false;
            numCars--;
        }
    }

    public void exitLot2(Car c)
    {
        if(isEmpty())
        {
            System.out.println("=======================================================================");
            System.out.println("Invalid command: No cars can exit a parking lot if it is already empty");
            System.out.println("=======================================================================");
        }
        else
        {
            long exitTime = System.nanoTime();
            long stayTime = 0;

            //Car c = list.get(index);

            stayTime = exitTime - c.getTimestamp();

            float stayTimeMS = (float)stayTime / 1000000;

            float price = (float) (Math.round(calcPrice(stayTimeMS) * 100.0) / 100.0);

            DecimalFormat df = new DecimalFormat("###,###.##"); //It's probably not even going to be close to $999, but better safe than sorry.

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Car is exiting: Please insert ticket...");
            System.out.println("Car stayed for " + stayTimeMS + " hours.");
            System.out.println("Car owes:  $" + df.format(price));
            System.out.println("Car has paid.");

            earnings += price;

            System.out.println("Car is exiting group " + c.getGroup() + "'s lot.");
            System.out.println("Car has exited the lot.");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            //list.remove(index);
            numCars--;
        }
    }

    //This method just outputs the current state of the lot. Shows which spots are taken (true) and which are empty (false).
    public void showLot()
    {
        System.out.println(Arrays.toString(spots));
    }

    //This method just checks to see if a spot is available in the current lot.
    public boolean isAvailable()
    {
        boolean available = false;

        for(int i = 0; i < spots.length; i++)
        {
            if(spots[i] == false)
            {
                available = true; //A spot is available
                break; //No point in checking other spots, only needed to know there was one available spot.
            }
        }

        return available;
    }
}
