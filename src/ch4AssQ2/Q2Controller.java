/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch4AssQ2;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author dinahabib
 */
public class Q2Controller implements Initializable {

    @FXML
    private TextArea textArea;
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, Integer> tcId;
    @FXML
    private TableColumn<Student, String> tcName;
    @FXML
    private TableColumn<Student, String> tcMajor;
    @FXML
    private TableColumn<Student, Integer> tcGrade;
    Statement statement;
    @FXML
    private Button buttonShow;

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
    }    

    @FXML
    private void buttonShowHandle(ActionEvent event) throws Exception {
        if(textArea.getText().equals("Select * From Student Where major='Software Engineering'")){
        ResultSet rs=this.statement.executeQuery("Select * From Student Where major='Software Engineering'");
        tableView.getItems().clear();
        while(rs.next()){
            Student student=new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setMajor(rs.getString("major"));
            student.setGrade(rs.getInt("grade"));
            tableView.getItems().addAll(student);
        }
        }else if(textArea.getText().equals("Select * From Student Where grade >90")){
        ResultSet rs=this.statement.executeQuery("Select * From Student Where grade >90");
        tableView.getItems().clear();
        while(rs.next()){
            Student student=new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setMajor(rs.getString("major"));
            student.setGrade(rs.getInt("grade"));
            tableView.getItems().add(student);
        }
        }else if(textArea.getText().equals("Select all pass students in ascending order based on their names")){
        ResultSet rs=this.statement.executeQuery("Select * From Student Where grade >60 Order By name ASC ");
        tableView.getItems().clear();
        while(rs.next()){
            Student student=new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setMajor(rs.getString("major"));
            student.setGrade(rs.getInt("grade"));
            tableView.getItems().add(student);
        }
        }else if(textArea.getText().equals("For all students studying Computer Science major and have grade less than 70%, increase their grades by 3")){
        String sql = "Update Student Set grade= grade + "+3+" Where major='Computer Science' And grade <70" ;
        this.statement.executeUpdate(sql);
        ResultSet rs=this.statement.executeQuery("Select * From Student ");
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
    }
    
}
