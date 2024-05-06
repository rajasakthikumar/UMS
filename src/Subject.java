import java.util.ArrayList;
import java.util.List;

public class Subject {
    private String subjectName;
    private String subjectId;
    private Mark marks; // List to store marks related to this subject

    public Subject(String subjectName, String subjectId) {
        this.subjectName = subjectName;
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void addMark(Mark mark) {
        if (mark.getSubjectId().equals(this.subjectId)) {
            mark = mark;
        }
    }

    public Mark getMarks() {
        return marks;
    }
}
