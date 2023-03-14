package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        GamePanel gamePanel = new GamePanel();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Finding Laptop");
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.add(gamePanel);

        window.pack();
        window.setVisible(true);

        gamePanel.startGameThread();
        gamePanel.setUpGame();
    }
}