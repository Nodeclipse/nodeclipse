// Copyright (c) 2009 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.nodeclipse.ui.highlight;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * Converts RGB to Color, reuses the existing Color instances. A singleton.
 */
public class EditorColors {

    private static final Map<Integer, Color> intToColor = new HashMap<Integer, Color>();

    public static Color getColor(RGB rgb) {
        Integer colorInt = rgbToInteger(rgb);
        Color color = intToColor.get(colorInt);
        if (color == null) {
            color = new Color(Display.getDefault(), rgb);
            intToColor.put(colorInt, color);
        }
        return color;
    }

    private static Integer rgbToInteger(RGB rgb) {
        return ((rgb.red & 0xFF) << 16) + ((rgb.green & 0xFF) << 8) + (rgb.blue & 0xFF);
    }
}
