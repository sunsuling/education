package models;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>Project: train</p>
 * <p>Title: AccidentAnalysis.java</p>
 * <p>Description: 事故案例分析</p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "ACCIDENT_ANALYSIS")
public class AccidentAnalysis extends BaseModel{
    public String message;
    public String messageUrl;

}
