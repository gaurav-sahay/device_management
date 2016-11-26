package org.device.management.ui.components;

import org.device.management.ui.decorators.SPUIButtonDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Link;

/**
 * Util class to get the Vaadin UI components for the SP-OS main UI. Factory
 * Approach to create necessary UI component which are decorated Aspect of fine
 * tuning the component or extending the component is separated.
 *
 */
public final class SPUIComponentProvider {
    private static final Logger LOG = LoggerFactory.getLogger(SPUIComponentProvider.class);

    /**
     * Prevent Instance creation as utility class.
     */
    private SPUIComponentProvider() {

    }


    /**
     * Get Button - Factory Approach for decoration.
     *
     * @param id
     *            as string
     * @param buttonName
     *            as string
     * @param buttonDesc
     *            as string
     * @param style
     *            string as string
     * @param setStyle
     *            string as boolean
     * @param icon
     *            as image
     * @param buttonDecoratorclassName
     *            as decorator
     * @return Button as UI
     */
    public static Button getButton(final String id, final String buttonName, final String buttonDesc,
            final String style, final boolean setStyle, final Resource icon,
            final Class<? extends SPUIButtonDecorator> buttonDecoratorclassName) {
        Button button = null;
        SPUIButtonDecorator buttonDecorator = null;
        try {
            // Create instance
            buttonDecorator = buttonDecoratorclassName.newInstance();
            // Decorate button
            button = buttonDecorator.decorate(new SPUIButton(id, buttonName, buttonDesc), style, setStyle, icon);
        } catch (final InstantiationException exception) {
            LOG.error("Error occured while creating Button decorator-" + buttonName, exception);
        } catch (final IllegalAccessException exception) {
            LOG.error("Error occured while acessing Button decorator-" + buttonName, exception);
        }
        return button;
    }


    /**
     * Method to create a link.
     *
     * @param id
     *            of the link
     * @param name
     *            of the link
     * @param resource
     *            path of the link
     * @param icon
     *            of the link
     * @param targetOpen
     *            specify how the link should be open (f. e. new windows =
     *            _blank)
     * @param style
     *            chosen style of the link. Might be {@code null} if no style
     *            should be used
     * @return a link UI component
     */
    public static Link getLink(final String id, final String name, final String resource, final FontAwesome icon,
            final String targetOpen, final String style) {

        final Link link = new Link(name, new ExternalResource(resource));
        link.setId(id);
        link.setIcon(icon);

        link.setTargetName(targetOpen);
        if (style != null) {
            link.setStyleName(style);
        }

        return link;

    }

    /**
     * Generates help/documentation links from within management UI.
     *
     * @param uri
     *            to documentation site
     *
     * @return generated link
     */
    public static Link getHelpLink(final String uri) {

        final Link link = new Link("", new ExternalResource(uri));
        link.setTargetName("_blank");
        link.setIcon(FontAwesome.QUESTION_CIRCLE);
        link.setDescription("Documentation");
        return link;

    }
}
