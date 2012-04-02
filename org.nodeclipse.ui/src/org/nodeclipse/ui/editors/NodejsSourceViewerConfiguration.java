package org.nodeclipse.ui.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.BufferedRuleBasedScanner;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.nodeclipse.ui.contentassist.NodejsContentAssistant;

public class NodejsSourceViewerConfiguration extends SourceViewerConfiguration {

    private static class MultilineCommentScanner extends BufferedRuleBasedScanner {
        public MultilineCommentScanner(TextAttribute attr) {
            setDefaultReturnToken(new Token(attr));
        }
    }

    private static final String[] CONTENT_TYPES = new String[] { IDocument.DEFAULT_CONTENT_TYPE, NodejsPartitionScanner.NODEJSDOC,
            NodejsPartitionScanner.MULTILINE_COMMENT };

    private final NodejsCodeScanner scanner = new NodejsCodeScanner();

    @Override
    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
        PresentationReconciler pr = new PresentationReconciler();
        pr.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));
        setDamagerRepairer(pr, new DefaultDamagerRepairer(scanner), IDocument.DEFAULT_CONTENT_TYPE);
        setDamagerRepairer(pr, new DefaultDamagerRepairer(new MultilineCommentScanner(scanner.getCommentAttribute())),
                NodejsPartitionScanner.MULTILINE_COMMENT);
        setDamagerRepairer(pr, new DefaultDamagerRepairer(new MultilineCommentScanner(scanner.getJsDocAttribute())),
                NodejsPartitionScanner.NODEJSDOC);
        return pr;
    }

    private void setDamagerRepairer(PresentationReconciler pr, DefaultDamagerRepairer damagerRepairer, String tokenType) {
        pr.setDamager(damagerRepairer, tokenType);
        pr.setRepairer(damagerRepairer, tokenType);
    }

    @Override
    public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
        return CONTENT_TYPES;
    }

    @Override
    public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
        // TODO Assistant
        // TODO Preferences
        ContentAssistant contentAssistant = new ContentAssistant();
        contentAssistant.setContentAssistProcessor(new NodejsContentAssistant(), IDocument.DEFAULT_CONTENT_TYPE);
        contentAssistant.enableAutoActivation(true);
        contentAssistant.setAutoActivationDelay(500);
        return contentAssistant;
    }

}
