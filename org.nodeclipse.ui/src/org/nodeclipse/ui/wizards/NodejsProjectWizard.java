package org.nodeclipse.ui.wizards;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.ide.undo.CreateProjectOperation;
import org.eclipse.ui.ide.undo.WorkspaceUndoUtil;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.PerspectiveDescriptor;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;
import org.nodeclipse.ui.nature.NodejsNature;
import org.nodeclipse.ui.perspectives.NodejsPerspective;
import org.nodeclipse.ui.util.LogUtil;

@SuppressWarnings("restriction")
public class NodejsProjectWizard extends Wizard implements INewWizard {

    private final String WINDOW_TITLE = "New Nodejs Project";

    private IWorkbench workbench;
    private IStructuredSelection selection;
    private NodejsProjectWizardPage mainPage;

    private IProject newProject;

    public NodejsProjectWizard() {
        setWindowTitle(WINDOW_TITLE);
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

    @Override
    public void addPages() {
        mainPage = new NodejsProjectWizardPage("nodejsNewProjectPage") { //$NON-NLS-1$
            /*
             * (non-Javadoc)
             * 
             * @see
             * org.eclipse.ui.dialogs.WizardNewProjectCreationPage#createControl
             * (org.eclipse.swt.widgets.Composite)
             */
            public void createControl(Composite parent) {
                super.createControl(parent);
                createWorkingSetGroup((Composite) getControl(), getSelection(), new String[] { "org.eclipse.ui.resourceWorkingSetPage" }); //$NON-NLS-1$
                Dialog.applyDialogFont(getControl());
            }
        };
        mainPage.setTitle("Create a Nodejs Project");
        mainPage.setDescription("Create a new Nodejs project.");
        addPage(mainPage);
    }

    protected IStructuredSelection getSelection() {
        return selection;
    }

    @Override
    public boolean performFinish() {
        createNewProject();
        if (newProject == null) {
            return false;
        }
        // add to workingsets
        IWorkingSet[] workingSets = mainPage.getSelectedWorkingSets();
        getWorkbench().getWorkingSetManager().addToWorkingSets(newProject, workingSets);

        updatePerspective();
        selectAndReveal();
        return true;
    }

    private void selectAndReveal() {
        BasicNewResourceWizard.selectAndReveal(newProject, workbench.getActiveWorkbenchWindow());
    }

    /**
     * 更新视图,跳转到NodejsPerspective视图.
     */
    private void updatePerspective() {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        IPerspectiveRegistry reg = WorkbenchPlugin.getDefault().getPerspectiveRegistry();
        PerspectiveDescriptor rtPerspectiveDesc = (PerspectiveDescriptor) reg.findPerspectiveWithId(NodejsPerspective.ID);
        // Now set it as the active perspective.
        if (window != null) {
            IWorkbenchPage page = window.getActivePage();
            page.setPerspective(rtPerspectiveDesc);
        }
    }

    private void createNewProject() {
        if (newProject != null) {
            return;
        }
        IProject newProjectHandle = mainPage.getProjectHandle();
        URI location = null;
        if (!mainPage.useDefaults()) {
            location = mainPage.getLocationURI();
        }

        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final IProjectDescription description = workspace.newProjectDescription(newProjectHandle.getName());
        description.setLocationURI(location);
        String[] natures = description.getNatureIds();
        String[] newNatures = new String[natures.length + 1];
        System.arraycopy(natures, 0, newNatures, 0, natures.length);
        newNatures[natures.length] = NodejsNature.NATURE_ID;
        description.setNatureIds(newNatures);

        IRunnableWithProgress op = new IRunnableWithProgress() {
            @Override
            public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                CreateProjectOperation op = new CreateProjectOperation(description, WINDOW_TITLE);
                try {
                    op.execute(monitor, WorkspaceUndoUtil.getUIInfoAdapter(getShell()));
                } catch (ExecutionException e) {
                    throw new InvocationTargetException(e);
                }
            }
        };

        try {
            getContainer().run(true, true, op);
        } catch (InvocationTargetException e) {
            LogUtil.error(e);
        } catch (InterruptedException e) {
        }

        newProject = newProjectHandle;
    }

    @SuppressWarnings("unused")
    private static class CreateNodejsProjectOperation extends WorkspaceModifyOperation {

        private String projectName;
        private IStatus result;
        private IProject project;

        public CreateNodejsProjectOperation(String projectName) {
            this.projectName = projectName;
        }

        @Override
        protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
            IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
            IProject project = root.getProject(projectName);
            project.create(new NullProgressMonitor());
            project.open(new NullProgressMonitor());

            this.project = project;

            IProjectDescription description = project.getDescription();
            String[] natures = description.getNatureIds();
            String[] newNatures = new String[natures.length + 1];
            System.arraycopy(natures, 0, newNatures, 0, natures.length);
            newNatures[natures.length] = NodejsNature.NATURE_ID;
            description.setNatureIds(newNatures);
            project.setDescription(description, null);

        }

    }

}
