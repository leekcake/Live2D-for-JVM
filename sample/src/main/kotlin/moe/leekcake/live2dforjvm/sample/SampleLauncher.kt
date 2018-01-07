package moe.leekcake.live2dforjvm.sample

import moe.leekcake.live2dforjvm.type.CubismMoc
import moe.leekcake.live2dforjvm.type.CubismModel
import java.io.File

//Run on (Project Root)/Native
fun main(args: Array<String>) {
    val sampleFolder = File("./Native/CubismNativeComponents/sample/assets/Koharu")

    val koharuMoc = CubismMoc( File(sampleFolder, "Koharu.moc3") )
    val koharuModel = CubismModel(koharuMoc)
}