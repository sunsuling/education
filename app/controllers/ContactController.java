package controllers;


import com.avaje.ebean.ExpressionList;

import models.Contact;
import play.mvc.Controller;
import play.mvc.Result;
import utils.ValueHelper;
/**
 * 联系人的控制器
 * @author Administrator
 *
 */
public class ContactController extends Controller {
	// 联系人首页
	public static Result contact(){
		ExpressionList<Contact> ele = Contact.find.where();
		Contact contact = ele.orderBy().desc("updatedAt").setMaxRows(1).findUnique();
		if(ValueHelper.isEmpty(contact)){
			contact = new Contact();
		}
	    return ok(views.html.contactList.render(contact));
	} 
}
