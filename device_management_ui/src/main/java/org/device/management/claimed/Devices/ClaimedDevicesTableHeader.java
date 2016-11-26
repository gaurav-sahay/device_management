package org.device.management.claimed.Devices;

import org.device.management.claim.ui.utils.SPUIStyleDefinitions;
import org.device.management.common.tables.AbstractTableHeader;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

@SpringComponent
@ViewScope
public class ClaimedDevicesTableHeader extends AbstractTableHeader {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Autowired
	private ClaimDeviceAddUpdateWindow claimDeviceAddUpdateWindow;

	@Override
	protected void init() {
		super.init();
		claimDeviceAddUpdateWindow.init();


	}
	@Override
	protected String getHeaderCaption() {
		return i18n.get("header.claim.device.table");
	}

	@Override
	protected String getSearchBoxId() {
		return SPUIStyleDefinitions.CLAIMED_DEVICE_SEARCH_BOX_ID;
	}

	@Override
	protected void searchBy(String newSearchText) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void addNewItem(ClickEvent event) {
		claimDeviceAddUpdateWindow.resetComponents();
		final Window claimAddUpdateWindow = claimDeviceAddUpdateWindow.getWindow();
		UI.getCurrent().addWindow(claimAddUpdateWindow);
		claimAddUpdateWindow.setVisible(Boolean.TRUE);
	}

	@Override
	protected String getAddIconId() {
		return SPUIStyleDefinitions.CLAIM_DEVICE_ADD_ICON_ID;
	}

	@Override
	protected void deleteItem(ClickEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void resetSearchText() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getSearchRestIconId() {
		return SPUIStyleDefinitions.CLAIM_DEVICE_SEARCH_ICON_RESET_ID;
	}

}
