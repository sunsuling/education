package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import play.Logger;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import exceptions.BusinessException;

/**
* Created by Administrator on 2014/11/13.
*/
public final class ConverterUtil {

    private ConverterUtil() {
    }

    /**
     * 把VO对象转换为对应的实体对象，要求VO对象和实体对象有相同的属性
     *
     * @param entity
     * @param vo
     * @param <E>
     * @param <V>
     * @return
     * @throws BusinessException
     */
    public static <E, V> E toEntity(E entity, V vo) throws BusinessException {
        try {
            BeanUtils.copyProperties(entity, vo);
        } catch (Exception e) {
            throw new BusinessException("转换" + vo.getClass().getName() + "失败", e);
        }
        return entity;
    }

    /**
     * 把实体对象转换为对应的VO对象，要求VO对象和实体对象有相同的属性
     */
    public static <E, V> V fromEntity(V vo, E entity) throws BusinessException {
        try {
            BeanUtils.copyProperties(vo, entity);
        } catch (Exception e) {
            throw new BusinessException("转换" + entity.getClass().getName() + "失败", e);
        }
        return vo;
    }

    /**
     * 把Map对象转换为对应的对象，map的key和实体的属性需要对应
     * @param mapObj
     * @param object
     * @param <O>
     * @return
     * @see #createMap(java.util.List, java.util.List)
     */
    public static <O> O convertMap2Object(Map<String, Object> mapObj, O object) throws BusinessException {
        for (String key : mapObj.keySet()) {
            try {
                BeanUtils.setProperty(object, key, mapObj.get(key));
            } catch (Exception e) {
                throw new BusinessException(object.getClass().getName()+" 没有对应的属性：" + key, e);
            }
        }
        return object;
    }

    /**
     * 把两个list对象转换为一个map对象
     * @param keys
     * @param values
     * @return
     */
    public static Map<String, Object> createMap(List<String> keys, List<Object> values){
        if(keys == null || values == null || keys.size()!=values.size()){
            throw new IllegalArgumentException("keys和values不能为空，且他们的size要相等");
        }

        Map<String, Object> result = Maps.newHashMap();
        for (int i = 0; i < keys.size(); i++) {
            result.put(keys.get(i), values.get(i));
        }

        return result;
    }

    public static<T> List<T> convert(String keys, List<Object[]> resultRows, Class<T> clazz) {
        List<T> list = Lists.newArrayList();
        for (Object[] row : resultRows) {
            List<Object> objects = Arrays.asList(row);

            Map<String, Object> objectMap = ConverterUtil.createMap(Arrays.asList(keys.split(",")), objects);
            try {
                T vo = clazz.newInstance();
                ConverterUtil.convertMap2Object(objectMap, vo);
                list.add(vo);
            } catch (Exception e) {
                Logger.error(e.toString(), e);
            }

        }
        return list;
    }

    /**
     * 转换相同类型不同的对象的值
     * 将oldObject中不为空的值而newObject为空的值转到newObject中去
     * @param oldObject
     * @param newObject
     * @param clazz
     * @param <T>
     * @throws Exception
     */
    public static<T> void covertSameObjValue(T oldObject, T newObject, Class<T> clazz) throws Exception {
        Field[] declaredFields = clazz.getDeclaredFields();
        for(Field field : declaredFields) {
            //获取成员变量的名字
            String name = field.getName();    //获取成员变量的名字，此处为id，name,age
            //System.out.println(name);

            //获取get和set方法的名字
            String firstLetter = name.substring(0,1).toUpperCase();    //将属性的首字母转换为大写
            String getMethodName = "get" + firstLetter + name.substring(1);
            String setMethodName = "set" + firstLetter + name.substring(1);
            //System.out.println(getMethodName + "," + setMethodName);

            //获取方法对象
            Method getMethod = clazz.getMethod(getMethodName, new Class[]{});
            Method setMethod = clazz.getMethod(setMethodName, new Class[]{field.getType()});//注意set方法需要传入参数类型

            //调用get方法获取旧的对象的值
            Object value = getMethod.invoke(newObject);
            if(value == null) {
                Object oldValue = getMethod.invoke(oldObject);
                //调用set方法将这个值复制到新的对象中去
                setMethod.invoke(newObject, new Object[]{oldValue});
            }
        }

    }

}
