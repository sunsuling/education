package bean;

import java.util.UUID;

import play.cache.Cache;
import play.mvc.Http.Session;


public class UserSession {
	public String userId;
	public String name;
	
	  public  UserSession() {
		  
	  }
	  
	  
	  
	  public static UserSession getCurrent(String token){		  
		  Cache.set("user_"+token, Cache.get("user_"+token)); 		  
		  return (UserSession) Cache.get("user_"+token);
	  }
	  
	  public static UserSession getCurrent(Session session){		  
		  Cache.set("user_"+session.get("education_webtoken"), Cache.get("user_"+session.get("education_webtoken")));
		  return (UserSession) Cache.get("user_"+session.get("education_webtoken"));
	  }
	  
	  
}
