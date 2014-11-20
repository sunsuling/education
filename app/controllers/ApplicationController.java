package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class ApplicationController extends Controller {
	public static Result index(){
		return ok(views.html.login.render());
	}
}
