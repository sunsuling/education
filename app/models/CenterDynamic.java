package models;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="center_dynamic")
public class CenterDynamic extends BaseModel{
	public String title;
	public String detail;
	public String source;
	public Timestamp effectiveAt;
	
	public static Finder<UUID,CenterDynamic> find = new Finder<UUID,CenterDynamic>(UUID.class, CenterDynamic.class);
	
	public static CenterDynamic find(UUID id){
		return find.ref(id);
	}
}
