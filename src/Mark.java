public class Mark {
    private String subjectName;
    private String subjectId; // Add this line
    private double individualMark;
    private double totalMark;

    public Mark() {
        this.subjectName = "";
        this.individualMark = 0;
        this.totalMark = 100; // Default value assuming full marks
    }

    public  Mark(String subjectId, String subjectName,double mark) {
        this.subjectName = subjectName;
                this.subjectId = subjectId;
                this.individualMark = mark;
    }

    public Mark(String subjectName, String subjectId, float individualMark, float totalMark) {
        this.subjectName = subjectName;
        this.subjectId = subjectId;
        this.individualMark = individualMark;
        this.totalMark = totalMark;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectId() {
        return subjectId; // Add getter for subjectId
    }

    public double getIndividualMark() {
        return individualMark;
    }

    public void setIndividualMark(float individualMark) {
        this.individualMark = individualMark;
    }

    public double getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(float totalMark) {
        this.totalMark = totalMark;
    }

    public double getPercentage() {
        return totalMark > 0 ? (individualMark / totalMark) * 100 : 0;
    }
}
