package org.nodeclipse.ui.editors;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.editors.text.FileDocumentProvider;
import org.nodeclipse.ui.highlight.NodePartitionScanner;

public class NodeDocumentProvider extends FileDocumentProvider {

    @Override
    protected IDocument createDocument(Object element) throws CoreException {
        IDocument doc = super.createDocument(element);
        if (doc != null) {
            IDocumentPartitioner partitioner = new FastPartitioner(new NodePartitionScanner(), NodePartitionScanner.PARTITION_TYPES);
            partitioner.connect(doc);
            doc.setDocumentPartitioner(partitioner);
        }
        return doc;
    }

    /**
     * Alternative implementation of the method that does not require file to be
     * a physical file.
     */
    @Override
    public boolean isDeleted(Object element) {
        if (element instanceof IFileEditorInput) {
            IFileEditorInput input = (IFileEditorInput) element;

            IProject project = input.getFile().getProject();
            if (project != null && !project.exists()) {
                return true;
            }

            return !input.getFile().exists();
        }
        return super.isDeleted(element);
    }

}
