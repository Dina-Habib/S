/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch4AssQ2;

import java.util.Map;
import java.util.TreeMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author dinahabib
 */
public class Ch4AssQ2 extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane paneTableView = FXMLLoader.load(getClass().getResource("Q2.fxml"));
        Map <String,Pane> mapPane=new TreeMap<>();
        mapPane.put("tableViewTextArea", paneTableView);
        Scene scene=new Scene(mapPane.get("tableViewTextArea"));
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
