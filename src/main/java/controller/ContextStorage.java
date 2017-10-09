package controller;

import model.entity.Periodical;

import java.util.HashMap;

public class HttpContextContent {
    private static HashMap<Integer, Periodical> periodicals;


    private HttpContextContent() {
        periodicals = new HashMap<>();
    }

    private static class Holder {
        private static final HttpContextContent INSTANCE = new HttpContextContent();
    }

    public static HttpContextContent getInstance() {
        return Holder.INSTANCE;
    }

    public HashMap<Integer, Periodical> getPeriodicals() {
        return periodicals;
    }

}
