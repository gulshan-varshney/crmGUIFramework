package practice.DataDrivenTesting;

public class GenerateAlphanumericString {
	public static void main(String[] args) {

		int n = 10;

		// choose a character randomly from this string
		String AlphanumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";

		// create stringBuffer size of alphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 1; i <= n; i++) {

			// generate a random number b/w 0 to alphaNumericString variable length
			int index = (int) (AlphanumericString.length() * Math.random());

			// add character one by one in the end of sb
			sb.append(AlphanumericString.charAt(index));
		}
		System.out.println(sb);
	}

}
