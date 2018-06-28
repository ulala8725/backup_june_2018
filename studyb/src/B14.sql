		//テーブルを作る
		String createSql = "CREATE TABLE testtable1" + 
				"(" + "	TESTNO int(3) not null," + 
				"	NAME varchar(20)," + 
				"	KANA varchar(20)," + 
				"	primary key(testno)" + 
				") default charset=utf8";
		
		int createRes = ac.updateExec(createSql);
		if(createRes == -1) {
			System.out.println("#テーブル作成失敗!!");
		} else {
			System.out.println("#テーブル作成成功!!");
		}