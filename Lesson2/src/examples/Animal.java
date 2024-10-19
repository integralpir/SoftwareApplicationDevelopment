package examples;

import java.time.LocalDate;

public class Animal {
    private String name;
    private final LocalDate birthDate;

    public Animal(String name) {
        this.name = name;
        birthDate = LocalDate.now();
    }

    public void move(int x, int y) {
        System.out.println(this.name + " : move from " + x + " to " + y + ".");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
