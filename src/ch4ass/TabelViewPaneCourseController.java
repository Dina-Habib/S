/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch4ass;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author dinahabib
 */
public class TabelViewPaneCourseController implements Initializable {

    @FXML
    private Button buttonShow;
    @FXML
    private Button buttonAdd;
    @FXML
    private TextField textFieldStId;
    @FXML
    private TextField textFieldCoId;
     @FXML
    private TextField textFieldSemester;
    @FXML
    private TableColumn<Registration, Integer> tcStId;
    @FXML
    private TableColumn<Registration, Integer> tcCoId;
    @FXML
    private TableColumn<Registration, String> tcSemester;
    @FXML
    private TableView<Registration> tableView;
    Statement statement;
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/University?serverTimezone=UTC","root","");
            this.statement=conn.createStatement();
        } catch (Exception ex) {
            System.out.println("not connected");
            ex.printStackTrace();
        }
        tcStId.setCellValueFactory(new PropertyValueFactory("studentId"));
        tcCoId.setCellValueFactory(new PropertyValueFactory("courseId"));
        tcSemester.setCellValueFactory(new PropertyValueFactory("semester"));
    }    

    @FXML
    private void buttonShowHandle(ActionEvent event) throws Exception {
        ResultSet rs=this.statement.executeQuery("Select * From Registeration");
        tableView.getItems().clear();
        while(rs.next()){
            Registration registration=new Registration();
            registration.setStudentId(rs.getInt("studentId"));
            registration.setCourseId(rs.getInt("courseId"));
            registration.setSemester(rs.getString("semester"));
            tableView.getItems().add(registration);
        }
    }

    @FXML
    private void buttonAddHandle(ActionEvent event) throws Exception {
        Integer studentId=Integer.parseInt(textFieldStId.getText());
        Integer courseId=Integer.parseInt(textFieldCoId.getText());
        String semester=textFieldSemester.getText();
        String sql="Insert Into Registeration values(" +studentId+ "," +courseId+ ",'" +semester+ "')";
        this.statement.executeUpdate(sql);
    }
    
}
