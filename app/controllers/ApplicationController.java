package controllers;

import bean.UserSession;
import models.AccountUser;
import play.mvc.Controller;
import play.mvc.Result;
import utils.DateUtil;
import utils.MD5Helper;
import utils.ValueHelper;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

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

        AccountUser user = AccountUser.findByAccountUser(username, pwd);

        if(ValueHelper.isEmpty(user)){
            // 退回到登陆页面
            flash("error","用户名或密码不正确");
            return redirect("/");
        }else if(user.accountType == 2){
            // 管理员登陆
            UserSession.setCurrent(user);
            return ok(views.html.admin.render());
        }

        return ok();
    }


    public static Result doRegister(){
        return ok(views.html.register.render());
    }

    public static Result register(){
        Map<String, String> formmap = form().bindFromRequest().data();
        ValueHelper.resetParams(formmap);

        String username = formmap.get("username");
        String password = formmap.get("password");
        String pwd = new MD5Helper().encryptPrefix(password);

        AccountUser accountUser = AccountUser.findByAccountUser(username, pwd);
        if (accountUser != null) {
            flash("error", "用户已存在！");
            return redirect("/education/admin/doregister");
        }

        accountUser = new AccountUser();
        accountUser.username = username;
        accountUser.pwd = pwd;
        accountUser.accountType = 2;
        accountUser.createdAt = DateUtil.getCurrent();

        try {
            accountUser.ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        accountUser.save();

        UserSession.setCurrent(accountUser);

        return temporaryRedirect("/education/admin/dologin");
    }
}
