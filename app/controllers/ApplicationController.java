package controllers;

import static play.data.Form.form;

import java.util.Map;
import java.util.UUID;

import models.AccountUser;

import play.Play;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.Result;
import utils.MD5Helper;
import utils.ValueHelper;
import bean.UserSession;

import com.avaje.ebean.ExpressionList;

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
			UserSession us = new UserSession();
			us.userId = user.id.toString();
			us.name = user.username;
			
			session("education_webtoken",UUID.randomUUID().toString());
			session("education_crmwebtime",
					String.valueOf(System.currentTimeMillis()));
			Cache.set(
					"user_" + session("education_webtoken"),
					us,
					Integer.valueOf(Play.application().configuration()
							.getString("session.timeout")));
			return ok(views.html.admin.render());
		}
		
		return ok();
	}
}
