package moe.leekcake.live2dforjvm.framework.jni.extend;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;

/***
 * Cubism Platform Abstraction Layer
 */
public class ExtendPALJNI {
    static {
        System.loadLibrary("Live2DCubismJNI");
        bindProxy();
    }

    public static void printCallback(String message) {
        System.out.println(message);
    }

    public static byte[] loadFileAsBytesProxy(String path) {
        try {
            File file = new File(path);
            byte[] result = new byte[(int)file.length()];
            DataInputStream dis = new DataInputStream( new FileInputStream(file) );
            dis.readFully(result);
            dis.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static native void bindProxy();


}
