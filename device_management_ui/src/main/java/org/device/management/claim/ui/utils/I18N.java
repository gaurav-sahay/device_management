package org.device.management.claim.ui.utils;

import java.io.Serializable;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import com.vaadin.ui.UI;

/**
 * Utility class leveraging Spring Boot auto configuration of
 * {@link MessageSource}.
 *
 *
 *
 *
 */
@Service
public class I18N implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(I18N.class);

    @Autowired
    private transient MessageSource source;

    /**
     * Tries to resolve the message.
     *
     * @param code
     *            the code to lookup up.
     * @param args
     *            Array of arguments that will be filled in for params within
     *            the message.
     *
     * @return the resolved message, or the message code if the lookup fails.
     *
     * @see MessageSource#getMessage(String, Object[], Locale)
     * @see #getLocale()
     */
    public String get(final String code, final Object... args) {
        return getMessage(code, args);
    }

    /**
     * Tries to resolve the message.
     *
     * @param code
     *            the code to lookup up.
     *
     * @return the resolved message, or the message code if the lookup fails.
     *
     * @see MessageSource#getMessage(String, Object[], Locale)
     * @see #getLocale()
     */
    public String get(final String code) {
        return getMessage(code, null);
    }

    private String getMessage(final String code, final Object[] args) {
        try {
            return source.getMessage(code, args, getLocale());
        } catch (final NoSuchMessageException ex) {
            LOG.error("Failed to retrieve message!", ex);
            return code;
        }
    }

    /**
     * Gets the locale of the current Vaadin UI. If the locale can not be
     * determinted, the default locale is returned instead.
     *
     * @return the current locale, never {@code null}.
     * @see com.vaadin.ui.UI#getLocale()
     * @see java.util.Locale#getDefault()
     */
    public Locale getLocale() {
        final UI currentUI = UI.getCurrent();
        Locale locale = currentUI == null ? null : currentUI.getLocale();
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return locale;
    }
}
