/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch4ass;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dinahabib
 */
public class TabelViewPaneCController implements Initializable {

    @FXML
    private TextField textFieldId;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldMajor;
    @FXML
    private TextField textFieldGrade;
    @FXML
    private TableColumn<Student, Integer> tcId;
    @FXML
    private TableColumn<Student, String> tcName;
    @FXML
    private TableColumn<Student, String> tcMajor;
    @FXML
    private TableColumn<Student, Integer> tcGrade;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonDelete;
    @FXML
    private TableView<Student> tableView;
    @FXML
    private Button buttonShow;
    @FXML
    private Button buttonAddCourse;
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
        tcId.setCellValueFactory(new PropertyValueFactory("id"));
        tcName.setCellValueFactory(new PropertyValueFactory("name"));
        tcMajor.setCellValueFactory(new PropertyValueFactory("major"));
        tcGrade.setCellValueFactory(new PropertyValueFactory("grade"));
        tableView.getSelectionModel().selectedItemProperty().addListener(
            event -> showSelectedStudent()
        );
        
    }    

    @FXML
    private void buttonShowHandle(ActionEvent event) throws Exception {
        ResultSet rs=this.statement.executeQuery("Select * From Student");
        tableView.getItems().clear();
        while(rs.next()){
            Student student=new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setMajor(rs.getString("major"));
            student.setGrade(rs.getInt("grade"));
            tableView.getItems().add(student);
        }
    }

    @FXML
    private void buttonAddHandle(ActionEvent event) throws Exception {
        Integer id=Integer.parseInt(textFieldId.getText());
        String name=textFieldName.getText();
        String major=textFieldMajor.getText();
        Integer grade=Integer.parseInt(textFieldGrade.getText());
        String sql="Insert Into Student values(" +id+ ",'" +name+ "','" +major+ "'," +grade +")";
        this.statement.executeUpdate(sql);
    }

    @FXML
    private void buttonUpdateHandle(ActionEvent event) throws Exception {
        Integer id = Integer.parseInt(textFieldId.getText());
        String name = textFieldName.getText();
        String major = textFieldMajor.getText();
        Integer grade = Integer.parseInt(textFieldGrade.getText());
        String sql = "Update Student Set name='" + name + "', major='" + 
                major + "', grade=" + grade + " Where id=" +id;
        this.statement.executeUpdate(sql);
    }

    @FXML
    private void buttonDeleteHandle(ActionEvent event) throws Exception {
        Integer id=Integer.parseInt(textFieldId.getText());
        String name=textFieldName.getText();
        String major=textFieldMajor.getText();
        Integer grade=Integer.parseInt(textFieldGrade.getText());
        String sql="Delete From Student Where id="+id;
        this.statement.executeUpdate(sql);
    }
    
    private void showSelectedStudent(){
        Student student = tableView.getSelectionModel().getSelectedItem();
        if(student != null){
        textFieldId.setText(String.valueOf(student.getId()));
        textFieldName.setText(student.getName());
        textFieldMajor.setText(student.getMajor());
        textFieldGrade.setText(String.valueOf(student.getGrade()));
        }
}

    @FXML
    private void buttonAddCourseHandle(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("TabelViewPaneCourse.fxml"));
        Parent root=(Parent) fxmlLoader.load();
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
