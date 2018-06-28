package kadai.actionForm;

import java.util.Date;

import org.apache.struts.action.ActionForm;

/**
 * login.jspからもらうパラメータ
 * @param id ユーザーのID
 * @param idCheck IDの重複を確認するときオスボタン
 * @param isused 重複確認するためにIDをコピーしたもの
 * @param pass idのパスワード
 * @param passCheck パスワードを確認するためもう一度入力
 * @param　name ユーザーの名前
 * @param　kana ユーザーの名前のフリガナ
 * @param　birth ユーザーの生年月日
 * @param　club ユーザーの委員会
 * @author kim.sunhye
 */
public class EnrollForm extends ActionForm {
	private String id;
	private String idCheck;
	private String pass;
	private String passCheck;
	private String name;
	private String kana;
	private String birth;
	private String club;
	
	private String idCanBeUsed;
	private String passCanBeUsed;
	private String nameCanBeUsed;
	private String kanaCanBeUsed;
	private String birthCanBeUsed;
	private String clubCanBeUsed;
	
	private String enrollConfirmBtn;
	
	public EnrollForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EnrollForm(String id, String pass, String name, 
				String kana, String birth, String club) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.kana = kana;
		this.birth = birth;
		this.club = club;
	}
	
	public String getIdCheck() {
		return idCheck;
	}
	public void setIdCheck(String idCheck) {
		this.idCheck = idCheck;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPassCheck() {
		return passCheck;
	}
	public void setPassCheck(String passCheck) {
		this.passCheck = passCheck;
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
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getClub() {
		return club;
	}
	public void setClub(String club) {
		this.club = club;
	}
	
	public String getIdCanBeUsed() {
		return idCanBeUsed;
	}
	public void setIdCanBeUsed(String idCanBeUsed) {
		this.idCanBeUsed = idCanBeUsed;
	}
	public String getPassCanBeUsed() {
		return passCanBeUsed;
	}
	public void setPassCanBeUsed(String passCanBeUsed) {
		this.passCanBeUsed = passCanBeUsed;
	}
	public String getNameCanBeUsed() {
		return nameCanBeUsed;
	}
	public void setNameCanBeUsed(String nameCanBeUsed) {
		this.nameCanBeUsed = nameCanBeUsed;
	}
	public String getKanaCanBeUsed() {
		return kanaCanBeUsed;
	}
	public void setKanaCanBeUsed(String kanaCanBeUsed) {
		this.kanaCanBeUsed = kanaCanBeUsed;
	}
	public String getBirthCanBeUsed() {
		return birthCanBeUsed;
	}
	public void setBirthCanBeUsed(String birthCanBeUsed) {
		this.birthCanBeUsed = birthCanBeUsed;
	}

	public String getClubCanBeUsed() {
		return clubCanBeUsed;
	}

	public void setClubCanBeUsed(String clubCanBeUsed) {
		this.clubCanBeUsed = clubCanBeUsed;
	}

	public String getEnrollConfirmBtn() {
		return enrollConfirmBtn;
	}

	public void setEnrollConfirmBtn(String enrollConfirmBtn) {
		this.enrollConfirmBtn = enrollConfirmBtn;
	}
	
	

}
