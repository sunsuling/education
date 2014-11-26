package models;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 行政法规
 * @author Administrator
 *
 */
@Entity
@Table(name="regulation")
public class Regulation extends BaseModel{
	public String title;
	public String detail;
	public Timestamp effectiveAt;
	
	public static Finder<UUID,Regulation>find = new Finder<UUID,Regulation>(UUID.class, Regulation.class);
	
	public static Regulation find(UUID id){
		return find.ref(id);
	}
	
}
