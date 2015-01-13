package models;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "training")
public class Training extends BaseModel{
	public String title;
	public String detail;
	public Timestamp effectiveAt;
	
	public static Finder<UUID,Training> find = new Finder<UUID,Training>(UUID.class, Training.class);
	
	public static Training find(UUID id){
		return find.ref(id);
	}
}
