package yabao.novel.system.txtsystem.business.utils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockUtils {

    //        ReentrantReadWriteLock lock= new ReentrantReadWriteLock();
//        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    public static void main(String[] args) throws InterruptedException {

//
//        new Thread(() -> {
//            try {
//                Thread.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            readLock.lock();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            readLock.unlock();
//        }).start();
//
//        readLock.lock();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("du");
//
//
//        readLock.unlock();


        test4();
    }

    private static void test4() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread t1 = new Thread(()-> {

            lock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();

        });
        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();

//        lock.lock();
//        condition.signal();
//        lock.unlock();

    }


    private static void test3() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        new Thread(() -> {

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {


            }
            try {
                lock.tryLock(200, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }).start();


        new Thread(() -> {

            try {
                Thread.sleep(501);
            } catch (InterruptedException e) {


            }
            lock.lock();

            lock.unlock();
        }).start();

        lock.lock();
        Thread.sleep(10000);
        lock.unlock();

    }
    private static void test2() throws InterruptedException {
        ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();


        new Thread(()-> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //先获得主线程的写锁，在争抢这把写锁，直接进入阻塞队列，然后再请求读锁。
            rwl.writeLock().lock();
            System.out.println("进来了吗");
            rwl.writeLock().unlock();
            System.out.println("???");
        }).start();

        rwl.writeLock().lock();
        //读锁 ，当别的线程占有写锁的时候，直接返回，如果是自己的线程占有写锁的话，
        // 还要判断阻塞队列里面是否有写操作
        Thread.sleep(50);
        rwl.readLock().lock();
        System.out.println("1");
        rwl.writeLock().unlock();
        System.out.println("2");
//        rwl.readLock().unlock();
//        ReentrantReadWriteLock rwl2 = new ReentrantReadWriteLock();
//        //等待队列里面没有写操作，所以可以自己读
//
//        rwl2.readLock().lock();
//        System.out.println("会开始阻塞");
//        //写锁只能当前线程重入，不支持锁升级
//        rwl2.writeLock().lock();
//        System.out.println("阻塞结束了吗？");
//        rwl2.readLock().unlock();

    }
}
