package org.device.management.claim.ui;

import java.util.List;

import org.device.management.menu.DashboardMenuItem;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;

/**
 * Menu item for deplyoment.
 * 
 *
 *
 */
@Component
@Order(100)
public class DeviceClaimViewMenuItem implements DashboardMenuItem {

    private static final long serialVersionUID = 6112540239655168995L;

    @Override
    public String getViewName() {
        return DeviceClaimView.VIEW_NAME;
    }

    @Override
    public Resource getDashboardIcon() {
        return FontAwesome.HOME;
    }

    @Override
    public String getDashboardCaption() {
        return "Claim Device";
    }

    @Override
    public String getDashboardCaptionLong() {
        return "Claim Device";
    }

    @Override
    public List<String> getPermissions() {
		return null;
    }
}
