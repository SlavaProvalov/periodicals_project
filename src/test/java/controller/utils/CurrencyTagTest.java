package controller.utils;

import org.junit.Ignore;
import org.junit.Test;

public class CurrencyTagTest {
    @Ignore
    @Test
    public void doTag() throws Exception {
        CurrencyTag tag = new CurrencyTag();
        tag.setValue(0);
        tag.setCurrType("");
        tag.setRate(0);
        tag.doTag();
    }
    @Ignore
    @Test
    public void doTagMinusValue() throws Exception {
        CurrencyTag tag = new CurrencyTag();
        tag.setValue(-1);
        tag.setCurrType("");
        tag.setRate(5);
        tag.doTag();

    }
}