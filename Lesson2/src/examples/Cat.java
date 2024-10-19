package examples;

public class Cat extends Animal implements Speaking {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void speakUp() {
        System.out.println("Cat: " + this.getName() + " say: \"Meow-meow\".");
    }
}
