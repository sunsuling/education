package controllers;

import static play.data.Form.form;

import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

import models.Summary;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.DateUtil;
import utils.ValueHelper;

import com.avaje.ebean.ExpressionList;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SummaryController extends Controller {
	
    public static Result summary(){
        ExpressionList<Summary> ele = Summary.find.where();
        Summary summary = ele.orderBy().desc("updatedAt").setMaxRows(1).findUnique();
        return ok(views.html.summaryList.render(summary));
    }

    public static Result saveInformation(){
		Map<String, String> formmap = form().bindFromRequest().data();
		String name = ValueHelper.isEmpty(formmap.get("name")) ? null : formmap.get("name").trim();
		String phone = ValueHelper.isEmpty(formmap.get("phone")) ? null : formmap.get("phone").trim();
		String address = ValueHelper.isEmpty(formmap.get("address")) ? null : formmap.get("address").trim();
		String email = ValueHelper.isEmpty(formmap.get("email")) ? null : formmap.get("email").trim();
		String qq = ValueHelper.isEmpty(formmap.get("qq")) ? null : formmap.get("qq").trim();
		String fax = ValueHelper.isEmpty(formmap.get("fax")) ? null : formmap.get("fax").trim();
		String title1 = ValueHelper.isEmpty(formmap.get("title1")) ? null : formmap.get("title1").trim();
		String title2 = ValueHelper.isEmpty(formmap.get("title2")) ? null : formmap.get("title2").trim();
		String title3 = ValueHelper.isEmpty(formmap.get("title3")) ? null : formmap.get("title3").trim();
		String content1 = ValueHelper.isEmpty(formmap.get("content1")) ? null : formmap.get("content1").trim();
		String content2 = ValueHelper.isEmpty(formmap.get("content2")) ? null : formmap.get("content2").trim();
		String content3 = ValueHelper.isEmpty(formmap.get("content3")) ? null : formmap.get("content3").trim();
		String id = ValueHelper.isEmpty(formmap.get("id")) ? null : formmap.get("id").trim();
		Timestamp now = DateUtil.getCurrent();
		Summary summary = null;
		if(ValueHelper.isEmpty(id) ){
			summary = new Summary();
			summary.createdAt = now;
		}else {
			summary = Summary.find(UUID.fromString(id));
		}
		summary.name = name;
		summary.phone = phone;
		summary.address = address;
		summary.email = email;
		summary.qq = qq;
		summary.fax = fax;
		summary.title1 = title1;
		summary.title2 = title2;
		summary.title3 = title3;
		summary.content1 = content1;
		summary.content2 = content2;
		summary.content3 = content3;
		summary.updatedAt = now;
		summary.save();
		
		ObjectNode node = Json.newObject(); 
		node.put("responseCode", "success");
		return ok(node.toString());
	}
}
