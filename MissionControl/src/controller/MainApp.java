package controller;

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
import view.mission.assign.AssignPageController;
import view.navigation.NavigationMenuController;
import view.signin.SignInController;

/**
 * Main class controller for user interfaces.
 * @author James Paul.
 */
public class MainApp extends Application {

	private Stage primaryStage, signInStage;
	private AnchorPane signInLayout;
	private BorderPane mainLayout;
	private User user;

	@Override
	public void start(final Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("APPLICATION NAME TBC");

		this.signInStage = new Stage();
		this.signInStage.setTitle("Sign In");

		initializeSignIn();
	}

	/**
	 * Initialise the sign in stage.
	 * Called from constructor on app start.
	 */
	public void initializeSignIn(){
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

	/**
	 * Initialise layout for main stage.
	 * Called from SignInController upon successful sign in.
	 */
	public void initalizeLayout() {
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

			loadNavigationMenu();
			loadHomePage();

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load navigation menu into the left border pane of main stage layout.
	 * Called upon initialisation of main stage layout.
	 */
	public void loadNavigationMenu(){
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

	/**
	 * Load home page menu into the centre border pane of main stage layout.
	 * Called upon initialisation of main stage layout.
	 * Called upon press of the home button in NavigationMenuController.
	 */
	public void loadHomePage(){
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

	/**
	 * Load administration page menu into the centre border pane of main stage layout.
	 * Called upon press of the administration button in NavigationMenuController.
	 */
	public void loadAdminPage(){
		try{
			//LOAD FXML
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/admin/AdminPage.fxml"));
			final AnchorPane adminPage = (AnchorPane) loader.load();


			final AdminPageController controller = loader.getController();
			controller.setMainApp(this);

			//PLACE IN SCENE
			this.mainLayout.setCenter(adminPage);

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Load assign mission page menu into the centre border pane of main stage layout.
	 * Called upon press of the assign button in NavigationMenuController.
	 */
	public void loadAssignPage(){
		try{
			//LOAD FXML
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/mission/assign/AssignPage.fxml"));
			final AnchorPane page = (AnchorPane) loader.load();

			//CONTROLLER
			final AssignPageController controller = loader.getController();
			controller.setUserName(getUser().getName());

			//PLACE IN SCENE
			this.mainLayout.setCenter(page);

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load review mission page menu into the centre border pane of main stage layout.
	 * Called upon press of the review button in NavigationMenuController.
	 */
	public void loadReviewPage(){
		try{
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/view/mission/review/ReviewPage.fxml"));
			final AnchorPane page = (AnchorPane) loader.load();

			this.mainLayout.setCenter(page);

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return logged in user.
	 */
	public User getUser(){
		return this.user;
	}

	/**
	 * @param user - set to logged in user.
	 */
	public void setUser(final User user){
		this.user = user;
	}

	/**
	 * @return primary stage.
	 */
	public Stage getPrimaryStage() {
		return this.primaryStage;
	}

	/**
	 * run application
	 * @param args default parameter for main.
	 */
	public static void main(final String[] args) {
		launch(args);
	}
}