package moe.leekcake.live2dforjvm.type

import moe.leekcake.live2dforjvm.Live2DCubismCoreJNI
import moe.leekcake.live2dforjvm.Live2DCubismFrameworkJNI
import moe.leekcake.live2dforjvm.MemoryAccessJNI

/**
 * TODO: I don't sure about this class's doing in now :(
 *
 * Wrapping of csmModel(Core)
 */
class CubismModel(moc: CubismMoc) {
    val pointer: Long
    private fun finalize() {
        MemoryAccessJNI.deAllocateAligned(pointer)
    }

    init {
        val size = Live2DCubismCoreJNI.getSizeofModel(moc.pointer)
        pointer = MemoryAccessJNI.allocateAligned(size.toInt(), Alignment.model)
        Live2DCubismCoreJNI.initializeModelInPlace(moc.pointer, pointer, size)
    }

    val useMask: Boolean
        get() = Live2DCubismFrameworkJNI.doesModelUseMasks(pointer) != 0

    fun update() = Live2DCubismCoreJNI.updateModel(pointer)

    data class CanvasInfo(val sizeInPixels: Vector2, val originInPixels: Vector2, val pixelsPerUnit: Float)
    fun readCanvasInfo(): CanvasInfo {
        val sizeInPixels = Vector2()
        val originInPixels = Vector2()

        val pixelsPerUnit = Live2DCubismCoreJNI.readCanvasInfo(pointer, sizeInPixels.pointer, originInPixels.pointer)
        return CanvasInfo(sizeInPixels, originInPixels, pixelsPerUnit)
    }

    inner class Parameter(val index: Int) {
        val id: String = Live2DCubismCoreJNI.getParameterId(pointer, index)
        val minimumValue: Float
            get() = Live2DCubismCoreJNI.getParameterMinimumValue(pointer, index)
        val maximumValue: Float
            get() = Live2DCubismCoreJNI.getParameterMaximumValue(pointer, index)
        val defaultValue: Float
            get() = Live2DCubismCoreJNI.getParameterDefaultValue(pointer, index)
        val value: Float
            get() = Live2DCubismCoreJNI.getParameterValue(pointer, index)
    }


    inner class ParameterProvider {
        operator fun get(index: Int) = Parameter(index)

        val size: Int = Live2DCubismCoreJNI.getParameterCount(pointer)
    }
    val parameters = ParameterProvider()
    inner class ParameterGetter {
        operator fun get(hash: Int): Int {
            return Live2DCubismFrameworkJNI.findParameterIndexByHash(pointer, hash)
        }
    }
    val parameterMap = ParameterGetter()


    inner class Part(val index: Int) {
        val id: String = Live2DCubismCoreJNI.getParameterId(pointer, index)
        val opacity: Float
            get() = Live2DCubismCoreJNI.getPartOpacity(pointer, index)
    }
    inner class PartProvider {
        operator fun get(index: Int) = Part(index)

        val size: Int = Live2DCubismCoreJNI.getPartCount(pointer)
    }
    val parts = PartProvider()
    inner class PartGetter {
        operator fun get(hash: Int): Int {
            return Live2DCubismFrameworkJNI.findPartIndexByHash(pointer, hash)
        }
    }
    val partMap = PartGetter()

    inner class Drawable(val index: Int) {
        val id: String = Live2DCubismCoreJNI.getDrawableId(pointer, index)
        val constantFlag: Int
            get() = Live2DCubismCoreJNI.getDrawableConstantFlag(pointer, index)
        val dynamicFlag: Int
            get() = Live2DCubismCoreJNI.getDrawableDynamicFlag(pointer, index)
        val textureIndex: Int
            get() = Live2DCubismCoreJNI.getDrawableTextureIndex(pointer, index)
        val drawOrder: Int
            get() = Live2DCubismCoreJNI.getDrawableDrawOrder(pointer, index)
        val renderOrder: Int
            get() = Live2DCubismCoreJNI.getDrawableRenderOrder(pointer, index)
        val opacity: Float
            get() = Live2DCubismCoreJNI.getDrawableOpacity(pointer, index)

        inner class MaskProvider {
            operator fun get(index: Int) = Live2DCubismCoreJNI.getDrawableMask(pointer, this@Drawable.index, index)

            val size: Int = Live2DCubismCoreJNI.getDrawableMaskCount(pointer, index)
        }
        val masks = MaskProvider()

        inner class Vertex(val index: Int) {
            val position = Vector2( Live2DCubismCoreJNI.getDrawableVertexPosition(pointer, this@Drawable.index, index) )
            val uv = Vector2( Live2DCubismCoreJNI.getDrawableVertexUv(pointer, this@Drawable.index, index) )
        }

        inner class VertexProvider {
            operator fun get(index: Int) = Vertex(index)

            val size: Int = Live2DCubismCoreJNI.getDrawableVertexCount(pointer, index)
        }
        val vertices = VertexProvider()

        inner class IndexProvider {
            operator fun get(index: Int) = Live2DCubismCoreJNI.getDrawableIndex(pointer, this@Drawable.index, index)

            val size: Int = Live2DCubismCoreJNI.getDrawableIndexCount(pointer, this@Drawable.index)
        }
        val indices = IndexProvider()
    }

    inner class DrawableProvider {
        operator fun get(index: Int) = Drawable(index)

        val size: Int = Live2DCubismCoreJNI.getDrawableCount(pointer)
    }
    val drawables = DrawableProvider()
    inner class DrawableGetter {
        operator fun get(hash: Int): Drawable {
            return Drawable( Live2DCubismFrameworkJNI.findDrawableIndexByHash(pointer, hash) )
        }
    }

    fun resetDrawableDynamicFlags() {
        Live2DCubismCoreJNI.resetDrawableDynamicFlags(pointer)
    }

    enum class AnimationCurveType(val value: Long) {
        OpacityAnimationCurve(0),
        EyeBlinkAnimationCurve(1),
        LipSyncAnimationCurve(2)
    }

    class CubismModelHashTable(val table: Long) {
        val parameter = Parameter()
        val part = Part()

        inner class Parameter {
            operator fun get(index: Int): Int {
                return Live2DCubismFrameworkJNI.getHashTableParameterHash(table, index)
            }

            val size: Int
                get() = Live2DCubismFrameworkJNI.getHashTableParameterCount(table)
        }

        inner class Part {
            operator fun get(index: Int): Int {
                return Live2DCubismFrameworkJNI.getHashTablePartHash(table, index)
            }

            val size: Int
                get() = Live2DCubismFrameworkJNI.getHashTablePartCount(table)
        }
    }
}