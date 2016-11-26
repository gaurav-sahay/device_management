package org.device.management.ui.decorators;

import com.vaadin.server.Resource;
import com.vaadin.ui.Button;

/**
 * Interface to define method for button decoration.
 * 
 *
 *
 */
@FunctionalInterface
public interface SPUIButtonDecorator {

    /**
     * Decorate Button.
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
    Button decorate(Button button, String style, boolean setStyle, Resource icon);

}
