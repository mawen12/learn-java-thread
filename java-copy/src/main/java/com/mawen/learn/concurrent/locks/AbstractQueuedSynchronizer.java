package com.mawen.learn.concurrent.locks;

import java.io.Serializable;

public abstract class AbstractQueuedSynchronizer
    extends AbstractOwnableSynchronizer
    implements Serializable {

    private static final long serialVersionUID = -6090347734763211938L;

    protected AbstractQueuedSynchronizer() {}

    static final class Node {
        static final Node SHARED = new Node();

        static final Node EXCLUSIVE = null;
    }
}
