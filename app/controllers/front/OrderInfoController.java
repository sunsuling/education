package controllers.front;

import common.util.CommonUtil;
import common.util.ExcelUtil;
import common.vo.ExcelDemoVo;
import common.vo.PageVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.OrderInfoService;
import service.RuleService;
import service.impl.OrderInfoServiceImpl;
import service.impl.RuleServiceImpl;
import vo.OrderInfoVo;
import vo.RuleVo;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static play.data.Form.form;

/**
 * Created by guxuelong on 2014/12/27.
 */
@Transactional
public class OrderInfoController extends Controller {

    private static final String SYSTEM_ERROR = "系统异常，请联系后台服务维护人员！";
    private OrderInfoService service = new OrderInfoServiceImpl();
    private RuleService ruleService = new RuleServiceImpl();

    /**
     * 查询订单信息列表
     *
     * @return
     */
    public Result findAll() {
        try {
            PageVo pageVo = Json.fromJson(request().body().asJson(), PageVo.class);
            List<OrderInfoVo> list = service.findAll(getDateString(pageVo));
            PageVo vo = new PageVo();
            vo.setList(list);
            return ok(Json.toJson(vo));
        } catch (Exception e) {
            Logger.error("订单信息查询失败；错误信息：" + e.getMessage());
            return Controller.badRequest(SYSTEM_ERROR);
        }

    }


