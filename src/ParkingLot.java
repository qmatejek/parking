import java.util.ArrayList;
import java.util.Arrays;

public class ParkingLot
{
    private int capacity;
    private int numCars = 0; //Starts at 1 so that cars that are added to carList are numbered 1, ..., n instead of 0, ..., n
    //numCars being initialized at one does not mean that the lot starts with 1 car already in it/

    private boolean spots[];

    private ArrayList<Car> carList = new ArrayList<Car>();

    ParkingLot(int cap)
    {
        capacity = cap;

        if(cap <= 0)
        {
            System.out.println("Invalid Capacity: Capacity provided was <= 0");
        }
        else {

            spots = new boolean[cap];

            for (int i = 0; i < cap; i++) {
                spots[i] = false;
            }

            System.out.println("In constructor: " + Arrays.toString(spots));
        }
    }

    public void setCapacity(int c)
    {
        capacity = c;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public void setNumCars(int nc)
    {
        numCars = nc;
    }

    public int getNumCars()
    {
        return numCars;
    }

    public void entrance()
    {
        long enterTime = 0;

        if(capacity == numCars)
            System.out.println("Car lot is full!");
        else
        {
            enterTime = System.nanoTime();

            for(int i = 0; i < capacity; i++)
            {
                if(spots[i] == false)
                {
                    //System.out.println("In enter, before: " + Arrays.toString(spots));
                    System.out.println("Car has parked in spot #: " + i);
                    carList.add(new Car(i, enterTime));
                    spots[i] = true;
                    break;
                    //System.out.println("In enter, after: " + Arrays.toString(spots));
                }
            }

//            carList.add(new Car(numCars, enterTime));

            numCars++;
        }
    }

    private int searchCarList(ArrayList<Car> list, int spotNum)
    {
        int index = -1;

        for(int i = 0; i < list.size(); i++)
        {
            Car cr = list.get(i);

            if(cr.getSpotNumber() == spotNum)
            {
                index = i;
            }
        }

        return index;
    }

    public void exitLot(int spotNum)
    {
        if(spotNum > (capacity-1))
        {
            System.out.println("===============================================================");
            System.out.println("Invalid ticket: spot number is invalid.");
            System.out.println("Spot number should be in range 0-" + (capacity-1) + ". Your spot number was: " + spotNum);
            System.out.println("===============================================================");
        }
        else {
            long exitTime = System.nanoTime();
            long stayTime = 0;

            int index = searchCarList(carList, spotNum);

            Car c = carList.get(index);

            stayTime = exitTime - c.getTimestamp();

            float stayTimeSeconds = (float)stayTime / 1000000000;
            float stayTimeMS = (float)stayTime / 1000000;

            //System.out.println("Car in spot #" + spotNum + "'s stay time in nanoseconds: " + stayTime);
            //System.out.println("Car in spot #" + spotNum + "'s stay time in seconds: " + stayTimeSeconds);
            System.out.println("Car in spot #" + spotNum + "'s stay time in milliseconds: " + stayTimeMS);
            System.out.println("Car that was in spot number " + spotNum + " has paid.");
            System.out.println("Car that was in spot number " + spotNum + " has exited the lot.");

            //System.out.println("In exit, before: " + Arrays.toString(spots));



            carList.remove(index);
            spots[spotNum] = false;
            numCars--;
            //System.out.println("In exit, after: " + Arrays.toString(spots));
        }
    }
}
