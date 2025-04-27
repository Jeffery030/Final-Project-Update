import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Callback;
import java.time.LocalDate;

public class MaintenanceAppUI extends Application {

    private MaintenanceApp app = new MaintenanceApp();

    @Override
    public void start(Stage primaryStage) {
        ListView<Vehicle> vehicleList = new ListView<>();
        ListView<Part> partList = new ListView<>();
        Label partDetails = new Label("Select a part to see details.");

        Button addVehicleButton = new Button("Add Vehicle");
        Button addPartButton = new Button("Add Part");

        vehicleList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            partList.getItems().clear();
            if (newVal != null) {
                partList.getItems().addAll(newVal.getAllParts());
            }
        });

        partList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                partDetails.setText(newVal.getName() + "\n\n" + newVal.getDescription() +
                        "\n\nDue: " + (newVal.isDue() ? "YES" : "NO"));
            }
        });

        // Color overdue parts red
        partList.setCellFactory(new Callback<ListView<Part>, ListCell<Part>>() {
            @Override
            public ListCell<Part> call(ListView<Part> list) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Part part, boolean empty) {
                        super.updateItem(part, empty);
                        if (empty || part == null) {
                            setText(null);
                        } else {
                            setText(part.getName());
                            if (part.isDue()) {
                                setTextFill(Color.RED);
                            } else {
                                setTextFill(Color.BLACK);
                            }
                        }
                    }
                };
            }
        });

        // Add vehicle button
        addVehicleButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Vehicle");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter vehicle name:");
            dialog.showAndWait().ifPresent(name -> {
                Vehicle v = new Vehicle(name);
                app.addVehicle(v);
                vehicleList.getItems().add(v);
            });
        });

        // Add part button
        addPartButton.setOnAction(e -> {
            Vehicle selectedVehicle = vehicleList.getSelectionModel().getSelectedItem();
            if (selectedVehicle == null) {
                showAlert("Please select a vehicle first.");
                return;
            }
            TextInputDialog nameDialog = new TextInputDialog();
            nameDialog.setTitle("Add Part");
            nameDialog.setHeaderText(null);
            nameDialog.setContentText("Enter part name:");
            nameDialog.showAndWait().ifPresent(partName -> {

                TextInputDialog intervalDialog = new TextInputDialog();
                intervalDialog.setTitle("Add Part");
                intervalDialog.setHeaderText(null);
                intervalDialog.setContentText("Enter replacement interval (days):");
                intervalDialog.showAndWait().ifPresent(intervalStr -> {

                    TextInputDialog descDialog = new TextInputDialog();
                    descDialog.setTitle("Add Part");
                    descDialog.setHeaderText(null);
                    descDialog.setContentText("Enter part description:");
                    descDialog.showAndWait().ifPresent(description -> {
                        try {
                            int interval = Integer.parseInt(intervalStr);
                            Part p = new Part(partName, LocalDate.now(), interval, description);
                            selectedVehicle.addPart(p);
                            partList.getItems().add(p);
                        } catch (NumberFormatException ex) {
                            showAlert("Invalid number for interval.");
                        }
                    });
                });
            });
        });

        VBox left = new VBox(10, vehicleList, addVehicleButton);
        VBox middle = new VBox(10, partList, addPartButton);
        VBox right = new VBox(10, partDetails);

        HBox root = new HBox(20, left, middle, right);
        root.setPrefSize(900, 400);
        root.setStyle("-fx-padding: 10;");

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Vehicle Maintenance App");
        primaryStage.show();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
