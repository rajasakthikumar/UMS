import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private List<Subject> subjects;
    private List<Mark> marks;

    public Student(int id, int grade, String name) {

        super("Stu" + (grade < 10 ? "0" + grade : grade) + (id < 10 ? "0" + id : id), name, UserType.Student);
        this.subjects = new ArrayList<>();
        this.marks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    // Add a new subject and a corresponding mark slot
    public void addSubjectWithMark(Subject subject, Mark mark) {
        subjects.add(subject);
        marks.add(mark);
    }

    // Add a list of subjects, initializing empty marks for them
    public void addSubjects(List<Subject> newSubjects) {
        for (Subject subject : newSubjects) {
            subjects.add(subject);
            marks.add(new Mark(subject.getSubjectId(),subject.getSubjectName(), 0)); // Assuming Mark has a constructor accepting subjectId and initial mark
        }
    }

    // Calculate total marks
    public double getTotalMarks() {
        return marks.stream()
                .mapToDouble(Mark::getIndividualMark)
                .sum();
    }

    // Update an existing mark
    public void updateMark(String subjectId, float newMark) {
        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getSubjectId().equals(subjectId)) {
                marks.get(i).setIndividualMark(newMark);
                return;
            }
        }
    }

    public void addMark(Mark newMark) {
        int subjectIndex = -1;
        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getSubjectId().equals(newMark.getSubjectId())) {
                subjectIndex = i;
                break;
            }
        }

        if (subjectIndex != -1) {
            // Subject found, add the mark at the corresponding index
            marks.set(subjectIndex, newMark);
        } else {
            // Subject not found, create a new subject and add it along with the new mark
            Subject newSubject = new Subject(newMark.getSubjectName(), newMark.getSubjectId());
            subjects.add(newSubject);
            marks.add(newMark);
        }
    }


    public void addMarkForSubject(Mark newMark) {
        String targetSubjectId = newMark.getSubjectId();
        for (Subject subject : subjects) {
            if (subject.getSubjectId().equals(targetSubjectId)) {
                subject.addMark(newMark);
                marks.add(newMark);
                return;
            }
        }
        System.out.println("Subject not found for ID: " + targetSubjectId);
    }

    public void setName(String name) {
        super.name = name;
    }

}
