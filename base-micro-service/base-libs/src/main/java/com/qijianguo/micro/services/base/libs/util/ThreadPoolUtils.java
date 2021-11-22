package com.qijianguo.micro.services.base.libs.util;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * 符合阿里巴巴规范的线程池
 *
 * @author qijianguo
 */
public class ThreadPoolUtils {

    private static ThreadPoolExecutor threadPool;

    /**
     * 无返回值直接执行
     *
     * @param runnable
     */
    public static void execute(Runnable runnable) {
        getThreadPool().execute(runnable);
    }

    /**
     * 无返回值直接执行
     */
    public static Future execute(Callable<Boolean> obj, int timeOut, TimeUnit util) throws InterruptedException {
        List<Callable<Boolean>> test = Collections.singletonList(obj);
        List<Future<Boolean>> t = getThreadPool().invokeAll(test, timeOut, util);
        if (!CollectionUtils.isEmpty(t)) {
            return t.get(0);
        }
        return null;
    }

    /**
     * 返回值直接执行
     *
     * @param callable
     */
    public static <T> Future<T> submit(Callable<T> callable) {
        return getThreadPool().submit(callable);
    }


    /**
     * dcs获取线程池
     *
     * @return 线程池对象
     */
    private static ThreadPoolExecutor getThreadPool() {
        if (threadPool != null) {
            return threadPool;
        } else {
            synchronized (ThreadPoolUtils.class) {
                if (threadPool == null) {
                    threadPool = new ThreadPoolExecutor(4, 8, 60, TimeUnit.SECONDS,
                            new LinkedBlockingQueue<>(128), new ThreadPoolExecutor.CallerRunsPolicy());
                }
                return threadPool;
            }
        }
    }
}