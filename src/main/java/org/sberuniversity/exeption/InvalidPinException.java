package org.sberuniversity.exeption;

public class InvalidPinException extends Exception{
    public InvalidPinException(String message) {
        super(message);
    }
}