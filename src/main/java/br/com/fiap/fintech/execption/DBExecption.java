package br.com.fiap.fintech.execption;

public class DBExecption extends Exception{

    public DBExecption() {
    }

    public DBExecption(String message) {
        super(message);
    }

    public DBExecption(String message, Throwable cause) {
        super(message, cause);
    }

    public DBExecption(Throwable cause) {
        super(cause);
    }

    public DBExecption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
