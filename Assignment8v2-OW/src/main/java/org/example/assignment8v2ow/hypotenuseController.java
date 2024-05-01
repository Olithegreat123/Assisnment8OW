package org.example.assignment8v2ow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Controller class for the hypotenuse calculator application
public class hypotenuseController {

    // File name for the calculation log
    private static final String LOG_FILE = "calculationLog.txt";
    private FileWriter fileWriter; // FileWriter to write logs to file

    // Constructor for the controller
    public hypotenuseController() {
        try {
            // Initialize the FileWriter to append to the log file
            fileWriter = new FileWriter(LOG_FILE, true);
            // Log the start of the session
            logSessionStart();
        } catch (IOException e) {
            // Handle errors related to file writing
            System.err.println("There was an error trying to open the log file: " + e);
            System.err.println("No log will be created for this session");
        }
    }

    // FXML annotations for UI elements
    @FXML
    private TextField aInput;

    @FXML
    private TextField bInput;

    @FXML
    private TextField cOutput;

    @FXML
    private Label errorMsg1;

    @FXML
    private Label errorMsg2;

    @FXML
    private Button calculateButton;

    @FXML
    private Button clearButton;

    @FXML
    private Label errorLabelA; // New label for error message for input field 'a'

    @FXML
    private Label errorLabelB; // New label for error message for input field 'b'

    // Initialization method called after FXML loading
    @FXML
    protected void initialize() {
        // Initialize event handlers for buttons
        calculateButton.setOnAction(event -> onCalcButtonClick());
        clearButton.setOnAction(event -> onClearButtonClick());
    }

    // Event handler for calculate button click
    @FXML
    protected void onCalcButtonClick() {
        // Get input values for side 'a' and 'b'
        String aStr = aInput.getText();
        String bStr = bInput.getText();
        // Check if inputs are numeric
        if (isNumeric(aStr) && isNumeric(bStr)) {
            // Convert inputs to double
            double a = Double.parseDouble(aStr);
            double b = Double.parseDouble(bStr);
            // Calculate hypotenuse
            double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
            // Display result
            cOutput.setText(String.format("%.2f", c));
            // Log the calculation
            logCalculation(a, b, c);
        } else {
            // Display error message for invalid input
            cOutput.setText("ERROR");
            errorMsg1.setText("INVALID INPUT");
            errorMsg2.setText("enter NUMERICAL values for \"a\" and \"b\"!");
        }
    }

    // Event handler for clear button click
    @FXML
    protected void onClearButtonClick() {
        // Clear input fields and error messages
        aInput.clear();
        bInput.clear();
        cOutput.clear();
        errorMsg1.setText("");
        errorMsg2.setText("");
    }

    // Event handler to clear input field 'a'
    @FXML
    protected void clearAInput() {
        aInput.clear();
    }

    // Event handler to clear input field 'b'
    @FXML
    protected void clearBInput() {
        bInput.clear();
    }

    // Method to log the start of the session
    private void logSessionStart() {
        try {
            // Get current date and time, format it, and write to log
            String sessionStart = "\n--> Start of session: " + getFormattedDateTimeString();
            fileWriter.write(sessionStart);
            fileWriter.flush();
        } catch (IOException e) {
            // Handle errors related to file writing
            System.err.println("Error writing session start to log: " + e);
        }
    }

    // Method to log a calculation
    private void logCalculation(double a, double b, double c) {
        try {
            // Format calculation information and write to log
            String calculation = String.format("\n>> %s | Hypotenuse Calculation: sqrt(%s^2 + %s^2) = %.2f",
                    getFormattedDateTimeString(), a, b, c);
            fileWriter.write(calculation);
            fileWriter.flush();
        } catch (IOException e) {
            // Handle errors related to file writing
            System.err.println("Error writing calculation to log: " + e);
        }
    }

    // Method to check if a string represents a numeric value
    private boolean isNumeric(String inputString) {
        if (inputString == null)
            return false;
        try {
            Double.parseDouble(inputString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Method to get formatted date-time string
    private String getFormattedDateTimeString() {
        // Get current date and time, format it, and return as string
        LocalDateTime currentDateTimeStamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return currentDateTimeStamp.format(formatter);
    }
}
