package spellcheker;

import java.util.HashSet;
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
	private final static String DICTIONARYPATH = "./src/main/resources/dictionary.csv";
	private final static String CHARSETPATH = "./src/main/resources/vietcharset.txt";
	private static JavaPairRDD<String, String> dict;
	private static Set<Character> charSet;
	private static Set<Character> vowels, consonants;

	private static JavaSparkContext sc;

	/**
	 * initial data
	 */
	public static void init() {
		sc = SparkUtil.getJavaSparkContext();
		readDictionaryFromFile(DICTIONARYPATH);
		readCharSetFromFile(CHARSETPATH);
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

						return new Tuple2<String, String>(text.split("\t")[0],
								text.split("\t")[1]);
					}
				});
	}

	/**
	 * get the correct meaning of sign word
	 * @param word String
	 * @return correctWord String
	 */
	public static String getDefination(String word) {

		if (dict.lookup(word) != null && !dict.lookup(word).isEmpty())
			return dict.lookup(word).get(0);
		else
			return word;
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

		for (int i = 0; i < charSetFile.collect().size(); i++) {
			for (char c : charSetFile.collect().get(i).toCharArray())
				if (c != ' ' && c != '\t') {
					charSet.add(c);
					if (i == 0)
						vowels.add(c);
					else if (i == 1)
						consonants.add(c);
				}
		}
	}

	/**
	 * check character c is that VietnameseChacter
	 * @param c char
	 * @return boolean
	 */
	public static boolean isVietnameseCharacter(char c) {
		return charSet.contains(c);
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
