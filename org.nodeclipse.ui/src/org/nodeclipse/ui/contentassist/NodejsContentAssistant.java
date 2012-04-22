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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.nodeclipse.ui.Activator;
import org.nodeclipse.ui.contentassist.api.APITool;
import org.nodeclipse.ui.util.Constants;

public class NodejsContentAssistant implements IContentAssistProcessor {

    public static final JSONArray METHODS = ContentProvider.METHODS;

    @Override
    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
        IDocument doc = viewer.getDocument();
        List<CompletionProposal> list;
        list = computCompletionProposal(getObjectName(doc, offset), offset);
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
                // if (charOffset == '.')
                // break;
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
            for (int i = 0; i < METHODS.length(); i++) {
                JSONObject method = (JSONObject) METHODS.get(i);
                String insert = method.getString("textRaw");
                if (insert != null && insert.contains(input)) {
                    String desc = APITool.clear(method.getString("desc"));
                    int length = input.length();
                    // JSONArray params =
                    // ((JSONObject)method.getJSONArray("signatures").get(0)).getJSONArray("params");
                    Image image = Activator.getImageDescriptor(Constants.METHOD_ICON).createImage();
                    list.add(new CompletionProposal(insert, offset - length - 1, length + 1, insert.length(), image, null, null, desc));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
