package myseasar2.dao;

import java.util.ArrayList;

import org.seasar.dao.annotation.tiger.S2Dao;

import myseasar2.entity.LoginEntity;
import myseasar2.entity.User2;

@S2Dao(bean=User2.class)
public interface LoginDao {
	public LoginEntity selectByIdPwd(String id, String password);
	public ArrayList<User2> selectUser();
}
