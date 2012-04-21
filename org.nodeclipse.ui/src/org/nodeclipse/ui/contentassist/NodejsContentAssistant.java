package org.nodeclipse.ui.contentassist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import org.nodeclipse.ui.util.Constants;

public class NodejsContentAssistant implements IContentAssistProcessor {

    public static final Map<String, JSONObject> contents = ContentProvider.contents;
    public static final String[] FILE_NAMES = ContentProvider.FILE_NAMES;

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
                if (charOffset == '.')
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
        boolean bFind = false;
        String content_key = null;
        for (int i = 0; i < FILE_NAMES.length; i++) {
            if (FILE_NAMES[i].contains(input)) {
                content_key =FILE_NAMES[i];
                bFind = true;
                break;
            }
        }
        if (bFind) {
            JSONObject object = contents.get(content_key);
            JSONArray methods;
            try {
                methods = object.getJSONArray(ContentProvider.METHODS);
                for (int i = 0; i < methods.length(); i++) {
                    JSONObject method = (JSONObject) methods.get(i);
                    String insert = method.getString("textRaw");
                    String desc = method.getString("desc");
                    int length = input.length();
                    //JSONArray params = ((JSONObject)method.getJSONArray("signatures").get(0)).getJSONArray("params");
                    Image image  = Activator.getImageDescriptor(Constants.METHOD_ICON).createImage();
                    list.add(new CompletionProposal(insert, offset - length - 1, length + 1, insert.length() + 1, image, null, null, desc));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
