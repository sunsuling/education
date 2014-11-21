package models;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "ACCIDENT_LETTERS")
public class AccidentLetters extends BaseModel{
    public String message;
    public String detail;

    public static Finder<String, AccidentLetters> finder = new Finder(String.class, AccidentLetters.class);

}
