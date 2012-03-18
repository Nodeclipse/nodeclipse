package org.nodeclipse.debug.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;

public class Thread extends NodeDebugElement implements IThread {

    public Thread(IDebugTarget target) {
        super(target);
        // TODO Auto-generated constructor stub
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
    public boolean canStepInto() {
        return true;
    }

    @Override
    public boolean canStepOver() {
        return true;
    }

    @Override
    public boolean canStepReturn() {
        return true;
    }

    @Override
    public boolean isStepping() {
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
    public IStackFrame[] getStackFrames() throws DebugException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasStackFrames() throws DebugException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getPriority() throws DebugException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public IStackFrame getTopStackFrame() throws DebugException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName() throws DebugException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IBreakpoint[] getBreakpoints() {
        // TODO Auto-generated method stub
        return null;
    }

}
