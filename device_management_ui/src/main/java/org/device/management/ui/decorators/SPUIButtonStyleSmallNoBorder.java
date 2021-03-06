package org.device.management.ui.decorators;

import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Style for button: Small NoBorder.
 * 
 *
 *
 *
 *
 *
 */
public class SPUIButtonStyleSmallNoBorder implements SPUIButtonDecorator {

    /**
     * Decorate Button and return.
     * 
     * @param button
     *            as Button
     * @param style
     *            as String
     * @param setStyle
     *            as String
     * @param icon
     *            as resource
     * @return Button
     */
    @Override
    public Button decorate(final Button button, final String style, final boolean setStyle, final Resource icon) {
        // Set Style
        if (null != style && setStyle) {
            button.addStyleName(style);
        }
        button.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        // Set icon
        if (null != icon) {
            button.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
            button.addStyleName("button-no-border");
            button.setIcon(icon);
        }

        return button;
    }

}
