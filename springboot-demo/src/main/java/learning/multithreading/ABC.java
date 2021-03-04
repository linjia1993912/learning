package learning.multithreading;

/**
 * @Description: 多线程面试题
 * 三个线程轮流打印十次abc
 * @Author LinJia
 * @Date 2021/2/25
 **/
public class ABC {

    private static int index = 1;

    //方法一  用while循环和变量实现
    /*public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (index > 30) {
                        break;
                    }
                    if (index % 3 == 1) {
                        System.out.println("a");
                        index++;
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (index > 30) {
                        break;
                    }
                    if (index % 3 == 2) {
                        System.out.println("b");
                        index++;
                    }
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (index > 30) {
                        break;
                    }
                    if (index % 3 == 0) {
                        System.out.println("c");
                        index++;
                    }
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }*/

    //方法二 用synchronized、wait、notifyAll实现
    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 0; i < 10; i++) {
                        while (index % 3 != 1) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("a");
                        index++;
                        lock.notifyAll();
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 0; i < 10; i++) {
                        while (index % 3 != 2) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("b");
                        index++;
                        lock.notifyAll();
                    }
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 0; i < 10; i++) {
                        while (index % 3 != 0) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("c");
                        index++;
                        lock.notifyAll();
                    }
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}