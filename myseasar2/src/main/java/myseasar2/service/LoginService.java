package myseasar2.service;

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import myseasar2.dao.LoginDao;
import myseasar2.dto.UserDto;
import myseasar2.entity.LoginEntity;

public class LoginService {
	private static final String PATH = "s2Dao.dicon";
	@Resource
	protected LoginDao loginDao;
	@Resource
	protected UserDto loginDto;
	
	public boolean login(String id, String password){
		SingletonS2ContainerFactory.setConfigPath(PATH);
		SingletonS2ContainerFactory.init();
		S2Container container = SingletonS2ContainerFactory.getContainer();
		loginDao = (LoginDao) container.getComponent(LoginDao.class);
		
		// session
		LoginEntity loginEntity = loginDao.selectByIdPwd(id, password);
		if (loginEntity == null) {
			return false;
		} else {
//			Beans.copy(loginEntity, loginDto).execute(); // loginEntity->loginDto(session, idだけ)にコピー。
			return true;
		}
	}
	
	// interceptor
    public boolean isLoginOK(){
        return loginDto.id == null ? false : true;
    }

}
