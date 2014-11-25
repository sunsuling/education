package controllers;


import play.mvc.Controller;
import play.mvc.Result;
/**
 * 联系人的控制器
 * @author Administrator
 *
 */
public class ContactController extends Controller {
	// 联系人首页
	public static Result contact(){
			return ok(views.html.contactList.render());
	} 
}
