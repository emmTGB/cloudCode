package Process;

public class UserException extends Throwable {
    private final String exceptionMsg;

    protected UserException(String exceptionMsg) {
        super();
        this.exceptionMsg = exceptionMsg;
    }

    @Override
    public String getMessage() {
        return this.exceptionMsg;
    }
}
