package app.server.handling;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface ServerInterf extends Remote {

	//util
	public ServerInterf getFreestServer() throws RemoteException;
	public String getServerIp() throws RemoteException;
	public ArrayList<String> getListServer() throws RemoteException;
	
	//thread manager
	public int getNumOfBusyThread() throws RemoteException;
	
	public void increaseBusyThread() throws RemoteException;
	
	public void decreaseBusyThread() throws RemoteException;
	
	

	public String hello() throws RemoteException;

	// check file in another server
	public boolean checkFileExist(String fileName, String userName)
			throws RemoteException;
	
	public double runAnalyze(String demoText) throws RemoteException;
}
