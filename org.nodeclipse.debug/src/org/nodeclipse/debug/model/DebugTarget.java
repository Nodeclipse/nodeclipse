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

    public DebugTarget(ILaunch launch, IProcess process, Process p) {
        super(null);
        this.launch = launch;
        this.process = process;
        this.p = p;
    }

    @Override
    public boolean canTerminate() {
        // TODO Auto-generated method stub
        return process.canTerminate();
    }

    @Override
    public boolean isTerminated() {
        return process.isTerminated();
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void disconnect() throws DebugException {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isDisconnected() {
        // TODO Auto-generated method stub
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
    public String getModelIdentifier() {
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
        // TODO Auto-generated method stub
        return new IThread[] {};
    }

    @Override
    public boolean hasThreads() throws DebugException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getName() throws DebugException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean supportsBreakpoint(IBreakpoint breakpoint) {
        // TODO Auto-generated method stub
        return false;
    }

}
