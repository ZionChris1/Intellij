import java.time.Year;
import java.util.Date;
import java.util.Objects;

public class Person {
    private String name;
    private Date birthdate;

    public Person(String name, Date birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(birthdate, person.birthdate);
    }

    public int calculateAge() {
        Year today = Year.now();
        return today.getValue() - birthdate.getYear();
    }
}
