package learning.basis.io_demo;


import java.io.*;

/**
 * @Description:IO相关
 * @Author LinJia
 * @Date 2020/8/5
 **/
public class IODemo {

    //BufferedReader读取控制台输入
    public static void test01() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入一个字符");
        char c;
        c = (char) bufferedReader.read();
        System.out.println("你输入的字符为"+c);
    }

    public static void test03() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入一行字符");
        String str = bufferedReader.readLine();
        System.out.println("你输入的字符为" + str);
    }

    //FileInputStream与FileOutputStream与用于操作诸如图像数据之类的原始字节流。要操作字符流，请考虑使用FileReader与FileWriter。
    //二进制文件的写入和读取
    public static void test04() throws IOException {
        byte[] bytes = {12,21,34,11,21};
        FileOutputStream fileOutputStream = new FileOutputStream(new File("E:/").getAbsolutePath()+"test.txt");
        // 写入二进制文件，直接打开会出现乱码
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

    public static void test05() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("E:/").getAbsolutePath()+"test.txt");
        int c;
        // 读取写入的二进制文件，输出字节数组
        while ((c = fileInputStream.read()) != -1) {
            System.out.print(c);
        }
    }

    //文本文件的写入和读取
    public static void test06() throws IOException {
        FileWriter fileWriter = new FileWriter(new File("E:/").getAbsolutePath()+"test.txt");
        fileWriter.write("Hello，world！\n欢迎来到 java 世界\n");
        fileWriter.write("不会覆盖文件原本的内容\n");
//        fileWriter.write(null); 不能直接写入 null
        fileWriter.append("并不是追加一行内容，不要被方法名迷惑\n");
        fileWriter.append(null);
        fileWriter.flush();
        System.out.println("文件的默认编码为" + fileWriter.getEncoding());
        fileWriter.close();
    }

    public static void test07() throws IOException {
        // 关闭追加模式，变为覆盖模式
        FileWriter fileWriter = new FileWriter(new File("E:/").getAbsolutePath()+"test.txt", false);
        fileWriter.write("Hello，world！欢迎来到 java 世界\n");
        fileWriter.write("我来覆盖文件原本的内容");
        fileWriter.append("我是下一行");
        fileWriter.flush();
        System.out.println("文件的默认编码为" + fileWriter.getEncoding());
        fileWriter.close();
    }

    //使用 Buffer 相关的类来读取文件的每一行
    public static void test08() throws IOException {
        FileReader fileReader = new FileReader(new File("E:/").getAbsolutePath()+"test.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }
        fileReader.close();
        bufferedReader.close();
    }

    public static void test09() throws IOException {
        FileReader fileReader = new FileReader(new File("E:/").getAbsolutePath()+"test.txt");
        int c;
        while ((c = fileReader.read()) != -1) {
            System.out.print((char) c);
        }
    }

    //使用字节流和字符流的转换类 InputStreamReader 和 OutputStreamWriter 可以指定文件的编码，
    public static void test10() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File("E:/")
                .getAbsolutePath()+"test.txt");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "GBK"); // 使用 GBK 编码文件
        outputStreamWriter.write("Hello，world！\n欢迎来到 java 世界\n");
        outputStreamWriter.append("另外一行内容");
        outputStreamWriter.flush();
        outputStreamWriter.close();
        fileOutputStream.close();
    }
    // 使用 Buffer 相关的类来读取文件的每一行
    public static void test11() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("E:/")
                .getAbsolutePath()+"test.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "GBK"); // 使用 GBK 解码文件
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
    }

    //复制文件
    //文件的复制实质还是文件的读写。缓冲流是处理流，是对节点流的装饰。
    public static void  test12() throws IOException {
        // 输入和输出都使用缓冲流
        FileInputStream in = new FileInputStream("E:\\3DEngine用户手册_1.4.1.1130.chm");
        BufferedInputStream inBuffer = new BufferedInputStream(in);
        FileOutputStream out = new FileOutputStream("E:\\3DEngine用户手册_1.chm");
        BufferedOutputStream outBuffer = new BufferedOutputStream(out);
        int len = 0;
        byte[] bs = new byte[1024];
        long begin = System.currentTimeMillis();
        while ((len = inBuffer.read(bs)) != -1) {
            outBuffer.write(bs, 0, len);
        }
        System.out.println("复制文件所需的时间：" + (System.currentTimeMillis() - begin));
        inBuffer.close();
        in.close();
        outBuffer.close();
        out.close();
    }

    public static void  test13() throws IOException {
        // 只有输入使用缓冲流
        FileInputStream in = new FileInputStream("E:\\3DEngine用户手册_1.4.1.1130.chm");
        BufferedInputStream inBuffer = new BufferedInputStream(in);
        FileOutputStream out = new FileOutputStream("E:\\3DEngine用户手册_1.chm");
        int len = 0;
        byte[] bs = new byte[1024];
        long begin = System.currentTimeMillis();
        while ((len = inBuffer.read(bs)) != -1) {
            out.write(bs, 0, len);
        }
        System.out.println("复制文件所需时间：" + (System.currentTimeMillis() - begin));
        inBuffer.close();
        in.close();
        out.close();
    }

    public static void test14() throws IOException {
        // 输入和输出都不使用缓冲流
        FileInputStream in = new FileInputStream("E:\\3DEngine用户手册_1.4.1.1130.chm");
        FileOutputStream out = new FileOutputStream("E:\\3DEngine用户手册_1.chm");
        int len = 0;
        byte[] bs = new byte[1024];
        long begin = System.currentTimeMillis();
        while ((len = in.read(bs)) != -1) {
            out.write(bs, 0, len);
        }
        System.out.println("复制文件所需时间：" + (System.currentTimeMillis() - begin));
        in.close();
        out.close();
    }

    //复制时间大大增加 性能很低
    public static void test15() throws IOException {
        // 不使用缓冲
        FileInputStream in = new FileInputStream("E:\\3DEngine用户手册_1.4.1.1130.chm");
        FileOutputStream out = new FileOutputStream("E:\\3DEngine用户手册_1.chm");
        int len = 0;
        long begin = System.currentTimeMillis();
        while ((len = in.read()) != -1) {
            out.write(len);
        }
        System.out.println("复制文件所需时间：" + (System.currentTimeMillis() - begin));
        in.close();
        out.close();
    }



    public static void main(String[] args) {
        try {
            test15();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
