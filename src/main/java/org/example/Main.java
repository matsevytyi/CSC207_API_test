package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        apiRoutingFrame frame = new apiRoutingFrame();
        frame.setTitle("Routing API Checking Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 700, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}

