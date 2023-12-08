package HTTPService;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	
	
	
	private Socket soc;
	private InputStream in;
	private OutputStream out;
	private String rawRequest;
	public Client(Socket s,String rawRequest) throws IOException {
		soc=s;
		in=s.getInputStream();
		out=s.getOutputStream();
		this.rawRequest=rawRequest;
		
		
	}
	
	public void send(byte[] respContent) throws IOException {
		out.write(("HTTP/1.1 200 OK\r\n").getBytes()); 
		out.write(("\r\n").getBytes());	
		out.write(respContent);
		out.flush();
	}

	public String getRawRequest() {
		return rawRequest;
	}

	public void setRawRequest(String rawRequest) {
		this.rawRequest = rawRequest;
	}
	
	
	
}
