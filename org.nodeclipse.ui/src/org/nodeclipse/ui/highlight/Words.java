package org.nodeclipse.ui.highlight;

public class Words {

    public static final String[] KEYWORDS = { "break", "do", "instanceof", "typeof",//
            "case", "else", "new", "var",//
            "catch", "finally", "return", "void",//
            "continue", "for", "switch", "while",//
            "debugger", "function", "this", "with",//
            "default", "if", "throw",//
            "delete", "in", "try"//
    };

    public static final String[] RESERVEDWORDS = { "let", "yield",//
            "abstract", "enum", "int", "short",//
            "boolean", "export", "interface", "static",//
            "byte", "extends", "long", "super",//
            "char", "final", "native", "synchronized",//
            "class", "float", "package", "throws",//
            "const", "goto", "private", "transient",//
            "debugger", "implements", "protected", "volatile",//
            "double", "import", "public"//
    };

    public static final String[] NODE_WORDS = new String[] { "require", "__filename",//
            "__dirname", "module", "exports", "setInterval"//
    };

}
