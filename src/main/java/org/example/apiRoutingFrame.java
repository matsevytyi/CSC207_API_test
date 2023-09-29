package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class apiRoutingFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel startLabel = new JLabel("START (Lat, Long)");
    JLabel destLabel = new JLabel("DESTINATION (Lat, Long)");

    JTextField StartLat = new JTextField();
    JTextField StartLong = new JTextField();
    JTextField DestLat = new JTextField();
    JTextField DestLong = new JTextField();
    JButton getRouteButton = new JButton("FIND ROUTE");
    JButton clearInputButton = new JButton("CLEAR INPUT");



    apiRoutingFrame() {
        //Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        setButtonsListener();;

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        //Setting location and Size of each components using setBounds() method.
        startLabel.setBounds(50, 50, 300, 30);
        destLabel.setBounds(50, 120, 300, 30);
        StartLat.setBounds(250, 50, 150, 30);
        StartLong.setBounds(420, 50, 150, 30);
        DestLat.setBounds(420, 120, 150, 30);
        DestLong.setBounds(250, 120, 150, 30);
        getRouteButton.setBounds(90, 200, 200, 30);
        clearInputButton.setBounds(360, 200, 200, 30);

    }

    public void addComponentsToContainer() {
        //Adding each components to the Container
        container.add(startLabel);
        container.add(destLabel);
        container.add(StartLat);
        container.add(StartLong);
        container.add(DestLat);
        container.add(DestLong);
        container.add(getRouteButton);
        container.add(clearInputButton);
    }

    public void setButtonsListener() {
        getRouteButton.addActionListener(this);
        clearInputButton.addActionListener(this);
    }

    public void executeAPI(String x1, String y1, String x2, String y2) {
        //TODO: api code execution
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == getRouteButton) {
            String destLat = DestLat.getText();
            String destLong = DestLong.getText();

            String startLat = StartLat.getText();
            String startLong = StartLong.getText();

            if(destLat.isEmpty() || destLong.isEmpty() || startLat.isEmpty() || startLong.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            executeAPI(startLat, startLong, destLat, destLong);

            JOptionPane.showMessageDialog(null, "API code executed", "API code execution", JOptionPane.INFORMATION_MESSAGE);
        }

        if(e.getSource() == clearInputButton) {
            DestLat.setText("");
            DestLong.setText("");
            StartLat.setText("");
            StartLong.setText("");
        }

    }
}

