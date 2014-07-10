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

    // tag attributes
    private String id;
    private String placeholder;
    private String divClass;
    private String inputClass;
    private String label;
    private boolean inputAddon;
    private String addonText;

    /**
     * Renderes an input field tag with various options defined
     *
     * @return
     * @throws JspException
     */
    @Override
    public int doStartTag() throws JspException {

        try {
            // get the writer object for output.
            JspWriter out = pageContext.getOut();

            // perform html output
            if(label != null && !label.isEmpty()) {
                out.println("<label for=\"input-hole1-par\" class=\"col-lg-2 control-label\">" + label +  "</label>");
            }
            out.println("<div class=\"" + divClass + "\">");

            // appends an 'input type' to the end of an input field if required
            if(inputAddon) {
                out.println("<div class=\"input-group\">");
            }

            out.println("<input type=\"text\" class=\"form-control\" id=\"" + id + "\" placeholder=\"" + placeholder +"\"></input>");

            // appends an 'input type' to the end of an input field if required
            if(inputAddon) {
                out.println("<span class=\"input-group-addon\">" + addonText + "</span></div>");
            }

            out.println("</div>");

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

    public boolean isInputAddon() {
        return inputAddon;
    }

    public void setInputAddon(boolean inputAddon) {
        this.inputAddon = inputAddon;
    }

    public String getAddonText() {
        return addonText;
    }

    public void setAddonText(String addonText) {
        this.addonText = addonText;
    }

}
