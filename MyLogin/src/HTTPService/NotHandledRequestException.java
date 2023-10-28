package HTTPService;

public class NotHandledRequestException extends Exception{
	public NotHandledRequestException(String s) {
		super(s);
	}
}
