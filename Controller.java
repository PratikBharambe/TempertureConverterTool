import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class Controller implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Button convertButton;

    @FXML
    private TextField userInputField;

    private static final String c_to_f = "Celcius to farenheit";
    private static final String f_to_c = "Farenheit to Celcius";
    private boolean isC_to_f = true;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        choiceBox.getItems().add(c_to_f);
        choiceBox.getItems().add(f_to_c);
        choiceBox.setValue(c_to_f);

        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            
            if(newValue.equals(c_to_f)){
                isC_to_f = true;
            }else{
                isC_to_f = false;
            }

        });

        convertButton.setOnAction(event -> {
            convert();
        });

    }

    private void convert() {
        String input = userInputField.getText();
        float enteredTemperture = 0.00f;
        try {
            enteredTemperture = Float.parseFloat(input);   
        } catch (Exception e) {
            warnUser();
            return;
        }

        float newTemp = 0.0f;
        if(isC_to_f){
            newTemp = (enteredTemperture * 9 / 5) + 32;
        } else{
            newTemp = (enteredTemperture - 32) * 5 / 9;
        }
        display(newTemp);
    }

    private void warnUser() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Occured");
        alert.setHeaderText("Invaid Temperture Input");
        alert.setContentText("Please Enter the valid temperture input");
        alert.show();
    }

    private void display(float newTemp) {
        String unit = isC_to_f ? " F" : " C";
        // System.out.println("The new temperture is : " + newTemp + unit);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setContentText("The new temperture is : " + newTemp + unit);
        alert.show();
    }
}
