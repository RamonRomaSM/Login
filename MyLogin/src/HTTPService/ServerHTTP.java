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

import javax.swing.plaf.synth.SynthOptionPaneUI;


public class ServerHTTP {
	private ServerSocket servSock;
	private String reqAct;
	private Socket clienteact;
	private HashMap<String,Response> responses;
	private ResponseHandler handler;	
	private String rawRequest;
	    
	
	
	
	
	public ServerHTTP() {
		responses=new HashMap<String,Response>();
		
	}
	public void addRequest(String req,Response r) {
		
		
		responses.put(req, r);
		System.out.println("[SERVER] Added request: "+req);
		
	}
	
	public void initialize() {
		
		System.out.println("[SERVER] Handled requests: "+responses.keySet().toString());
		
		setServerPort();
		try {
			handler=new ResponseHandler(responses);
		} catch (IOException e) {
			System.err.println("[SERVER] Failed declaring the handler");
		}	
		File f=new File(".//errorLog.txt");
		File f2=new File(".//log.txt");
		
		try {							
		if(!f.exists()) {f.createNewFile();}
		if(!f2.exists()) {f2.createNewFile();}
		System.out.println("[SERVER] Server located at :  "+InetAddress.getLocalHost().toString().split("/")[1]+":"+servSock.getLocalPort()+"\r\r");		
		while(true) {
			clienteact=servSock.accept();		
			System.out.println("[SERVER] Actual client: "+clienteact.toString());			
			//Here we recieve the raw request
			InputStreamReader isr=  new InputStreamReader(clienteact.getInputStream());
			BufferedReader br= new BufferedReader(isr);
			rawRequest=br.readLine();
			while(br.ready()) {
				rawRequest=rawRequest+br.readLine()+"\r";
				
			}			
			
			//here we isolate the request
			String[] datosPeticion=rawRequest.split("\r");			
			String datos=datosPeticion[datosPeticion.length-1];			
			reqAct=datosPeticion[0].split(" ")[1]+" "+datos;			
			//here we resolve 
			//the request is formed by 2 parts separated by " ", te tequest, and the data from the form
			System.out.println("[SERVER] Actual request: "+reqAct);
			
			handler.resolve(reqAct.split(" ")[0], clienteact,rawRequest);				
			}
		} catch (Exception e) {
			System.out.println("[SERVER] Failed initializing the server");
			e.printStackTrace();
		}
		
	}
	private void setServerPort() {
		
		boolean init=false;
		int i=1025;
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
