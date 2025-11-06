public class Labrador extends Dog {
    public Labrador(int age, String name, String colour) {
        super(age, name, colour);
    }

    @Override
    public String toString() {
        return "Labrador: " + name + ", Age: " + age + ", Colour: " + colour;
    }

}
