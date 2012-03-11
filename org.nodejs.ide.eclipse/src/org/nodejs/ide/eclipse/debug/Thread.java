package org.nodejs.ide.eclipse.debug;

import java.io.IOException;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IValue;

public class Thread implements IThread {
    private final DebugTarget target;

    public Thread(DebugTarget target) {
        this.target = target;
    }

    @Override
    public String getModelIdentifier() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IDebugTarget getDebugTarget() {
        return target;
    }

    @Override
    public ILaunch getLaunch() {
        return target.getLaunch();
    }

    @Override
    public Object getAdapter(Class adapter) {
        // TODO Auto-generated method stub
        return null;
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
    public boolean canStepInto() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean canStepOver() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean canStepReturn() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isStepping() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void stepInto() throws DebugException {
        sendRequest("step");
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

    private void sendRequest(String request) throws DebugException {
        synchronized (target.requestSocket) {
            target.requestWriter.println(request);
            target.requestWriter.flush();
            try {
                // wait for "ok"
                String response = target.requestReader.readLine();
            } catch (IOException e) {
               e.printStackTrace();
            }
        }
    }

//    protected IValue getVariableValue(Value variable) throws DebugException {
//        synchronized (target.requestSocket) {
//            target.requestWriter.println("var " + variable.getModelIdentifier() + " " + variable.getValueString());
//            target.requestWriter.flush();
//            try {
//                String value = target.requestReader.readLine();
//                return new Value(this, value);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
}
