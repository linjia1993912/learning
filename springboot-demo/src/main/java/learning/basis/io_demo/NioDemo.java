package learning.basis.io_demo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description:NIO相关知识
 * NIO主要有三大核心部分：Channel(通道)，Buffer(缓冲区), Selector
 * IO是面向流的，NIO是面向缓冲区的
 * IO的各种流是阻塞的。这意味着，当一个线程调用read() 或 write()时，该线程被阻塞，直到有一些数据被读取，或数据完全写入。该线程在此期间不能再干任何事情了
 * NIO的非阻塞模式,使一个线程从某通道发送请求读取数据，但是它仅能得到目前可用的数据，如果目前没有数据可用时，就什么都不会获取。
 * 而不是保持线程阻塞，所以直至数据变得可以读取之前，该线程可以继续做其他的事情
 * @Author LinJia
 * @Date 2020/8/7
 **/
public class NioDemo {

    //传统IO vs NIO

    //案例1是采用FileInputStream读取文件内容
    public static void method2() {
        InputStream in = null;
        try {
            /*in = new BufferedInputStream(new FileInputStream("E:/test.txt"));
            byte[] buf = new byte[1024];
            int bytesRead = in.read(buf);
            while (bytesRead != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    System.out.print((char) buf[i]);
                }
                bytesRead = in.read(buf);
            }*/

            in = new FileInputStream(new File("E:/")
                    .getAbsolutePath()+"test.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(in, "GBK"); // 使用 GBK 解码文件
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                System.out.println(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //案例1是对应的NIO（这里通过RandomAccessFile进行操作，当然也可以通过FileInputStream.getChannel()进行操作）：
    public static void method1(){
        RandomAccessFile randomAccessFile = null;
        try{
            randomAccessFile = new RandomAccessFile("E:/test.txt","rw");
            FileChannel fileChannel = randomAccessFile.getChannel();
            //分配空间（ByteBuffer buf = ByteBuffer.allocate(1024); 还有一种allocateDirector）
            //Buffer顾名思义：缓冲区，实际上是一个容器，一个连续数组。
            // Channel提供从文件、网络读取数据的渠道，但是读写的数据都必须经过Buffer
            ByteBuffer buf = ByteBuffer.allocate(1024);

            //写入数据到Buffer(int bytesRead = fileChannel.read(buf);)
            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);
            while(bytesRead != -1)
            {
                buf.flip();
                while(buf.hasRemaining())
                {
                    //从Buffer中读取数据（System.out.print((char)buf.get());）
                    System.out.print((char)buf.get());
                }
                buf.compact();
                bytesRead = fileChannel.read(buf);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(randomAccessFile!= null){
                    randomAccessFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //通过FileInputStream.getChannel()的方式
    public static void method3(){
        try {
            FileInputStream fin = new FileInputStream(new File("E:/test.txt"));
            //获取通道
            FileChannel fcin = fin.getChannel();
            //创建缓冲区
            ByteBuffer buff = ByteBuffer.allocate(256);

            int len = fcin.read(buff);

            System.out.println(len);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //具体使用
    //基于通道 & 缓冲数据
    public void methodDemo(){
        try{
            // 1. 获取数据源 和 目标传输地的输入输出流（此处以数据源 = 文件为例）
            FileInputStream fin = new FileInputStream(new File("E:/test.txt"));
            FileOutputStream fout = new FileOutputStream(new File("E:/test.txt"));

            // 2. 获取数据源的输入输出通道
            FileChannel fcin = fin.getChannel();
            FileChannel fcout = fout.getChannel();

            // 3. 创建 缓冲区 对象：Buffer（共有2种方法）
            // 方法1：使用allocate()静态方法
            ByteBuffer buff = ByteBuffer.allocate(256);
            // 上述方法创建1个容量为256字节的ByteBuffer
            // 注：若发现创建的缓冲区容量太小，则重新创建一个大小合适的缓冲区

            // 方法2：通过包装一个已有的数组来创建
            // 注：通过包装的方法创建的缓冲区保留了被包装数组内保存的数据
            byte [] byteArray = new byte[128];
            ByteBuffer buff1 = ByteBuffer.wrap(byteArray);

            // 额外：若需将1个字符串存入ByteBuffer，则如下
            String sendString="你好,服务器. ";
            ByteBuffer sendBuff = ByteBuffer.wrap(sendString.getBytes("UTF-16"));

            // 4. 从通道读取数据 & 写入到缓冲区
            // 注：若 以读取到该通道数据的末尾，则返回-1
            fcin.read(buff);

            // 5. 传出数据准备：将缓存区的写模式 转换->> 读模式
            buff.flip();

            // 6. 从 Buffer 中读取数据 & 传出数据到通道
            fcout.write(buff);

            // 7. 重置缓冲区
            // 目的：重用现在的缓冲区,即 不必为了每次读写都创建新的缓冲区，在再次读取之前要重置缓冲区
            // 注：不会改变缓冲区的数据，只是重置缓冲区的主要索引值
            buff.clear();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //基于选择器（Selecter）
    public void methodDemo1(){
        try{
            // 1. 创建Selector对象
            Selector sel = Selector.open();

            // 2. 向Selector对象绑定通道
            // a. 创建可选择通道，并配置为非阻塞模式
            ServerSocketChannel server = ServerSocketChannel.open();
            server.configureBlocking(false);

            // b. 绑定通道到指定端口
            ServerSocket socket = server.socket();
            InetSocketAddress address = new InetSocketAddress(8080);
            socket.bind(address);

            // c. 向Selector中注册感兴趣的事件
            server.register(sel, SelectionKey.OP_ACCEPT);
            //return sel;

            // 3. 处理事件
            while(true) {
                // 该调用会阻塞，直到至少有一个事件就绪、准备发生
                sel.select();
                // 一旦上述方法返回，线程就可以处理这些事件
                Set<SelectionKey> keys = sel.selectedKeys();
                Iterator<SelectionKey> iter = keys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = (SelectionKey) iter.next();
                    iter.remove();
                }
            }
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //method2();

        //method1();

        method3();
    }

    //实例讲解
    //实现文件复制功能
    //实现方式：通道FileChannel、 缓冲区ByteBuffer
    private static class NioDemo2{
        public static void main(String[] args) {
            // 设置输入源 & 输出地 = 文件
            String infile = "E:\\1.chm";
            String outfile = "E:\\2.chm";

            try{
                // 1. 获取数据源 和 目标传输地的输入输出流（此处以数据源 = 文件为例）
                FileInputStream fileInputStream = new FileInputStream(infile);
                FileOutputStream outputStream = new FileOutputStream(outfile);

                // 2. 获取数据源的输入输出通道
                FileChannel inputChannel = fileInputStream.getChannel();
                FileChannel outChannel = outputStream.getChannel();

                //3. 创建缓冲区对象
                ByteBuffer buffer = ByteBuffer.allocate(1024);

                while (true) {
                    // 4. 从通道读取数据 & 写入到缓冲区
                    // 注：若 以读取到该通道数据的末尾，则返回-1
                    int r = inputChannel.read(buffer);
                    if (r == -1) {
                        break;
                    }
                    // 5. 传出数据准备：调用flip()方法
                    buffer.flip();

                    // 6. 从 Buffer 中读取数据 & 传出数据到通道
                    outChannel.write(buffer);

                    // 7. 重置缓冲区
                    buffer.clear();

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
