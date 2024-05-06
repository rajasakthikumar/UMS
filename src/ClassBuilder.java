import java.util.ArrayList;

public class ClassBuilder {

    private String className;
    private ArrayList<Subject> subjects;

    public ClassBuilder() {
        this.subjects = new ArrayList<>();
    }

    public ClassBuilder setGrade(int grade) {
        className = String.valueOf(grade);



        if ((grade >= 1) && (grade <= 10)) {
//            addSubjects
            if (subjects.size() !=0) {
                subjects.clear();
            }
            subjects.add(new Subject("Tamil","1"));
            subjects.add(new Subject("English", "2"));
            subjects.add(new Subject("Maths","3"));
            subjects.add(new Subject("Science","4"));
            subjects.add(new Subject("Social Science","5"));

        } else if ((grade > 10) && (grade <= 12) ){
            subjects.clear();
            subjects.add(new Subject("Tamil","6"));
            subjects.add(new Subject("English", "7"));
            subjects.add(new Subject("Maths","8"));
            subjects.add(new Subject("Physics","9"));
            subjects.add(new Subject("Chemistry","10"));
            subjects.add(new Subject("Biology","11"));
        } else {
            return this;
        }
    return this;
    }

    public Class build() {
    return new Class(className, subjects);
    }


}
