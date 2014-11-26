package controllers;


import static play.data.Form.form;

import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

import models.Contact;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.ValueHelper;
import bean.UserSession;

import com.avaje.ebean.ExpressionList;
import com.fasterxml.jackson.databind.node.ObjectNode;

import utils.DateUtil;
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
	    return ok(views.html.contactList.render(contact));

	} 
	
	public static Result saveInformation(){
		Map<String, String> formmap = form().bindFromRequest().data();
		String contactName = ValueHelper.isEmpty(formmap.get("contactName")) ? null : formmap.get("contactName").trim();
		String phone = ValueHelper.isEmpty(formmap.get("phone")) ? null : formmap.get("phone").trim();
		String address = ValueHelper.isEmpty(formmap.get("address")) ? null : formmap.get("address").trim();
		String id = ValueHelper.isEmpty(formmap.get("id")) ? null : formmap.get("id").trim();
		Timestamp now = DateUtil.getCurrent();
		Contact contact = null;
		if(ValueHelper.isEmpty(id) ){
			contact = new Contact();
			contact.createdAt = now;
		}else {
			contact = Contact.find(UUID.fromString(id));
		}
		contact.contactName = contactName;
		contact.address = address;
		contact.phone = phone;
		contact.updatedAt = now;
		contact.save();
		
		ObjectNode node = Json.newObject(); 
		node.put("responseCode", "success");
		return ok(node.toString());
	}
	
}
