package models;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 部门规章
 * @author Administrator
 *
 */
@Entity
@Table(name="department")
public class Department extends BaseModel{
	public String title;
	public String detail;
	public Timestamp effectiveAt;
	
	public static Finder<UUID,Department> find = new Finder<UUID,Department>(UUID.class, Department.class);
	
	public static Department find(UUID id){
		return find.ref(id);
	}

}
