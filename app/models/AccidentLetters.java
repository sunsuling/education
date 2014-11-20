package models;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>Project: train</p>
 * <p>Title: AccidentLetter.java</p>
 * <p>Description: 事故快报 </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "ACCIDENT_LETTERS")
public class AccidentLetters extends BaseModel{
    public String message;
    public String detail;

    public static Finder<String, AccidentLetters> finder = new Finder(String.class, AccidentLetters.class);

}