    /**
     * 查询订单信息列表
     *
     * @return
     */
    public Result findByUser() {
        try {
            setEndTimeToSession();
            String user = session().get("user");
            if (StringUtils.isEmpty(user) || "sunlights035".equals(user)) {
                return Controller.badRequest("登录超时，请重新登录！");
            }
            List<OrderInfoVo> list = service.findByUser(user);
            for (OrderInfoVo orderInfoVo : list) {
                if (canBeToDelete(orderInfoVo.getCreateTime())) {
                    orderInfoVo.setCanBeToDel("Y");
                }
            }
            PageVo vo = new PageVo();
            vo.setList(list);
            return ok(Json.toJson(vo));
        } catch (Exception e) {
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
     * 添加新订单
     *
     * @return
     */
    public Result add() {
        try {

            String user = session().get("user");
            // 用户登录信息校验
            if (StringUtils.isEmpty(user) || "sunlights035".equals(user)) {
                return Controller.badRequest("登录超时，请重新登录！");
            }
            // 获取设置订餐截止时间
            setEndTimeToSession();

            // 订餐时间控制
            if (!isOrderTime()) {
                return Controller.badRequest(getEndTimeError());
            }

            PageVo pageVo = Json.fromJson(request().body().asJson(), PageVo.class);
            List<Map> orderInfoVoList = pageVo.getList();

            if(!"guest".equals(user) && orderInfoVoList.size() > 1){
                return Controller.badRequest("暂定一人一份，请删除购物车中订单重新提交！");
            }

            for (Map map : orderInfoVoList) {
                OrderInfoVo orderInfoVo = getOrderInfoVo(user, map);
                service.add(orderInfoVo);
            }
            return ok();
        } catch (Exception e) {
            Logger.error("订单信息添加失败；错误信息：" + e.getMessage());
            return Controller.badRequest(e.getMessage());
        }
    }

    private String getEndTimeError() {
        String errorMsg = null;
        String lunchEndTime = session().get("Lunch");
        int endTime = Integer.valueOf(lunchEndTime) + 2;
        if (new DateTime().getHourOfDay() <= endTime) {
            errorMsg = "请在" + lunchEndTime + "时之前订餐！";
        } else {
            String dinnerEndTime = session().get("Dinner");
            errorMsg = "请在" + endTime + "时到" + dinnerEndTime + "时之间订餐！";
        }
        return errorMsg;
    }

    private OrderInfoVo getOrderInfoVo(String user, Map map) throws IllegalAccessException, InvocationTargetException {
        OrderInfoVo orderInfoVo = new OrderInfoVo();
        BeanUtils.copyProperties(orderInfoVo, map);
        orderInfoVo.setId(null);
        orderInfoVo.setUserId(user);
        orderInfoVo.setUserName(session().get("userName"));
        return orderInfoVo;
    }


    private boolean isOrderTime() {
        DateTime dateTime = new DateTime();
        if (dateTime.getHourOfDay() < Integer.valueOf(session().get("Lunch"))) {
            return true;
        }

        if (dateTime.getHourOfDay() > Integer.valueOf(session().get("Lunch")) + 2
                && dateTime.getHourOfDay() < Integer.valueOf(session().get("Dinner"))) {
            return true;
        }
        return false;
    }

    /**
     * 更新订单信息
     *
     * @return
     */
    public Result update() {
        try {
            OrderInfoVo orderInfoVo = Json.fromJson(request().body().asJson(), OrderInfoVo.class);
            service.update(orderInfoVo);
            return ok();
        } catch (Exception e) {
            Logger.error("订单信息更新失败；错误信息：" + e.getMessage());
            return Controller.badRequest(e.getMessage());
        }
    }

    /**
     * 删除订单信息（逻辑删除）
     *
     * @return
     */
    public Result delete() {

        try {
            setEndTimeToSession();
            OrderInfoVo orderInfoVo = Json.fromJson(request().body().asJson(), OrderInfoVo.class);
            if (!canBeToDelete(orderInfoVo.getCreateTime())) {
                return Controller.badRequest("订单不可修改");
            }
            service.delete(orderInfoVo);
            return ok();
        } catch (Exception e) {
            Logger.error("订单信息删除失败；错误信息：" + e.getMessage());
            return Controller.badRequest(SYSTEM_ERROR);
        }
    }

    private void setEndTimeToSession() throws Exception {
        if (session().get("Lunch") == null || session().get("Dinner") == null) {
            List<RuleVo> rules = ruleService.findByType("EndTime");
            for (RuleVo rule : rules) {
                session().put(rule.getRuleKey(), rule.getRuleValue());
            }
        }
    }

    private boolean canBeToDelete(Date createTime) {
        DateTime dateTime = new DateTime();
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(dateTime.getYear());
        strBuilder.append(dateTime.getMonthOfYear());
        strBuilder.append(dateTime.getDayOfMonth());

        DateTime createTimeDt = new DateTime(createTime);
        StringBuilder createTimeStr = new StringBuilder();
        createTimeStr.append(createTimeDt.getYear());
        createTimeStr.append(createTimeDt.getMonthOfYear());
        createTimeStr.append(createTimeDt.getDayOfMonth());

        if (!strBuilder.toString().equals(createTimeStr.toString())) {
            return false;
        }
        int hour = dateTime.getHourOfDay();
        String lunchEndTime = session().get("Lunch");
        String dinnerEndTime = session().get("Dinner");
        if (hour > Integer.valueOf(dinnerEndTime)) {
            return false;
        }

        if (hour > Integer.valueOf(lunchEndTime) && createTimeDt.getHourOfDay() < Integer.valueOf(lunchEndTime)) {
            return false;
        }
        return true;
    }

    /**
     * 导出订餐清单
     *
     * @return
     */
    public Result exportDailyOrderListTotal() {
        Logger.info(">>>>>>>>exportDailyOrderListTotal  start");
        try {
            String createTime = getCreateTimeStr();
            String fileName = "订餐清单" + createTime + ".xls";
            setResponse(fileName);
            File chunks = exportToExcelForTotal(createTime, fileName);
            Logger.info(">>>>>>>>exportDailyOrderListTotal  end");
            return ok(chunks);
        } catch (Exception e) {
            Logger.error("exportDailyOrderListTotal error：" + e.getMessage());
            return Controller.badRequest(SYSTEM_ERROR);
        }
    }

    /**
     * 导出订单明细
     *
     * @return
     */
    public Result exportDailyOrderListDetail() {
        Logger.info(">>>>>>>>exportOrderListDetail  start");
        try {
            String createTime = getCreateTimeStr();
            String fileName = "订单明细" + createTime + ".xls";
            setResponse(fileName);
            File chunks = exportToExcelForDetail(createTime, fileName);
            Logger.info(">>>>>>>>exportOrderListDetail  end");
            return ok(chunks);
        } catch (Exception e) {
            Logger.error("exportOrderListDetail error：" + e.getMessage());
            return Controller.badRequest(SYSTEM_ERROR);
        }
    }

    private void setResponse(String fileName) throws Exception {
        response().setContentType("application/x-excel;charset=UTF-8");//可选择不同类型
        response().setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));

    }

    private File exportToExcelForTotal(String createTime, String fileName) throws Exception {
        // query order list to set exportVo
        ExcelDemoVo excelDemoVo = service.queryDailyOrderList(createTime);
        excelDemoVo.setFileName(fileName);
        // export order list to excel
        return ExcelUtil.exportExcel(excelDemoVo);
    }

    private File exportToExcelForDetail(String createTime, String fileName) throws Exception {
        // query order list to set exportVo
        ExcelDemoVo excelDemoVo = service.queryDailyOrderDetail(createTime);
        excelDemoVo.setFileName(fileName);
        // export order list to excel
        return ExcelUtil.exportExcel(excelDemoVo);
    }

    private String getCreateTimeStr() throws Exception {
        PageVo pageVo = new PageVo();
        Map<String, String> params = form().bindFromRequest().data();
        Iterator iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            pageVo.put(key, ConvertUtils.convert(params.get(key), String.class));
        }
        return (String) pageVo.get("createTime");
    }
}
