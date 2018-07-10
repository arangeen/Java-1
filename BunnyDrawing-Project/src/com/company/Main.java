package com.company;

import javax.swing.JFrame;


public class Main {

    public static void main(String[] args) {
	// write your code here
        JFrame frame = new JFrame();

        //setting the size of the frame
        frame.setSize(800, 900);
        frame.setTitle("Bunny");

        // give them the option to close out of the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Refer to the Bunny class
        Bunny bunnyClass = new Bunny();
        frame.add(bunnyClass);


        // to show the window
        frame.setVisible(true);

    }
}
