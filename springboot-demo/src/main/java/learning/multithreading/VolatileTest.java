package learning.multithreading;

/**
 * @Description:volatile关键字的非原子性
 * @Author LinJia
 * @Date 2020/7/3
 **/
public class VolatileTest implements Runnable {

    public volatile static int count;

    private static void addCount() {
        for (int i = 0; i < 100; i++) {
            count++;
        }
        System.out.println("count=" + count);
    }


    @Override
    public void run() {
        addCount();
    }


    /**
     * 假设 i 自增到 5，线程A从主内存中读取i，值为5，将它存储到自己的线程空间中，执行加1操作，值为6。
     * 此时，CPU切换到线程B执行，从主从内存中读取变量i的值。由于线程A还没有来得及将加1后的结果写回到主内存，
     * 线程B就已经从主内存中读取了i，因此，线程B读到的变量 i 值还是5
     *
     * 相当于线程B读取的是已经过时的数据了，从而导致线程不安全性。这种情形在《Effective JAVA》中称之为“安全性失败”
     *
     * 综上，仅靠volatile不能保证线程的安全性。（原子性）
     *
     **/
    public static class Run {

        public static void main(String[] args) {
            Thread[] mythreadArray = new Thread[100];
            for (int i = 0; i < 100; i++) {
                VolatileTest myThread = new VolatileTest();
                Thread thread = new Thread(myThread);
                mythreadArray[i] = thread;
            }

            for (int i = 0; i < 100; i++) {
                mythreadArray[i].start();
            }
        }
    }
}
