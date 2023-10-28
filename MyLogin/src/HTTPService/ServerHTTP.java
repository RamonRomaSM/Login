package HTTPService;

import java.io.BufferedReader;  
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;


public class ServerHTTP {
	private ServerSocket servSock;
	private String reqAct;
	private Socket clienteact;
	private HashMap<String,Response> responses;
	
	public ServerHTTP() {
		responses=new HashMap<String,Response>();
		
	}
	/*
	 *TODO:	
	 * 
	 * 
	 * */
	public void addRequest(String req,Response r) {
		String s="/";
		if(req.charAt(0)!='/'&&req.length()!=1) {
			s="/?"+req+"=";
		}
		responses.put(s, r);
		
	}
	public void initialize() {
		
		System.out.println("Handled requests: "+responses.keySet().toString());
		
		setServerPort();
		File f=new File(".//errorLog.txt");
		File f2=new File(".//log.txt");
		
		try {
			
		
		
		if(!f.exists()) {f.createNewFile();}
		if(!f2.exists()) {f2.createNewFile();}
		System.out.println("SERVER LOCATED IN :  "+InetAddress.getLocalHost().toString().split("/")[1]+":"+servSock.getLocalPort()+"\r\r");
		
		
		
		
		while(true) {
			clienteact=servSock.accept();		
			System.out.println("ACTUAL CLIENT: "+clienteact.toString());
			
			
			
			//Here we recieve the raw request
			InputStreamReader isr=  new InputStreamReader(clienteact.getInputStream());
			BufferedReader br= new BufferedReader(isr);
			String petic=br.readLine();
			while(br.ready()) {
				petic=petic+br.readLine()+"\r";
				
			}
			
			
			
			//here we isolate the request
			String[] datosPeticion=petic.split("\r");
			reqAct=datosPeticion[0].split(" ")[1];
			
			
			
			ResponseHandler handler=new ResponseHandler(responses);			
			handler.resolve(reqAct, clienteact);		
			System.out.println("ACTUAL REQUEST: "+reqAct);
			
			
		
		
			}
		} catch (Exception e) {
			System.out.println("Failed initializing the server");
		}
		
	}
	private void setServerPort() {
		
		boolean init=false;
		int i=0;
		while(!init) {
			try {
				servSock =new ServerSocket(i);
				init=true;
			} catch (Exception e) {
				i++;
			}
			
		}
		
	}
	public void close() {
		try {
			servSock.close();
		} catch (IOException e) {
			
		}
		
	}
	
	
	
	
	
}
