package exceptions;

public class MyException extends Throwable {
    private final String exceptionMsg;

    protected MyException(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    @Override
    public String getMessage() {
        return this.exceptionMsg;
    }
}
