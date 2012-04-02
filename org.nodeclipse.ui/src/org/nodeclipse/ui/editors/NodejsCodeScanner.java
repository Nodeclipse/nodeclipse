package org.nodeclipse.ui.editors;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.NumberRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

/**
 * JavaScript code scanner for source code highlighting.
 */
public class NodejsCodeScanner extends RuleBasedScanner {

	private Token commentToken;

	private final TextAttribute commentAttribute = new TextAttribute(
			EditorColors.getColor(new RGB(63, 127, 95)), null, SWT.NORMAL);

	private final TextAttribute jsDocAttribute = new TextAttribute(
			EditorColors.getColor(new RGB(127, 127, 159)), null, SWT.NORMAL);

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
		Token keywordToken = new Token(new TextAttribute(
				EditorColors.getColor(new RGB(127, 0, 85)), null, SWT.BOLD));

		commentToken = new Token(commentAttribute);

		Token jsDocToken = new Token(jsDocAttribute);

		Token stringToken = new Token(new TextAttribute(
				EditorColors.getColor(new RGB(42, 0, 255)), null, SWT.NORMAL));

		RGB blackRgb = new RGB(0, 0, 0);

		Token numberToken = new Token(new TextAttribute(
				EditorColors.getColor(blackRgb), null, SWT.NORMAL));

		Token normalToken = new Token(new TextAttribute(
				EditorColors.getColor(blackRgb), null, SWT.NORMAL));
		setDefaultReturnToken(normalToken);

		setRules(new IRule[] {
				new EndOfLineRule("//", commentToken),
				new KeywordRule(keywordToken),
				new MultiLineRule("/**", "*/", jsDocToken, (char) 0, false), //$NON-NLS-2$
				new MultiLineRule("/*", "*/", commentToken, (char) 0, false), //$NON-NLS-2$
				new SingleLineRule("\"", "\"", stringToken, '\\'), //$NON-NLS-2$
				// Regexp
				new SingleLineRule("/", "/", stringToken, '\\'), //$NON-NLS-2$
				new SingleLineRule("'", "'", stringToken, '\\'), //$NON-NLS-2$
				new WhitespaceRule(new IWhitespaceDetector() {
					public boolean isWhitespace(char c) {
						return Character.isWhitespace(c);
					}
				}),

				new WordRule(new WordDetector(), normalToken),
				new NumberRule(numberToken) });
	}

	private static class KeywordRule extends WordRule {
		// TODO 现在是32个，还缺少一些保留关键字
		private static final String[] KEYWORDS = { "break", "case", "catch",
				"const", "continue", "debugger", "default", "delete", "do",
				"else", "false", "finally", "for", "function", "if", "in",
				"instanceof", "new", "null", "return", "switch", "this",
				"throw", "true", "try", "typeof", "var", "void", "while",
				"with",

				// Highlight important qualifiers
				"__proto__", "prototype" };
		private static final String[] NODEJS_KEYWORDS_VARS = new String[] {
				"require", "__filename", "__dirname", "module", "exports",
				"setInterval" };
		
		public KeywordRule(Token token) {
			super(new WordDetector());

			for (String word : KEYWORDS) {
				addWord(word, token);
			}
			for (String word : NODEJS_KEYWORDS_VARS) {
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
