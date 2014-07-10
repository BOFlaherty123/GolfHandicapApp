package main.java.co.uk.myhandicap.tags.fields;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Custom JSP Tag for Input Field
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 10/07/14
 * @project MyHandicapApp
 */
public class InputFieldTag extends TagSupport {

    private String id;
    private String placeholder;
    private String divClass;
    private String inputClass;
    private String label;

    @Override
    public int doStartTag() throws JspException {

        try {
            //Get the writer object for output.
            JspWriter out = pageContext.getOut();

            //Perform output
            if(label != null && !label.isEmpty()) {
                out.println("<label for=\"input-hole1-par\" class=\"col-lg-2 control-label\">" + label +  "</label>");
            }

            out.println("<div class=\"" + divClass + "\">" +
                    "<input type=\"text\" class=\"form-control\" id=\"" + id + "\" placeholder=\"" + placeholder +"\"></input>" +
                    "</div>");

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

    public String getDivClass() {
        return divClass;
    }

    public void setDivClass(String divClass) {
        this.divClass = divClass;
    }

    public String getInputClass() {
        return inputClass;
    }

    public void setInputClass(String inputClass) {
        this.inputClass = inputClass;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
