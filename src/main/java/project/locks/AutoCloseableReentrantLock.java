package project.locks;

import java.util.concurrent.locks.ReentrantLock;

public class AutoCloseableReentrantLock extends ReentrantLock implements AutoCloseable {
    public AutoCloseableReentrantLock open(){
        this.lock();
        return this;
    }

    @Override
    public void close() throws Exception {
        this.unlock();
    }
}
