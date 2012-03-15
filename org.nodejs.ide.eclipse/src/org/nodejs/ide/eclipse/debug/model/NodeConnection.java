package org.nodejs.ide.eclipse.debug.model;

import java.io.IOException;

import org.eclipse.debug.core.IStreamListener;
import org.eclipse.debug.core.model.IStreamMonitor;
import org.nodejs.ide.eclipse.debug.util.LogUtil;

public class NodeConnection implements IStreamListener {

    private static final String EOL = System.getProperty("line.separator");
    private StringBuilder inBuffer = new StringBuilder();

    @Override
    public void streamAppended(String text, IStreamMonitor monitor) {
        inBuffer.append(text);
        processBuffer();
    }

    private void processBuffer() {
        int eolStart = inBuffer.indexOf(EOL);

        while (eolStart != -1) {
            String eventStr = inBuffer.substring(0, eolStart);

            inBuffer.delete(0, eolStart + EOL.length());

            try {
                handleNodeInput(eventStr);
            } catch (IOException exception) {
                LogUtil.error(exception);
            }

            eolStart = inBuffer.indexOf(EOL);
        }
    }

    private void handleNodeInput(String eventStr) throws IOException {
        // TODO Auto-generated method stub

    }
}
