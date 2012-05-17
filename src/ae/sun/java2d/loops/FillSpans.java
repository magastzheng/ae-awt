/*
 * Copyright 1998-2002 Sun Microsystems, Inc.  All Rights Reserved.
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

 /*
 * @author Jim Graham
 * @author Charlton Innovations, Inc.
 */

package ae.sun.java2d.loops;

import ae.java.awt.Color;
import ae.java.awt.image.ColorModel;
import ae.java.awt.image.Raster;
import ae.sun.java2d.SunGraphics2D;
import ae.sun.java2d.SurfaceData;
import ae.sun.java2d.loops.GraphicsPrimitive;
import ae.sun.java2d.pipe.SpanIterator;

/**
 * FillSpans
 * 1) draw solid color onto destination surface
 * 2) rectangular areas to fill come from SpanIterator
 */
public class FillSpans extends GraphicsPrimitive
{
    public final static String methodSignature = "FillSpans(...)".toString();

    public final static int primTypeID = makePrimTypeID();

    public static FillSpans locate(SurfaceType srctype,
                                   CompositeType comptype,
                                   SurfaceType dsttype)
    {
        return (FillSpans)
            GraphicsPrimitiveMgr.locate(primTypeID,
                                        srctype, comptype, dsttype);
    }

    protected FillSpans(SurfaceType srctype,
                        CompositeType comptype,
                        SurfaceType dsttype)
    {
        super(methodSignature, primTypeID, srctype, comptype, dsttype);
    }

    public FillSpans(long pNativePrim,
                     SurfaceType srctype,
                     CompositeType comptype,
                     SurfaceType dsttype)
    {
        super(pNativePrim, methodSignature, primTypeID, srctype, comptype, dsttype);
    }

    private native void FillSpans(SunGraphics2D sg2d, SurfaceData dest,
                                  int pixel, long pIterator, SpanIterator si);

    /**
     * All FillSpan implementors must have this invoker method
     */
    public void FillSpans(SunGraphics2D sg2d, SurfaceData dest,
                          SpanIterator si)
    {
        FillSpans(sg2d, dest, sg2d.pixel, si.getNativeIterator(), si);
    }

    public GraphicsPrimitive makePrimitive(SurfaceType srctype,
                                           CompositeType comptype,
                                           SurfaceType dsttype)
    {
        // REMIND: iterate with a FillRect primitive?
        throw new InternalError("FillSpans not implemented for "+
                                srctype+" with "+comptype);
    }

    public GraphicsPrimitive traceWrap() {
        return new TraceFillSpans(this);
    }

    private static class TraceFillSpans extends FillSpans {
        FillSpans target;

        public TraceFillSpans(FillSpans target) {
            super(target.getSourceType(),
                  target.getCompositeType(),
                  target.getDestType());
            this.target = target;
        }

        public GraphicsPrimitive traceWrap() {
            return this;
        }

        public void FillSpans(SunGraphics2D sg2d, SurfaceData dest,
                              SpanIterator si)
        {
            tracePrimitive(target);
            target.FillSpans(sg2d, dest, si);
        }
    }
}
