package br.com.renatolop3s.sjf.exceptions;

public class HeadlessNotSupportedException extends RuntimeException {
    public HeadlessNotSupportedException(String message) {
        super(message);
    }
}
