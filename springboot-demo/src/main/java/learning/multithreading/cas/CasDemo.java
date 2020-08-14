package learning.multithreading.cas;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Description:CAS(乐观锁)
 * synchronized是悲观锁，这种线程一旦得到锁，其他需要锁的线程就挂起的情况就是悲观锁。
 * CAS操作的就是乐观锁，每次不加锁而是假设没有冲突而去完成某项操作，如果因为冲突失败就重试，直到成功为止。
 * @Author LinJia
 * @Date 2020/8/6
 **/
public class CasDemo {

    private static int count = 0;

    //先看看下面的代码
    public static void main(String[] args) {
        /*for (int i = 0; i <2 ; i++) {
            Thread thread = new Thread(()->{
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //每个线程让count自增100次
                for (int j = 0; j <100 ; j++) {
                    count++;
                }
            });
            thread.start();
        }
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(count);*/

        //count的值是否等于200？ 答案是否定的，这明显不是一个线程安全的程序

        //如何解决？其中一种方式就是加Synchronized同步锁
        //只需要将count用同步锁包住即可
        for (int i = 0; i <2 ; i++) {
            Thread thread = new Thread(()->{
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //每个线程让count自增100次
                for (int j = 0; j <100 ; j++) {
                    //注意 此处锁的是类CasDemo，所有CasDemo对象得到的都是同一把锁
                    synchronized (CasDemo.class){
                        count++;
                    }
                }
            });
            thread.start();
        }
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(count);

        //经过测试加了同步锁之后，count自增的操作变成了原子性操作，所以最终的输出一定是count=200，代码实现了线程安全。
        //但是Synchronized虽然确保了线程的安全，但是在性能上却不是最优的，
        // Synchronized关键字会让没有得到锁资源的线程进入BLOCKED状态，而后在争夺到锁资源后恢复为RUNNABLE状态，
        // 这个过程中涉及到操作系统用户模式和内核模式的转换，代价比较高
    }


    //从思想上来说，Synchronized属于悲观锁，悲观地认为程序中的并发情况严重，所以严防死守。
    // CAS属于乐观锁，乐观地认为程序中的并发情况不那么严重，所以让线程不断去尝试更新
    //下面就是一个无阻塞多线程争抢资源代码示例
    private static class AtomicBooleanTest implements Runnable{

        private static AtomicBoolean flag = new AtomicBoolean(true);

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+";flag:"+flag.get());
            //现代的CPU提供了特殊的指令，可以自动更新共享数据，而且能够检测到其他线程的干扰，而 compareAndSet() 就用这些代替了锁定

            //compareAndSet的意思就是，则以原子方式将该值设置为给定的更新值
            // 从jdk1.5开始，jdk的Atomic包里就提供了一个类AtomicStampedReference来解决ABA问题，
            // 这个类中的compareAndSet方法的作用就是首先检查当前引用是否等于预期引用，并且检查当前标志是否等于预期标志，
            // 如果全部相等，则以原子方式将该引用和该标志的值更新为指定的新值

            //compareAndSet可以分为2步，如果flag == true，则修改为false
            if (flag.compareAndSet(true,false)){
                System.out.println(Thread.currentThread().getName()+""+flag.get());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //再设置为true
                flag.set(true);
            }else{
                System.out.println("重试机制:"+Thread.currentThread().getName()+";flag:"+flag.get());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                run();
            }

            //上面这段代码就是模仿CAS操作
            //1.执行代码，Thread-1和Thread-2都会进入if，此时两个线程都要把flag改为false，当Thread-2要修改的时候，
            //2.发现Thread-1已经改为false了，睡眠5秒，
            //3.这个时候Thread-2手里拿着还是flag=true，跟原来内存地址的flag比较不相等（flag已经被Thread-1改为false了）
            //4.Thread-2修改失败，在这5秒的时间里，Thread-2不断的尝试修改
            //5.等Thread-1重新唤醒将flag设置为true
            //6.Thread-2经过比较相等，则代码走完
        }

        public static void main(String[] args) {
            AtomicBooleanTest ast = new AtomicBooleanTest();
            Thread thread1 = new Thread(ast,"Thread-1");
            Thread thread2 = new Thread(ast,"Thread-2");
            thread1.start();
            thread2.start();
        }
    }

    //CAS的缺点：
    //1.CPU开销较大
    //在并发量比较高的情况下，如果许多线程反复尝试更新某一个变量，却又一直更新不成功，循环往复，会给CPU带来很大的压力。

    //2.不能保证代码块的原子性
    //CAS机制所保证的只是一个变量的原子性操作，而不能保证整个代码块的原子性。
    //比如需要保证3个变量共同进行原子性的更新，就不得不使用Synchronized了。

}
