package moe.leekcake.live2dforjvm.type

import moe.leekcake.live2dforjvm.Live2DCubismCoreJNI
import moe.leekcake.live2dforjvm.MemoryAccessJNI
import java.io.InputStream
import java.io.File
import java.io.FileInputStream


/**
 * .moc3 file wrapper
 *
 * Wrapping of csmMoc(Core)
 */
class CubismMoc(val pointer: Long) {
    companion object {
        private fun generateMocFromStream(stream: InputStream, count: Int): Long {
            val mocDataPointer = MemoryAccessJNI.allocateAligned(count, Alignment.moc)

            var totalReaded = 0
            var left = count
            var readed = 0
            val buf = ByteArray(1024)
            while (left != 0) {
                readed = stream.read(buf, 0, Math.min(1024, left))
                if (readed == -1) {
                    try {
                        Thread.sleep(10)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                    continue
                }
                MemoryAccessJNI.writeToMemory(mocDataPointer, totalReaded, buf, 0, readed)
                totalReaded += readed
                left -= readed
            }

            return Live2DCubismCoreJNI.reviveMocInPlace(mocDataPointer, count.toLong())
        }
    }

    constructor(stream: InputStream, count: Int) : this(generateMocFromStream(stream, count))
    constructor(file: File) : this(generateMocFromStream(FileInputStream(file), file.length().toInt()))

    private fun finalize() {
        MemoryAccessJNI.deAllocateAligned(pointer)
    }
}