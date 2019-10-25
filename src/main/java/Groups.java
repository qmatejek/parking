import java.util.Arrays;

public class Groups
{
    private String groupName;
    private int discount;
    public static String[] names = {"A", "B", "C"};
    private ParkingLot gLot;

    //Arrays.binarySearch(files, fileName) < 0


    Groups(String gName, ParkingLot pl)
    {
        groupName = gName;
        gLot = pl;

        if(Arrays.binarySearch(names, gName) < 0)
        {
            System.out.println("ERROR: Invalid group. Available groups are: A, B, or C");
        }
    }

    public void setLot(ParkingLot lt) { gLot = lt; }

    public ParkingLot getLot(ParkingLot lt) { return gLot; }

    public int getDiscount() { return discount; }

    public void setDiscount(int dis) { discount = dis; }

    public static String[] getNames() { return names; }

    //This method returns the prices for all groups, so it is static so that it can be run without instantiating an object.
    public static float[] getPricing(String gName, int discount)
    {
        float price[] = new float[8];

        //Arrays.fill(price, 0.0f);

        float priceA[] = {0.0f, 2.0f, 4.0f, 8.0f, 10.0f, 12.0f, 14.0f, 26.0f};
        float priceB[] = {0.0f, 2.0f, 4.0f, 6.0f, 8.0f, 10.0f, 12.0f, 22.0f};
        float priceC[] = {0.0f, 2.0f, 4.0f, 10.0f, 14.0f, 16.0f, 18.0f, 30.0f};

        if(discount != 0)
        {
            for(int i = 0; i < 8; i++)
            {
                priceA[i] = priceA[i] * ((100 - discount) / 100);
                priceB[i] = priceB[i] * ((100 - discount) / 100);
                priceC[i] = priceC[i] * ((100 - discount) / 100);
            }
        }

        if(gName == "A")
            price = priceA;
        else if(gName == "B")
            price = priceB;
        else if(gName == "C")
            price = priceC;

        return price;
    }
}
