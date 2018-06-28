package myseasar2.dto;

import java.io.Serializable;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

@Component(instance=InstanceType.SESSION)
public class UserDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * ログインしているかどうかを返します。
	 */
	public boolean isLogin(){
		return id != null;
	}
	
	/**
	 * ログインしている場合はログイン中のid、
	 * ログインしていない場合はnullを設定します。
	 */
	public String id = null;
}
