package controllers;

import static play.data.Form.form;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import models.AccidentAnalysis;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.DateUtil;
import utils.ValueHelper;
import bean.UserSession;

import com.avaje.ebean.Expr;
import com.avaje.ebean.ExpressionList;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import controllers.enums.ResponseCode;
/**
 * 事故案例分析
 * @author Administrator
 *
 */
public class AccidentAnalysisController extends Controller {
	public static Result list(){
		return ok(views.html.accidentAnalysisList.render());
	}
	
	// 结果集
    public static Result listRs(String sEcho, String iDisplayStart, String iDisplayLength) {
        int sEchoint = Integer.valueOf(sEcho);
        int maxrows = Integer.valueOf(iDisplayLength);
        int intpage = Integer.valueOf(iDisplayStart);

        Map<String, String> formmap = form().bindFromRequest().data();
        String search = formmap.get("sSearch");
        
        ExpressionList<AccidentAnalysis> ele = AccidentAnalysis.find.where();

        	
        if (ValueHelper.isNotEmpty(search)) {
            ele.or(Expr.ilike("message", "%" + search + "%"), Expr.ilike("detail", "%" + search + "%"));
        }

        ele.orderBy().desc("updatedAt");
        int total = ele.findRowCount();
        List<AccidentAnalysis> ers = ele.setFirstRow(intpage).setMaxRows(maxrows).findList();

        ObjectNode objectNode = Json.newObject();
        objectNode.put("sEcho", sEchoint + 1);
        objectNode.put("iTotalRecords", String.valueOf(total));
        objectNode.put("iTotalDisplayRecords", String.valueOf(total));
        ArrayNode anode = objectNode.putArray("aaData");

        for (AccidentAnalysis row : ers) {

            ObjectNode robjectNode = anode.addObject();

            robjectNode.put("id", row.id.toString());
            robjectNode.put("message", row.message);
            robjectNode.put("detail", row.detail);            
            robjectNode.put("deleted", row.deleted);
            robjectNode.put("updatedAt", DateUtil.timestamp2String3(row.updatedAt));
            robjectNode.put("createdAt", DateUtil.timestamp2String3(row.createdAt));
        }
        response().setContentType("text/json;charset=UTF-8");
        return ok(objectNode.toString());
    }
    
    /**
     * 新增页面
     * @return
     */
    public static Result add(){
    	return ok(views.html.accidentAnalysisAdd.render());
    }
    
    /**
     * 新增的保存操作
     * @return
     */
    public static Result save(){
    	Map<String, String> formmap = form().bindFromRequest().data();
    	String message = ValueHelper.isEmpty(formmap.get("message")) ? null : formmap.get("message").trim();
    	String detail = ValueHelper.isEmpty(formmap.get("detail")) ? null : formmap.get("detail");
    	
    	ObjectNode objectNode = Json.newObject();
        response().setContentType("text/json;charset=UTF-8");

    	if(!checkRepeat(null, message)){
    		objectNode.put("responseCode", ResponseCode.REPEATCODE.name());
            return ok(objectNode.toString());    		
    	}
    	
    	AccidentAnalysis accidentAnalysis = new AccidentAnalysis();
    	
    	Timestamp now = DateUtil.getCurrent();
    	String userId = UserSession.getCurrent(session()).userId;
    	
    	accidentAnalysis.message = message;
    	accidentAnalysis.detail = detail;
    	
    	accidentAnalysis.createdAt = now;
    	accidentAnalysis.createdBy = userId;
    	accidentAnalysis.updatedAt = now;
    	accidentAnalysis.updatedBy = userId;
    	
    	accidentAnalysis.save();
    	
    	return ok(objectNode.toString());
    }
    
    /**
     * 验证重复性
     * @param id 主键id
     * @param message 字段message
     * @return true 表示不存在  false表示存在
     */
    private static boolean checkRepeat(UUID id, String message) {
        int count = 1;
        ExpressionList<AccidentAnalysis> ele = AccidentAnalysis.find.where();
            ele.eq("message", message);
        
        if (ValueHelper.isNotEmpty(id)) {
            ele.ne("id", id);
        }
        
        count =  ele.findRowCount();
        
        if (count > 0) { 
            return false;
        }

        return true;
    }
    
    /**
     * 编辑操作
     * @return
     */
    public static Result edit(){
		Map<String, String> formmap = form().bindFromRequest().data();
		String id = ValueHelper.isEmpty(formmap.get("id")) ? null : formmap.get("id").trim();
		System.out.println(id);
		AccidentAnalysis accidenAnalysis = null;
		accidenAnalysis = AccidentAnalysis.find(UUID.fromString(id));
		return ok(views.html.accidentAnalysisEdit.render(accidenAnalysis));
	}
	
    /**
     * 编辑的保存操作
     * @return
     */
	public static Result update(){
		Map<String, String> formmap = form().bindFromRequest().data();
		String message = ValueHelper.isEmpty(formmap.get("message")) ? null
				: formmap.get("message").trim();
		String detail = ValueHelper.isEmpty(formmap.get("detail")) ? null
				: formmap.get("detail");
		String id = ValueHelper.isEmpty(formmap.get("id")) ? null
				: formmap.get("id").trim();
		
		ObjectNode objectNode = Json.newObject();
		response().setContentType("text/json;charset=UTF-8");
		if (!checkRepeat(UUID.fromString(id), message)) {
			objectNode.put("responseCode", ResponseCode.REPEATCODE.name());
			return ok(objectNode.toString());
		}
		 
		AccidentAnalysis accidenAnalysis = null;
		Timestamp now = DateUtil.getCurrent();
		
		accidenAnalysis = AccidentAnalysis.find(UUID.fromString(id));
		String userId = UserSession.getCurrent(session()).userId;
		
		accidenAnalysis.message = message;
		accidenAnalysis.detail = detail;

		accidenAnalysis.updatedAt = now;
		accidenAnalysis.updatedBy = userId;

		accidenAnalysis.save();

		return ok(objectNode.toString());
	}
	
	/**
	 * 删除恢复操作
	 * @param id
	 * @param isDel
	 * @return
	 */
	public static Result del(String id,String isDel){
		AccidentAnalysis accidenAnalysis  = AccidentAnalysis.find(UUID.fromString(id));
		accidenAnalysis.deleted = !Boolean.valueOf(isDel);
		accidenAnalysis.update();
		return ok();
	}

}
