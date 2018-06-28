
public class Sample {

	public static void main(String[] args) {
		String str1 = null;
		String str2 = "";
		String str3 = new String();
		
		System.out.println("null:" + str1 + "###");
		System.out.println("\"\":" + str2 + "###");
		System.out.println("new:" + str3 + "###");
		
		if (str2.equals(str3)) {
			System.out.println("same");
		} else System.out.println("not the same");
	}
}
