import java.util.ArrayList;
import java.util.List;

public abstract class  Person {

    private final Name name;
    private static final List<Person> people = new ArrayList<>();
    private static int userId = 1000;
    private String role;
    private final int personId;
    private int age;

    protected List<Course> courses = new ArrayList<>(); ;

    Person(Name name, Course courses) {
        this.name = name;
        this.courses.add(courses);
        this.personId = getUserId();
    }

    Person(Name name) {
        this.name = name;
        this.personId = getUserId();
    }

    private static int getUserId() {
        userId += 1;
        return userId;
    }

    int getAge() {
        return age;
    }

    void setAge(int age) {
        if (this.age == 0) {
            this.age = age;
        } else System.out.println("You can not change age");
    }

    String fullName() {
        return name.toString();
    }

    void addPerson(Person person) {
        people.add(person);
    }

    List<Person> getPeople() {
        return people;
    }

    void addCourse(Person person, Course course) {
        person.courses.add(course);
    }

    int getPersonId() {
        return personId;
    }

    void setRole(String role) {
        this.role = role;
    }

    String getRole() {
        return this.role;
    }
}
