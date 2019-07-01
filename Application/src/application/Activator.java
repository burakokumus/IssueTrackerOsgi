package application;

import com.ekinoks.controller.Controller;
import com.ekinoks.controller.LoginController;
import com.ekinoks.view.LoginView;
import com.ekinoks.view.MainView;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		MainView mainView = new MainView();
		LoginView loginView = new LoginView();
		Controller controller = new Controller(mainView);
		LoginController loginController = new LoginController(loginView, mainView, controller);
		loginController.initController();
		controller.initController();
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
