package controller;

import model.entity.Periodical;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SessionContent {
    private HashMap<Integer, Periodical> periodicals;
    private static Set<Periodical> buyList;
    private static Set<Periodical> userPeriodicals;

    private SessionContent() {
        periodicals = new HashMap<>();
        buyList = new HashSet<>();
        userPeriodicals = new HashSet<>();
    }

    private static class Holder {
        private static final SessionContent INSTANCE = new SessionContent();
    }

    public static SessionContent getInstance() {
        return Holder.INSTANCE;
    }

    public HashMap<Integer, Periodical> getPeriodicals() {
        return periodicals;
    }

    public Set<Periodical> getBuyList() {
        return buyList;
    }

    public Set<Periodical> getUserPeriodicals() {
        return userPeriodicals;
    }
}
