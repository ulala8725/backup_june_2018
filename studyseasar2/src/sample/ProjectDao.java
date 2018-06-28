package sample;

import java.util.ArrayList;

import org.seasar.dao.annotation.tiger.Arguments;
import org.seasar.dao.annotation.tiger.Query;
import org.seasar.dao.annotation.tiger.S2Dao;
import org.seasar.dao.annotation.tiger.SqlFile;

/*
Data Access Object用のインターフェース
*/
@S2Dao(bean=Project.class) // JavaBeansとの関連付けの定数宣言
public interface ProjectDao {
   // tigerアノテーションを使用しない場合には以下のように記載します。
   // public static final Class BEAN = Project.class;
	
	// ex) select文を発行するためのメソッド - 全件検索用
	public ArrayList<Project> selectProject();
	
    // Queryアノテーションをtigerアノテーションを使用しない場合には以下のように記載します。
    // public static final String selectWhereProject_QUERY = "ID BETWEEN ? AND ? ORDER BY ID";
	
	// to add a condition
	@Query("id BETWEEN ? AND ? ORDER BY id")
	public ArrayList<Project> selectWhereProject(String startId, String endId);
	
	// ＳＱＬファイル 使用メソッド
	@SqlFile    // SQLファイルがない場合にエラーを出力するアノテーション
	public ArrayList<Project> selectUseSqlFileProject(int startId, int endId);
	
	// ＳＱＬファイル 使用メソッド
    @SqlFile    // SQLファイルがない場合にエラーを出力するアノテーション
    @Arguments({"startId", "endId", "pjName"}) // メソッドの引数名を指定するアノテーション
    public ArrayList<Project> selectUseSqlFileProject2(int startId, int endId, String pjName);
    
    //ARGSアノテーション tigerアノテーションを使用しない場合
    //public static final String selectUseSqlFileProject3_ARGS = "startId,endId,pjName";
    
    @SqlFile    // SQLファイルがない場合にエラーを出力するアノテーション
    @Arguments({"orderByKey", "sortKey"}) // メソッドの引数名を指定するアノテーション
    public ArrayList<Project> selectUseSqlFileProject3(String orderByKey, String sortKey);
    
    @SqlFile    // SQLファイルがない場合にエラーを出力するアノテーション
    @Arguments({"pjName"}) // メソッドの引数名を指定するアノテーション
    public ArrayList<Project> selectUseSqlFileProject4(String pjName);
    
    @SqlFile    // SQLファイルがない場合にエラーを出力するアノテーション
    @Arguments({"pjName"}) // メソッドの引数名を指定するアノテーション
    public ArrayList<Project> selectUseSqlFileProject5(String pjName);
    
    /*
    to insert a new project into project table
    */
    public int insertProject(Project pj);
    
    /*
    to update the project with new data
     */
    public int updateProject(Project pj);
    
    /*
    to delete the project
    */
    public int deleteProject(Project pj);
	
}
