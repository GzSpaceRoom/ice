// **********************************************************************
//
// Copyright (c) 2001
// MutableRealms, Inc.
// Huntsville, AL, USA
//
// All Rights Reserved
//
// **********************************************************************

package IceInternal;

public final class ProxyFactory
{
    public Ice.ObjectPrx
    stringToProxy(String s)
    {
        Reference reference = new Reference(_instance, s);
        return referenceToProxy(reference);
    }

    public String
    proxyToString(Ice.ObjectPrx proxy)
    {
        Ice.ObjectPrxHelper h = (Ice.ObjectPrxHelper)proxy;
        return h.__reference().toString();
    }

    public Ice.ObjectPrx
    streamToProxy(BasicStream s)
    {
        String identity = s.readString();

        if (identity.length() == 0)
        {
            return null;
        }
        else
        {
            Reference reference = new Reference(identity, s);
            return referenceToProxy(reference);
        }
    }

    public Ice.ObjectPrx
    referenceToProxy(Reference reference)
    {
        Ice.ObjectPrxHelper proxy = new Ice.ObjectPrxHelper();
        proxy.setup(reference);
        return proxy;
    }

    public void
    proxyToStream(Ice.ObjectPrx proxy, BasicStream s)
    {
        if (proxy != null)
        {
            Ice.ObjectPrxHelper h = (Ice.ObjectPrxHelper)proxy;
            Reference ref = h.__reference();
            s.writeString(ref.identity);
            ref.streamWrite(s);
        }
        else
        {
            s.writeString("");
        }
    }

    //
    // Only for use by Instance
    //
    ProxyFactory(Instance instance)
    {
        _instance = instance;
    }

    private Instance _instance;
}
