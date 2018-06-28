DECLARE		--宣言部
	v_ename VARCHAR2(20);
BEGIN		--実行部
	SELECT name
	INTO v_ename
	FROM userst
	WHERE id = 'id1';
	
	DBMS_OUTPUT.PUT_LINE('ユーザーの名前は　'|| v_ename);
	
EXCEPTION	--例外処理部
	WHEN NO_DATA_FOUND THEN
		DBMS_OUTPUT.PUT_LINE('該当ユーザーはいません。');
END;
/