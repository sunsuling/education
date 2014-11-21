package controllers;

import java.util.Map;

import com.avaje.ebean.ExpressionList;

import models.AccountUser;

import play.mvc.Controller;
import play.mvc.Result;
import utils.MD5Helper;
import utils.ValueHelper;

import static play.data.Form.form;

public class ApplicationController extends Controller {
	public static Result index(){
		return ok(views.html.login.render());
	}
	
	public static Result doLogin(){
		Map<String, String> formmap = form().bindFromRequest().data();
		ValueHelper.resetParams(formmap);

		String username = formmap.get("username");
		String password = formmap.get("password");
		
		System.out.println(username+":"+password+"\n");
		// 密码加密
		String pwd = new MD5Helper().encryptPrefix(password);
		System.out.println(pwd+"\n");
		
		ExpressionList<AccountUser> ele = AccountUser.find.where();
		ele.eq("username", username);
		ele.eq("pwd", pwd);
		AccountUser user = ele.setMaxRows(1).findUnique();
		
		if(ValueHelper.isEmpty(user)){
			// 退回到登陆页面
			flash("error","用户名或密码不正确");
			return redirect("/");
		}else if(user.accountType == 2){
			// 管理员登陆
			return ok(views.html.admin.render());
		}
		
		return ok();
	}
}
