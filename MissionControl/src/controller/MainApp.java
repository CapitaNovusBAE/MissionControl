package controller;

/**
 * Main controller for User Interfaces
 * @author James Paul Novus BAE
 */

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.home.HomePageController;
import view.admin.AdminPageController;
import view.layout.LayoutController;
import view.navigation.NavigationMenuController;
import view.signin.SignInController;

public class MainApp extends Application {

    private Stage primaryStage, signInStage;
    private AnchorPane signInLayout;
    private BorderPane mainLayout;

    //SET STAGES AND INITIALIZE SIGN IN
    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("APPLICATION NAME TBC");

        this.signInStage = new Stage();
        this.signInStage.setTitle("Sign In");

        SignIn();
    }

    //INITIALIZE SIGN IN STAGE
    public void SignIn(){
    	try{
    		//LOAD FXML
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("/view/signin/SignIn.fxml"));
    		signInLayout = (AnchorPane) loader.load();

    		//CONTROLLER
    		SignInController controller = loader.getController();
    		controller.setMainApp(this);

    		//SET SIGN IN SCENE
    		Scene scene = new Scene(signInLayout);
    		signInStage.setScene(scene);
    		signInStage.show();

    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

    //INITIALIZE PRIMARY STAGE LAYOUT
    public void initLayout() {
        try {
        	//HIDE SIGN IN WINDOW
        	signInStage.hide();

        	//LOAD FXML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/layout/Layout.fxml"));
            mainLayout = (BorderPane) loader.load();

            //CONTROLLER
            LayoutController controller = loader.getController();
    		controller.setMainApp(this);

    		//SET MAIN SCENE
            Scene scene = new Scene(mainLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            navigationMenu();
            homePage();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void navigationMenu(){
    	try{
    	//LOAD FXML
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/view/navigation/NavigationMenu.fxml"));
        AnchorPane navigationMenu = (AnchorPane) loader.load();

        //CONTROLLER
        NavigationMenuController controller = loader.getController();
		controller.setMainApp(this);

		//PLACE IN SCENE
		mainLayout.setLeft(navigationMenu);

    	} catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void homePage(){
    	try{
        	//LOAD FXML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/home/HomePage.fxml"));
            AnchorPane homePage = (AnchorPane) loader.load();

            //CONTROLLER
            HomePageController controller = loader.getController();
    		controller.setMainApp(this);

    		//PLACE IN SCENE
    		mainLayout.setCenter(homePage);

        	} catch (IOException e) {
                e.printStackTrace();
            }
    }

	public void adminPage(){
		try{
			//LOAD FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/admin/AdminPage.fxml"));
			AnchorPane adminPage = (AnchorPane) loader.load();

			//CONTROLLER
			AdminPageController controller = loader.getController();
    		controller.setMainApp(this);

    		//PLACE IN SCENE
    		mainLayout.setCenter(adminPage);

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