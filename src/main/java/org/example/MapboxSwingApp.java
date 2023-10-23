package org.example;

import javax.swing.*;
import java.awt.*;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MapboxSwingApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Mapbox in Java Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        final JFXPanel jfxPanel = new JFXPanel();

        frame.getContentPane().add(jfxPanel, BorderLayout.CENTER);

        Platform.runLater(() -> {
            WebView webView = new WebView();

            String mapboxURL = "https://api.mapbox.com/styles/v1/mapbox/streets-v11.html?access_token=pk.eyJ1IjoiZmFyYWhkaWJhMTY3IiwiYSI6ImNsbjNzNGl3eTBpNWoyamxqaGU1eDdwcWsifQ.FQtf_fe9_clfS3hnLjPF3g";
            webView.getEngine().load(mapboxURL);

            jfxPanel.setScene(new Scene(webView));

            frame.setVisible(true);
        });
    }
}