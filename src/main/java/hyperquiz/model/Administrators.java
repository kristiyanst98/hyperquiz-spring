package hyperquiz.model;

import java.util.ArrayList;
import java.util.List;

public class Administrators extends User {
    private List<Quiz> quizzesBlocked;

    public Administrators() {
        this.quizzesBlocked = new ArrayList<>();
    }

}
