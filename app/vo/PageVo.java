package vo;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <p>Project: train</p>
 * <p>Title: PageVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class PageVo implements Serializable {
    @JsonIgnore
    public int pageSize = 0;
    @JsonIgnore
    public int index = 0;
    
    public int count = 0;
    public List list;

    @JsonIgnore
    public Map<String, Object> filterMap = new HashMap<String, Object>();

}
