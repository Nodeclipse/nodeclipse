package org.nodeclipse.ui.editors;

import org.eclipse.ui.editors.text.TextEditor;

public class NodejsEditor extends TextEditor {

    public static final String EDITOR_ID = "org.nodeclipse.ui.editors.NodejsEditor";

    public static final String EDITOR_CONTEXT = EDITOR_ID + ".context";

    public static final String RULER_CONTEXT = EDITOR_ID + ".ruler";

    public NodejsEditor() {
        setSourceViewerConfiguration(new NodejsSourceViewerConfiguration());
        setKeyBindingScopes(new String[] { "org.eclipse.ui.textEditorScope", EDITOR_CONTEXT });
    }

    @Override
    protected void initializeEditor() {
        super.initializeEditor();
        setEditorContextMenuId(EDITOR_CONTEXT);
        setRulerContextMenuId(RULER_CONTEXT);
        setDocumentProvider(new NodejsDocumentProvider());
    }

}
