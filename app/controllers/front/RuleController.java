package controllers.front;

import common.util.CommonUtil;
import common.vo.PageVo;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.RuleService;
import service.impl.RuleServiceImpl;
import vo.RuleVo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by guxuelong on 2014/12/27.
 */
@Transactional
public class RuleController extends Controller {

    private static final String SYSTEM_ERROR = "系统异常，请联系后台服务维护人员！";
    private RuleService service = new RuleServiceImpl();


    /**
     * 查询订单信息列表
     *
     * @return
     */
    public Result findAll(){
        try{
            List<RuleVo> list =  service.findAll();
            PageVo vo = new PageVo();
            vo.setList(list);
            return ok(Json.toJson(vo));
        }catch(Exception e){
            Logger.error("订单信息查询失败；错误信息：" + e.getMessage());
            return Controller.badRequest(SYSTEM_ERROR);
        }

    }

    private String getDateString(PageVo pageVo) throws Exception {
        String chkDate = ((String) pageVo.getFilter().get("createTime")).substring(0, 10);
        return getDate(chkDate);
    }

    private String getDate(String chkDate) {
        Date date = CommonUtil.stringToDate(chkDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        chkDate = CommonUtil.dateToString(cal.getTime());
        return chkDate;
    }

    /**
     * 添加新规则
     *
     * @return
     */
    public Result add(){
        try{
            RuleVo ruleVo = Json.fromJson(request().body().asJson(), RuleVo.class);
            service.add(ruleVo);
            return ok();
        }catch(Exception e){
            Logger.error("订单信息添加失败；错误信息：" + e.getMessage());
            return Controller.badRequest(e.getMessage());
        }
    }


    /**
     * 更新规则信息
     *
     * @return
     */
    public Result update(){
        try{
            RuleVo ruleVo = Json.fromJson(request().body().asJson(), RuleVo.class);
            service.update(ruleVo);
            return ok();
        }catch(Exception e){
            Logger.error("订单信息更新失败；错误信息：" + e.getMessage());
            return Controller.badRequest(e.getMessage());
        }
    }

    /**
     * 删除规则信息（逻辑删除）
     *
     * @return
     */
    public Result delete(){
        try{
            RuleVo ruleVo = Json.fromJson(request().body().asJson(), RuleVo.class);
            service.delete(ruleVo);
            return ok();
        }catch(Exception e){
            Logger.error("订单信息删除失败；错误信息：" + e.getMessage());
            return Controller.badRequest(SYSTEM_ERROR);
        }
    }
}
