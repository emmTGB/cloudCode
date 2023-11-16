package exceptions;

import consts.FILE_CONST;

public class DataException extends MyException {
    public static final DataException FILE_CONTENT_ERR = new DataException("Some Thing Wrong With '" + FILE_CONST.FILE_NAME + "'");

    public DataException(String exceptionMsg) {
        super(exceptionMsg);
    }
}
