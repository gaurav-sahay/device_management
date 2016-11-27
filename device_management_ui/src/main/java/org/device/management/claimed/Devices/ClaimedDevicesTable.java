package org.device.management.claimed.Devices;

import java.util.ArrayList;
import java.util.List;

import org.device.management.claim.ui.utils.SPUIStyleDefinitions;
import org.device.management.claim.ui.utils.TableColumn;
import org.device.management.common.tables.AbstractTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
@SpringComponent
@VaadinSessionScope
public class ClaimedDevicesTable extends AbstractTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(ClaimedDevicesTable.class);

	@Override
	protected void init() {
		super.init();
	}

	@Override
	protected void setColumnProperties() {
		final List<TableColumn> columnList = getTableVisibleColumns();
		final List<Object> swColumnIds = new ArrayList<>();
		for (final TableColumn column : columnList) {
			setColumnHeader(column.getColumnPropertyId(),column.getColumnHeader());
			setColumnExpandRatio(column.getColumnPropertyId(),column.getExpandRatio());
			swColumnIds.add(column.getColumnPropertyId());
		}
		setVisibleColumns(swColumnIds.toArray());
	}

	@Override
	protected String getTableId() {
		return SPUIStyleDefinitions.CLAIM_DEVICE_TABLE_ID;
	}

	@Override
	protected Container createContainer() {
		IndexedContainer container = new IndexedContainer();
		addContainerProperties(container);
//		for(int i = 0;i<10; i++){
//			Item item = container.addItem(""+i);
//			item.getItemProperty(SPUIStyleDefinitions.DEVICE_ID_COLUMN).setValue(""+i);
//			item.getItemProperty(SPUIStyleDefinitions.DEVICE_NAME_COLUMN).setValue("test"+i);
//			item.getItemProperty(SPUIStyleDefinitions.DEVICE_STATUS_COLUMN).setValue("test"+i);
//			item.getItemProperty(SPUIStyleDefinitions.DEVICE_CLAIMED_BY).setValue("test"+i);
//		
//		}
		return container;
	}

	@Override
	protected void addContainerProperties(Container container) {
		container.addContainerProperty(SPUIStyleDefinitions.DEVICE_ID_COLUMN, String.class, "none");
		container.addContainerProperty(SPUIStyleDefinitions.DEVICE_NAME_COLUMN, String.class, "none");
		container.addContainerProperty(SPUIStyleDefinitions.DEVICE_STATUS_COLUMN, String.class, "none");
		container.addContainerProperty(SPUIStyleDefinitions.DEVICE_CLAIMED_BY, String.class, "none");

	}

	@Override
	protected boolean isFirstRowSelectedOnLoad() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Object getItemIdToSelect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean isMaximized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected List<TableColumn> getTableVisibleColumns() {
		final List<TableColumn> columnList = new ArrayList<>();
		columnList.add(new TableColumn(SPUIStyleDefinitions.DEVICE_ID_COLUMN,
				i18n.get("claim.device.table.column.id"), 0.1F));
		columnList.add(new TableColumn(SPUIStyleDefinitions.DEVICE_NAME_COLUMN,
				i18n.get("claim.device.table.column.name"), 0.2F));
		columnList.add(new TableColumn(
				SPUIStyleDefinitions.DEVICE_STATUS_COLUMN, i18n
						.get("claim.device.table.column.status"), 0.1F));
		columnList.add(new TableColumn(SPUIStyleDefinitions.DEVICE_CLAIMED_BY,
				i18n.get("claim.device.table.column.claimedby"), 0.1F));
		return columnList;
	}

	@Override
	protected void setDataAvailable(boolean available) {
		// TODO Auto-generated method stub

	}

}
