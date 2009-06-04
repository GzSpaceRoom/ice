// **********************************************************************
//
// Copyright (c) 2003-2009 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

package Ice;

/**
 * Base class for Slice user exceptions.
 **/
public abstract class UserException extends Exception implements Cloneable
{
    /**
     * Creates a copy of this exception.
     *
     * @return The copy of this exception.
     **/
    public java.lang.Object clone()
    {
        java.lang.Object o = null;
        try
        {
            o = super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // Impossible
        }
        return o;
    }

    /**
     * Returns the name of this exception.
     *
     * @return The name of this exception.
     **/
    public abstract String
    ice_name();

    /**
     * Returns a string representation of this exception.
     *
     * @return A string representation of this exception.
     **/
    public String
    toString()
    {
        java.io.StringWriter sw = new java.io.StringWriter();
        java.io.PrintWriter pw = new java.io.PrintWriter(sw);
        IceUtilInternal.OutputBase out = new IceUtilInternal.OutputBase(pw);
        out.setUseTab(false);
        out.print(getClass().getName());
        out.inc();
        IceInternal.ValueWriter.write(this, out);
        pw.flush();
        return sw.toString();
    }

    public abstract void
    __write(IceInternal.BasicStream __os);

    public abstract void
    __read(IceInternal.BasicStream __is, boolean __rid);

    public void
    __write(Ice.OutputStream __outS)
    {
        assert(false);
    }

    public void
    __read(Ice.InputStream __inS, boolean __rid)
    {
        assert(false);
    }

    public boolean
    __usesClasses()
    {
        return false;
    }
}
