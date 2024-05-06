import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Class {
    public String className;
    public ArrayList<Student> students;
    public ArrayList<Subject> subjects;
    private Map<String, Map<Date, Boolean>> attendanceRecords;


    public Class(String className,ArrayList<Subject> subjects) {
        this.className = className;
        this.subjects = subjects;
        students = new ArrayList<Student>();
        this.attendanceRecords = new HashMap<>();

    }


    public String getClassName() {
        return className;
    }

    public void addStudent(Student student) {
        student.addSubjects(subjects);
        students.add(student);
    }

    public List<String> getStudentNames() {
        List<String> names = students.stream()
                .map(Student::getName)
                .collect(Collectors.toList());
        return names;
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
    }

    public void recordAttendanceForStudent(String studentId, Date date, boolean isPresent) {
        Map<Date, Boolean> studentAttendance = attendanceRecords.getOrDefault(studentId, new HashMap<>());
        studentAttendance.put(date, isPresent);
        attendanceRecords.put(studentId, studentAttendance);
    }

    public Map<Date, Boolean> getAttendanceForStudent(String studentId) {
        return attendanceRecords.getOrDefault(studentId, new HashMap<>());
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public boolean removeStudent(String studentId) {
        return students.removeIf(student -> student.getUserId().equals(studentId));
    }
}
