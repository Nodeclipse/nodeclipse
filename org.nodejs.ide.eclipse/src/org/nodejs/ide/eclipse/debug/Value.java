package org.nodejs.ide.eclipse.debug;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IVariable;

public class Value implements org.eclipse.debug.core.model.IValue {
    DebugTarget target;

    public Value(DebugTarget target, String value) {
        this.target =target;
    }

    @Override
    public String getModelIdentifier() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IDebugTarget getDebugTarget() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ILaunch getLaunch() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getAdapter(Class arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getReferenceTypeName() throws DebugException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getValueString() throws DebugException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isAllocated() throws DebugException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public IVariable[] getVariables() throws DebugException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasVariables() throws DebugException {
        // TODO Auto-generated method stub
        return false;
    }

}
