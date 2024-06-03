package com.ballgame;

import javax.swing.JFrame;

import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;


public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        frame.setSize(1200  , 800);
        
        
        Game g = new Game(frame.getWidth(), frame.getHeight());
        
        g.setSize(frame.getSize());
        frame.add(g, BorderLayout.CENTER);
        
        frame.setVisible(true);

        Timer t = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.simulate();
                g.repaint();                
            }
        });

        t.start();
    }
}