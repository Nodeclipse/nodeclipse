package org.nodeclipse.debug.model;

import java.io.IOException;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStreamsProxy;
import org.eclipse.debug.core.model.IThread;
import org.nodeclipse.debug.util.Constants;
import org.nodeclipse.debug.util.LogUtil;

public class DebugTarget extends NodeDebugElement implements IDebugTarget {

    private ILaunch launch;
    private IProcess process;
    private Process p;
    private IThread[] threads;
    private Thread thread;
    private boolean suspended;

    public DebugTarget(ILaunch launch, IProcess process, Process p) {
        super(null);
        this.launch = launch;
        this.process = process;
        this.p = p;
        this.thread = new Thread(this);
        threads = new Thread[] { this.thread };
    }

    @Override
    public boolean canTerminate() {
        return !isTerminated();
    }

    @Override
    public boolean isTerminated() {
        return getProcess().isTerminated();
    }

    @Override
    public void terminate() throws DebugException {
        IStreamsProxy streamsProxy = process.getStreamsProxy();
        try {
            if (!isTerminated()) {
                streamsProxy.write(Constants.QUIT + Constants.EOL);
                // wait for subprocess exit
                p.waitFor();
            }
        } catch (IOException e) {
            LogUtil.error(e);
        } catch (InterruptedException e) {
            LogUtil.error(e);
        }
    }

    @Override
    public boolean canResume() {
        return !isTerminated() && isSuspended();
    }

    @Override
    public boolean canSuspend() {
        return !isTerminated() && !isSuspended();
    }

    @Override
    public boolean isSuspended() {
        return suspended;
    }

    public void sendCommand(String command) {
        IStreamsProxy streamsProxy = process.getStreamsProxy();
        try {
            if (!isTerminated()) {
                streamsProxy.write(command + Constants.EOL);
            }
        } catch (IOException e) {
            LogUtil.error(e);
        }
    }

    @Override
    public void resume() throws DebugException {
        sendCommand(Constants.CONT);
    }

    @Override
    public void suspend() throws DebugException {
        // TODO Auto-generated method stub

    }

    @Override
    public void breakpointAdded(IBreakpoint breakpoint) {
        // TODO Auto-generated method stub

    }

    @Override
    public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta) {
        // TODO Auto-generated method stub

    }

    @Override
    public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean canDisconnect() {
        return false;
    }

    @Override
    public void disconnect() throws DebugException {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isDisconnected() {
        return false;
    }

    @Override
    public boolean supportsStorageRetrieval() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public IMemoryBlock getMemoryBlock(long startAddress, long length) throws DebugException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IDebugTarget getDebugTarget() {
        return this;
    }

    @Override
    public ILaunch getLaunch() {
        return launch;
    }

    @Override
    public IProcess getProcess() {
        return process;
    }

    @Override
    public IThread[] getThreads() throws DebugException {
        if (threads == null) {
            return new IThread[0];
        } else {
            return threads;
        }
    }

    @Override
    public boolean hasThreads() throws DebugException {
        return true;
    }

    @Override
    public String getName() throws DebugException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean supportsBreakpoint(IBreakpoint breakpoint) {
        // TODO Auto-generated method stub
        return true;
    }

}
