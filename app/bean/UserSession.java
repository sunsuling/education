package bean;

import static play.mvc.Controller.session;

import java.util.UUID;

import models.AccountUser;
import play.Play;
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


    public static void setCurrent(AccountUser accountUser){
        UserSession us = new UserSession();
        us.userId = accountUser.id.toString();
        us.name = accountUser.username;

        session("education_webtoken", UUID.randomUUID().toString());
        session("education_crmwebtime", String.valueOf(System.currentTimeMillis()));
        Cache.set(
                "user_" + session("education_webtoken"),
                us,
                Integer.valueOf(Play.application().configuration().getString("session.timeout")));

    }


}
