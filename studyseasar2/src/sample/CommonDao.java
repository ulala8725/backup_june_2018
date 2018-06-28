package sample;

import java.util.ArrayList;
import java.util.Map;

import org.seasar.dao.annotation.tiger.SqlFile;

//アノテーションなし 
public interface CommonDao {
	
	@SqlFile
	public ArrayList<Map> selectCommonDao();
	
}
