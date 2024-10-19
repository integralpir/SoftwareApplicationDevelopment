package examples;

public class Main {
    public static void main(String[] args) {
        // создаем объекты, пса Rex'a и кота Scheldon'a
        Dog rex = new Dog("Rex");
        Cat sheldon = new Cat("Sheldon");

        // вызываем методы уникальные методы классов
        rex.speakUp();
        sheldon.speakUp();

        // вызываем общие методы агрегированные в классе Animal
        rex.move(1, 2);
        sheldon.move(1, 2);

        // вызываем общие поля описанные в классе Animal
        System.out.println(rex.getName());
        System.out.println(rex.getBirthDate());

        System.out.println(sheldon.getName());
        System.out.println(sheldon.getBirthDate());
    }
}