package controller.utils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class CurrencyTag extends SimpleTagSupport {
    private long value;
    private String currType;
    private int rate;

    public CurrencyTag() {

    }

    public void setValue(long value) {
        this.value = value;
    }

    public void setCurrType(String currType) {
        this.currType = currType;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }


    @Override
    public void doTag() throws JspException, IOException {
        String result = String.valueOf(value * rate);
        if (value * rate < 0) {
            throw new IllegalArgumentException();
        }
        String unit;
        int length = result.length();
        if (length < 3) {
            unit = "0";
        } else {
            unit = result.substring(0, length - 2);
        }
        String rest = result.substring(length - 2, length);
        getJspContext().getOut().print(currType + unit + "." + rest);
    }

}
