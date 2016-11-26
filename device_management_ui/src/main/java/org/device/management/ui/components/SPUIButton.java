package org.device.management.ui.components;

import com.vaadin.server.Resource;
import com.vaadin.ui.Button;

/**
 * Basic button for SPUI. Any commonality can be decorated.
 * 
 *
 *
 */

public class SPUIButton extends Button {

    /**
     * ID.
     */
    private static final long serialVersionUID = -7327726430436273739L;

    /**
     * Parametric constructor.
     * 
     * @param id
     *            as String
     * @param buttonName
     *            as String
     * @param buttonDesc
     *            as String
     */
    public SPUIButton(final String id, final String buttonName, final String buttonDesc) {
        super(buttonName);
        setDescription(buttonDesc);
        setImmediate(false);
        if (null != id) {
            setId(id);
        }
    }

    /**
     * Toogle Icon on action.
     * 
     * @param icon
     *            as Resource
     */
    public void togleIcon(Resource icon) {
        setIcon(icon);
    }
}
