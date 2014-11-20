package bean;

import java.util.List;
import java.util.Map;


import play.cache.Cache;
import play.mvc.Http.Session;


public class UserSession {
	
	  public  UserSession() {
		  
	  }
	  
	  
	  
	  public static UserSession getCurrent(String token){		  
		  Cache.set("crmwebuser_"+token, Cache.get("crmwebuser_"+token)); 		  
		  return (UserSession) Cache.get("crmwebuser_"+token);
	  }
	  
	  public static UserSession getCurrent(Session session){		  
		  Cache.set("crmwebuser_"+session.get("WilmarTech_crmwebtoken"), Cache.get("crmwebuser_"+session.get("WilmarTech_crmwebtoken")));
		  return (UserSession) Cache.get("crmwebuser_"+session.get("WilmarTech_crmwebtoken"));
	  }
	  
	  
}
