package models;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 法律
 * @author Administrator
 *
 */
@Entity
@Table(name="law")
public class Law extends BaseModel{
	public String title;
	public String detail;
	public Timestamp effectiveAt;
	
	public static Finder<UUID,Law>find = new Finder<UUID,Law>(UUID.class, Law.class);
	
	public static Law find(UUID id){
		return find.ref(id);
	}
}
