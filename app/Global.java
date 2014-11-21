import play.Application;
import play.GlobalSettings;
import play.Logger;

public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app) {
		 
		Logger.info("education Application started...");
    }


	@Override
	public void onStop(Application app) {
	}
}
