package ch4ass;


import java.util.Map;
import java.util.TreeMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dinahabib
 */
public class Q1d extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane paneTableView = FXMLLoader.load(getClass().getResource("TabelViewPaneCourse.fxml"));
        Map <String,Pane> mapPane=new TreeMap<>();
        mapPane.put("tableView", paneTableView);
        Scene scene=new Scene(mapPane.get("tableView"));
        primaryStage.setTitle("java fxml");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
