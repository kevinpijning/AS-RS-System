package com.kevin889.as_rs.visual;

import com.kevin889.as_rs.algoritme.GA_TSP;
import com.kevin889.as_rs.core.Order;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kevin889 on 16-04-15.
 */
public class TourPanel extends JPanel {

    private Order order;

    public static final int PWIDTH = 620;
    public static final int PHEIGHT = 500;

    private int xBox = 5;
    private int yBox = 5;

    private int boxWidth;
    private int boxHeight;

    private int padding = 10;
    private int lineHeight = 12;

    public TourPanel(){
        setBackground(Color.BLACK);
        boxWidth = (PWIDTH - (2 * padding)) / xBox;
        boxHeight = (PHEIGHT - (2 * padding)) / yBox;
    }

    public void init(Order order){
        this.order = order;
        super.repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //Set background to black
        g.setColor(Color.BLACK);
        g.fillRect(0,0,620,500);

        //Draw grid
        g.setColor(Color.green);
        for(int x = 0; x < xBox; x++){
            for(int y = 0; y < yBox; y++){
                g.drawRect(x * boxWidth + padding, y * boxHeight + padding, boxWidth, boxHeight);
            }
        }


        if(order == null) return;

        for(int i = 0; i < order.numberOfProducts(); i++){
            g.setColor(Color.WHITE);

            //Draw Product box
            int productX = order.getProduct(i).getX() * boxWidth + (padding * 2);
            //int productY = order.getProduct(i).getY() * boxHeight + (padding * 2);
            int productY = (PHEIGHT - order.getProduct(i).getY() * boxHeight) - boxHeight;
            int productWidth = boxWidth - (padding * 2);
            int productHeight = boxHeight - padding;
            g.fillRect(productX, productY, productWidth, productHeight);

            //Draw product info strings
            g.setColor(Color.BLACK);

            //Draw product name
            int productNameX = order.getProduct(i).getX() * boxWidth + (padding * 3);
            int productNameY = PHEIGHT - (order.getProduct(i).getY() * boxHeight) - boxHeight + (padding * 2);
            g.drawString(order.getProduct(i).getName(), productNameX, productNameY);

            //Draw product size
            String size;
            if(order.getProduct(i).getSize() == 1){
                size = "Klein";
            }else{
                size = "Groot";
            }
            int sizeX = order.getProduct(i).getX() * boxWidth + (padding * 3);
            int sizeY = PHEIGHT - (order.getProduct(i).getY() * boxHeight) - boxHeight + (padding * 2) + lineHeight;
            g.drawString(size, sizeX, sizeY);
        }

    }

    public void start(){
        System.out.println("START GA");
        GA_TSP tsp = new GA_TSP(order);
    }

    public void stop(){

    }

}
