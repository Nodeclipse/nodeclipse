package org.nodejs.ide.eclipse.debug.model;

import org.eclipse.debug.core.model.DebugElement;
import org.eclipse.debug.core.model.IDebugTarget;
import org.nodejs.ide.eclipse.debug.Activator;

public class NodeDebugElement extends DebugElement {

    public NodeDebugElement(IDebugTarget target) {
        super(target);
    }

    @Override
    public String getModelIdentifier() {
        return Activator.PLUGIN_ID;
    }

}
