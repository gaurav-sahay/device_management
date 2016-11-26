package org.device.management.claim.ui.utils;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Page;
import com.vaadin.server.WebBrowser;

/**
 * On different systems there are different modifier for short cuts. This
 * utility class handles the cross-platform functionality.
 */
public final class ShortCutUtils {

    private ShortCutUtils() {

    }

    /**
     * Returns the ctrl or meta modifier depending on the platform.
     * 
     * @return on mac return
     *         {@link com.vaadin.event.ShortcutAction.ModifierKey#META} other
     *         platform return
     *         {@link com.vaadin.event.ShortcutAction.ModifierKey#CTRL}
     */
    public static int getCtrlOrMetaModifier() {
        final WebBrowser webBrowser = Page.getCurrent().getWebBrowser();
        if (webBrowser.isMacOSX()) {
            return ShortcutAction.ModifierKey.META;
        }

        return ShortcutAction.ModifierKey.CTRL;
    }
}
