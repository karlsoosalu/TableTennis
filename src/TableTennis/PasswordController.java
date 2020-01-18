package TableTennis;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


public class PasswordController {

    @FXML
    private TextField passwordentered;
    @FXML
    private Text wrongtext;


    public void enter(){
        if (passwordentered.getText().equals("fxml")){
            System.out.println("jee");
        }
        else {
            wrongtext.setVisible(true);
        }
    }

}
