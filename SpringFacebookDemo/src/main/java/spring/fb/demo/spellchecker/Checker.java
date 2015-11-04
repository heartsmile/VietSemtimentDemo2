package spring.fb.demo.spellchecker;

/**
 * Check spelling of String[] input
 * @author thaint
 *
 */
public class Checker {
	
	static final String ie = "i í ì ỉ ĩ ị e é è ẻ ẽ ẹ ê ế ề ể ễ ệ";
	static final String o = "o ó ò ỏ õ ọ ô ố ồ ổ ỗ ộ ơ ớ ờ ở ỡ ợ";

	/**
	 * default constructor
	 */
	public Checker(){
		Dictionary.init();
	}
	
	// sentence input pháº£i á»Ÿ dáº¡ng lower case
	/**
	 * get the correct of sentences
	 * @param sentences String[]
	 * @return result String[]
	 */
	public String[] correct(String[] sentences) {
		String[] result = new String[sentences.length];
		for (int i = 0; i < sentences.length; ++i) {
			result[i] = correctSentence(sentences[i]);
		}
		return result;
	}

	/**
	 * get the correct of sentence
	 * @param sentence String
	 * @return String
	 */
	public String correctSentence(String sentence) {
		char[] c = sentence.toCharArray();
		StringBuilder sb = new StringBuilder();
		StringBuilder word = new StringBuilder();
		for (int i = 0; i < c.length; ++i) {
			if (Dictionary.isVietnameseCharacter(c[i])) {
				word.append(c[i]);
			} else {
				if (word.length() > 0)
					sb.append(correctWord(word.toString()));
				word.delete(0, word.length());
				sb.append(c[i]);
			}
		}
		if (word.length() > 0)
			sb.append(correctWord(word.toString()));
		return sb.toString();
	}

	/**
	 * get the correct word
	 * @param word String
	 * @return word String
	 */
	private String correctWord(String word) {
		word= Dictionary.getDefination(word);
		return word;
	}
}
