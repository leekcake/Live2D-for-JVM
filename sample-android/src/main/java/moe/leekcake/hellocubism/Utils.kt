package moe.leekcake.hellocubism

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.GLES20
import android.util.Log
import ar.com.hjg.pngj.ImageLineHelper
import ar.com.hjg.pngj.ImageLineInt
import ar.com.hjg.pngj.PngReader
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder


object Utils {


    //From http://gogorchg.tistory.com/entry/Android-Opengl-es-20-LoadTexture
    //Tweaked
    fun loadTexture(stream: InputStream): Int {
        val textureId = IntArray(1)
        val reader = PngReader(stream)

        val width = reader.imgInfo.cols
        val height = reader.imgInfo.rows

        val byteBuffer = ByteBuffer.allocateDirect(width * height * 4)
        byteBuffer.order(ByteOrder.BIG_ENDIAN)
        val ib = byteBuffer.asIntBuffer()

        var offset = 0
        for(y in 0 until reader.imgInfo.rows) {
            val row = reader.readRow() as ImageLineInt
            val line = row.scanline
            for(x in 0 until reader.imgInfo.cols) {
                offset = x * row.imgInfo.channels
                ib.put(x + (y * width), line[offset].shl(24) or line[offset + 1].shl(16) or line[offset + 2].shl(8) or line[offset + 3])
            }
        }

        byteBuffer.position(0)
        GLES20.glGenTextures(1, textureId, 0)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId[0])

        GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, width, height, 0,
                GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, byteBuffer)

        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE)

        return textureId[0]
    }
}