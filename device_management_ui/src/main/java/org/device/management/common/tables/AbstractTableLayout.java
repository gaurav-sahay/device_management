package org.device.management.common.tables;

import org.device.management.claim.ui.utils.ShortCutUtils;

import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class AbstractTableLayout extends VerticalLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AbstractTableHeader tableHeader;

	private AbstractTable table;

	protected void init(final AbstractTableHeader tableHeader, final AbstractTable table) {
		this.tableHeader = tableHeader;
		this.table = table;
		buildLayout();
	}

	private void buildLayout() {
        setSizeFull();
        setSpacing(true);
        setMargin(false);
        //setStyleName("group");
        final VerticalLayout tableHeaderLayout = new VerticalLayout();
        tableHeaderLayout.setSizeFull();
        tableHeaderLayout.setSpacing(false);
        tableHeaderLayout.setMargin(false);
        
//        tableHeaderLayout.setStyleName("table-layout");
        tableHeaderLayout.addComponent(tableHeader);

        tableHeaderLayout.setComponentAlignment(tableHeader, Alignment.TOP_CENTER);
        if (isShortCutKeysRequired()) {
            final Panel tablePanel = new Panel();
//            tablePanel.setStyleName("table-panel");
            //tablePanel.setHeight(100.0F, Unit.PERCENTAGE);
//            tablePanel.setWidth(100.0F, Unit.PERCENTAGE);
            tablePanel.setWidth("500px");
            tablePanel.setContent(table);
            tablePanel.addActionHandler(getShortCutKeysHandler());
            tablePanel.addStyleName(ValoTheme.PANEL_BORDERLESS);
            tableHeaderLayout.addComponent(tablePanel);
            tableHeaderLayout.setComponentAlignment(tablePanel, Alignment.TOP_CENTER);
            tableHeaderLayout.setExpandRatio(tablePanel, 1.0F);
        } else {
            tableHeaderLayout.addComponent(table);
            tableHeaderLayout.setComponentAlignment(table, Alignment.TOP_CENTER);
            tableHeaderLayout.setExpandRatio(table, 1.0F);
        }

        addComponent(tableHeaderLayout);
        setComponentAlignment(tableHeaderLayout, Alignment.TOP_CENTER);
        setExpandRatio(tableHeaderLayout, 1.0F);
    }
	
	/**
     * If any short cut keys required on the table.
     * 
     * @return true if required else false. Default is 'true'.
     */
    protected boolean isShortCutKeysRequired() {
        return true;
    }
    
    /**
     * Get the action handler for the short cut keys.
     * 
     * @return reference of {@link Handler} to handler the short cut keys.
     *         Default is null.
     */
    protected Handler getShortCutKeysHandler() {
        return new TableShortCutHandler();
    }
    
    public void publishEvent() {
		// TODO Auto-generated method stub
		
	}
    
    private class TableShortCutHandler implements Handler {

        private static final String SELECT_ALL_TEXT = "Select All";
        private final ShortcutAction selectAllAction = new ShortcutAction(SELECT_ALL_TEXT, ShortcutAction.KeyCode.A,
                new int[] { ShortCutUtils.getCtrlOrMetaModifier() });

        private static final long serialVersionUID = 1L;

        @Override
        public void handleAction(final Action action, final Object sender, final Object target) {
            if (!selectAllAction.equals(action)) {
                return;
            }
            table.selectAll();
            publishEvent();
        }

        @Override
        public Action[] getActions(final Object target, final Object sender) {
            return new Action[] { selectAllAction };
        }
    }

	
}
