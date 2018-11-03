package moe.leekcake.live2dforjvm.framework.jni.extend;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;

/***
 * Cubism Platform Abstraction Layer
 */
public class ExtendPALJNI {
    public interface TextureProvider {
        int GetTexture(String path);
    }

    private static TextureProvider textureProvider = null;
    public static void setTextureProvider(TextureProvider textureProvider) {
        ExtendPALJNI.textureProvider = textureProvider;
    }

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
            byte[] result = new byte[(int) file.length()];
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            dis.readFully(result);
            dis.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getTextureProxy(String path) {
        try {
            return textureProvider.GetTexture(path);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static native void bindProxy();
}
