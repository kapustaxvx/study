package project.locks;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LocksProvider{
    private Map<Long, AutoCloseableReentrantLock> taskLocks = new HashMap<>();
    private Map<Long, AutoCloseableReentrantLock> userLocks = new HashMap<>();

    public AutoCloseableReentrantLock provideTaskLock(Long taskId){
        return taskLocks.computeIfAbsent(taskId, id -> new AutoCloseableReentrantLock());
    }

    public AutoCloseableReentrantLock provideUserLock(Long userId){
        return userLocks.computeIfAbsent(userId, id -> new AutoCloseableReentrantLock());
    }
}
