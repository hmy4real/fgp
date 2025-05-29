package fgp.engine;

/**
 * @author steve.han
 */
public class Vector {

    public double x;
    public double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector copy() {
        return new Vector(this.x, this.y);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector normalize() {
        double mag = magnitude();
        if (mag == 0) {
            return new Vector(0, 0);
        }
        return new Vector(x / mag, y / mag);
    }

    public static Vector add(Vector a, Vector b) {
        return new Vector(a.x + b.x, a.y + b.y);
    }

    public static Vector subtract(Vector a, Vector b) {
        return new Vector(a.x - b.x, a.y - b.y);
    }

    public static Vector multiply(Vector v, double scalar) {
        return new Vector(v.x * scalar, v.y * scalar);
    }

    public double dot(Vector other) {
        return this.x * other.x + this.y * other.y;
    }

    public double distanceTo(Vector other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // Optionally override equals and hashCode
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector) {
            Vector v = (Vector) obj;
            return Double.compare(x, v.x) == 0 && Double.compare(y, v.y) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(x) ^ Double.hashCode(y);
    }
}

