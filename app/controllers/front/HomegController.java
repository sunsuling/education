package controllers.front;

import java.util.List;
import java.util.UUID;

import models.CenterDynamic;
import models.Contact;
import models.Summary;
import models.Training;
import play.mvc.Controller;
import play.mvc.Result;
import utils.ValueHelper;

import com.avaje.ebean.ExpressionList;

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
            
            //中心简介
            ExpressionList<Summary> summaryEle = Summary.find.where();
            Summary summary = summaryEle.orderBy().desc("updatedAt").setMaxRows(1).findUnique();
            
            //培训信息
            ExpressionList<Training> trainingEle = Training.find.where();
            List<Training> trainingList = trainingEle.findList();
            return ok(views.html.front.shouye.render(contact,summary,centerDynamicList,trainingList));
        }
        /**
         * 
         * @param id
         * @return中心动态
         */
        public static Result centerDynamic(String id){
        	CenterDynamic centerDynamic = CenterDynamic.find(UUID.fromString(id));
        	return ok(views.html.front.centerDynamic.render(centerDynamic));
        }
        /**
         * 中心动态列表
         * @return
         */
        public static Result centerDynamicMore(){
        	ExpressionList<CenterDynamic> centerDynamicEle = CenterDynamic.find.where();
            List<CenterDynamic> centerDynamicList = centerDynamicEle.findList();
        	return ok(views.html.front.centerDynamicMore.render(centerDynamicList));
        }
        
        /**
         * 培训信息
         * @param id
         * @return
         */
        public static Result training(String id){
        	Training training = Training.find(UUID.fromString(id));
        	return ok(views.html.front.training.render(training));
        }
        
        /**
         * 培训信息列表
         * @return
         */
        public static Result trainingMore(){
        	ExpressionList<Training> trainingEle = Training.find.where();
            List<Training> trainingList = trainingEle.findList();
            return ok(views.html.front.trainingMore.render(trainingList));
        }
}
