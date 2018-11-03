package moe.leekcake.hellocubism

import android.app.Activity
import android.opengl.GLES20
import android.opengl.GLES20.GL_COLOR_BUFFER_BIT
import android.opengl.GLES20.glClear
import android.opengl.GLSurfaceView
import android.os.Bundle
import moe.leekcake.live2dforjvm.sample.SampleApp
import java.io.InputStream
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


class MainActivity : Activity() {
    inner class AndroidGLApp: SampleApp() {
        override fun generateTexture(fileName: String): Int {
            return Utils.loadTexture( application.assets.open(fileName) )
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
            app.update()
        }

        override fun onSurfaceChanged(p0: GL10?, p1: Int, p2: Int) {

        }

        override fun onSurfaceCreated(p0: GL10?, p1: EGLConfig?) {
            app.init()
        }
    }
}