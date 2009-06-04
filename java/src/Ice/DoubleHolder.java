// **********************************************************************
//
// Copyright (c) 2003-2009 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

package Ice;

public final class DoubleHolder
/**
 * Holder class for doubles that are in- or inout-parameters.
 **/
{
    /**
     * Instantiates the class with the value zero.
     **/
    public
    DoubleHolder()
    {
    }

    /**
     * Instantiates the class with the passed value.
     *
     * @param value The <code>double</code> value stored by this holder.
     **/
    public
    DoubleHolder(double value)
    {
        this.value = value;
    }

    /**
     * The <code>double</code> value stored by this holder.
     **/
    public double value;
}
