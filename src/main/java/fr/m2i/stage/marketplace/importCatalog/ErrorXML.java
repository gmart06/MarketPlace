package fr.m2i.stage.marketplace.importCatalog;

public class ErrorXML {

	private int line;
	private String message;
	
	public ErrorXML(int line, String message) {

		this.line = line;
		this.message = message;
	}
	
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorXML [line=" + line + ", message=" + message + "]";
	}

	
}
