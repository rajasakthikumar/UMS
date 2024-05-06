import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.stream.Stream;

public class Controller {
    private View view;
    private Class[] classes = new Class[12];
    private Map<String, Map<Date, Boolean>> attendanceRecords = new HashMap<>();
    private SchoolController schoolController;

    private NotificationController notificationController;

    public Controller(View view, SchoolController schoolController, NotificationController notificationController) {
        this.view = view;
        this.schoolController = schoolController;
        this.notificationController = notificationController;
    }

    public void execute() {
        view.printOutput("Hello");
        enterManagementSystem();
    }

    private void enterManagementSystem() {
        loadClasses();
        view.printOutput("Hi!. Welcome to User Management System");
        boolean running = true;
        while (running) {
            view.printOutput("Enter \n1. Manage Students \n2. Manage Staff \n3. Manage Teachers \n4. Exit");
            String input = view.getInput();
            switch (input) {
                case "1":
                    studentManagement();
                    break;
                case "2":
                    staffManagement();
                    break;
                case "3":
                    teacherManagement();
                    break;
                case "4":
                    running = false;
                    view.printOutput("Exiting System");
                    break;
                default:
                    view.printOutput("Invalid input, please try again.");
            }
        }
    }

    private void loadClasses() {
        ClassBuilder cb = new ClassBuilder();
        for (int i = 0; i < classes.length; i++) {
            classes[i] = cb.setGrade(i + 1).build();
        }
        view.printOutput("Classes are loaded.");
    }

    private void studentManagement() {
        boolean exit = false;
        while (!exit) {
            view.printOutput("Student Management: \n1. Add Student to Class \n2. List Students in Class \n3. Update Student Details \n4. Remove Student from Class \n5. View Student Details \n6. Add Mark to Student \n7. Exit");
            String input = view.getInput();
            switch (input) {
                case "1":
                    addStudentToClass();
                    break;
                case "2":
                    listStudentsInClass();
                    break;
                case "3":
                    updateStudentDetails();
                    break;
                case "4":
                    removeStudentFromClass();
                    break;
                case "5":
                    viewStudentDetails();
                    break;
                case "6":
                    addMarkToStudent();
                    break;
                case "7":
                    exit = true;
                    view.printOutput("Exiting Student Management");
                    break;
                default:
                    view.printOutput("Invalid input, please try again.");
            }
        }
    }

    private void staffManagement() {
        view.printOutput("Functionality to manage staff not implemented yet.");
    }

    private void teacherManagement() {
        boolean exit = false;
        while (!exit) {
            view.printOutput("Teacher Management: \n1. Add Teacher \n2. Delete Teacher \n3. Update Teacher Subject \n4. Associate Teacher with Class \n5. Disassociate Teacher from Class \n6. Exit");
            String input = view.getInput();
            switch (input) {
                case "1":
                    addTeacher();
                    break;
                case "2":
                    deleteTeacher();
                    break;
                case "3":
                    updateTeacherSubject();
                    break;
                case "4":
                    associateTeacherWithClass();
                    break;
                case "5":
                    disassociateTeacherFromClass();
                    break;
                case "6":
                    exit = true;
                    view.printOutput("Exiting Teacher Management");
                    break;
                default:
                    view.printOutput("Invalid input, please try again.");
            }
        }
    }

    private void addTeacher() {
        view.printOutput("Enter Teacher ID: ");
        String id = view.getInput();
        view.printOutput("Enter Teacher Name: ");
        String name = view.getInput();
        Teacher newTeacher = new Teacher(Integer.parseInt(id), name);
        schoolController.addTeacher(newTeacher);
        view.printOutput("Teacher added successfully.");
    }

    private void deleteTeacher() {
        view.printOutput("Enter Teacher ID to delete: ");
        String id = view.getInput();
        if (schoolController.deleteTeacher(id)) {
            view.printOutput("Teacher deleted successfully.");
        } else {
            view.printOutput("Teacher not found.");
        }
    }

    private void updateTeacherSubject() {
        view.printOutput("Enter Teacher ID to update their subject:");
        String teacherId = view.getInput();
        Teacher teacher = schoolController.findTeacherById(teacherId);
        if (teacher == null) {
            view.printOutput("Teacher not found.");
            return;
        }
        view.printOutput("Current Subject: " + (teacher.getSubject() != null ? teacher.getSubject().getSubjectName() : "None"));
        view.printOutput("Enter new Subject ID:");
        view.printOutput("Enter new Subject ID:");
        String subjectId = view.getInput();
        Subject newSubject = findSubjectById(subjectId);
        if (newSubject == null) {
            view.printOutput("Subject not found.");
            return;
        }
        teacher.setSubject(newSubject);
        view.printOutput("Teacher's subject updated successfully to " + newSubject.getSubjectName());
    }

