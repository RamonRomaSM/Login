package HTTPService;

import java.io.IOException;
import java.net.Socket;

public interface Response {
	
	public void execute(Client client) throws NotHandledRequestException, IOException;
	
	

}
