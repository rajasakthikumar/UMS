import java.util.List;

public interface Observer {
    void update(String message);
    void setInterestCategories(List<String> categories);

}


