public class Car
{
    private long timestamp;
    private int spotNumber;

    public Car(int sn, long ts)
    {
        spotNumber = sn;
        timestamp = ts;
    }

    //Returns the timestamp on the ticket for this car.
    public long getTimestamp()
    {
        return timestamp;
    }

    //Returns the spot number on the ticket for this car.
    public int getSpotNumber() { return spotNumber; }

}
