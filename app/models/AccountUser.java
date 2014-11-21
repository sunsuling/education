package models;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="account_user")
public class AccountUser extends BaseModel {
	public String username; // 用戶名 
	public String pwd; // 密码
	public String ip; // 登陆ip 
	public boolean lock;// 锁定
	public int accountType;// 角色   0表示普通用户   1代表会员 2代表系统管理员
	
	public static Finder<UUID, AccountUser> find = new Finder<UUID,AccountUser>(UUID.class, AccountUser.class);

	public AccountUser find(UUID id){
		return find.byId(id);
	}
}
