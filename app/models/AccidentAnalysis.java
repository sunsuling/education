package models;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "ACCIDENT_ANALYSIS")
public class AccidentAnalysis extends BaseModel{
    public String message;
    public String detail;

    public static Finder<UUID,AccidentAnalysis> find = new Finder<UUID,AccidentAnalysis>(UUID.class, AccidentAnalysis.class);
    
    public static AccidentAnalysis find(UUID id){
    	return find.ref(id);
    }
}
