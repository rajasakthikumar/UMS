import java.util.ArrayList;
import java.util.List;


public class Teacher extends Staff {

    public Subject subject;
    public ArrayList<Student> students;
    private ArrayList<Class> classGrades;


    public Teacher(int id, String name) {
        super("T"+id,name, UserType.Teacher);
    }

    public Teacher (int id, String name,Subject subject) {
        super("T"+id, name, UserType.Teacher);
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    // Manage students
    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
        }
    }

    public void addSubject(Subject sub) {
        subject = subject;
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void removeClass(Class grade) {
        classGrades.remove(grade);
    }

    public void removeSubject(String subjectId) {
        if (subject.getSubjectId().equals(subjectId) ) {
            subject = null;
        }
    }

    public void addClass(Class cls) {
        if (!classGrades.contains(cls)) {
            classGrades.add(cls);
            cls.getStudents().forEach(this::addStudent);
        }
    }

    public String getName() {
        return super.getUserId();
    }
}