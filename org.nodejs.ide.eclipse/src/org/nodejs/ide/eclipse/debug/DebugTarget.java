package org.nodejs.ide.eclipse.debug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.MessageFormat;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IValue;

public class DebugTarget implements IDebugTarget {
    private final ILaunch launch;
    private final IProcess process;
    private final Thread thread;
    private final IThread[] THREADS;
    Socket requestSocket;
    PrintWriter requestWriter;
    BufferedReader requestReader;

    public DebugTarget(ILaunch launch, IProcess process) {
        this.launch = launch;
        this.process = process;
        this.thread = new Thread(this);
        this.THREADS = new IThread[] { thread };
        try {
            requestSocket = new Socket("localhost", 5858);
            requestWriter = new PrintWriter(requestSocket.getOutputStream());
            requestReader = new BufferedReader(new InputStreamReader(requestSocket.getInputStream()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // fEventDispatch = new EventDispatchJob();
        // fEventDispatch.schedule();
        DebugPlugin.getDefault().getBreakpointManager().addBreakpointListener(this);
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
    public Object getAdapter(Class adapter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean canTerminate() {
        return launch.canTerminate();
    }

    @Override
    public boolean isTerminated() {
        return launch.isTerminated();
    }

    @Override
    public void terminate() throws DebugException {
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
    public IProcess getProcess() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IThread[] getThreads() throws DebugException {
        return THREADS;
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
