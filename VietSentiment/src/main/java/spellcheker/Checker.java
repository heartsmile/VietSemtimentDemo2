package spellcheker;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import utils.SparkUtil;

/**
 * Check spelling of String[] input
 * @author thaint
 *
 */
public class Checker {
	
	static final String ie = "i í ì ỉ ĩ ị e é è ẻ ẽ ẹ ê ế ề ể ễ ệ";
	static final String o = "o ó ò ỏ õ ọ ô ố ồ ổ ỗ ộ ơ ớ ờ ở ỡ ợ";
	static int index = 1;

	/**
	 * default constructor
	 */
	public Checker(){
		SparkUtil.createJavaSparkContext();
		Dictionary.init();
	}
	
	/**
	 * get the correct of sentences
	 * @param sentences String[]
	 * @return result String[]
	 */
	public String[] correct(String[] sentences) {
		String[] result = new String[sentences.length];
		FileOutputStream out;
		try {
			out = new FileOutputStream("afterCheck" + index++ + ".txt");
			PrintWriter output = new PrintWriter(out, true);// auto flush
			for (int i = 0; i < sentences.length; ++i) {
				result[i] = correctSentence(sentences[i]);
				output.println(result[i] + "\n");
			}
			out.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
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
		//qtran
		for (int i = 0; i < c.length; ++i) {
			if (/*!Dictionary.isVietnameseCharacter(c[i]) && */c[i] != ' ' && c[i] != '.' && c[i] != ',') {
				word.append(c[i]);
			} else {
				if (word.length() > 0){
					sb.append(correctWord(word.toString()));
				}
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
	
	/**
	 * get the correct of sentences
	 * @param sentences String[]
	 * @return result String[]
	 */
	public String[] parseEmoticons(String[] sentences) {
		String[] result = new String[sentences.length];
			for (int i = 0; i < sentences.length; ++i) {
				result[i] = correctEmoticons(sentences[i]);
			}
		return result;
	}
	
	/**
	 * get the correct of sentence
	 * @param emoticons String
	 * @return String
	 */
	public String correctEmoticons(String emoticons) {
		char[] c = emoticons.toCharArray();
		StringBuilder sb = new StringBuilder();
		StringBuilder word = new StringBuilder();
		//qtran
		for (int i = 0; i < c.length; ++i) {
			if (/*!Dictionary.isVietnameseCharacter(c[i]) && */c[i] != ' ' && c[i] != '.' && c[i] != ',') {
				word.append(c[i]);
			} else {
				if (word.length() > 0){
					sb.append(correctEmoticon(word.toString()));
				}
				word.delete(0, word.length());
				sb.append(c[i]);
			}
		}
		if (word.length() > 0)
			sb.append(correctEmoticon(word.toString()));
		return sb.toString();
	}
	
	/**
	 * get the correct word
	 * @param emo String
	 * @return word String
	 */
	private String correctEmoticon(String emo) {
		emo= Dictionary.getEmoticonDefination(emo);
		return emo;
	}

	
}
