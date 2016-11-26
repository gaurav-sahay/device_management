package org.device.management.claim.ui.utils;

/**
 * Table conlumn properties.
 * 
 *
 *
 */
public class TableColumn {

    private String columnPropertyId;

    private String columnHeader;

    private float expandRatio;

    /**
     * Constructor.
     * 
     * @param columnPropertyId
     * @param columnHeader
     * @param expandRatio
     */
    public TableColumn(final String columnPropertyId, final String columnHeader, final float expandRatio) {
        this.columnPropertyId = columnPropertyId;
        this.columnHeader = columnHeader;
        this.expandRatio = expandRatio;
    }

    public String getColumnPropertyId() {
        return columnPropertyId;
    }

    public void setColumnPropertyId(final String columnProperty) {
        this.columnPropertyId = columnProperty;
    }

    public String getColumnHeader() {
        return columnHeader;
    }

    public void setColumnHeader(final String columnHeader) {
        this.columnHeader = columnHeader;
    }

    public float getExpandRatio() {
        return expandRatio;
    }

    public void setExpandRatio(final float expandRatio) {
        this.expandRatio = expandRatio;
    }

}
