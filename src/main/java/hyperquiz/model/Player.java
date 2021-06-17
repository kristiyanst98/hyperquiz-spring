package hyperquiz.model;

import java.util.ArrayList;
import java.util.List;

public class Player extends User {
    private List<QuizResult> results;
    private int overallScore;
    private Rank rank;

    public Player() {
        this.results = new ArrayList<>();
    }

    public Player(String username, String email, String password, Gender gender, boolean status) {
        super(username, email, password, gender, status);
        this.results=new ArrayList<>();
    }

    public int getOverallScore() {
        for(QuizResult result:results){
            this.overallScore+=result.getScore();
        }
        return overallScore;
    }

    public Rank getRank() {
        if(overallScore<50){
            setRank(Rank.Bad);
        }else{
            setRank(Rank.Good);
        }
        return rank;
    }

    private void setRank(Rank rank) {
        this.rank = rank;
    }

    public List<QuizResult> getResults() {
        return results;
    }
}
