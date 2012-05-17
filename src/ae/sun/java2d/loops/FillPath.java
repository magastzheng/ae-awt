/*
 * Copyright 2005-2006 Sun Microsystems, Inc.  All Rights Reserved.
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

package ae.sun.java2d.loops;

import ae.java.awt.geom.Path2D;
import ae.sun.java2d.SunGraphics2D;
import ae.sun.java2d.SurfaceData;
import ae.sun.java2d.loops.GraphicsPrimitive;

/**
 *   FillPath
 *   1. fill path onto destination surface
 *   2. must accept output area [x, y, dx, dy]
 *      from within the surface description data for clip rect
 */
public class FillPath extends GraphicsPrimitive {

    public final static String methodSignature =
        "FillPath(...)".toString();

    public final static int primTypeID = makePrimTypeID();

    public static FillPath locate(SurfaceType srctype,
                                  CompositeType comptype,
                                  SurfaceType dsttype)
    {
        return (FillPath)
            GraphicsPrimitiveMgr.locate(primTypeID,
                                        srctype, comptype, dsttype);
    }

    protected FillPath(SurfaceType srctype,
                       CompositeType comptype,
                       SurfaceType dsttype)
    {
        super(methodSignature, primTypeID,
              srctype, comptype, dsttype);
    }

    public FillPath(long pNativePrim,
                    SurfaceType srctype,
                    CompositeType comptype,
                    SurfaceType dsttype)
    {
        super(pNativePrim, methodSignature, primTypeID,
              srctype, comptype, dsttype);
    }


    /**
     *   All FillPath implementors must have this invoker method
     */
    public native void FillPath(SunGraphics2D sg2d, SurfaceData sData,
                                int transX, int transY,
                                Path2D.Float p2df);

    public GraphicsPrimitive makePrimitive(SurfaceType srctype,
                                           CompositeType comptype,
                                           SurfaceType dsttype)
    {
        throw new InternalError("FillPath not implemented for "+
                                srctype+" with "+comptype);
    }

    public GraphicsPrimitive traceWrap() {
        return new TraceFillPath(this);
    }

    private static class TraceFillPath extends FillPath {
        FillPath target;

        public TraceFillPath(FillPath target) {
            super(target.getSourceType(),
                  target.getCompositeType(),
                  target.getDestType());
            this.target = target;
        }

        public GraphicsPrimitive traceWrap() {
            return this;
        }

        public void FillPath(SunGraphics2D sg2d, SurfaceData sData,
                             int transX, int transY,
                             Path2D.Float p2df)
        {
            tracePrimitive(target);
            target.FillPath(sg2d, sData, transX, transY, p2df);
        }
    }
}
