package mainPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import HTTPService.Client;
import HTTPService.NotHandledRequestException;
import HTTPService.Response;
import HTTPService.ServerHTTP;

public class Main {
	static File htmlFolder;
	static File resp;
	public static void main(String[] args) {
		//TODO: añadir las respuestas de html, gestion de usuarios, mejorar el css
		//TODO: en la gestion de usuarios, hacer append al body en caso de fail (crear mi etiqueta de: <FAIL>FALLO AL HACER TAL</FAIL>)
		
		htmlFolder=new File(".\\src\\HTML");
		
		ServerHTTP server=new ServerHTTP();
		server.addRequest("/", new Response() {
			
			@Override
			public void execute(Client client) throws NotHandledRequestException, IOException {
				resp=new File(htmlFolder.getPath()+"\\index.html");
				client.send(parseFile(resp));			
			}
		});
		server.addRequest("?opcion=login", new Response() {
			
			@Override
			public void execute(Client client) throws NotHandledRequestException, IOException {
				resp=new File(htmlFolder.getPath()+"\\login.html");
				client.send(parseFile(resp));	
			}
		});
		
		
		server.addRequest("?opcion=register", new Response() {
			
			@Override
			public void execute(Client client) throws NotHandledRequestException, IOException {
				resp=new File(htmlFolder.getPath()+"\\register.html");
				client.send(parseFile(resp));	
			}
		});
		
		
		server.initialize();

	}
	
	public static byte[] parseFile(File resp) throws IOException {
		String s="";
		File f=new File(htmlFolder.getPath()+"\\index.html");
		FileReader fr=new FileReader(resp);
		BufferedReader br=new BufferedReader(fr);
		while(br.ready()) {
		s=s+br.readLine();
		}
		return s.getBytes();
	}
	
	
}
