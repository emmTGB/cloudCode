package shapes;

public abstract class Shape {
    protected Type type;

    abstract public void setEdges();

    abstract public double getArea();

    abstract public double getCircumference();

    public int compareArea(Shape s) {
        return Double.compare(this.getArea(), s.getArea());
    }

    public int compareCircumference(Shape s) {
        return Double.compare(this.getCircumference(), s.getCircumference());
    }

    public String getType() {
        return type.toString();
    }
}
