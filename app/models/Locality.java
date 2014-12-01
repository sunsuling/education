package models;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 地方法规
 * @author Administrator
 *
 */
@Entity
@Table(name="locality")
public class Locality extends BaseModel{
	public String title;
	public String detail;
	public Timestamp effectiveAt;
	
	public static Finder<UUID,Locality> find = new Finder<UUID,Locality>(UUID.class, Locality.class);
	
	public static Locality find(UUID id){
		return find.ref(id);
	}

}
