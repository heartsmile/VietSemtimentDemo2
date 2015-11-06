/**
 * 
 */
package spring.fb.demo.services;

import spring.fb.demo.sparkutils.SparkUtil;
import spring.fb.demo.spellchecker.Checker;
import spring.fb.demo.vietsentiment.VietSentiData;
import vn.hus.nlp.tokenizer.VietTokenizer;

/**
 * @author QuanTA5
 *
 */
public class VietSentiService {
		
	private VietTokenizer tokenizer;
	private Checker check;
	
	public VietSentiService(){
		tokenizer = new VietTokenizer();
		check = new Checker();
		SparkUtil.createJavaSparkContext();
		
		VietSentiData.init();
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
}
