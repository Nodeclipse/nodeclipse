package org.nodeclipse.debug.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IRegisterGroup;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;

public class StackFrame extends NodeDebugElement implements IStackFrame {

    private Thread thread;
    
    public StackFrame(IDebugTarget target, Thread thread) {
        super(target);
        // TODO Auto-generated constructor stub
        this.thread = thread;
    }

    @Override
    public boolean canStepInto() {
        // TODO Auto-generated method stub
        return thread.canStepInto();
    }

    @Override
    public boolean canStepOver() {
        // TODO Auto-generated method stub
        return thread.canStepOver();
    }

    @Override
    public boolean canStepReturn() {
        // TODO Auto-generated method stub
        return thread.canStepReturn();
    }

    @Override
    public boolean isStepping() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void stepInto() throws DebugException {
        // TODO Auto-generated method stub

    }

    @Override
    public void stepOver() throws DebugException {
        // TODO Auto-generated method stub

    }

    @Override
    public void stepReturn() throws DebugException {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean canResume() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean canSuspend() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isSuspended() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void resume() throws DebugException {
        // TODO Auto-generated method stub

    }

    @Override
    public void suspend() throws DebugException {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean canTerminate() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isTerminated() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void terminate() throws DebugException {
        // TODO Auto-generated method stub

    }

    @Override
    public IThread getThread() {
        // TODO Auto-generated method stub
        return null;
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

    @Override
    public int getLineNumber() throws DebugException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getCharStart() throws DebugException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getCharEnd() throws DebugException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getName() throws DebugException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IRegisterGroup[] getRegisterGroups() throws DebugException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasRegisterGroups() throws DebugException {
        // TODO Auto-generated method stub
        return false;
    }

}
