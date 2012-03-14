package org.nodejs.ide.eclipse.debug;

import org.eclipse.debug.core.model.DebugElement;
import org.eclipse.debug.core.model.IDebugTarget;
import org.nodejs.ide.eclipse.Activator;

public class NodeDebugElement extends DebugElement {

    public NodeDebugElement(IDebugTarget target) {
        super(target);
    }

    @Override
    public String getModelIdentifier() {
        return Activator.PLUGIN_ID;
    }

}
