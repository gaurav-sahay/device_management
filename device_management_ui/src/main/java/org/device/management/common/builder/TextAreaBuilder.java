package org.device.management.common.builder;

import com.vaadin.ui.TextArea;
import com.vaadin.ui.themes.ValoTheme;

/**
 * TextArea builder.
 *
 */
public class TextAreaBuilder extends AbstractTextFieldBuilder<TextArea> {

    private static final int TEXT_AREA_DEFAULT_MAX_LENGTH = 512;

    /**
     * Constructor.
     */
    public TextAreaBuilder() {
        maxLengthAllowed(TEXT_AREA_DEFAULT_MAX_LENGTH);
        styleName(ValoTheme.TEXTAREA_TINY);
    }

    @Override
    protected TextArea createTextComponent() {
        final TextArea textArea = new TextArea();
        textArea.addStyleName(ValoTheme.TEXTAREA_SMALL);
        return textArea;
    }

}
