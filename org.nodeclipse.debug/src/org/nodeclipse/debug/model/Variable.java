package org.nodeclipse.debug.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

public class Variable extends NodeDebugElement implements IVariable {
    private Value value;

    public Variable(IDebugTarget target) {
        super(target);
        // TODO Auto-generated method stub
        value = new Value(target, "value");
    }

    @Override
    public void setValue(String expression) throws DebugException {
        // TODO Auto-generated method stub

    }

    @Override
    public void setValue(IValue value) throws DebugException {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean supportsValueModification() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean verifyValue(String expression) throws DebugException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean verifyValue(IValue value) throws DebugException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public IValue getValue() throws DebugException {
        // TODO Auto-generated method stub
        return value;
    }

    @Override
    public String getName() throws DebugException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getReferenceTypeName() throws DebugException {
        // TODO Auto-generated method stub
        return value.getReferenceTypeName();
    }

    @Override
    public boolean hasValueChanged() throws DebugException {
        // TODO Auto-generated method stub
        return false;
    }

}
