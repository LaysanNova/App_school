import java.util.stream.Collectors;

public class Teacher extends Person {

    Teacher(Name name, Course courses) {
        super(name, courses);
        setRole("T");
    }

    Teacher(Name name) {
        super(name);
        setRole("T");
    }

    @Override
    public String toString() {

        String listOfCourses = !courses.isEmpty() ?
                " with courses: " +
                        courses.stream()
                                .map(x-> x.courseName)
                                .collect(Collectors.joining(", "))
                : "";

        return "ID: " + getRole() + getPersonId() +
                " Teacher: " + fullName() +
                listOfCourses + "\n";
    }
}
