package vo;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Project: train</p>
 * <p>Title: AccidentAnalysisVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class AccidentLettersVo implements Serializable{
    public String message;
    public String detail;
    @JsonFormat(pattern = "yyyy-MM-dd HH:ss")
    public Date createdAt;
    public boolean deleted;

}
