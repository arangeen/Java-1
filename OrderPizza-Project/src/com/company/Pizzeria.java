package com.company;

public class Pizzeria {

    public final static String toppingsOffered[] = {"Onions" , "Bell Peppers" , "Pepperoni"};
    public final static double toppingsBaseCost = 1.25d;
    public final static double basePrice = 10.00d;

    private int size;
    private String toppings[];
    private int numToppings;

    public Pizzeria()
    {
        size = 0;
        toppings = new String[10];
        numToppings = 0;
    }

    public boolean setSize (int size)
    {
        this.size = size;
        return true;
    }

    public int getSize()
    {
        return size;
    }

    public boolean addTopping(String topping)
    {
        if (numToppings <10)
        {
            toppings[numToppings] = topping;
        }
        else
        {
            String toppings1[] = new String [toppings.length+1];
            for (int i = 0; i<toppings.length; i++)
            {
                toppings1[i] = toppings[i];
            }
            toppings = new String[toppings1.length];
            for (int i = 0; i<toppings.length; i++)
            {
                toppings[i] = toppings1[i];
            }
            toppings[toppings.length-1] = topping;
        }
        numToppings++;
        return true;
    }


    public boolean addTopping(int n)
    {
        if (n >= toppingsOffered.length)
        {
            return false;  //error message.
        }

        if (numToppings < 10)
        {
            toppings[numToppings] = toppingsOffered[n];
        }
        else
        {
            //destroy old array; and create new one with one more element and copy the data over
            String toppings1[] = new String [toppings.length+1];
            for (int i = 0 ; i < toppings.length; i++)
            {
                toppings1[i] = toppings[i];
            }
            toppings = new String [toppings1.length];
            for (int i = 0; i<toppings.length; i++)
            {
                toppings[i] = toppings1[i];
            }
            toppings[toppings.length-1] = toppingsOffered[n];

        }
        numToppings++;
        return true;
    }


    public double getPrice()
    {
        double price = 0.0;
        if (size == 0)
        {
            price = basePrice;
        }
        else if(size == 1)
        {
            price = basePrice + (double)25*basePrice/100.00;
        }
        else
        {
            price = basePrice + (double)25*basePrice/100.00;
        }
        price += (double)numToppings*toppingsBaseCost;
        return price;
    }


    public String stringizeSize()
    {
        switch (size)
        {
            case 0:
                return "small";
            case 1:
                return "medium";
            case 2:
                return "large";
            default:
                System.out.println("Invalid Size");
                return "";
        }
    }


    public String getToppings()
    {
        if (numToppings == 0)
            return "";
        String allToppings = "";
        for (int i = 0; i<numToppings-1; i++)
        {
            allToppings += toppings[i] + " + ";
        }
        allToppings += toppings[numToppings-1];
        return allToppings;
    }


    public void displayPizza()
    {
        System.out.print("\nCurrent pizza: " + stringizeSize());
        if (numToppings != 0)
            System.out.println(" + " + getToppings());
        else
            System.out.println();
    }


    public void resetTopping()
    {
        numToppings = 0;

        toppings = new String[10];
    }
}
