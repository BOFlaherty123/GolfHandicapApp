package main.java.co.uk.myhandicap.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * JSP Tag for Par Field
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 10/07/14
 * @project MyHandicapApp
 */
public class Test extends TagSupport {

    private String id;
    private String placeholder;

    @Override
    public int doStartTag() throws JspException {

        try {
            //Get the writer object for output.
            JspWriter out = pageContext.getOut();

            //Perform output
            out.println("<div class=\"col-lg-10\">" +
                    "<input type=\"text\" class=\"form-control\" id=\"" + id + "\" placeholder=\"" + placeholder + "\"" +
                    "</input> </div>");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }
}
