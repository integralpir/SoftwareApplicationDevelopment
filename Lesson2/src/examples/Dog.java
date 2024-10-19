package examples;

public class Dog extends Animal implements Speaking {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void speakUp() {
        System.out.println("Dog: " + this.getName() + " say: \"Woof-woof\".");
    }
}
