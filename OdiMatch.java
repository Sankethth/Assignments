package com.example.Cricket;

import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.ArrayList;
import java.util.Comparator;

class MatchController
{
    Integer getRunsOrWicket()
    {
        return (int) (Math.random()*7);
    }
    void playOver(Team team,Team teamBattingFirst){
        for(int i=0; i<6; i++) {
            Integer runsThisBall = getRunsOrWicket();
            if(runsThisBall > 6) {   //An Invalid run implies a Wicket
                team.loseWicket();
                if (team.getWicketsLost() == 10)
                    return;
            }
            else {
                team.scoreRuns(runsThisBall);
            }
            if(teamBattingFirst != null && team.getRunsScored() > teamBattingFirst.getRunsScored())
                return;
        }
    }
}

class OdiMatch{
    Team team1 = new Team();
    Team team2 = new Team();

    OdiMatchController odiMatchController = new OdiMatchController();
    //.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);


    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public OdiMatchController getOdiMatchController() {
        return odiMatchController;
    }

    public String startMatch(){
        //System.out.println("Match Started");
        odiMatchController.playOdiInnings(team1,null);
        System.out.println("TeamA score "+team1.getRunsScored());
        odiMatchController.playOdiInnings(team2,team1);
        System.out.println("TeamB score "+team2.getRunsScored());
       if(team1.compareTo(team2) > 0)
           return "Team1";
        if(team1.compareTo(team2) == 0)
            return "Tie";
        return "Team2";

    }
}
class OdiMatchController extends MatchController {

   // MatchController matchController;

    public void playOdiInnings(Team team,Team teamBattingFirst)
    {

        for (int i = 0; i < 50; i++) {
            playOver(team,teamBattingFirst);
            if(team.getWicketsLost() == 10)
                return;
            if(teamBattingFirst != null && team.getRunsScored() > teamBattingFirst.getRunsScored())
                return;
        }
    }
}

class Team implements Comparable {
    //ArrayList<Player> team = new ArrayList<>();
    Integer runsScored = 0;
     Integer wicketsLost = 0;
    //Player currentPlayer = team.get(0);
    //Player otherPlayer = team.get(1);
    public Integer getWicketsLost(){
        return wicketsLost;
    }
    public Integer getRunsScored(){
        return runsScored;
    }
    void loseWicket()
    {
        wicketsLost++;
    }
    void scoreRuns(Integer runs){
        runsScored += runs;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(runsScored,((Team)(o)).getRunsScored());
    }
}
class Player
{
    String name;
    Integer runsScored;
}