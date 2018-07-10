package com.company;
import java.util.Scanner;

public class SlotMachine {

    public static int getBet()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("How much would you like to bet ( 1 - 100 or 0 to quit)?");
        return input.nextInt();
    }

    public static TripleString pull()
    {
        String S1 = randString();
        String S2 = randString();
        String S3 = randString();

        TripleString TS = new TripleString();
        TS.setString1(S1);
        TS.setString2(S2);
        TS.setString3(S3);
        return TS;
    }

    public static String randString()
    {
        double randomNumber = Math.random();
        if (randomNumber <= .125)
        {
            return "7";
        }
        else if (randomNumber <= .250)
        {
            return " (Space) ";
        }
        else if (randomNumber <= .500)
        {
            return " cherries ";
        }
        else if (randomNumber <= 1)
        {
            return " BAR ";
        }
        return "";  // we will never end up getting here

    }


    public static int getPayMultiplier (TripleString Pull)
    {
        if (Pull.gettingString1().compareTo(" cherries ") == 0 && Pull.gettingString2().compareTo(" cherries ") != 0)
        {
            return 5;
        }
        else if (Pull.gettingString1().compareTo(" cherries ") == 0 && Pull.gettingString2().compareTo(" cherries ") == 0
                && Pull.gettingString3().compareTo(" cherries ") != 0)
        {
            return 15;
        }
        else if (Pull.gettingString1().compareTo(" cherries ") == 0 && Pull.gettingString2().compareTo(" cherries ") == 0
                && Pull.gettingString3().compareTo(" cherries ") == 0)
        {
            return 30;
        }
        else if (Pull.gettingString1().compareTo(" BAR ") == 0 && Pull.gettingString2().compareTo(" BAR ") == 0
                && Pull.gettingString3().compareTo(" BAR ") == 0)
        {
            return 50;
        }
        else if (Pull.gettingString1().compareTo(" 7 ") == 0 && Pull.gettingString2().compareTo(" 7 ") == 0
                && Pull.gettingString3().compareTo(" 7 ") == 0)
        {
            return 100;
        }
        return 0;
    }


    public static void display (TripleString Pull, int winnings)
    {
        System.out.println(Pull.gettingString1() + " " + Pull.gettingString2() + " " + Pull.gettingString3());
    }




    public static void main(String[] args) {
	// write your code here

        int Bet = 1;
        int Mul = 0;
        int Win = 0;
        while (Bet != 0)
        {
            Bet = SlotMachine.getBet();

            if (Bet == 0)
            {
                System.out.println("Shutting Down.");
                return;
            }
            else if (Bet >= 1 && Bet <= 100)
            {
                System.out.println("Whirrrrrr .... and your pull is ...");

                TripleString Pull = SlotMachine.pull();
                Mul = SlotMachine.getPayMultiplier(Pull);
                SlotMachine.display(Pull, 0);
                if (Mul == 0)
                {
                    System.out.println("Sorry, You lose.\n");
                    continue;
                }
                Win = Mul*Bet;
                System.out.println("Congratulations you win: " + Win + "\n");

            }
            else
            {
                System.out.println("write a number from 0 to 100 \n");
            }
        }




    }
}
