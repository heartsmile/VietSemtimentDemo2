package app.server.handling;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ServerInterf extends Remote {

	public String hello() throws RemoteException;

	public double runAnalyzeSentiment(String inputText, boolean isNeedToCheck) throws RemoteException;
	public String[] runSpellCheckAndToken(String inputText) throws RemoteException;
}
