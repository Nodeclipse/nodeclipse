package org.nodeclipse.lab.grammar;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;
import org.nodeclipse.lab.grammar.JavaScriptParser.program_return;

public class Main {
    // java -jar D:\OpenSource\antlr\antlrworks-1.4.3.jar
    // java org.antlr.Tool JavaScript.g
    public static void run(String expr) throws Exception {
        ANTLRStringStream in = new ANTLRStringStream(expr);
        JavaScriptLexer lexer = new JavaScriptLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaScriptParser parser = new JavaScriptParser(tokens);
        parser.program();

    }

    public static void testTree(String expr) throws RecognitionException {
        ANTLRStringStream in = new ANTLRStringStream(expr);
        JavaScriptLexer lexer = new JavaScriptLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaScriptParser parser = new JavaScriptParser(tokens);
        program_return r = parser.program();
        System.out.println("tree: " + ((Tree) r.tree).toStringTree());
        CommonTree commonTree = ((CommonTree) r.tree);
        System.out.println(commonTree.getChildCount());

    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String s = "var a =1;b=2;";
        //run(s);
        testTree(s);
    }
}
