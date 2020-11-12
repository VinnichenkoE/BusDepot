package com.vinnichenko.bdepot.controller.tag;

import com.vinnichenko.bdepot.util.DateConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class DateTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger();
    private long date;

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public int doStartTag() {
        String date = DateConverter.toDate(this.date);
        try {
            pageContext.getOut().write(date);
        } catch (IOException e) {
            LOGGER.error("Error while writing to out stream", e);
        }
        return SKIP_BODY;
    }
}
