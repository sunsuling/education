package models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by hy on 2015/1/12.
 * 中心简介
 */

@Entity
@Table(name="summary")
public class Summary extends BaseModel {
    public String name; // 联系人姓名
    public String address; // 地址
    public String phone; // 手机号
    public String email; // 邮箱地址
    public String qq; // 手机号
    public String fax; //传真
    public String title1;
    public String title2;
    public String title3;
    public String content1;
    public String content2;
    public String content3;
    
    public static Finder<UUID, Summary> find = new Finder<UUID,Summary>(UUID.class, Summary.class);

    public static Summary find(UUID id){
        return find.ref(id);
    }
}
