import java.util.OptionalInt;

public class Person {
    protected final String name;
    protected final String surname;
    protected OptionalInt age = OptionalInt.empty();
    protected String address;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, int age) {
        this(name, surname);
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным");
        }
        this.age = OptionalInt.of(age);
    }

    public boolean hasAge() {
        return age.isPresent();
    }

    public boolean hasAddress() {
        return address != null;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public OptionalInt getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void happyBirthday() {
        if (age.isPresent()) {
            age = OptionalInt.of(age.getAsInt() + 1);
        }
    }

    @Override
    public String toString() {
        return name + " " + surname + (hasAge() ? ", возраст: " + age.getAsInt() : ", возраст неизвестен") +
                (hasAddress() ? ", город: " + address : ", город неизвестен");
    }

    @Override
    public int hashCode() {
        return name.hashCode() + surname.hashCode();
    }

    public PersonBuilder newChildBuilder() {
        return new PersonBuilder().setSurname(this.surname)
                .setAge(0) // Дети по умолчанию имеют возраст 0
                .setAddress(this.address);
    }
}
