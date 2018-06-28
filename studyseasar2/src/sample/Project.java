package sample;

import org.seasar.dao.annotation.tiger.Bean;

@Bean(table="PROJECT") // 対象のテーブルとのマッピング
public class Project {
    // tigerを使用しない場合にはいかように記載します。
    // public static final String TABLE = "PROJECT";
	
	// ＤＢの各カラムをS2の機能であるpublic フィールドを用いて記載
	public Integer id; 
	public String pjName;
	
    // プロパティ名とカラム名が異なっている場合には、カラムアノテーションを記載します。
    // public static final String name_COLUMN = "PJNAME";
}
