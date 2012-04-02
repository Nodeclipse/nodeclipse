package org.nodeclipse.ui.util;

public class Constants {
    /**
     * 语法使用的关键字
     */
    public static final String[] JS_SYNTAX_KEYWORD = { "abstract", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "delete", "do", "double", "else", "extends", "false", "final", "finally", "float", "for", "function", "goto", "if", "implements", "import", "short", "in", "instanceof", "int", "interface", "long", "native", "new", "null", "package", "private", "protected", "public", "return", "short", "static", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "true", "try", "typeof", "var", "void", "while", "with", "script", "language" };
    /**
     * 语法使用的内置对象
     */
    public static final String[] JS_SYNTAX_BUILDIB_OBJECT = { "Anchor", "anchors", "Applet", "applets", "Area", "Array", "Button", "Checkbox", "Date", "document", "FileUpload", "Form", "forms", "Frame", "frames", "Hidden", "history", "Image", "images", "Link", "write" };

    public static void main(String[] args) {
        System.out.println(JS_SYNTAX_KEYWORD.length);
        System.out.println(JS_SYNTAX_BUILDIB_OBJECT.length);
    }

}
