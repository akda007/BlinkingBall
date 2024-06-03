package com.ballgame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Ball {
    public Point position;
    public double speedX, speedY;
    public double radius = 10;
    
    public Ball(double speedX, double speedY, int x, int y) {
        this.speedX = speedX;
        this.speedY = speedY;
        position = new Point(x, y);
    }

    public void move() {
        position.x += speedX;
        position.y += speedY;
    }

    public void invertX() {
        speedX *= -1;
    }

    public void invertY() {
        speedY *= -1;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.red);
        g.fillOval((int)(position.x - radius), (int)(position.y - radius), (int)radius*2, (int)radius*2);
    }
}
