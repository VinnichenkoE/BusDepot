package com.vinnichenko.bdepot.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * The type Pagination tag.
 */
public class PaginationTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger();
    private int numberPages;

    /**
     * Sets page amount.
     *
     * @param numberPages the number pages
     */
    public void setPageAmount(int numberPages) {
        this.numberPages = numberPages;
    }

    @Override
    public int doStartTag() {
        try {
            for (int i = 1; i <= numberPages; i++) {
                pageContext.getOut().write("<a href=\"controller?command=welcome_page&page_number="
                        + i + "\" class=\"btn btn-outline-info\">" + i + "</a>");
            }
        } catch (IOException e) {
            LOGGER.error("Error while writing to out stream", e);
        }
        return SKIP_BODY;
    }
}
