package controller;

/**
 * Main controller for User Interfaces
 * @author James Paul Novus BAE
 */

import java.io.IOException;

import dao.user.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.admin.AdminPageController;
import view.home.HomePageController;
import view.layout.LayoutController;
import view.navigation.NavigationMenuController;
import view.signin.SignInController;

public class MainApp extends Application {

	private Stage primaryStage, signInStage;
	private AnchorPane signInLayout;
	private BorderPane mainLayout;
	private User user;
	//SET STAGES AND INITIALIZE SIGN IN
	@Override
	public void start(final Stage primaryStage) {

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
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/signin/SignIn.fxml"));
			this.signInLayout = (AnchorPane) loader.load();

			//CONTROLLER
			final SignInController controller = loader.getController();
			controller.setMainApp(this);

			//SET SIGN IN SCENE
			final Scene scene = new Scene(this.signInLayout);
			this.signInStage.setScene(scene);
			this.signInStage.show();

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	//INITIALIZE PRIMARY STAGE LAYOUT
	public void initLayout() {
		try {
			//HIDE SIGN IN WINDOW
			this.signInStage.hide();

			//LOAD FXML
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/layout/Layout.fxml"));
			this.mainLayout = (BorderPane) loader.load();

			//CONTROLLER
			final LayoutController controller = loader.getController();
			controller.setMainApp(this);

			//SET MAIN SCENE
			final Scene scene = new Scene(this.mainLayout);
			this.primaryStage.setScene(scene);
			this.primaryStage.show();

			navigationMenu();
			homePage();

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public void navigationMenu(){
		try{
			//LOAD FXML
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/navigation/NavigationMenu.fxml"));
			final AnchorPane navigationMenu = (AnchorPane) loader.load();

			//CONTROLLER
			final NavigationMenuController controller = loader.getController();
			controller.setMainApp(this);

			//PLACE IN SCENE
			this.mainLayout.setLeft(navigationMenu);

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public void homePage(){
		try{
			//LOAD FXML
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/home/HomePage.fxml"));
			final AnchorPane homePage = (AnchorPane) loader.load();

			//CONTROLLER
			final HomePageController controller = loader.getController();
			controller.setMainApp(this);

			//PLACE IN SCENE
			this.mainLayout.setCenter(homePage);

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public void adminPage(){
		try{
			//LOAD FXML
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/admin/AdminPage.fxml"));
			final AnchorPane adminPage = (AnchorPane) loader.load();

			//CONTROLLER
			final AdminPageController controller = loader.getController();
			controller.setMainApp(this);

			//PLACE IN SCENE
			this.mainLayout.setCenter(adminPage);

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Load assign page
	 */
	public void loadAssignPage(){
		try{
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/mission/assign/AssignPage.fxml"));
			final AnchorPane page = (AnchorPane) loader.load();

			this.mainLayout.setBottom(page);

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public User getUser(){
		return this.user;
	}


	public Stage getPrimaryStage() {
		return this.primaryStage;
	}

	public static void main(final String[] args) {
		launch(args);
	}
}