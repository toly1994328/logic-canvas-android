package com.toly1994.logic_canvas.Jutils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：张风捷特烈
 * 时间：2018/5/7:7:58
 * 邮箱：1981462002@qq.com
 * 说明：
 */
public class FileHelper {

    ////////////////////////单例模式start//////////////////////////////
    private static FileHelper sFileHelper;

    private FileHelper() {
    }

    public static FileHelper getFileHelper() {

        if (sFileHelper == null) {
            synchronized (FileHelper.class) {
                if (sFileHelper == null) {
                    sFileHelper = new FileHelper();
                }
            }
        }
        return sFileHelper;
    }
    ////////////////////////单例模式end//////////////////////////////

    private void save(OutputStream os, String file_content) {

        try {
            if (file_content != null) {
                os.write(file_content.getBytes());
            }
            close(os);//关闭输出流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取InputStream
     *
     * @param is 输入流
     * @return 流转化的字符串
     * @throws IOException
     */
    private static String readIs(InputStream is) throws IOException {
        byte[] temp = new byte[1024];
        int len = 0;
        StringBuilder sb = new StringBuilder("");
        while ((len = is.read(temp)) > 0) {
            sb.append(new String(temp, 0, len));
        }
        return sb.toString();
    }

    /**
     * 关闭可关闭对象
     *
     * @param io 可关闭对象
     * @return
     */
    private boolean close(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
            }
        }
        return true;
    }

    /**
     * ////////////////////////////////在创建文件/////////////////////////////////
     *
     * @param savePath     保存路径
     * @param file_content 文件内容
     */
    public File saveFileWithAbsolutePath(String savePath, String file_content) {

        File filePic = null;
        try {

            filePic = new File(savePath);
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(savePath);

            save(fos, file_content);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePic;
    }
    ////////////////////////////////在创建文件END/////////////////////////////////

    /**
     * 在SD卡中读取文件
     *
     * @param filename 文件名
     * @return 文件内容
     */
    public String readFileWithAbsolutePath(String filename) {
        String result = null;
        try {
            FileInputStream input = new FileInputStream(filename);//文件输入流
            result = readIs(input);//读取InputStream
            close(input); //关闭输入流
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    ///////////////////////////////////////////

    /**
     * 列出指定目录（包括其子目录）所以文件
     *
     * @param dir
     */
    public List<String> listAllFiles(File dir) {
        List<String> allFiles = new ArrayList<>();

        if (!dir.exists()) {//目录不存在
            throw new IllegalArgumentException("目录" + dir + "不存在");
        }

        if (!dir.isDirectory()) {//不是目录
            throw new IllegalArgumentException(dir + "不存在");
        }

//        String[] fileNames = dir.list();//返回文件名称，不包括子目录内容
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listAllFiles(file);//递归

                } else {
                    allFiles.add(file.getAbsolutePath());
                }
            }
        }
        return allFiles;
    }
}
