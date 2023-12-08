package HTTPService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TestClass {
	public static void main(String[] args) {
		ServerHTTP server=new ServerHTTP();
		server.addRequest("/", new Response() {
			 
			@Override
			public void execute(Client client) throws NotHandledRequestException, IOException {
				System.out.println("SE LE DA EL LOGIN ");
				login(client);
				System.out.println(client.getRawRequest());
			}
		});
		server.initialize();
		
	}
	static void login(Client clienteact) throws IOException {
		File arc=new File(".\\HTTP_Project\\src\\login.html");		
		FileInputStream fis=new FileInputStream(arc);		
		clienteact.send(fis.readAllBytes());	
		
	}
}
