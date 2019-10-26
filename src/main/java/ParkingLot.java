import java.text.DecimalFormat;
import java.util.ArrayList;

public class ParkingLot
{
    private int capacity; //The capacity of the lot.
    private int numCars = 0; //The number of cars in the lot.
    private float earnings = 0; //The lot's total earnings.
    private String group; //The group the lot belongs to.
    private float[] prices; //The price list of the current lot.

    ParkingLot(int cap, String grp)
    {
        capacity = cap;
        group = grp;
        prices = Groups.getPricing(grp);

        if(cap <= 0)//Input validation: Checking to make sure the capacity is a valid number.
        {
            System.out.println("Invalid Capacity: Capacity must be more than or equal to 0." +
                    "\nThe Capacity Provided was " + cap);
            System.exit(0);
        }

    }

    //Not really necessary, but here just in case. Sets the value of earnings.
    public void setEarnings(float earn) { earnings = earn; }

    //Not really necessary, but here just in case. Returns the value of earnings.
    public String getEarnings()
    {
        DecimalFormat df = new DecimalFormat("###,###.##"); //It's probably not even going to be close to $999, but better safe than sorry.
        return df.format(earnings);
    }

    //Not really necessary, but here just in case. Sets the value of capacity.
    public void setCapacity(int c) { capacity = c; }

    //Not really necessary, but here just in case. Returns the value of capacity.
    public int getCapacity() { return capacity; }

    //Not really necessary, but here just in case. Sets the value of numCars.
    public void setNumCars(int nc) { numCars = nc; }

    //Not really necessary, but here just in case. Returns the value of numCars.
    public int getNumCars() { return numCars; }

    //This method is basically the entrance gate.
    public void entrance(Car c)
    {
        long enterTime = 0;

        if(capacity == numCars)
            System.out.println("Sorry, lot is full! Please try again later." + "\n[Time passes...]"); //Car has attempted to enter, but the lot
        //is full so they are turned away. :(
        else
        {
            enterTime = System.nanoTime();


            System.out.println("======================================================");
            System.out.println("Car has inquired the latest prices from all the" +
                    "\ngroups and found group " + group + " to be the cheapest.");
            System.out.println("Car is entering: Printing ticket...");
            System.out.println("Car has entered group " + group + "'s Parking lot.");
            System.out.println("======================================================");

            c.setTimestamp(enterTime);

            numCars++;
        }
    }



    //This method searches a list of cars/tickets in the lot to see which one has the matching car number.
    public static int searchCarList(ArrayList<Car> list, int carNum)
    {
        int index = -1;

        for(int i = 0; i < list.size(); i++)
        {
            Car cr = list.get(i);

            if(cr.getCarNum() == carNum)
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
            price = prices[0];
        }
        else if(stayTime > 0.5 && stayTime < 1)
        {
            price = prices[1];
        }
        else if(stayTime > 1 && stayTime < 2)
        {
            price = prices[2];
        }
        else if(stayTime > 2 && stayTime < 3)
        {
            price = prices[3];
        }
        else if(stayTime > 3 && stayTime < 4)
        {
            price = prices[4];
        }
        else if(stayTime > 4 && stayTime < 5)
        {
            price = prices[5];
        }
        else if(stayTime > 5 && stayTime < 24)
        {
            price = prices[6];
        }
        else if(stayTime > 24)
        {
            float temp = stayTime / 24;
            price = prices[7] * temp;
        }

        return price;
    }



    //This method is basically the exit gate. It receives the spot number of the car from its ticket, gets the price from
    //calcPrice, takes payment, and says that the car has exited the lot.
    public void exitLot(Car c)
    {
        if(numCars == 0)
        {
            System.out.println("=======================================================================");
            System.out.println("Invalid command: No cars can exit a parking lot if it is already empty");
            System.out.println("=======================================================================");
        }
        else
        {
            long exitTime = System.nanoTime();
            long stayTime = 0;

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

            System.out.println("Car is exiting group " + group + "'s lot.");
            System.out.println("Car has exited the lot.");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            numCars--;
        }
    }

    //This method just checks to see if a spot is available in the current lot.
    public boolean isAvailable()
    {
        boolean available = false;

        if(numCars < capacity)
            available = true;

        return available;
    }
}
