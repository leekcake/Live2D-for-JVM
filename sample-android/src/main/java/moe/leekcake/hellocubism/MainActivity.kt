package moe.leekcake.hellocubism

import android.app.Activity
import android.opengl.GLES20
import android.opengl.GLES20.GL_COLOR_BUFFER_BIT
import android.opengl.GLES20.glClear
import android.opengl.GLSurfaceView
import android.os.Bundle
import moe.leekcake.live2dforjvm.Live2DCubismFrameworkJNI
import moe.leekcake.live2dforjvm.Live2DCubismGLRenderingJNI
import moe.leekcake.live2dforjvm.type.*
import moe.leekcake_live2dforjvm.sample.SampleGLApp
import java.io.InputStream
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


class MainActivity : Activity() {
    inner class AndroidGLApp: SampleGLApp() {
        override val width: Float
            get() = glView.measuredWidth.toFloat()
        override val height: Float
            get() = glView.measuredHeight.toFloat()

        override fun generateTexture(fileName: String): Int {
            return Utils.loadTexture( application.assets.open(fileName) )
        }

        override fun destroyTexture(id: Int) {
            val ids = IntArray(1)
            ids[0] = id

            GLES20.glDeleteTextures(ids.size, ids, 0)
        }

        override fun openFile(fileName: String): InputStream {
            return application.assets.open(fileName)
        }
    }
    private val app = AndroidGLApp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        glView = GLSurfaceView(this)
        glView.setEGLContextClientVersion(2)
        glView.setPreserveEGLContextOnPause(true)
        glView.setRenderer(Renderer())

        setContentView(glView)
    }

    override fun onPause() {
        super.onPause()
        glView.onPause()
    }

    override fun onResume() {
        super.onResume()
        glView.onResume()
    }
    private lateinit var glView: GLSurfaceView

    private inner class Renderer : GLSurfaceView.Renderer {
        override fun onDrawFrame(p0: GL10?) {
            glClear(GL_COLOR_BUFFER_BIT)
            app.tick()
        }

        override fun onSurfaceChanged(p0: GL10?, p1: Int, p2: Int) {

        }

        override fun onSurfaceCreated(p0: GL10?, p1: EGLConfig?) {
            app.init()
        }
    }
}