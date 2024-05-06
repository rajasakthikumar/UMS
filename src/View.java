import java.util.Scanner;
import java.util.List;

public class View implements Observer {
    private Scanner scanner;
    private List<String> interestCategories;
    public View() {
        this.scanner = new Scanner(System.in);
    }

    public void printOutput(String outputString){

        System.out.println(outputString);
    }

    public String getInput(){
        return  scanner.nextLine();
    }

    @Override
    public void update(String message) {
        System.out.println("Notification: " + message);
    }

    @Override
    public void setInterestCategories(List<String> categories) {
        this.interestCategories = categories;
    }

}
