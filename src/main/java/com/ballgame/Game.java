package com.ballgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JComponent;

public class Game extends JComponent {
    Ball ball;
    LinkedList<Square> squares = new LinkedList<>();
    
    public Game(int w, int h) {
        ball = new Ball(-30, -30, w/2,h-100);

        double count = (w-300) / (Square.size);
        double total = count * (Square.size);
        double start = ((w - total) / 2) + Square.size/2;
        double startO = ((w - total) / 2) + Square.size/2;
        

        int y = 100;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < count; j++) {
                squares.add(new Square((int)start, y));
                start += Square.size;
                
            }
            y+=Square.size;
            start = startO;
        }
    }

    public double separation(Point rectangle, Point circle) {
        Point relativeCenter = new Point(circle.x - rectangle.x, circle.y - rectangle.y);
    
        Point offsetFromCorner = new Point(Math.abs(relativeCenter.x) - Square.size/2,
                                           Math.abs(relativeCenter.y) - Square.size/2);
    
        float maxOffsetX = Math.max(offsetFromCorner.x, 0);
        float maxOffsetY = Math.max(offsetFromCorner.y, 0);
        float minOffset = Math.min(maxOffsetX, maxOffsetY);
    
        float length = (float) Math.sqrt(Math.pow(Math.max(offsetFromCorner.x, 0), 2) +
                                         Math.pow(Math.max(offsetFromCorner.y, 0), 2));
    
        return minOffset + length - ball.radius;
    }
    
    boolean collidedX = false;
    boolean collidedY = false;
    public void simulate() {
        ball.move();


        if ((ball.position.x - ball.radius <= 0 || ball.position.x + ball.radius >= getWidth())) {
            ball.invertX();
            collidedX = true;
        }

        if ((ball.position.y - ball.radius <= 0 || ball.position.y + ball.radius >= getHeight())) {
            ball.invertY();
            collidedY = true;
        }

        LinkedList<Square> remove = new LinkedList<>();

        for (Square square : squares) {
            if (separation(square.position, ball.position) <= 0) {

                if (squares.size() == 1) {
                    ball.position = new Point(-10000, -10000);
                    return;
                }
                
                if (ball.position.x > square.position.x + Square.size/2 && Math.abs(ball.position.y - square.position.y) <= Square.size/2) {
                    ball.invertX();
                } else if (ball.position.x < square.position.x - Square.size/2 && Math.abs(ball.position.y - square.position.y) <= Square.size/2) {
                    ball.invertX();
                } else if (ball.position.y > square.position.y + Square.size/2 && Math.abs(ball.position.x - square.position.x) <= Square.size/2) {
                   ball.invertY();
                } else if (ball.position.y < square.position.y - Square.size/2 && Math.abs(ball.position.x - square.position.x) <= Square.size/2) {
                    ball.invertY();
                } else {
                    ball.invertY();
                    ball.invertX();
                }

                remove.add(square);
            }
        }

        squares.removeAll(remove);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.black);

        g2d.fillRect(0, 0, getWidth(), getHeight());

        ball.draw(g2d);
        for (Square square : squares) {
            square.draw(g2d);
        }
    }
}
