public class Dog {
    protected int age;
    protected String name, colour;

    public Dog(int age, String name, String colour) {
        this.age = age;
        this.name = name;
        this.colour = colour;
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }

    public String getColour() {
        return this.colour;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

}
