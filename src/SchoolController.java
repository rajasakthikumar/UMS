import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;

public class SchoolController {
    private List<Teacher> teachers = new ArrayList<>();
    private List<Subject> subjects = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public Teacher findTeacherById(String id) {
        for (Teacher t : teachers) {
            if (t.getUserId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public Subject findSubjectById(String id) {
        for (Subject s : subjects) {
            if (s.getSubjectId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getUserId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public boolean deleteTeacher(String teacherId) {
        return teachers.removeIf(teacher -> teacher.getUserId().equals(teacherId));
    }

}



