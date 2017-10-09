package controller;

import model.entity.Periodical;

import java.util.HashMap;

public class ContextStorage {
    private HashMap<Integer, Periodical> periodicals;


    private ContextStorage() {
        periodicals = new HashMap<>();
    }

    private static class Holder {
        private static final ContextStorage INSTANCE = new ContextStorage();
    }

    public static ContextStorage getInstance() {
        return Holder.INSTANCE;
    }

    public HashMap<Integer, Periodical> getPeriodicals() {
        return periodicals;
    }

}
