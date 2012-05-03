package org.nodeclipse.ui.highlight;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.BufferedRuleBasedScanner;
import org.eclipse.jface.text.rules.Token;

public class MultilineCommentScanner extends BufferedRuleBasedScanner {
    public MultilineCommentScanner(TextAttribute attr) {
        setDefaultReturnToken(new Token(attr));
    }
}
