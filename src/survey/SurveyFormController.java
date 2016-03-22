/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package survey;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import survey.beans.RestRequest;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import survey.beans.RestResponse;
import survey.client.ServiceClient;
import static survey.validations.FormValidation.choiceBoxSelected;
import static survey.validations.FormValidation.emailTextField;
import static survey.validations.FormValidation.radioSelected;
import static survey.validations.FormValidation.textFieldNotEmpty;


/**
 *
 * @author anket
 */
public class SurveyFormController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Label psLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label mobileLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private TextField ps;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField address;
    @FXML
    private TextField mobile;
    @FXML
    private TextField email;
    @FXML
    private ChoiceBox<String> city;
    @FXML
    private CheckBox passport;
    @FXML
    private CheckBox pancard;
    @FXML
    private CheckBox adhar;
    @FXML
    private Button submitBtn;
    @FXML
    private ToggleGroup myToggleGroup;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("handleButtonAction called");
        label.setText("");
        /* Validations for the form */
        if(!textFieldNotEmpty(ps,label,"Please Enter Ps Number.")||!textFieldNotEmpty(firstName,label,"Please Enter First Name.")||!textFieldNotEmpty(lastName,label,"Please Enter Last Name.")||!textFieldNotEmpty(address,label,"Please Enter Address.")||!choiceBoxSelected(city,label,"Please Select City.")||!textFieldNotEmpty(mobile,label,"Please Enter Mobile.")||!textFieldNotEmpty(email,label,"Please Enter Email.")||!emailTextField(email,label,"Please Enter Valid Email.")||!radioSelected((RadioButton)myToggleGroup.getSelectedToggle(),label,"Please Select Gender."))
        {
            return;
        }
        if(!passport.isSelected()&&!pancard.isSelected()&&!adhar.isSelected())
        {
            label.setText("Please Select Atleast One KYC.");
            return;
        }
        /* Validation ends */
        /* Creating Request object */
        RestRequest request;
        request = new RestRequest();
        request.setId(ps.getText());
        request.setFirstName(firstName.getText());
        request.setLastName(lastName.getText());
        request.setAddress(address.getText());
        request.setCity(city.getSelectionModel().getSelectedItem());
        request.setMobile(mobile.getText());
        request.setMail(email.getText());
        RadioButton chk = (RadioButton)myToggleGroup.getSelectedToggle();
        System.out.println("chk : " + chk);
        if(chk.getText().equalsIgnoreCase("Male"))
        {
            request.setGender("M");
        }else{
            request.setGender("F");
        }
        System.out.println(passport.isSelected());
        StringBuilder sBuffer = new StringBuilder("");
        if(passport.isSelected()){
        sBuffer.append(" Passport");
        }
        if(pancard.isSelected()){
        sBuffer.append(" Pancard");
        }
        if(adhar.isSelected()){
        sBuffer.append(" Adharcard");
        }
        request.setKyc(sBuffer.toString());
       /* Creating Request object ends*/
       /* Calling service */
        try {
           ServiceClient client = new ServiceClient();
        RestResponse res = client.callService(request);
        System.out.println("Res : " + res.getResponse() + " " + res.getResponseCode());
        label.setText(res.getResponse());
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        /* Calling service ends */
    }
    
    ObservableList<String> list = FXCollections.observableArrayList(
    "Select","Mumbai","Delhi","Pune","Chennai","Jaipur","Banglore"
    );
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        city.setItems(list);
        city.getSelectionModel().selectFirst();
        ps.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(8));
        firstName.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(25));
        lastName.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(25));
        //address.addEventFilter(KeyEvent.KEY_TYPED, letter_special_Validation(50));
        mobile.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(10));
        email.addEventFilter(KeyEvent.KEY_TYPED,email_Validation(25));
    }    
    
    /* Numeric Validation Limit the  characters to maxLengh AND to ONLY DigitS *************************************/
public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
    return (KeyEvent e) -> {
        TextField txt_TextField = (TextField) e.getSource();
        if (txt_TextField.getText().length() >= max_Lengh) {
            e.consume();
        }                    
        if(e.getCharacter().matches("[0-9]")){
            if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
                e.consume();
            }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
                e.consume();
            }
        }else{
            e.consume();
        }
    };
}    
/*****************************************************************************************/

 /* Letters Validation Limit the  characters to maxLengh AND to ONLY Letters *************************************/
public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) {
    return (KeyEvent e) -> {
        TextField txt_TextField = (TextField) e.getSource();
        if (txt_TextField.getText().length() >= max_Lengh) {
            e.consume();
        }
        if(e.getCharacter().matches("[A-Za-z]")){
        }else{
            e.consume();
        }
    };
}    
/*****************************************************************************************/
    
 /* Letters Validation Limit the  characters to maxLengh AND to ONLY Letters *************************************/
public EventHandler<KeyEvent> letter_special_Validation(final Integer max_Lengh) {
    return (KeyEvent e) -> {
        TextField txt_TextField = (TextField) e.getSource();
        if (txt_TextField.getText().length() >= max_Lengh) {
            e.consume();
        }
        if(e.getCharacter().matches("[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]*$")){
        }else{
            e.consume();
        }
    };
}
/*****************************************************************************************/

/* Email Validation *************************************/
public EventHandler<KeyEvent> email_Validation(final Integer max_Lengh) {
    return (KeyEvent e) -> {
        TextField txt_TextField = (TextField) e.getSource();
        if (txt_TextField.getText().length() >= max_Lengh) {
            e.consume();
        }
        if(e.getCharacter().matches("[a-zA-Z0-9@_.]*$")){
        }else{
            e.consume();
        }
    };
}
/*****************************************************************************************/
}
