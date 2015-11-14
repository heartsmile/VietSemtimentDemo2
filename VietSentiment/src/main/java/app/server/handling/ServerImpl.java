package app.server.handling;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

import spellcheker.Checker;
import utils.SparkUtil;
import vietSentiData.VietSentiData;
import vn.hus.nlp.tokenizer.VietTokenizer;

public class ServerImpl extends UnicastRemoteObject implements ServerInterf {

	final int MAXTHREAD = 100;
	private static final String IP_THIS_SERVER = "127.0.0.1";

	private static final long serialVersionUID = 1L;
	private Registry rmiRegistry;

	private VietTokenizer tokenizer;
	private Checker check;

	public void start() throws Exception {
		rmiRegistry = LocateRegistry.createRegistry(1099);
		rmiRegistry.bind("server", this);
		System.out.println("Server " + IP_THIS_SERVER + " started successful!");
	}

	public void stop() throws Exception {
		rmiRegistry.unbind("server");
		unexportObject(this, true);
		unexportObject(rmiRegistry, true);
		System.out.println("Server stopped");
	}

	public ServerImpl() throws RemoteException, SQLException {
		super();
		try {
			SparkUtil.createJavaSparkContext();
			tokenizer = new VietTokenizer("tokenizer.properties");
			check = new Checker();
			VietSentiData.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String hello() throws RemoteException {
		return "Server " + IP_THIS_SERVER + " say: Hi client!";
	}

	@Override
	public double runAnalyzeSentiment(String inputText, boolean isNeedToCheck)
			throws RemoteException {

		if (inputText == null || "".equals(inputText)) {
			return -6.0;
		}

		double rs = -3.0;
		if (isNeedToCheck) {
			// check spell
			try {
				/*String[] rsCheckSpell = check.correct(tokenizer
						.tokenize(inputText));
				rs = VietSentiData.scoreTokens(rsCheckSpell);
				*/
				////////////////////////////////////////////////////
				String[] rsCheckedAndToken = runSpellCheckAndToken(inputText);
				if(rsCheckedAndToken.length > 0){
					rs = VietSentiData.scoreTokens(rsCheckedAndToken);
				}			
				
			} catch (Exception ex) {
				ex.printStackTrace();
				return -3.0;
			}
		} else {
			rs = VietSentiData.scoreTokens(tokenizer.tokenize(inputText));
		}

		return rs;
	}

	@Override
	public String[] runSpellCheckAndToken(String inputText) throws RemoteException {

		// check spell
		//String[] rsCheckSpell = check.correct(tokenizer.tokenize(inputText));
		String temp[] = new String[1];
		temp[0] = inputText.replaceAll("[\n\r]", " ");
		String[] rsParseEmoticon = check.parseEmoticons(temp);
		if(rsParseEmoticon != null && rsParseEmoticon.length > 0 && rsParseEmoticon[0] != null){
			temp = tokenizer.tokenize(rsParseEmoticon[0]);
		}
		String[] rsCheckSpell = check.correct(temp);
		/*String[] rsToken = new String[1];
		if(rsCheckSpell.length > 0){
			rsToken = tokenizer.tokenize(rsCheckSpell[0]);
			//rs = VietSentiData.scoreTokens(rsToken);
		}
*/
		return rsCheckSpell;
	}
}
