package Login;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import HTTPService.Client;
import HTTPService.NotHandledRequestException;
import HTTPService.Response;
import HTTPService.ServerHTTP;

public class Main {
	private static DataBase db;
	private static ServerHTTP server;
	
	
	
	public static void main(String[] args) {
		db=new DataBase();		
		server=new ServerHTTP();
		addRequests();
		server.initialize();
		
		
	}
	
	

	
	static void addRequests() {		
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
				if(db.logIn(getCreds(client.getRawRequest()))) {
					sendTheEnd(client);
				}
				else {
					sendLoginError(client);
				}
			}
		});
		server.addRequest("/gotoRegister", new Response() {			 
			@Override
			public void execute(Client client) throws NotHandledRequestException, IOException {				
				sendRegister(client);				
			}
		});
		server.addRequest("/register", new Response() {
			@Override
			public void execute(Client client) throws NotHandledRequestException, IOException {						
				if(!db.contains(getCreds(client.getRawRequest()))) {
					db.add(getCreds(client.getRawRequest()));
					sendLogin(client);
				}
				else {
					sendRegisterExistingName(client);
				}
				
			}
		});
		
	}
	
	
	
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
	static void sendLoginError(Client clienteact) throws IOException {
		File arc=new File(".\\HTML\\login_error.html");		
		FileInputStream fis=new FileInputStream(arc);		
		clienteact.send(fis.readAllBytes());	
		
	}
	static void sendRegisterExistingName(Client clienteact) throws IOException {
		File arc=new File(".\\HTML\\register_error_existing_name.html");		
		FileInputStream fis=new FileInputStream(arc);		
		clienteact.send(fis.readAllBytes());	
		
	}
	static void sendRegisterPasswError(Client clienteact) throws IOException {
		File arc=new File(".\\HTML\\register_error_unmatching_password.html");		
		FileInputStream fis=new FileInputStream(arc);		
		clienteact.send(fis.readAllBytes());	
		
	}
	static User getCreds(String rawReq) {
		//GET /login?username=qqqq&password=qqqqq HTTP/1.1Host: 192.168.43.91:50180
		String name=rawReq.split("\\?")[1].split("\\&")[0].split("=")[1];
		String passw=rawReq.split("\\?")[1].split("\\&")[1].split(" ")[0].split("=")[1];			
		return new User(name, passw);
	}
	
}