    private void associateTeacherWithClass() {
        Teacher teacher = findTeacherById();
        if (teacher == null) {
            view.printOutput("Teacher not found.");
            return;
        }
        Class cls = getClassForAssociation();
        if (cls == null) {
            view.printOutput("Invalid class grade. Please enter a valid class grade.");
            return;
        }
        teacher.addClass(cls);
        view.printOutput("Teacher associated with class " + cls.getClassName());
    }

    private void disassociateTeacherFromClass() {
        Teacher teacher = findTeacherById();
        if (teacher == null) {
            view.printOutput("Teacher not found.");
            return;
        }
        Class cls = getClassForAssociation();
        if (cls == null) {
            view.printOutput("Invalid class grade. Please enter a valid class grade.");
            return;
        }
        teacher.removeClass(cls);
        view.printOutput("Teacher disassociated from class " + cls.getClassName());
    }

    private void addStudentToClass() {
        view.printOutput("Enter class grade (1-12):");
        int grade = Integer.parseInt(view.getInput());
        if (grade < 1 || grade > 12) {
            view.printOutput("Invalid class grade. Please enter a valid number between 1 and 12.");
            return;
        }
        Class cls = classes[grade - 1];
        view.printOutput("Enter Student Name:");
        String name = view.getInput();
        int studentId = cls.getStudents().size() + 1;
        Student student = new Student(studentId, grade,name);
        cls.addStudent(student);
        view.printOutput("Student added successfully. Student ID: Stu" + studentId);
        notificationController.notifyObservers("New student added: " + name + " in grade " + grade);
    }

    private void listStudentsInClass() {
        view.printOutput("Enter class grade (1-12):");
        int grade = Integer.parseInt(view.getInput());
        if (grade < 1 || grade > 12) {
            view.printOutput("Invalid class grade. Please enter a valid number between 1 and 12.");
            return;
        }
        Class cls = classes[grade - 1];
        List<Student> students = cls.getStudents();
        if (students.isEmpty()) {
            view.printOutput("No students found in grade " + grade);
        } else {
            view.printOutput("Students in grade " + grade + ":");
            students.forEach(student -> view.printOutput(student.getName() + " (ID: " + student.getUserId() + ")"));
        }
    }

    private void addMarkToStudent() {
        view.printOutput("Enter Student ID:");
        String studentId = view.getInput();
        Student student = schoolController.findStudentById(studentId);
        if (student == null) {
            view.printOutput("Student not found.");
            return;
        }
        view.printOutput("Enter Subject ID:");
        String subjectId = view.getInput();
        view.printOutput("Enter Subject Name:");
        String subjectName = view.getInput();
        view.printOutput("Enter mark:");
        float mark = Float.parseFloat(view.getInput());
        student.addMark(new Mark(subjectId, subjectName, mark, 100)); // Assuming a full score of 100
        view.printOutput("Mark added successfully.");
    }

    private Teacher findTeacherById() {
        view.printOutput("Enter Teacher ID:");
        String teacherId = view.getInput();
        return schoolController.findTeacherById(teacherId);
    }

    private Class getClassForAssociation() {
        view.printOutput("Enter class grade to associate (1-12):");
        int grade = Integer.parseInt(view.getInput());
        if (grade >= 1 && grade <= classes.length) {
            return classes[grade - 1];
        }
        return null;
    }

    private Subject findSubjectById(String id) {
        return schoolController.findSubjectById(id);
    }

    private void updateStudentDetails() {
        view.printOutput("Enter Student ID to update:");
        String studentId = view.getInput();
        Student student = schoolController.findStudentById(studentId);
        if (student == null) {
            view.printOutput("Student not found.");
            return;
        }

        view.printOutput("Enter new Student Name (current: " + student.getName() + "):");
        String newName = view.getInput();
        student.setName(newName);
        view.printOutput("Student details updated successfully.");
    }

    private void removeStudentFromClass() {
        view.printOutput("Enter class grade (1-12):");
        int grade = Integer.parseInt(view.getInput());
        if (grade < 1 || grade > 12) {
            view.printOutput("Invalid class grade. Please enter a valid number between 1 and 12.");
            return;
        }

        Class cls = classes[grade - 1];
        view.printOutput("Enter Student ID to remove:");
        String studentId = view.getInput();
        boolean removed = cls.removeStudent(studentId);

        if (removed) {
            view.printOutput("Student removed successfully.");
        } else {
            view.printOutput("Student not found.");
        }
    }

    private void viewStudentDetails() {
        view.printOutput("Enter Student ID to view details:");
        String studentId = view.getInput();
        Student student = schoolController.findStudentById(studentId);
        if (student == null) {
            view.printOutput("Student not found.");
            return;
        }

        view.printOutput("Student Details:");
        view.printOutput("Name: " + student.getName());
        view.printOutput("User ID: " + student.getUserId());
        view.printOutput("Subjects: " + student.getSubjects().size());
        view.printOutput("Marks: " + student.getMarks().size());
    }


}

