package sample;

import java.util.ArrayList;
import java.util.Map;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/*
main class of S2Dao
*/
public class S2Dao_sample11 {
	// path of sample file
	private static final String PATH = "sample/s2Dao.dicon";
	/*
	main class
	@param args parameter
	*/
	public static void main(String[] args){
		System.out.println("S2Dao_sample10 start");
		
		// 1.to read a setting file
		SingletonS2ContainerFactory.setConfigPath(PATH);
		
		// 2.to initiate
		SingletonS2ContainerFactory.init();
		
		// 3.to get a container
		S2Container container = SingletonS2ContainerFactory.getContainer();
		
		// 4.to call the component
		CommonDao cd = (CommonDao) container.getComponent(CommonDao.class);
		
		ArrayList<Map> taskList = cd.selectCommonDao();
		int count = 0;
		for (Map map : taskList) {
			count++;
			System.out.println(count + "行目  : " + map);
		}
	}
}
