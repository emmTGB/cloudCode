package shapes;

public enum Type {
    TRIANGLE("Triangle"), RECTANGLE("Rectangle"), CIRCLE("Circle");
    final String type;

    Type(String type) {
        this.type = type;
    }

    public static Type getType(String type) throws ClassFormatError {
        for (Type t : Type.values()) {
            if (type.equalsIgnoreCase(t.toString())) {
                return t;
            }
        }
        throw new ClassFormatError();
    }

    @Override
    public String toString() {
        return type;
    }
}
