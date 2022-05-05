package app.TagSupport;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class MyTag extends TagSupport {
    private String mark;

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public int doStartTag() throws JspException {

        try {
            JspWriter out = pageContext.getOut();
            if (mark != null && mark!="") {
                int grade = Integer.parseInt(mark);
                if (grade < 0) {
                    out.write("check please rating " + mark);
                } else if (grade > 5) {
                    out.write("check please rating " + mark);
                } else {
                    out.write(mark);
                }
            } else {
                out.write("");
            }
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
