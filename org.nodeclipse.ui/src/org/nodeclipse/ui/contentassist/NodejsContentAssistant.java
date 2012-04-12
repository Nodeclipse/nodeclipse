package org.nodeclipse.ui.contentassist;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.nodeclipse.ui.util.Constants;

public class NodejsContentAssistant implements IContentAssistProcessor {

    private static final String[] JS_SYNTAX_BUILDIB_OBJECT = Constants.JS_SYNTAX_BUILDIB_OBJECT;

    @Override
    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
        IDocument doc = viewer.getDocument();
        List<CompletionProposal> list = computCompletionProposal(getObjectName(doc, offset), offset);
        return (CompletionProposal[]) list.toArray(new CompletionProposal[list.size()]);
    }

    @Override
    public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public char[] getCompletionProposalAutoActivationCharacters() {
        // TODO Preferences
        return ".abcdefghijklmnopqrstuvwxyz".toCharArray();
    }

    @Override
    public char[] getContextInformationAutoActivationCharacters() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getErrorMessage() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IContextInformationValidator getContextInformationValidator() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getObjectName(IDocument doc, int offset) {
        StringBuffer buf = new StringBuffer();
        offset--;
        while (true) {
            try {
                char charOffset = doc.getChar(--offset);
                if (Character.isWhitespace(charOffset))
                    break;
                if (charOffset == '.')
                    break;
                buf.append(charOffset);
            } catch (BadLocationException e) {
                break;
            }
        }
        return buf.reverse().toString();
    }

    public List<CompletionProposal> computCompletionProposal(String objName, int offset) {
        List<CompletionProposal> list = new ArrayList<CompletionProposal>();
        boolean bFind = false;
        for (int i = 0; i < JS_SYNTAX_BUILDIB_OBJECT.length; i++) {
            String tempString = JS_SYNTAX_BUILDIB_OBJECT[i];
            if (objName.equals(tempString)) {
                bFind = true;
                break;
            }
        }
        if (bFind) {
            for (int i = 0; i < JS_SYNTAX_BUILDIB_OBJECT.length; i++) {
                String insert = objName + "." + JS_SYNTAX_BUILDIB_OBJECT[i];
                int length = objName.length();
                list.add(new CompletionProposal(insert, offset - length - 1, length + 1, insert.length() + 1, null, null, null, "aaa"));
            }
        }
        return list;
    }

}
