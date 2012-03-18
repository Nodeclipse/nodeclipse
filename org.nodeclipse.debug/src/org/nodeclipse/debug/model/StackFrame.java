package org.nodeclipse.debug.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IRegisterGroup;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;

public class StackFrame extends NodeDebugElement implements IStackFrame {

    private Thread thread;
    private IVariable[] variables;
    
    public StackFrame(IDebugTarget target, Thread thread) {
        super(target);
        // TODO Auto-generated constructor stub
        this.thread = thread;
    }

    @Override
    public boolean canStepInto() {
        return thread.canStepInto();
    }

    @Override
    public boolean canStepOver() {
        return thread.canStepOver();
    }

    @Override
    public boolean canStepReturn() {
        return thread.canStepReturn();
    }

    @Override
    public boolean isStepping() {
        return thread.isStepping();
    }

    @Override
    public void stepInto() throws DebugException {
        thread.stepInto();
    }

    @Override
    public void stepOver() throws DebugException {
        thread.stepOver();
    }

    @Override
    public void stepReturn() throws DebugException {
        thread.stepReturn();
    }

    @Override
    public boolean canResume() {
        return getDebugTarget().canResume();
    }

    @Override
    public boolean canSuspend() {
        return getDebugTarget().canSuspend();
    }

    @Override
    public boolean isSuspended() {
        return getDebugTarget().isSuspended();
    }

    @Override
    public void resume() throws DebugException {
        getDebugTarget().resume();
    }

    @Override
    public void suspend() throws DebugException {
        getDebugTarget().suspend();
    }

    @Override
    public boolean canTerminate() {
        return getDebugTarget().canTerminate();
    }

    @Override
    public boolean isTerminated() {
        return getDebugTarget().isTerminated();
    }

    @Override
    public void terminate() throws DebugException {
        getDebugTarget().terminate();
    }

    @Override
    public IThread getThread() {
        return thread;
    }

    @Override
    public IVariable[] getVariables() throws DebugException {
        // TODO Auto-generated method stub
        if (variables == null) {
        //    variables = getVariablesImpl();
        }
        
        return variables;
    }

    @Override
    public boolean hasVariables() throws DebugException {
        return true;
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
