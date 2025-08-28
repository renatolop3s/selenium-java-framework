package br.com.renatolop3s.sjf.exceptions;

public class BrowserNotSupportedException extends RuntimeException {
    public BrowserNotSupportedException(String message) {
        super(message);
    }
}
