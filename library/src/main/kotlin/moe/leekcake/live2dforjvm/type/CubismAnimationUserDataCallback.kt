package moe.leekcake.live2dforjvm.type

//Kotlin porting of AnimationUserDataCallback.c
class CubismAnimationUserDataCallback(val state: CubismAnimationState, val animation: CubismAnimation) {
    interface Callback {
        fun onCallback(time: Float, value: String)
    }
    var callback: Callback? = null

    private var lastTime = -1f

    fun update() {
        var time = state.time

        time %= animation.duration

        val sink = CubismAnimation.Sink()

        for(i in 0 until animation.userData.count) {
            animation.userData.getDataTo(i, sink)

            if(lastTime >= sink.time || sink.time > time || lastTime >= time) {
                continue
            }

            if(callback != null) {
                callback!!.onCallback(sink.time, sink.value)
            }
        }

        sink.release()
        lastTime = time
    } 
}