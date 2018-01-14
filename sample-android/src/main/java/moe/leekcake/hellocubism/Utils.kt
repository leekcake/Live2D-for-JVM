package moe.leekcake.hellocubism

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.GLES20
import android.util.Log
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder


object Utils {


    //From http://gogorchg.tistory.com/entry/Android-Opengl-es-20-LoadTexture
    //Tweaked
    fun loadTexture(stream: InputStream): Int {
        val textureId = IntArray(1)
        val bitmap: Bitmap
        bitmap = BitmapFactory.decodeStream(stream)

        val byteBuffer = ByteBuffer.allocateDirect(bitmap.width * bitmap.height * 4)
        byteBuffer.order(ByteOrder.BIG_ENDIAN)
        val ib = byteBuffer.asIntBuffer()

        val pixels = IntArray(bitmap.width * bitmap.height)
        bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
        /*
        for (i in pixels.indices) {
            ib.put(pixels[i] shl 8 or pixels[i].ushr(24))
        }
        */
        //Flip Y for OpenGL using
        val forHeight = bitmap.height - 1
        for (y in 0 until bitmap.height) {
            for (x in 0 until bitmap.width) {
                val value = pixels[x + (y * bitmap.width)]
                try {
                    ib.put(x + ((forHeight - y) * bitmap.height), value.shl(8) or value.ushr(24))
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e("xy", "$x . $y")
                    throw e
                }
            }
        }

        bitmap.recycle()

        byteBuffer.position(0)
        GLES20.glGenTextures(1, textureId, 0)
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId[0])

        GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, bitmap.width, bitmap.height, 0,
                GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, byteBuffer)

        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE)
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE)

        return textureId[0]
    }
}