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
import org.eclipse.swt.graphics.Image;
import org.json.JSONException;
import org.json.JSONObject;
import org.nodeclipse.ui.Activator;
import org.nodeclipse.ui.util.Constants;

public class NodejsContentAssistant implements IContentAssistProcessor {
    
    public static Image METHOD = Activator.getImageDescriptor(Constants.METHOD_ICON).createImage();

    @Override
    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
        IDocument doc = viewer.getDocument();
        List<CompletionProposal> list;
        list = computCompletionProposal(getInputString(doc, offset), offset);
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

    public String getInputString(IDocument doc, int offset) {
        StringBuffer buf = new StringBuffer();
        while (true) {
            try {
                char charOffset = doc.getChar(--offset);
                if (Character.isWhitespace(charOffset))
                    break;
                buf.append(charOffset);
            } catch (BadLocationException e) {
                break;
            }
        }
        return buf.reverse().toString();
    }

    public List<CompletionProposal> computCompletionProposal(String input, int offset) {
        List<CompletionProposal> list = new ArrayList<CompletionProposal>();
        try {
            for (int i = 0; i < ContentProvider.COMPLETIONS.length(); i++) {
                JSONObject method = (JSONObject) ContentProvider.COMPLETIONS.get(i);
                String trigger = method.getString("trigger");
                if (trigger != null && trigger.indexOf(input)!=-1) {
                    int length = input.length();
                    list.add(new CompletionProposal(trigger, offset - length - 1, length + 1, trigger.length(), METHOD, null, null, null));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
