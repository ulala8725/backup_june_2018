package myseasar2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.seasar.dao.annotation.tiger.Id;

@Entity
@Table(name="USER2")
public class User2 {
	@Id
	public String id;
	@Column
	public String password;
	@Column
	public String email;
}
