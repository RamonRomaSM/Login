package LoginBack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import HTTPService.Client;
import HTTPService.NotHandledRequestException;
import HTTPService.Response;
import HTTPService.ServerHTTP;

public class Main {

	public static void main(String[] args) {
		
		ServerHTTP server=new ServerHTTP();
		server.addRequest("/", new Response() {
			 
			@Override
			public void execute(Client client) throws NotHandledRequestException, IOException {
				
				sendLogin(client);
			
			}
		});
		server.addRequest("/volver", new Response() {
			 
			@Override
			public void execute(Client client) throws NotHandledRequestException, IOException {
				
				sendLogin(client);
			
			}
		});
		server.addRequest("/login", new Response() {
			 
			@Override
			public void execute(Client client) throws NotHandledRequestException, IOException {
				
				sendTheEnd(client);
				
			}
		});
		server.addRequest("/gotoRegister", new Response() {
			 
			@Override
			public void execute(Client client) throws NotHandledRequestException, IOException {
				
				sendRegister(client);
				
			}
		});
		server.initialize();
		
		
	}
	
	//TODO:analizar credenciales
	
	static void sendLogin(Client clienteact) throws IOException {
		File arc=new File(".\\HTML\\login.html");		
		FileInputStream fis=new FileInputStream(arc);		
		clienteact.send(fis.readAllBytes());	
		
	}
	static void sendTheEnd(Client clienteact) throws IOException {
		File arc=new File(".\\HTML\\accediste.html");		
		FileInputStream fis=new FileInputStream(arc);		
		clienteact.send(fis.readAllBytes());	
		
	}
	static void sendRegister(Client clienteact) throws IOException {
		File arc=new File(".\\HTML\\register.html");		
		FileInputStream fis=new FileInputStream(arc);		
		clienteact.send(fis.readAllBytes());	
		
	}
	
	
	
}
