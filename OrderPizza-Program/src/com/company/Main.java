package com.company;
import java.util.Scanner;
public class Main {

    static void displayMainMenu()
    {
        String toppingChoice = "";
        Scanner scanner = new Scanner (System.in);
        Pizzeria po = new Pizzeria();

        boolean bContinue = false;
        char choice = getSizeFromUser();
        while (choice != 'q')
        {
            bContinue = false;

            if (choice == 's' || choice == 'S')
            {
                bContinue = true;
            }
            else if (choice == 'm' || choice == 'M')
            {
                po.setSize(1);
                bContinue = true;
            }
            else if (choice == 'l' || choice == 'L')
            {
                po.setSize(2);
                bContinue = true;
            }
            int toppingNumber = -1;
            while (bContinue)
            {
                po.displayPizza();
                System.out.println("Select an item by number ( O when done):");
                System.out.println("1. Onions");
                System.out.println("2. Bell peppers");
                System.out.println("3. Olives");
                System.out.println("4. Pepperoni");
                System.out.println("Selection: ");

                toppingChoice = scanner.nextLine();
                toppingNumber = Integer.parseInt(toppingChoice);

                if ( toppingNumber >= 0 && toppingNumber <= 4)
                {
                    if (toppingNumber == 0)
                    {
                        break;
                    }
                    po.addTopping(toppingNumber - 1);
                }
            }
            if (toppingNumber == 0)
            {
                System.out.println("Total price: $" + po.getPrice());
                po.resetTopping();
            }
            System.out.println("\n");
            choice = getSizeFromUser();
        }

    }
    static char getSizeFromUser()
    {
        System.out.println("Size of pizze ( 's' , 'm' , 'l') or 'q' to quit:");
        String choice = "";
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextLine();
        return choice.charAt(0);
    }

    public static void main(String[] args) {
	// write your code here
        displayMainMenu();
    }
}
