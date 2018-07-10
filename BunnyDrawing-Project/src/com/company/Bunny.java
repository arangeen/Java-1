package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JComponent;


public class Bunny extends JComponent {
    public void paintComponent(Graphics g)
    {
        // Recover Graphics
        Graphics2D g2 = (Graphics2D) g;


        // Drawing the head ( x,y , width, height )
        Ellipse2D.Double head = new Ellipse2D.Double(300, 150, 200, 250);
        g2.setColor(Color.PINK);  // setting color to pink
        g2.fill(head);             // making the whole head pink
        g2.draw(head);             // showing the drawing



        // drawing the left ear (x1 , y1, x2, y2)
        Line2D.Double leftEar1 = new Line2D.Double(330, 185, 260, 100);
        g2.setColor(Color.PINK);
        g2.draw(leftEar1);

        Line2D.Double leftEar2 = new Line2D.Double(360, 180, 295, 100);
        g2.setColor(Color.PINK);
        g2.draw(leftEar2);

        // ( x , y , over to the right, down )
        Rectangle leftEar3 = new Rectangle(260,100, 40, 40);
        g2.setColor(Color.PINK);
        g2.fill(leftEar3);



        // drawing the right ear
        Line2D.Double rightEar1 = new Line2D.Double(470, 185, 510, 100 );
        g2.setColor(Color.PINK);
        g2.draw(rightEar1);

        Line2D.Double rightEar2 = new Line2D.Double(440, 180, 475, 100 );
        g2.setColor(Color.PINK);
        g2.draw(rightEar2);

        Rectangle rightEar3 = new Rectangle(478,95, 40, 40);
        g2.setColor(Color.PINK);
        g2.fill(rightEar3);



        // drawing the eyes
        g2.setColor(Color.BLACK);
        Rectangle eyes = new Rectangle(360, 210, 15,15);
        g2.fill(eyes);
        eyes.translate(55,0);
        g2.fill(eyes);



        // drawing the nose
        Line2D.Double nose = new Line2D.Double(395, 235, 395, 275);
        g2.draw(nose);



        // drawing the mouth
        Line2D.Double mouth = new Line2D.Double(360, 295, 435, 295);
        g2.draw(mouth);


        // drawing the teeth's
        Rectangle teeths = new Rectangle(385,295, 9, 30);
        g2.setColor(Color.WHITE);
        g2.fill(teeths);
        teeths.translate(15, 0);
        g2.fill(teeths);


        // Drawing the body
        Ellipse2D.Double body = new Ellipse2D.Double(310, 395, 200, 250);
        g2.setColor(Color.PINK);  // setting color to pink
        g2.fill(body);             // making the whole head pink
        g2.draw(body);             // showing the drawing


        // drawing the arms
        Rectangle arms = new Rectangle(230,450, 100, 7);
        g2.setColor(Color.PINK);
        g2.fill(arms);
        arms.translate(250, 0);
        g2.fill(arms);


        // drawing the legs
        g2.setColor(Color.BLACK);
        Line2D.Double legs = new Line2D.Double(365, 615, 335, 695);
        g2.draw(legs);


        //right leg
        Line2D.Double legs2 = new Line2D.Double(445, 615, 465, 695);
        g2.draw(legs2);


    }

}
