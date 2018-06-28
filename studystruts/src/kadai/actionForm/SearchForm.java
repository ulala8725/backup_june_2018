package kadai.actionForm;

import org.apache.struts.action.ActionForm;

/**
 * search.jspからもらうパラメータ
 * @param id ユーザーのID
 * @param　name ユーザーの名前
 * @param　kana ユーザーの名前のフリガナ
 * @param　search 検索ボタン
 * @param　logout ログアウトボタン
 * @param　modify 更新ボタン
 * @param　delete 削除ボタン
 * @author kim.sunhye
 */
public class SearchForm extends ActionForm {
	private String id;
	private String name;
	private String kana;
	private String searchBtn;	//検索ボタン
	
	//ユーザ情報を更新する時に使う変数
	private String mId;
	private String mName;
	private String mKana;
	private String mBirth;
	private String mClub;
	private String modifyConfirmBtn;
	
	public SearchForm(){}
	
	public SearchForm(String mId, String mName, String mKana, String mBirth, String mClub){
		this.mId = mId;
		this.mName = mName;
		this.mKana = mKana;
		this.mBirth = mBirth;
		this.mClub = mClub;
	}
	
	
	public String getSearch() {
		return searchBtn;
	}
	public void setSearch(String search) {
		this.searchBtn = search;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKana() {
		return kana;
	}
	public void setKana(String kana) {
		this.kana = kana;
	}
	public String getSearchBtn() {
		return searchBtn;
	}
	public void setSearchBtn(String searchBtn) {
		this.searchBtn = searchBtn;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmKana() {
		return mKana;
	}
	public void setmKana(String mKana) {
		this.mKana = mKana;
	}
	public String getmBirth() {
		return mBirth;
	}
	public void setmBirth(String mBirth) {
		this.mBirth = mBirth;
	}
	public String getmClub() {
		return mClub;
	}
	public void setmClub(String mClub) {
		this.mClub = mClub;
	}
	public String getModifyConfirmBtn() {
		return modifyConfirmBtn;
	}
	public void setModifyConfirmBtn(String modifyConfirmBtn) {
		this.modifyConfirmBtn = modifyConfirmBtn;
	}
	
}
