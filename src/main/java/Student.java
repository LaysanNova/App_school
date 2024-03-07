public class Student extends Person {

    private Grade grade;

    Student(Name name) {
        super(name);
        setRole("S");
    }

    Student(Name name, Grade grade) {
        super(name);
        this.grade = grade;
        setRole("S");
    }

    Grade getGrade() {
        return grade;
    }

    void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "ID: " + getRole() + getPersonId() +
                " Student: " + fullName() +
                " age: " + getAge() +
                " in grade: " + getGrade() + "\n"
        ;
    }
}
