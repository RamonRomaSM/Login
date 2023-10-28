package HTTPService;

import java.io.File; 
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;



public class ResponseHandler {
	private File log;
	private File errorLog;
	private FileWriter logWriter;
	private FileWriter errorWriter;
	
	static HashMap<String, Response> responses;
	protected ResponseHandler(HashMap<String,Response> resp) throws IOException {	
		responses=new HashMap<String,Response>();
		responses=resp;
		log=new File(".//log.txt");
		log.createNewFile();
		errorLog=new File(".//errorLog.txt");
		errorLog.createNewFile();
		logWriter=new FileWriter(log);
		errorWriter=new FileWriter(errorLog,true);
		
		
	}
	
	
	protected void resolve(String req,Socket client) throws IOException {
		try {
			findResponse(req).execute(new Client(client));						
			logWriter.write("\rUser: "+client+" Requested: "+req+" "+LocalDateTime.now()+"\r");
			logWriter.flush();
		} catch (Exception e) {
			System.err.println("Request not handled: "+req+"\r");			
			errorWriter.write("\rRequest not handled: "+req+" "+LocalDateTime.now()+"\r");
			errorWriter.flush();
		}
		
	}
	
	
	protected Response findResponse(String req) {
		
		return responses.get(req);
	}
	
	
	
	
	
	
}
