package process;

public class UserException extends Throwable {
    private final String exceptionMsg;
    public static final UserException USER_NOT_EXIST_ERR = new UserException("User Does Not Exist!");
    public static final UserException USER_ALREADY_EXISTS_ERR = new UserException("User Already Exists!");
    public static final UserException PASS_WRONG_ERR = new UserException("Wrong Password!");
    public static final UserException PASS_UNSUPPORTED_ERR = new UserException("Unsupported Password!");
    public static final UserException ROLE_WRONG_ERR = new UserException("Wrong Role!");

    protected UserException(String exceptionMsg) {
        super();
        this.exceptionMsg = exceptionMsg;
    }

    @Override
    public String getMessage() {
        return this.exceptionMsg;
    }
}
