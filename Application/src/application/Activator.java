package application;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.ekinoks.controller.Controller;
import com.ekinoks.controller.LoginController;

public class Activator implements BundleActivator
{

	private static BundleContext context;

	static BundleContext getContext()
	{
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception
	{

		Controller controller = new Controller();
		LoginController loginController = new LoginController(controller);
		loginController.initController();
		controller.initController();
	}

	public void stop(BundleContext bundleContext) throws Exception
	{
		Activator.context = null;
	}

}
