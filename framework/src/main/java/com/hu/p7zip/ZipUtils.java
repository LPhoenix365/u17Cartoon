package com.hu.p7zip;

/**
 * Created by iceman on 16/5/10 11:49
 * 邮箱：xubin865@pingan.com.cn
 * 加载so文件.准备lzma解压工作
 */
public class ZipUtils {

    /**
     * Execute a command
     *
     * @param command command string
     * @return return code
     */
    public static native int executeCommand(String command);

    /**
     * load native library
     */
    static {
        System.loadLibrary("p7zip");
    }


    /**
     * 将压缩文件解压至指定文件夹
     * @param srcPath 压缩文件
     * @param outputDir 目标文件夹
     */
    public static void deCompressZip(String srcPath, String outputDir) {
        //7z为统一命令
        StringBuilder sbCmd = new StringBuilder("7z ");
        //e为忽略压缩文件中的文件结构,将所有文件解压到一起
        //x为保留压缩文件中的文件结构,即"完整路径解压"
        sbCmd.append("x ");
        //压缩文件的绝对路径
        sbCmd.append("'" + srcPath + "' ");
        //输出文件夹   eg:7z x 'a.zip' '-o/out/'
        sbCmd.append("'-o" + outputDir + "' ");
        //aoa直接覆盖现有文件,不提示
        sbCmd.append("-aoa ");
        executeCommand(sbCmd.toString());
    }

}
