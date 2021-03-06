/*
 * Copyright 2003-2006 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */

package ae.javax.accessibility;

import java.io.InputStream;
import ae.java.awt.datatransfer.DataFlavor;

/**
 *
 * The <code>AccessibleStreamable</code> interface should be implemented
 * by the <code>AccessibleContext</code> of any component that presents the
 * raw stream behind a component on the display screen.  Examples of such
 * components are HTML, bitmap images and MathML.  An object that implements
 * <code>AccessibleStreamable</code> provides two things: a list of MIME
 * types supported by the object and a streaming interface for each MIME type to
 * get the data.
 *
 * @author Lynn Monsanto
 * @author Peter Korn
 *
 * @see ae.javax.accessibility.AccessibleContext
 * @since 1.5
 */
public interface AccessibleStreamable {
    /**
      * Returns an array of DataFlavor objects for the MIME types
      * this object supports.
      *
      * @return an array of DataFlavor objects for the MIME types
      * this object supports.
      */
     DataFlavor[] getMimeTypes();

    /**
      * Returns an InputStream for a DataFlavor
      *
      * @param flavor the DataFlavor
      * @return an ImputStream if an ImputStream for this DataFlavor exists.
      * Otherwise, null is returned.
      */
     InputStream getStream(DataFlavor flavor);
}
