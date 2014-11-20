package controllers;


import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.AccidentService;
import vo.PageVo;

/**
 * <p>Project: train</p>
 * <p>Title: AccidentController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class AccidentController extends Controller{

    public static Result searchAccidentLetters(){
        PageVo pageVo = Json.fromJson(request().body().asJson(), PageVo.class);
        AccidentService.searchAccidentLetters(pageVo);


        return ok(Json.toJson(pageVo));
    }



    public static Result searchAccidentAnalysis(){
        PageVo pageVo = Json.fromJson(request().body().asJson(), PageVo.class);
        AccidentService.searchAccidentAnalysis(pageVo);

        return ok(Json.toJson(pageVo));
    }

}
