package org.device.management.common.tables;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.device.management.claim.ui.utils.I18N;
import org.device.management.claim.ui.utils.SPUIStyleDefinitions;
import org.device.management.claim.ui.utils.TableColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.EventBus;

import com.vaadin.data.Container;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Abstract table to handling entity
 *
 * @param <E>
 *            e is the entity class
 * @param <I>
 *            i is the id of the table
 */
public abstract class AbstractTable extends Table {

    private static final float DEFAULT_COLUMN_NAME_MIN_SIZE = 0.8F;

    private static final long serialVersionUID = 4856562746502217630L;

    protected static final String ACTION_NOT_ALLOWED_MSG = "message.action.not.allowed";

//    @Autowired
//    protected transient EventBus.SessionEventBus eventBus;
    
    @Autowired
    protected I18N i18n;


    /**
     * Initialize the components.
     */
    @PostConstruct
    protected void init() {
        //setStyleName("sp-table");
        setSizeFull();
        setImmediate(true);
        setHeight(100.0F, Unit.PERCENTAGE);
        addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        addStyleName(ValoTheme.TABLE_SMALL);
        setSortEnabled(false);
        setId(getTableId());
        addCustomGeneratedColumns();
        addNewContainerDS();
        setColumnProperties();
        setDefault();
       // addValueChangeListener(event -> onValueChange());
        selectRow();
        setPageLength(SPUIStyleDefinitions.PAGE_SIZE);
        setWidth(100.0F, Unit.PERCENTAGE);
        setDataAvailable(getContainerDataSource().size() != 0);
       // eventBus.subscribe(this);
    }

    @PreDestroy
    protected void destroy() {
        //eventBus.unsubscribe(this);
    }

    /**
     * Gets the selected item id or in multiselect mode a set of selected ids.
     * 
     * @param table
     *            the table to retrieve the selected ID(s)
     * @return the ID(s) which are selected in the table
     */
    public static <T> Set<T> getTableValue(final Table table) {
        @SuppressWarnings("unchecked")
        Set<T> values = (Set<T>) table.getValue();
        if (values == null) {
            values = Collections.emptySet();
        }
        return values;
    }


    private void setDefault() {
        setSelectable(true);
        setMultiSelect(true);
        setDragMode(TableDragMode.MULTIROW);
        setColumnCollapsingAllowed(false);
    }

    private void addNewContainerDS() {
        final Container container = createContainer();
        addContainerProperties(container);
        setContainerDataSource(container);
        final int size = container.size();
        if (size == 0) {
            setData(SPUIStyleDefinitions.NO_DATA);
        }
    }

    protected void selectRow() {
        if (!isMaximized()) {
            if (isFirstRowSelectedOnLoad()) {
                selectFirstRow();
            } else {
                setValue(getItemIdToSelect());
            }
        }
    }

    /**
     * Select all rows in the table.
     */
    protected void selectAll() {
        if (isMultiSelect()) {
            // only contains the ItemIds of the visible items in the table
            setValue(getItemIds());
        }
    }

    protected abstract void setColumnProperties() ;

    private void selectFirstRow() {
        final Container container = getContainerDataSource();
        final int size = container.size();
        if (size > 0) {
            select(firstItemId());
        }
    }

    protected void applyMaxTableSettings() {
        setColumnProperties();
        setValue(null);
        setSelectable(false);
        setMultiSelect(false);
        setDragMode(TableDragMode.NONE);
        setColumnCollapsingAllowed(true);
    }

    protected void applyMinTableSettings() {
        setDefault();
        setColumnProperties();
        selectRow();
    }

    protected void refreshFilter() {
        addNewContainerDS();
        setColumnProperties();
        selectRow();
    }


    /**
     * Get Id of the table.
     * 
     * @return Id.
     */
    protected abstract String getTableId();

    /**
     * Create container of the data to be displayed by the table.
     */
    protected abstract Container createContainer();

    /**
     * Add container properties to the container passed in the reference.
     * 
     * @param container
     *            reference of {@link Container}
     */
    protected abstract void addContainerProperties(Container container);

    /**
     * Add any generated columns if required.
     */
    protected void addCustomGeneratedColumns() {
        // can be overriden
    }

    /**
     * Check if first row should be selected by default on load.
     * 
     * @return true if it should be selected otherwise return false.
     */
    protected abstract boolean isFirstRowSelectedOnLoad();

    /**
     * Get Item Id should be displayed as selected.
     * 
     * @return reference of Item Id of the Row.
     */
    protected abstract Object getItemIdToSelect();

    /**
     * Check if the table is maximized or minimized.
     * 
     * @return true if maximized, otherwise false.
     */
    protected abstract boolean isMaximized();

    /**
     * Based on table state (max/min) columns to be shown are returned.
     * 
     * @return List<TableColumn> list of visible columns
     */
    protected abstract List<TableColumn> getTableVisibleColumns() ;


    protected abstract void setDataAvailable(boolean available);

}
