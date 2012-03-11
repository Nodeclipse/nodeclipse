package org.nodejs.ide.eclipse.debug;

import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.IValueDetailListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;

public class DebugModelPresentation implements IDebugModelPresentation {

    public DebugModelPresentation() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void addListener(ILabelProviderListener listener) {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isLabelProperty(Object element, String property) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void removeListener(ILabelProviderListener listener) {
        // TODO Auto-generated method stub

    }

    @Override
    public IEditorInput getEditorInput(Object element) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getEditorId(IEditorInput input, Object element) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setAttribute(String attribute, Object value) {
        // TODO Auto-generated method stub

    }

    @Override
    public Image getImage(Object element) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getText(Object element) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void computeDetail(IValue value, IValueDetailListener listener) {
        // TODO Auto-generated method stub

    }

}
