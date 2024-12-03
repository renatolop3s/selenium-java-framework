package br.com.renatolop3s.sjf.exception;

public class BrowserNotSupportedException extends RuntimeException {
    public BrowserNotSupportedException(String message) {
        super(message);
    }
}
