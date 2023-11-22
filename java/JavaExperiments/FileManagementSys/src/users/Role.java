package users;

import exceptions.UserException;

public enum Role {
    ADMINISTRATOR("Administrator"),
    OPERATOR("Operator"),
    BROWSER("Browser"),
    ;

    final String role;

    public static Role getRole(String role) throws UserException {
        for (Role r : Role.values()) {
            if (role.equalsIgnoreCase(r.toString())) {
                return r;
            }
        }
        throw UserException.ROLE_UNEXPECTED_ERR;
    }

    Role(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
