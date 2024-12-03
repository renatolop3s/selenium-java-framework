package br.com.renatolop3s.sjf.exception;

public class HeadlessNotSupportedException extends RuntimeException {
    public HeadlessNotSupportedException(String message) {
        super(message);
    }
}
