import java.util.Arrays;

public class Groups
{
    private String groupName;
    private static int discountA, discountB, discountC;
    public static String[] names = {"A", "B", "C"};
    private static String policiesA, policiesB, policiesC;

    //These may not be necessary, but they get the discounts for each group
    public static int getDiscountA() { return discountA; }
    public static int getDiscountB() { return discountB; }
    public static int getDiscountC() { return discountC; }

    //These set the dicounts for each group.
    public static void setDiscountA(int disA) { discountA = disA; }
    public static void setDiscountB(int disB) { discountB = disB; }
    public static void setDiscountC(int disC) { discountC = disC; }

    //Returns an array of the names of each group.
    public static String[] getNames() { return names; }

    //The next 6 methods just set and return the policies for each group.
    public static void setPolicyA(String polA) { policiesA = polA;}
    public static String getPolicyA() { return policiesA; }

    public static void setPolicyB(String polB) { policiesB = polB;}
    public static String getPolicyB() { return policiesB; }

    public static void setPolicyC(String polC) { policiesC = polC;}
    public static String getPolicyC() { return policiesC; }

    //This method returns the prices for all groups, so it is static so that it can be run without instantiating an object.
    public static float[] getPricing(String gName)
    {
        float price[] = new float[8];

        //These are the default prices of each group. This is where each groups prices are set.
        float priceA[] = {0.0f, 2.0f, 4.0f, 8.0f, 10.0f, 12.0f, 14.0f, 26.0f};
        float priceB[] = {0.0f, 2.0f, 4.0f, 6.0f, 8.0f, 10.0f, 12.0f, 22.0f};
        float priceC[] = {0.0f, 2.0f, 4.0f, 10.0f, 14.0f, 16.0f, 18.0f, 30.0f};


        for(int i = 0; i < 8; i++)
        {
            //They are casted to float here, because discount represents a percentage, so I don't think a float is necessary
            //for that, but it has to be casted to float here to make sure the correct result is given
            priceA[i] = priceA[i] * ((100.0f - (float)discountA) / 100.0f);
            priceB[i] = priceB[i] * ((100.0f - (float)discountB) / 100.0f);
            priceC[i] = priceC[i] * ((100.0f - (float)discountC) / 100.0f);
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
