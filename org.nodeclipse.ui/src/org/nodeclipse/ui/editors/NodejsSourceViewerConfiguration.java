package org.nodeclipse.ui.editors;

import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.widgets.Shell;
import org.nodeclipse.ui.contentassist.NodejsContentAssistant;
import org.nodeclipse.ui.highlight.MultilineCommentScanner;
import org.nodeclipse.ui.highlight.NodejsCodeScanner;
import org.nodeclipse.ui.highlight.NodejsPartitionScanner;

public class NodejsSourceViewerConfiguration extends SourceViewerConfiguration {

    @Override
    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
        PresentationReconciler pr = new PresentationReconciler();
        pr.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));
        
        NodejsCodeScanner scanner = new NodejsCodeScanner();
        setDamagerRepairer(pr, scanner, IDocument.DEFAULT_CONTENT_TYPE);
        setDamagerRepairer(pr, new MultilineCommentScanner(scanner.getCommentAttribute()), NodejsPartitionScanner.MULTILINE_COMMENT);
        setDamagerRepairer(pr, new MultilineCommentScanner(scanner.getDocAttribute()), NodejsPartitionScanner.NODEJSDOC);
        return pr;
    }

    private void setDamagerRepairer(PresentationReconciler pr, ITokenScanner scanner, String tokenType) {
        DefaultDamagerRepairer damagerRepairer = new DefaultDamagerRepairer(scanner);
        pr.setDamager(damagerRepairer, tokenType);
        pr.setRepairer(damagerRepairer, tokenType);
    }

    @Override
    public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
        return NodejsPartitionScanner.CONTENT_TYPES;
    }

    @Override
    public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
        // TODO Preferences
        ContentAssistant contentAssistant = new ContentAssistant();
        contentAssistant.setInformationControlCreator(new IInformationControlCreator() {
            public IInformationControl createInformationControl(Shell parent) {
                DefaultInformationControl control = new DefaultInformationControl(parent, true);
                return control;
            }
        });
        contentAssistant.setContentAssistProcessor(new NodejsContentAssistant(), IDocument.DEFAULT_CONTENT_TYPE);
        contentAssistant.enableAutoActivation(true);
        contentAssistant.setAutoActivationDelay(500);
        return contentAssistant;
    }

}
