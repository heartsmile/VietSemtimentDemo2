package spellcheker;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;
import utils.SparkUtil;

/**
 * get data for correct dictionary
 * @author thaint
 *
 */
public class Dictionary {
	private final static String DICTIONARYPATH = "./src/main/resources/dictionary.txt";
	private final static String EMOTICONPATH = "./src/main/resources/emoticon.txt";
	private final static String CHARSETPATH = "./src/main/resources/vietcharset.txt";
	private static JavaPairRDD<String, String> dict;
	private static JavaPairRDD<String, String> emoticon;
	private static Set<Character> charSet;
	private static Set<Character> vowels, consonants;

	private static JavaSparkContext sc;

	/**
	 * initial data
	 */
	public static void init() {
		sc = SparkUtil.getJavaSparkContext();
		readDictionaryFromFile(DICTIONARYPATH);
		readEmoticonFromFile(EMOTICONPATH);
		readCharSetFromFile(CHARSETPATH);
	}

	private static void readEmoticonFromFile(String emoticonpath) {
		JavaRDD<String> emoticonFile = sc.textFile(emoticonpath);

		emoticon = emoticonFile
				.mapToPair(new PairFunction<String, String, String>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Tuple2<String, String> call(String text)
							throws Exception {
						if(text.split("\t").length > 1){
							return new Tuple2<String, String>(text.split("\t")[0],
								text.split("\t")[1]);
						} else {
							return new Tuple2<String, String>("","");
						}
					}
				});
		
	}

	/**
	 * read Dictionary FromFile
	 * @param filePath String
	 */ 
	private static void readDictionaryFromFile(String filePath) {
		JavaRDD<String> dictionaryFile = sc.textFile(filePath);

		dict = dictionaryFile
				.mapToPair(new PairFunction<String, String, String>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Tuple2<String, String> call(String text)
							throws Exception {
						if(text.split("\t").length > 1){
							return new Tuple2<String, String>(text.split("\t")[0],
								text.split("\t")[1]);
						} else {
							return new Tuple2<String, String>("","");
						}
					}
				});
	}

	/**
	 * get the correct meaning of sign word
	 * @param word String
	 * @return correctWord String
	 */
	public static String getDefination(String word) {
		//qtran
		List<String> foundList = dict.lookup(word.trim().toLowerCase());
		if (foundList != null && !foundList.isEmpty()){
			return foundList.get(0);
		}
		return word;
	}
	
	/**
	 * get the correct meaning of sign word
	 * @param word String
	 * @return correctWord String
	 */
	public static String getEmoticonDefination(String emo) {
		//qtran
		List<String> foundList = emoticon.lookup(emo != null ? emo.trim() : "");
		if (foundList != null && !foundList.isEmpty()){
			return foundList.get(0);
		}
		return emo;
	}

	/**
	 * read CharSet From File
	 * @param filePath String
	 */
	private static void readCharSetFromFile(String filePath) {
		JavaRDD<String> charSetFile = sc.textFile(filePath);

		charSet = new HashSet<>();
		vowels = new HashSet<>();
		consonants = new HashSet<>();
		//qtran
		if(charSetFile.collect() != null){ 
			for (int i = 0; i < charSetFile.collect().size(); i++) {
				for (char c : charSetFile.collect().get(i).toCharArray())
					if (c == ' ' || c == '\t') {
						//ignore
					} else {
						charSet.add(c);
						if (i == 0)
							vowels.add(c);
						else if (i == 1)
							consonants.add(c);
					}
			}
		}
	}

	/**
	 * check character c is that VietnameseChacter
	 * @param c char
	 * @return boolean
	 */
	public static boolean isVietnameseCharacter(char c) {
		//qtran
		return charSet.contains(Character.toLowerCase(c));
	}

	/**
	 * check char c is vowel(nguyen-am)
	 * @param c char
	 * @return boolean
	 */
	public static boolean isVowel(char c) {
		return vowels.contains(c);
	}

	/**
	 * check char c is consonant(phu-am)
	 * @param c char
	 * @return boolean
	 */
	public static boolean isConsonant(char c) {
		return consonants.contains(c);
	}
}
