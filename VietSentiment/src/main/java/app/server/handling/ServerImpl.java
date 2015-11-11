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
			tokenizer = new VietTokenizer();
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
			return -3.0;
		}

		double rs = -3.0;
		if (isNeedToCheck) {
			// check spell
			try {
				String[] rsCheckSpell = check.correct(tokenizer
						.tokenize(inputText));
				rs = VietSentiData.scoreTokens(rsCheckSpell);
			} catch (Exception ex) {
				ex.printStackTrace();
				return -3.0;
			}
		} else {
			String arrayString[] = new String[1];
			arrayString[0] = inputText;
			rs = VietSentiData.scoreTokens(arrayString);
		}

		return rs;
	}

	@Override
	public String[] runSpellCheck(String inputText) throws RemoteException {

		// check spell
		String[] rsCheckSpell = check.correct(tokenizer.tokenize(inputText));

		return rsCheckSpell;
	}
}
