/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package survey.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 *
 * @author anket
 */
public class FormValidation {

    private static Pattern pattern;
    private static Matcher matcher;

    private static final String EMAIL_PATTERN = 
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    static {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public static boolean textFieldNotEmpty(TextField t)
    {
        boolean r =false;
        if(t.getText()!=null && t.getText().isEmpty()){
            r =true;
        }
       return r; 
    }

    public static boolean textFieldNotEmpty(TextField t,Label l,String errorMsg)
    {
        boolean r =true;
        String c = null;
        if(textFieldNotEmpty(t)){
            r =false;
            c=errorMsg;
        }
        l.setText(c);
       return r; 
    }

    public static boolean emailTextField(TextField t)
    { 
        matcher = pattern.matcher(t.getText());
                return matcher.matches();
    }

    public static boolean emailTextField(TextField t,Label l,String errorMsg)
    {
        boolean r =true;
        String c = null;
        if(!emailTextField(t)){
            r =false;
            c=errorMsg;
        }
        l.setText(c);
       return r; 
    }

    public static boolean choiceBoxSelected(ChoiceBox cb)
    {
        boolean r =false;
        if(cb.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Select")){
            r =true;
        }
       return r; 
    }

    public static boolean choiceBoxSelected(ChoiceBox cb,Label l,String errorMsg)
    {
        boolean r =true;
        String c = null;
        if(choiceBoxSelected(cb)){
            r =false;
            c=errorMsg;
        }
        l.setText(c);
       return r; 
    }

    public static boolean radioSelected(RadioButton rb)
    {
        boolean r =false;
        if(rb==null||rb.getText().equalsIgnoreCase("")){
            r =true;
        }
       return r; 
    }

    public static boolean radioSelected(RadioButton rb,Label l,String errorMsg)
    {
        boolean r =true;
        String c = null;
        if(radioSelected(rb)){
            r =false;
            c=errorMsg;
        }
        l.setText(c);
       return r; 
    }

}
