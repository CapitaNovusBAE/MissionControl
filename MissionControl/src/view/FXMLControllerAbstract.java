package view;

import controller.MainApp;

/**
 * Abstract FXML controller
 * @author James Paul
 */
public abstract class FXMLControllerAbstract {

	/**
	 *
	 */
	public MainApp mainApp;

	/**
	 * @param mainApp - link to MainApp.
	 */
	public void setMainApp(final MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;
	}
}
