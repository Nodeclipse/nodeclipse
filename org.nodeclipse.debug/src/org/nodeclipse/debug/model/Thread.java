package org.nodeclipse.debug.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IStreamsProxy;
import org.eclipse.debug.core.model.IThread;
import org.nodeclipse.debug.util.Constants;
import org.nodeclipse.debug.util.LogUtil;

public class Thread extends NodeDebugElement implements IThread {
    IStreamsProxy streamsProxy;
    private List<StackFrame> frames;

    public Thread(IDebugTarget target, Thread thread) {
        super(target);
        streamsProxy = target.getProcess().getStreamsProxy();
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

    public void sendCommand(String command) {
        try {
            if (!isTerminated()) {
                streamsProxy.write(command + Constants.EOL);
            }
        } catch (IOException e) {
            LogUtil.error(e);
        }
    }

    @Override
    public void stepInto() throws DebugException {
        sendCommand(Constants.STEP);
    }

    @Override
    public void stepOver() throws DebugException {
        sendCommand(Constants.NEXT);
    }

    @Override
    public void stepReturn() throws DebugException {
        sendCommand(Constants.OUT);
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
    public IStackFrame[] getStackFrames() throws DebugException {
        //TODO
        if (frames == null) {
            frames = new ArrayList<StackFrame>();

            //for (StackFrame frame : frames) {
                StackFrame frame = new StackFrame(getDebugTarget(), this);

                frames.add(frame);
            //}
        }

        return frames.toArray(new IStackFrame[frames.size()]);
    }

    @Override
    public boolean hasStackFrames() throws DebugException {
        return getStackFrames().length > 0;
    }

    @Override
    public int getPriority() throws DebugException {
        return 0;
    }

    @Override
    public IStackFrame getTopStackFrame() throws DebugException {
        IStackFrame[] frames = getStackFrames();
        return frames.length > 0 ? frames[0] : null;
    }

    @Override
    public String getName() throws DebugException {
        return "nodethread-" + this.toString();
    }

    @Override
    public IBreakpoint[] getBreakpoints() {
        return new IBreakpoint[0];
    }

}
