package models;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 联系我们
 * @author Administrator
 *
 */
@Entity
@Table(name="contact")
public class Contact extends BaseModel {
	public String contactName; // 联系人姓名
	public String phone; // 手机号
	public String address; // 地址
	 
	public static Finder<UUID, Contact> find = new Finder<UUID,Contact>(UUID.class, Contact.class);
	
	public static Contact find(UUID id){
		return find.ref(id);
	}
}
