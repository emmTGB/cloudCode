package process;

import consts.FILE_CONST;

public class DataException extends Throwable {
    public static final DataException FILE_CONTENT_ERR = new DataException("Some Thing Wrong With '" + FILE_CONST.FILE_NAME + "'");

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
