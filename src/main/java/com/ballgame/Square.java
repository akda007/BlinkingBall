package com.ballgame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;

public class Square {
    public Point position;
    public static int size = 50;

    public Square(int x, int y) {
        position = new Point(x , y);
    }

    public void draw(Graphics2D g) {
        Random rd = new Random();

        g.setColor(new Color(rd.nextInt(255), rd.nextInt(255), rd.nextInt(255)));
        g.fillRect(position.x - size/2, position.y - size/2, size, size);
    }
}
