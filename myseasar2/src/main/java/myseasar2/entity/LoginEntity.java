package myseasar2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.seasar.dao.annotation.tiger.Id;

//@Entity
//@Table(name="USER2")
public class LoginEntity {
	@Id // @Id: primary key
	public String id;
	
	@Column
	public String password;

	@Override
	public String toString() {
		return "LoginEntity [id=" + id + ", password=" + password + "]";
	}
	
}
