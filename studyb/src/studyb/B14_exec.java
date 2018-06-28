package studyb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DBと連結するクラス
 * @author kim.sunhye
 */

public class B14_exec {
	public static void main(String[] args) {
		B14_DBAccess ac = new B14_DBAccess();
		//DB接続
		ac.connect();
		
		//１０行データをインサート
		String insertSqlArray[] = new String[10];
		String[] nameArray = {"浅田","内田","木野村","杉浦","道明寺","井口","山口","内山","沢田","寺泊"};
		String[] kanaArray = {"アサだ","ウチダ","キノムラ","スギウラ","ドウミョウジ","イノクチ","ヤマグチ","ウチヤマ","サワダ","テラドマリ"};
		
		for (int i = 0; i < insertSqlArray.length; i++) {
			insertSqlArray[i] = "INSERT INTO testtable1 VALUES (" 
						+ (i+1) +", '" + nameArray[i] + "', '" + kanaArray[i] + "')"; //文字列は必ず''の中に入力!!
		}
		
		int insertRes = 0;
		for (int i = 0; i < insertSqlArray.length; i++) {
			insertRes = ac.updateExec(insertSqlArray[i]);
			if(insertRes == -1) {
				System.out.println("#データ入力失敗!!");
				break;
			}
		}
		//コミット
		if(insertRes == 1){
			System.out.println("#データ入力成功!!");
			ac.commit();
		} else {
			ac.rollback();
		}
		
		//５行目までを取得して表示
		System.out.println("###########５行目までを取得して表示");
//		for (int i = 0; i < 5; i++) {
//			String[][] selResult = new String[1][2];
//			selResult = ac.selectExec("SELECT name, kana FROM testtable1 WHERE testno = " + String.valueOf(i+1));
//			System.out.println(i+1 + "," + selResult[0][0] + "," + selResult[0][1]);
//		}
		int fromIdx = 1;
		int toIdx = 5;
		String[][] selResult = new String[toIdx-fromIdx+1][2]; // 5, 2
		selResult = ac.selectExec("SELECT name, kana FROM testtable1 WHERE testno between ? and ?", 
				fromIdx, toIdx);
		for (int i = 0; i < selResult.length; i++) {
			System.out.println(fromIdx++ + "," + selResult[i][0] + "," + selResult[i][1]);
		}
		
		//６行目から１０行目までを取得して表示
		System.out.println("###########６行目から１０行目までを取得して表示");
		String[][] selResult2 = new String[5][2];
		int fromIdx2 = 6;
		for (int i = 0; i < 10-fromIdx2+1; i++) {
			selResult2 = ac.selectExec("SELECT name, kana FROM testtable1 WHERE testno >= ?", fromIdx2);
		}
		for (int i = 0; i < selResult2.length; i++) {
			System.out.println(fromIdx++ + "," + selResult[i][0] + "," + selResult[i][1]);
		}
		
		//全ての行を削除
		System.out.println("###########全ての行を削除");
		int deleteRes = 0;
		String deleteSql = "DELETE FROM testtable1";
		insertRes = ac.updateExec(deleteSql);
		if(deleteRes == -1) {
			System.out.println("#データ削除失敗!!");
			ac.rollback();
		} else {
			System.out.println("#データ削除成功!!");
		}
		
		//全ての行を取得して表示
		System.out.println("###########全ての行を取得して表示");
		String[][] outArray = ac.selectExec("SELECT * FROM testtable1");
		if (outArray[0][0] != null) {
			for (int i = 0; i < outArray.length; i++) {
				System.out.println(i+1 + "," + outArray[i][0] + "," + outArray[i][1]);
			}
		} else {
			System.out.println("#出力するデータがありません。");
		}

		//ロールバック
		ac.rollback();
		System.out.println("#ロールバック実行");
		
		//全ての行を取得して表示
		System.out.println("###########全ての行を取得して表示");
		String[][] outArray2 = ac.selectExec("SELECT * FROM testtable1");
		if (outArray2[0][0] != null) {
			for (int i = 0; i < outArray2.length; i++) {
				System.out.println(i+1 + "," + outArray2[i][0] + "," + outArray2[i][1]);
			}
		} else {
			System.out.println("#出力するデータがありません。");
		}
		
		//DB切断
		System.out.println("#DB切断実行");
		ac.disconnect();
		
	}// main
}// class

