import java.util.ArrayList;
import java.util.Objects;

public class PersonDirectory {
    ArrayList<Person> people = new ArrayList();

    public PersonDirectory(ArrayList<Person> people) {
        this.people = people;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "PersonDirectory{" +
                "people=" + people +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDirectory that = (PersonDirectory) o;
        return Objects.equals(people, that.people);
    }

    public double calculateAverageAge() {
        double sum = 0;
        for (Person person : people) {
            sum += person.calculateAge();
        }
        return sum / people.size();
    }
}
