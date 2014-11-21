package models;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "ACCIDENT_ANALYSIS")
public class AccidentAnalysis extends BaseModel{
    public String message;
    public String messageUrl;

}
