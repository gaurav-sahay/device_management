package org.device.management.menu;

import java.io.Serializable;
import java.util.List;

import com.vaadin.server.Resource;

/**
 * Describe a menu entry for the Dashboard.
 * 
 *
 *
 */
public interface DashboardMenuItem extends Serializable {

    /**
     * Return the view name, which is navigate to after clicking the menu item.
     * 
     * @return the view name
     */
    String getViewName();

    /**
     * The icon for the menu item.
     * 
     * @return vaadin resource
     */
    Resource getDashboardIcon();

    /**
     * Return the dashboard caption.
     * 
     * @return the caption
     */
    String getDashboardCaption();

    /**
     * Return the menu item description.
     * 
     * @return the menu item description
     */
    String getDashboardCaptionLong();

    /**
     * Return the view permission to see the menu item. One permission must
     * match to see the menu item.
     * 
     * @return the list of permissions.
     */
    List<String> getPermissions();

}
