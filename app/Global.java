import static play.mvc.Results.redirect;

import java.lang.reflect.Method;
import java.text.MessageFormat;

import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import bean.UserSession;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {

        Logger.info("education Application started...");
    }


    @Override
    public void onStop(Application app) {
    }

    @Override
    public Action onRequest(Http.Request request, Method method) {
        Action action = new Action.Simple() {
            @Override
            public F.Promise<Result> call(Http.Context context) throws Throwable {
                Http.Request request = context.request();
                String path = request.path();
                String clientAddress = request.remoteAddress();

                String pattern = "onRequest  clientAddress = {0}，path = {1}";
                Logger.info(MessageFormat.format(pattern, clientAddress, path));

                if (path.matches("/education/.*") && !path.matches("/education/admin/dologin")
                        && !path.matches("/education/admin/doregister")
                        && !path.matches("/education/admin/register")){
                    UserSession userSession = UserSession.getCurrent(context.session());
                    if (userSession == null) {
                        return F.Promise.pure(redirect("/"));
                    }
                }

                F.Promise callPoint = delegate.call(context);
                return callPoint;
            }
        };

        return action;
    }

    @Override
    public F.Promise<Result> onHandlerNotFound(Http.RequestHeader requestHeader) {
        Logger.info("=======onHandlerNotFound===============");
        Http.Context context = Http.Context.current();
        UserSession userSession = UserSession.getCurrent(context.session());
        if (userSession == null) {
            return F.Promise.pure(redirect("/"));
        }
        return super.onHandlerNotFound(requestHeader);
    }
}
