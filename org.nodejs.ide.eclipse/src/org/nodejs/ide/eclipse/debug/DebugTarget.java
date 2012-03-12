package org.nodejs.ide.eclipse.debug;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugElement;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;

public class DebugTarget implements IDebugTarget, IDebugElement {
    ILaunch launch;
    IProcess process;
    boolean isTerminated = false;

    public DebugTarget(ILaunch launch, IProcess process) {
        this.launch = launch;
        this.process = process;
    }

    @Override
    public Object getAdapter(Class adapter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean canTerminate() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isTerminated() {
        return process.isTerminated();
    }

    @Override
    public void terminate() throws DebugException {
        System.out.println("kill");
        System.out.println(launch.getProcesses().length);
        process.terminate();
        isTerminated = true;
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
        return null;
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
