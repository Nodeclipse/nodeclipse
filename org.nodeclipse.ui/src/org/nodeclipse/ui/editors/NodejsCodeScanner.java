package org.nodeclipse.ui.editors;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.BufferedRuleBasedScanner;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.NumberRule;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

/**
 * JavaScript code scanner for source code highlighting.
 */
public class NodejsCodeScanner extends BufferedRuleBasedScanner {

    private Token commentToken;

    private final TextAttribute commentAttribute = new TextAttribute(EditorColors.getColor(new RGB(63, 127, 95)), null, SWT.NORMAL);

    private final TextAttribute jsDocAttribute = new TextAttribute(EditorColors.getColor(new RGB(127, 127, 159)), null, SWT.NORMAL);

    public NodejsCodeScanner() {
        createRules();
    }

    public TextAttribute getCommentAttribute() {
        return commentAttribute;
    }

    public TextAttribute getJsDocAttribute() {
        return jsDocAttribute;
    }

    /**
     * Use the default Eclipse higlighting scheme.
     */
    private void createRules() {
        Token keywordToken = new Token(new TextAttribute(EditorColors.getColor(new RGB(127, 0, 85)), null, SWT.BOLD));

        commentToken = new Token(commentAttribute);

        Token jsDocToken = new Token(jsDocAttribute);

        Token stringToken = new Token(new TextAttribute(EditorColors.getColor(new RGB(42, 0, 255)), null, SWT.NORMAL));

        RGB blackRgb = new RGB(0, 0, 0);

        Token numberToken = new Token(new TextAttribute(EditorColors.getColor(blackRgb), null, SWT.NORMAL));

        Token normalToken = new Token(new TextAttribute(EditorColors.getColor(blackRgb), null, SWT.NORMAL));
        setDefaultReturnToken(normalToken);

        setRules(new IRule[] { new EndOfLineRule("//", commentToken), //$NON-NLS-1$
                new KeywordRule(keywordToken), new MultiLineRule("/**", "*/", jsDocToken, (char) 0, false), //$NON-NLS-1$ //$NON-NLS-2$
                new MultiLineRule("/*", "*/", commentToken, (char) 0, false), //$NON-NLS-1$ //$NON-NLS-2$
                new SingleLineRule("\"", "\"", stringToken, '\\'), //$NON-NLS-1$ //$NON-NLS-2$
                // Regexp
                new SingleLineRule("/", "/", stringToken, '\\'), //$NON-NLS-1$ //$NON-NLS-2$
                new SingleLineRule("'", "'", stringToken, '\\'), //$NON-NLS-1$ //$NON-NLS-2$
                new WhitespaceRule(new IWhitespaceDetector() {
                    public boolean isWhitespace(char c) {
                        return Character.isWhitespace(c);
                    }
                }),

                new WordRule(new WordDetector(), normalToken), new NumberRule(numberToken), });
    }

    private static class KeywordRule extends WordRule {

        private static final String[] KEYWORDS = { "break", //$NON-NLS-1$
                "case", //$NON-NLS-1$
                "catch", //$NON-NLS-1$
                "const", //$NON-NLS-1$
                "continue", //$NON-NLS-1$
                "debugger", //$NON-NLS-1$
                "default", //$NON-NLS-1$
                "delete", //$NON-NLS-1$
                "do", //$NON-NLS-1$
                "else", //$NON-NLS-1$
                "false", //$NON-NLS-1$
                "finally", //$NON-NLS-1$
                "for", //$NON-NLS-1$
                "function", //$NON-NLS-1$
                "if", //$NON-NLS-1$
                "in", //$NON-NLS-1$
                "instanceof", //$NON-NLS-1$
                "new", //$NON-NLS-1$
                "null", //$NON-NLS-1$
                "return", //$NON-NLS-1$
                "switch", //$NON-NLS-1$
                "this", //$NON-NLS-1$
                "throw", //$NON-NLS-1$
                "true", //$NON-NLS-1$
                "try", //$NON-NLS-1$
                "typeof", //$NON-NLS-1$
                "var", //$NON-NLS-1$
                "void", //$NON-NLS-1$
                "while", //$NON-NLS-1$
                "with", //$NON-NLS-1$

                // Highlight important qualifiers
                "__proto__", //$NON-NLS-1$
                "prototype", //$NON-NLS-1$
        };

        public KeywordRule(Token token) {
            super(new WordDetector());

            for (String word : KEYWORDS) {
                addWord(word, token);
            }
        }
    }

    private static class WordDetector implements IWordDetector {

        public boolean isWordPart(char c) {
            return Character.isJavaIdentifierPart(c);
        }

        public boolean isWordStart(char c) {
            return Character.isJavaIdentifierStart(c);
        }

    }
}
