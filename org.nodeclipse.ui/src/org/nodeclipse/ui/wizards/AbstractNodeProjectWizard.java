package org.nodeclipse.ui.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.PerspectiveDescriptor;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.nodeclipse.ui.perspectives.NodePerspective;

@SuppressWarnings("restriction")
public abstract class AbstractNodeProjectWizard extends Wizard implements INewWizard {

    private IWorkbench workbench;
    private IStructuredSelection selection;

    private IProject newProject;

    public AbstractNodeProjectWizard() {
        setNeedsProgressMonitor(true);
    }

    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.workbench = workbench;
        this.selection = selection;
    }

    public IWorkbench getWorkbench() {
        return workbench;
    }

    protected IStructuredSelection getSelection() {
        return selection;
    }

    @Override
    public boolean performFinish() {
        newProject = createNewProject();
        if (newProject == null) {
            return false;
        }
        
        updatePerspective();
        selectAndReveal();
        return true;
    }
    
    protected abstract IProject createNewProject();

    private void selectAndReveal() {
        BasicNewResourceWizard.selectAndReveal(newProject, workbench.getActiveWorkbenchWindow());
    }

    private void updatePerspective() {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        IPerspectiveRegistry reg = WorkbenchPlugin.getDefault().getPerspectiveRegistry();
        PerspectiveDescriptor rtPerspectiveDesc = (PerspectiveDescriptor) reg.findPerspectiveWithId(NodePerspective.ID);
        // Now set it as the active perspective.
        if (window != null) {
            IWorkbenchPage page = window.getActivePage();
            page.setPerspective(rtPerspectiveDesc);
        }
    }
}

