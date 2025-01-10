package com.mawen.learn.concurrent.locks;

import java.io.Serializable;

/**
 * 可能由一个线程独占的同步器。该类为创建可能涉及所有权概念的锁和相关同步器提供了基础。
 * 该类本身并不管理或使用这些信息。然后，子类或工具可以使用适当维护的值来帮助控制和
 * 监视访问并提供诊断。
 */
public abstract class AbstractOwnableSynchronizer implements Serializable {

    /**
     * 尽管所有字段都是transient，但仍然使用序列化ID
     */
    private static final long serialVersionUID = -7083337248763354060L;

    /**
     * 提供给子类使用的空构造器
     */
    protected AbstractOwnableSynchronizer() {

    }

    /**
     * 独占模式同步器的当前拥有者
     */
    private transient Thread exclusiveOwnerThread;

    /**
     * 设置当前拥有独占访问权限的线程。传递null代表没有线程拥有权限。
     * 此方法不会强加任何同步或volatile字段访问。
     *
     * @param thread 拥有者线程
     */
    protected final void setExclusiveOwnerThread(Thread thread) {
        this.exclusiveOwnerThread = thread;
    }

    /**
     * 返回最后由{@link #setExclusiveOwnerThread(Thread)}设置的线程，
     * 如果从未设置则返回null。此方法不会强加任何同步或volatile字段访问。
     *
     * @return 拥有者线程
     */
    private final Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }
}
