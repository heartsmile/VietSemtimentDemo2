/**
 * 
 */
package spring.fb.demo.services;

import main.ExtractOpinion;
import spring.fb.demo.sparkutils.SparkUtil;
import spring.fb.demo.spellchecker.Checker;
import spring.fb.demo.vietsentiment.VietSentiData;
import vn.hus.nlp.tokenizer.VietTokenizer;

/**
 * @author QuanTA5
 *
 */
//@Component
public class VietSentiService {
		
	private VietTokenizer tokenizer;
	private Checker check;
	
	public VietSentiService(){
		tokenizer = new VietTokenizer();
		check = new Checker();
		SparkUtil.createJavaSparkContext();
		
		VietSentiData.init();
		
		//ExtractOpinion eoDemo = new ExtractOpinion();
		//System.out.println("" + ExtractOpinion.runAnalyze("Hôm nay anh không được vui lắm em à, anh nhớ em nhiều lắm!"));
	}
	
	public void SentimentDemo(){
		String demo = "anh đang không vui lắm em à";
		String[] result = tokenizer.tokenize(demo);
		String[] rsCheckSpell = check.correct(result);
		// System.err.println(rsCheckSpell.toString());
		double rs = VietSentiData.scoreTokens(rsCheckSpell);
		System.out
				.println("--------------------------------------------------------------------------------");
		System.out.println("input text: " + rsCheckSpell[0]);
		System.out.println("value of score: " + rs);
		System.out
				.println("--------------------------------------------------------------------------------");
	}
	
	/*public static void main(String[] args) {
		SparkUtil.createJavaSparkContext();
		VietTokenizer tokenizer = new VietTokenizer();
		Checker check = new Checker();

		VietSentiData.init();
		String demo = "Hôm nay anh không vui và cũng không hạnh phúc tí nào em à!";
		// check spell

		//String[] result = tokenizer.tokenize(demo);
		String[] rsCheckSpell = check.correct(tokenizer.tokenize(demo));
		// System.err.println(rsCheckSpell.toString());
		double rs = VietSentiData.scoreTokens(rsCheckSpell);
		System.out
				.println("--------------------------------------------------------------------------------");
		System.out.println("input text: " + rsCheckSpell[0]);
		System.out.println("value of score: " + rs);
		System.out
				.println("--------------------------------------------------------------------------------");
	}*/
}
