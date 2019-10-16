public class Car
{
    private long timestamp;
    private int spotNumber;

    public Car(int sn, long ts)
    {
        spotNumber = sn;
        timestamp = ts;
    }

//    public void setTimestamp(long ts)
//    {
//        timestamp = ts;
//    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public int getSpotNumber()
    {
        return spotNumber;
    }
}
