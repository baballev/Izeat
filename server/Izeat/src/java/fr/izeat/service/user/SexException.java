package fr.izeat.service.user;

public class SexException extends Exception {
	
	char wrongChar;
	
	public SexException(char c) {
		wrongChar = c;
	}
	
	public void print() {
		System.out.println(wrongChar + " is not a known sex type");
	}
}
