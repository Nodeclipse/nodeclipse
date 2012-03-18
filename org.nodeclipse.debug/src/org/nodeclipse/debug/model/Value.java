package org.nodeclipse.debug.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

public class Value extends NodeDebugElement implements IValue {
    private String value;

    public Value(IDebugTarget target, String value) {
        super(target);
        this.value = value;
    }

    @Override
    public String getReferenceTypeName() throws DebugException {
        // TODO Auto-generated method stub
        return "string";
    }

    @Override
    public String getValueString() throws DebugException {
        return value;
    }

    @Override
    public boolean isAllocated() throws DebugException {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public IVariable[] getVariables() throws DebugException {
        // TODO Auto-generated method stub
        return new IVariable[0];
    }

    @Override
    public boolean hasVariables() throws DebugException {
        // TODO Auto-generated method stub
        return false;
    }

}
