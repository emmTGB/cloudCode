package Process;

public class DataException extends Throwable {
    private final String exceptionMsg;

    protected DataException(String exceptionMsg) {
        super();
        this.exceptionMsg = exceptionMsg;
    }

    @Override
    public String getMessage() {
        return this.exceptionMsg;
    }
}
