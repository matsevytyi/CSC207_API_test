package org.example;

import com.mapbox.api.directions.v5.DirectionsCriteria;
import com.mapbox.api.directions.v5.MapboxDirections;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.geojson.Point;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    JButton getRouteButton = new JButton("FIND INFORMATION");
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

    public void executeAPI(double x1, double  y1, double  x2, double  y2) {

        String accessToken = "pk.eyJ1IjoibGl6b2trayIsImEiOiJjbG40c3lxOGswMnB2MmtwaGVwbnF2M3RoIn0.ETemsYB1tkNiHfcindemMQ";

        Point origin = Point.fromLngLat(x1, y1);
        Point destination = Point.fromLngLat(x2, y2);

        MapboxDirections directionsClient = MapboxDirections.builder()
                .origin(origin)
                .destination(destination)
                .accessToken(accessToken)
                .profile(DirectionsCriteria.PROFILE_DRIVING)
                .steps(true)
                .voiceInstructions(true)
                .geometries(DirectionsCriteria.GEOMETRY_POLYLINE)
                .build();

        // GET request to the Mapbox Directions API
        directionsClient.enqueueCall(new Callback<DirectionsResponse>() {
            @Override
            public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                if (response.isSuccessful()) {
                    DirectionsResponse directionsResponse = response.body();
                    if (directionsResponse != null && !directionsResponse.routes().isEmpty()) {
                        DirectionsRoute route = directionsResponse.routes().get(0);
                        double totalDistance = route.distance() / 1000.0; // Convert to kilometers
                        double totalDuration = route.duration() / 60.0; // Convert to minutes


                        JOptionPane.showMessageDialog(null, "Duration: " + totalDuration + " minutes\nDistance: " + totalDistance + " km", "Route Information", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        JOptionPane.showMessageDialog(null, "No route found", "Route Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error in API request: " + response.message(), "API Error", JOptionPane.ERROR_MESSAGE);
                }
            }


            @Override
            public void onFailure(Call<DirectionsResponse> call, Throwable t) {
                System.err.println("Request failed: " + t.getMessage());
            }
        });
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
            double x1 = Double.parseDouble(startLat);
            double y1 = Double.parseDouble(startLong);
            double x2 = Double.parseDouble(destLat);
            double y2 = Double.parseDouble(destLong);
            executeAPI(x1, y1, x2, y2);

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

