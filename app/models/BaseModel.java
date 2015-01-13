package models;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import play.db.ebean.Model;

@MappedSuperclass
public abstract class BaseModel extends Model {

	@Id
    public String id = UUID.randomUUID().toString();//主键

    public String createdBy; //创建人
    public Timestamp createdAt;//创建事件

    public String updatedBy;//修改人
    public Timestamp updatedAt;//修改时间

    public boolean deleted;//是否删除
}
