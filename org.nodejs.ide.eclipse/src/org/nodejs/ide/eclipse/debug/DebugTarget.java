package org.nodejs.ide.eclipse.debug;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    Process p;
    boolean isTerminated = false;

    public DebugTarget(ILaunch launch, IProcess process, Process p) {
        this.launch = launch;
        this.process = process;
        this.p = p;
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
        System.out.println("terminate");
        System.out.println(launch.getChildren().length);
        System.out.println(launch.getChildren()[0] instanceof IDebugTarget);
        System.out.println(launch.getChildren()[1] instanceof IProcess);
        //InputStream is = p.getInputStream();
        OutputStream os = p.getOutputStream();
        String s = "quit\r";
        byte[] c= s.getBytes();
        try {
            os.write(c, 0, c.length);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
