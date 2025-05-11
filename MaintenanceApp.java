package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VehicleMaintenanceApp extends Application {

    private List<Vehicle> vehicles = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        ListView<Vehicle> vehicleList = new ListView<>();
        ListView<Part> partList = new ListView<>();
        Label partDetails = new Label("Select a part to see details.");

        Button addVehicleButton = new Button("Add Vehicle");

        // Add Vehicle Button Action
        addVehicleButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Vehicle");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter vehicle name:");
            dialog.showAndWait().ifPresent(name -> {
                Vehicle v = new Vehicle(name);
                vehicles.add(v);
                vehicleList.getItems().add(v);
            });
        });

        VBox layout = new VBox(10, vehicleList, addVehicleButton, partDetails);
        Scene scene = new Scene(layout, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Vehicle Maintenance App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
