package controllers.front;

import play.mvc.*;

/**
 * Created by Administrator on 2015/1/29.
 */
public class HomegController extends Controller {
        public static Result home(){
            return ok(views.html.front.shouye.render());
        }
}
