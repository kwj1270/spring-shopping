package shopping.common.aspect;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class MainCategoryLockHandler {
    private final ConcurrentMap<String, ReentrantLock> locks = new ConcurrentHashMap();

    public void lock(final String key) {
        final ReentrantLock lock = locks.computeIfAbsent(key, k -> new ReentrantLock());
        lock.lock();
    }

    public void unlock(final String key) {
        final ReentrantLock lock = locks.get(key);
        if (lock != null) {
            lock.unlock();
        }
    }
}
