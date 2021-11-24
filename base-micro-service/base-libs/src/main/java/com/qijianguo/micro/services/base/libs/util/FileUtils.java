package com.qijianguo.micro.services.base.libs.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;

/**
 * @author qijianguo
 */
public class FileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 创建文件
     *
     * @param fullname 文件名称
     * @return 是否创建成功，成功则返回true
     */
    public static boolean createFile(String fullname, String fileName) {
        Boolean bool = false;
        File file = new File(fullname);
        try {
            //如果文件不存在，则创建新的文件
            if (!file.exists()) {
                bool = file.createNewFile();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return bool;
    }

    /**
     * 创建文件
     *
     * @param fullname    文件名称
     * @param filecontent 文件内容
     * @return 是否创建成功，成功则返回true
     */
    public static boolean writeContent(String fullname, String filecontent) {
        Boolean bool = false;
        try {
            //如果文件不存在，则创建新的文件
            bool = createFile(fullname);
            //写入内容到文件里
            bool = writeFileContent(fullname, filecontent);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return bool;
    }

    /**
     * 向文件中写入内容
     *
     * @param filepath 文件路径与名称
     * @param newstr   写入的内容
     * @return
     * @throws IOException
     */
    public static boolean writeFileContent(String filepath, String newstr) throws IOException {
        Boolean bool = false;
        String filein = newstr + "\r\n";//新写入的行，换行
        String temp;

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            File file = new File(filepath);//文件路径(包括文件名称)
            //将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuilder buffer = new StringBuilder();

            //文件原有内容
            for (int i = 0; (temp = br.readLine()) != null; i++) {
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            //不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return bool;
    }

    /**
     * 创建多级目录文件
     *
     * @param path 文件路径
     * @throws IOException
     */
    public static boolean createFile(String path) throws IOException {
        if (!StringUtils.isEmpty(path)) {
            File file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            return file.createNewFile();
        }
        return false;
    }

    /**
     * 读取文件并拼接成字符串
     * @param filePath
     * @return
     */
    public static String readFileToString(String filePath) {
        File file = new File(filePath);
        try (FileReader reader = new FileReader(file)) {
            BufferedReader bReader = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();
            String s = "";
            while ((s = bReader.readLine()) != null) {
                sb.append(s + "\n");
            }
            return sb.toString();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return "";
    }

}
