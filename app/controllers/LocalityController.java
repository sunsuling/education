package controllers;

import static play.data.Form.form;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import models.Department;
import models.Law;
import models.Locality;
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

public class LocalityController extends Controller{
	public static Result list(){
		return ok(views.html.localityList.render());
	}
	
	// 结果集
    public static Result listRs(String sEcho, String iDisplayStart, String iDisplayLength) {
        int sEchoint = Integer.valueOf(sEcho);
        int maxrows = Integer.valueOf(iDisplayLength);
        int intpage = Integer.valueOf(iDisplayStart);

        Map<String, String> formmap = form().bindFromRequest().data();
        String search = formmap.get("sSearch");
        
        ExpressionList<Locality> ele = Locality.find.where();
        	
        if (ValueHelper.isNotEmpty(search)) {
            ele.or(Expr.ilike("title", "%" + search + "%"), Expr.ilike("detail", "%" + search + "%"));
        }

        ele.orderBy().desc("effectiveAt");
        int total = ele.findRowCount();
        List<Locality> ers = ele.setFirstRow(intpage).setMaxRows(maxrows).findList();

        ObjectNode objectNode = Json.newObject();
        objectNode.put("sEcho", sEchoint + 1);
        objectNode.put("iTotalRecords", String.valueOf(total));
        objectNode.put("iTotalDisplayRecords", String.valueOf(total));
        ArrayNode anode = objectNode.putArray("aaData");

        for (Locality row : ers) {

            ObjectNode robjectNode = anode.addObject();

            robjectNode.put("id", row.id.toString());
            robjectNode.put("title", row.title);
            robjectNode.put("detail", row.detail);            
            robjectNode.put("deleted", row.deleted);
            robjectNode.put("updatedAt", DateUtil.timestamp2String3(row.updatedAt));
            robjectNode.put("createdAt", DateUtil.timestamp2String3(row.createdAt));
            robjectNode.put("effectiveAt", DateUtil.timestamp2String(row.effectiveAt));
        }
        response().setContentType("text/json;charset=UTF-8");
        return ok(objectNode.toString());
    }
    
    /**
     * 新增页面
     * @return
     */
    public static Result add(){
    	return ok(views.html.localityAdd.render());
    }
    
    /**
     * 新增的保存操作
     * @return
     */
    public static Result save(){
    	Map<String, String> formmap = form().bindFromRequest().data();
    	String title = ValueHelper.isEmpty(formmap.get("title")) ? null : formmap.get("title").trim();
    	String detail = ValueHelper.isEmpty(formmap.get("detail")) ? null : formmap.get("detail");
    	String effectiveAt = ValueHelper.isEmpty(formmap.get("effectiveAt")) ? null : formmap.get("effectiveAt");
    	
    	ObjectNode objectNode = Json.newObject();
        response().setContentType("text/json;charset=UTF-8");

    	if(!checkRepeat(null, title)){
    		objectNode.put("responseCode", ResponseCode.REPEATCODE.name());
            return ok(objectNode.toString());    		
    	}
    	
    	Locality locality = new Locality();
    	
    	Timestamp now = DateUtil.getCurrent();
    	String userId = UserSession.getCurrent(session()).userId;
    	
    	locality.title = title;
    	locality.detail = detail;
    	locality.effectiveAt = DateUtil.string2TimestampAuto(effectiveAt);
    	locality.createdAt = now;
    	locality.createdBy = userId;
    	locality.updatedAt = now;
    	locality.updatedBy = userId;
    	
    	locality.save();
    	
    	return ok(objectNode.toString());
    }
    
    /**
     * 编辑
     * @param id 部门规章的主键
     * @return
     */
    public static Result edit(String id){
    	Locality locality = Locality.find(UUID.fromString(id));
    	
    	String effectiveAt = DateUtil.timestamp2String(locality.effectiveAt);
    	
    	return ok(views.html.localityEdit.render(locality,effectiveAt));
    }

    /**
     * 跟新操作
     * @return
     */
    public static Result update(){
    	Map<String, String> formmap = form().bindFromRequest().data();
    	String title = ValueHelper.isEmpty(formmap.get("title")) ? null : formmap.get("title").trim();
    	String detail = ValueHelper.isEmpty(formmap.get("detail")) ? null : formmap.get("detail");
    	String effectiveAt = ValueHelper.isEmpty(formmap.get("effectiveAt")) ? null : formmap.get("effectiveAt");
    	String id = ValueHelper.isEmpty(formmap.get("id")) ? null : formmap.get("id").trim();
    	
    	ObjectNode objectNode = Json.newObject();
        response().setContentType("text/json;charset=UTF-8");

    	if(!checkRepeat(UUID.fromString(id), title)){
    		objectNode.put("responseCode", ResponseCode.REPEATCODE.name());
            return ok(objectNode.toString());    		
    	}
    	
    	Locality locality = Locality.find(UUID.fromString(id));
    	
    	Timestamp now = DateUtil.getCurrent();
    	String userId = UserSession.getCurrent(session()).userId;
    	
    	locality.title = title;
    	locality.detail = detail;
    	locality.effectiveAt = DateUtil.string2TimestampAuto(effectiveAt);
    	
    	locality.updatedAt = now;
    	locality.updatedBy = userId;
    	
    	locality.save();
    	
    	return ok(objectNode.toString());
    	
    }
    
    /**
	 * 删除恢复操作
	 * @param id
	 * @param isDel
	 * @return
	 */
	public static Result del(String id,String isDel){
		Locality locality  = Locality.find(UUID.fromString(id));
		locality.deleted = !Boolean.valueOf(isDel);
		locality.update();
		return ok();
	}

    
    /**
     * 验证重复性
     * @param id 主键id
     * @param message 字段message
     * @return true 表示不存在  false表示存在
     */
    private static boolean checkRepeat(UUID id, String title) {
        int count = 1;
        ExpressionList<Law> ele = Law.find.where();
            ele.eq("title", title);
        
        if (ValueHelper.isNotEmpty(id)) {
            ele.ne("id", id);
        }
        
        count =  ele.findRowCount();
        
        if (count > 0) { 
            return false;
        }

        return true;
    }

}
