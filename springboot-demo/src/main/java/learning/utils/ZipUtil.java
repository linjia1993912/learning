package learning.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @Description:解压 压缩
 * @Author LinJia
 * @Date 2019/10/10 14:32
 * @Param
 * @return
 **/
public class ZipUtil {

    private static final int BUFFER_SIZE = 2 * 1024;


    public static void toZip(List<File> srcFiles, OutputStream out) throws RuntimeException {
        long start = System.currentTimeMillis();
        try (ZipOutputStream zos = new ZipOutputStream(out)) {
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        }
    }

    public static void toZip(Map<String, File> fileMap, OutputStream out) throws RuntimeException {
        long start = System.currentTimeMillis();
        try (ZipOutputStream zos = new ZipOutputStream(out)) {
            for(Map.Entry<String, File> it : fileMap.entrySet()){
                String fileName = it.getKey();
                File srcFile = it.getValue();
                byte[] buf = new byte[BUFFER_SIZE];
                zos.putNextEntry(new ZipEntry(fileName));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils", e);
        }
    }

    /**
     * @param srcDir           压缩文件夹路径
     * @param outDir           压缩文件输出流
     * @param keepDirStructure 是否保留原来的目录结构,
     *                         true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception 压缩失败会抛出运行时异常
     */
    public static void toZip(List<String> srcDir, String outDir,
                             boolean keepDirStructure) throws Exception {

        OutputStream out = new FileOutputStream(new File(outDir));

        long start = System.currentTimeMillis();
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(out);
            List<File> sourceFileList = new ArrayList<File>();
            for (String dir : srcDir) {
                File sourceFile = new File(dir);
                sourceFileList.add(sourceFile);
            }
            compress(sourceFileList, zos, keepDirStructure);
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtil", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("压缩完成，耗时：" + (end - start) + " ms");
    }

    private static void compress(List<File> sourceFileList, ZipOutputStream zos, boolean keepDirStructure) throws Exception {
        for (File sourceFile : sourceFileList) {
            compress(sourceFile, zos, sourceFile.getName(), keepDirStructure);
        }
    }

    /**
     * 递归压缩方法
     *
     * @param sourceFile       源文件
     * @param zos              zip输出流
     * @param name             压缩后的名称
     * @param keepDirStructure 是否保留原来的目录结构,
     *                         true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name, boolean keepDirStructure) throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        if (sourceFile.isFile()) {
            zos.putNextEntry(new ZipEntry(name));
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            zos.closeEntry();
            in.close();
        } else {
            if (keepDirStructure) {
                zos.putNextEntry(new ZipEntry(name + "/"));
                zos.closeEntry();
            }
            File[] listFiles = sourceFile.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    if (keepDirStructure) {
                        compress(file, zos, name + "/" + file.getName(),
                                keepDirStructure);
                    } else {
                        compress(file, zos, file.getName(), keepDirStructure);
                    }

                }
            }
        }
    }

    /**
     * @param zipPath    zip路径
     * @param charset    编码
     * @param outPutPath 输出路径
     * @description (解压)
     */
    public static void decompression(String zipPath, String charset, String outPutPath) {
        long startTime = System.currentTimeMillis();
        try {
            // 输入源zip路径
            ZipInputStream zin = new ZipInputStream(new FileInputStream(zipPath), Charset.forName(charset));
            BufferedInputStream bin = new BufferedInputStream(zin);
            File fout = null;
            ZipEntry entry;
            try {
                while ((entry = zin.getNextEntry()) != null) {
                    if (entry.isDirectory()) {
                        continue;
                    }
                    fout = new File(outPutPath, entry.getName());
                    File parent = new File(fout.getParent());
                    if (!parent.exists()) {
                        parent.mkdirs();
                    }
                    FileOutputStream out = new FileOutputStream(fout);
                    BufferedOutputStream bout = new BufferedOutputStream(out);
                    int b;
                    while ((b = bin.read()) != -1) {
                        bout.write(b);
                    }
                    bout.close();
                    out.close();
                    System.out.println(fout + "解压成功");
                }
                bin.close();
                zin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗费时间： " + (endTime - startTime) + " ms");
    }
}
