package utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import vo.PageVo;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;

import consts.AppConst;

/**
 * <p>Project: train</p>
 * <p>Title: CommonUtil.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class CommonUtil {
    /**
     * 分页查询实体对象，未删除倒序
     * 过滤条件只能是equals
     * @param pageVo
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<T> searchEntity(PageVo pageVo, Class<T> t){
        int index = pageVo.index;
        int pageSize = pageVo.pageSize;
        Map<String, Object> filterMap = pageVo.filterMap;

        ExpressionList<?> expression = Ebean.createQuery(t).where().eq(AppConst.DELETED, AppConst.VALID);

        int count = expression.findRowCount();
        pageVo.count = count;

        Iterator iterator = filterMap.keySet().iterator();
        while(iterator.hasNext()){
            String key = (String)iterator.next();
            Object value = filterMap.get(key);

            expression.eq(key, value);
        }

        List<T> list = (List<T>) expression.setFirstRow(index).setMaxRows(index + pageSize).orderBy(AppConst.CREATEDAT_DESC).findList();
        pageVo.list = list;

        return list;
    }
    
}
