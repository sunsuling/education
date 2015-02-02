package controllers.front;

import java.util.List;
import java.util.UUID;

import com.avaje.ebean.ExpressionList;
import models.CenterDynamic;
import models.Contact;
import play.mvc.*;

/**
 * Created by Administrator on 2015/1/29.
 */
public class HomegController extends Controller {
        public static Result home(){
            // 查找联系人
            ExpressionList<Contact> ele = Contact.find.where();
            Contact contact = ele.orderBy().desc("updatedAt").setMaxRows(1).findUnique();

            // 中心动态
            ExpressionList<CenterDynamic> centerDynamicEle = CenterDynamic.find.where();

            List<CenterDynamic> centerDynamicList = centerDynamicEle.findList();
            
            return ok(views.html.front.shouye.render(contact,centerDynamicList));
        }
        
        public static Result centerDynamic(String id){
        	CenterDynamic centerDynamic = CenterDynamic.find(UUID.fromString(id));
        	return ok(views.html.front.centerDynamic.render(centerDynamic));
        }
        
        public static Result centerDynamicMore(){
        	ExpressionList<CenterDynamic> centerDynamicEle = CenterDynamic.find.where();
            List<CenterDynamic> centerDynamicList = centerDynamicEle.findList();
        	return ok(views.html.front.centerDynamicMore.render(centerDynamicList));
        }
}
