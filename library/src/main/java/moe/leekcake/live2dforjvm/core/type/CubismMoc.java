package moe.leekcake.live2dforjvm.core.type;

import moe.leekcake.live2dforjvm.core.jni.Live2DCubismCoreJNI;
import moe.leekcake.live2dforjvm.core.jni.MemoryAccessJNI;
import moe.leekcake.live2dforjvm.core.constraints.Alignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CubismMoc extends AutoPointer {
    public static long generateMocFromStream(InputStream stream, int count) throws IOException {
        long mocDataPointer = MemoryAccessJNI.allocateAligned(count, Alignment.moc);

        int totalReaded = 0;
        int left = count;
        int readed = 0;
        byte[] buf = new byte[1024];
        while (left != 0) {
            readed = stream.read(buf, 0, Math.min(1024, left));
            if (readed == -1) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                continue;
            }
            MemoryAccessJNI.writeToMemory(mocDataPointer, totalReaded, buf, 0, readed);
            totalReaded += readed;
            left -= readed;
        }

        return Live2DCubismCoreJNI.reviveMocInPlace(mocDataPointer, count);
    }

    public CubismMoc(InputStream stream, int count) throws IOException {
        super( generateMocFromStream(stream, count) );
    }

    public CubismMoc(File file) throws IOException {
        super( generateMocFromStream(new FileInputStream(file), (int) file.length()) );
    }

    @Override
    protected void releasePointer() {
        MemoryAccessJNI.deAllocateAligned(pointer);
    }
}
