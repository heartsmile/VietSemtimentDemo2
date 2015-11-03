package main;

import spellcheker.Checker;
import utils.SparkUtil;
import vietSentiData.VietSentiData;
import vn.hus.nlp.tokenizer.VietTokenizer;

public class ExtractOpinion {

	public static void main(String[] args) {
		SparkUtil.createJavaSparkContext();
		VietTokenizer tokenizer = new VietTokenizer();
		Checker check = new Checker();

		VietSentiData.init();
		String demo = "anh đang không vui lắm em à";
		// check spell

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
