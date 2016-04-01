package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.signin.SignInController;

public class MainApp extends Application {

    private Stage primaryStage, signInStage;
    private AnchorPane signInLayout;
    private BorderPane mainLayout;
    private GridPane gridPane;

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("APPLICATION NAME TBC");

        this.signInStage = new Stage();
        this.signInStage.setTitle("Sign In");

        SignIn();
        
        //showMap();
        //showWaypointList();
        //showWaypointPlacement();
    }

    public void SignIn(){
    	try{

    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("/view/signin/SignIn.fxml"));
    		signInLayout = (AnchorPane) loader.load();

    		SignInController controller = loader.getController();
    		controller.setMainApp(this);

    		Scene scene = new Scene(signInLayout);
    		signInStage.setScene(scene);
    		signInStage.show();

    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

    public void initMainLayout() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/MainLayout.fxml"));
            mainLayout = (BorderPane) loader.load();

            gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);
      
            mainLayout.setCenter(gridPane);
            
            signInStage.hide();

            // Show the scene containing the root layout.
            Scene scene = new Scene(mainLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            navigation();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public void navigation(){
	
		try{
	    	FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/navigation/NavigationPage.fxml"));
			AnchorPane navigation = (AnchorPane) loader.load();
	
			gridPane.add(navigation, 0, 0);
			//mainLayout.setCenter(navigation);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

/*
    public void showMap(){
    	try{
    		// Load root layout from fxml file.
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("/view/WaypointList.fxml"));
    		AnchorPane Map = (AnchorPane) loader.load();

            gridPane.add(Map, 1, 0);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

    public void showWaypointList(){
    	try{
    		// Load root layout from fxml file.
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("/view/Map.fxml"));
    		AnchorPane waypointList = (AnchorPane) loader.load();

            gridPane.add(waypointList, 0, 0);

//          Give the controller access to the main app.
//          PersonOverviewController controller = loader.getController();
//          controller.setMainApp(this);

    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

    public void showWaypointPlacement(){
    	try{
    		// Load root layout from fxml file.
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("/view/WaypointPlacement.fxml"));
    		AnchorPane waypointPlacement = (AnchorPane) loader.load();

            gridPane.add(waypointPlacement, 0, 1);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

    //NEED TO MERGE CELLS 0/1 AND 1/1 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
*/


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}