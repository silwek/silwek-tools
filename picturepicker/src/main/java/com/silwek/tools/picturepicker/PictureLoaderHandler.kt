package com.silwek.tools.picturepicker

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import java.io.IOException
import java.io.InputStream
import kotlin.math.max
import kotlin.math.min


interface PictureLoaderHandler {

    fun getSafePicture(
        ctx: Context,
        path: String? = null,
        uri: Uri? = null,
        targetSize: Int,
        minTargetSize: Int
    ): Bitmap? {
        if (path == null && uri == null) return null
        var bitmap: Bitmap? = null
        var currentTargetSize = targetSize
        do {
            try {
                bitmap = when {
                    path != null -> loadImageWithPath(path, targetSize)
                    uri != null -> loadImageWithUri(ctx, uri, targetSize)
                    else -> null
                }
            } catch (OoM: OutOfMemoryError) {
                if (targetSize == minTargetSize) {
                    onPictureError(OoM)
                }
                currentTargetSize -= 100
            }
        } while (bitmap == null && currentTargetSize >= minTargetSize)

        val bitmapToRotate = bitmap
        if (bitmapToRotate != null && uri != null)
            bitmap = rotateImageIfRequired(ctx, bitmapToRotate, uri)
        return bitmap
    }

    fun loadImageWithPath(path: String, targetSize: Int): Bitmap {
        val bmOptions = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
            val photoW: Int = outWidth
            val photoH: Int = outHeight
            val scaleFactor: Int = max(1, min(photoW / targetSize, photoH / targetSize))
            inJustDecodeBounds = false
            inSampleSize = scaleFactor
            inPurgeable = true
        }
        return BitmapFactory.decodeFile(path, bmOptions)
    }


    private fun loadImageWithUri(ctx: Context, selectedImage: Uri, targetSize: Int): Bitmap? {
        var bitmap: Bitmap? = null
        val scaleResolution: (Int, Int) -> Int = { photoW, photoH ->
            max(1, min(photoW / targetSize, photoH / targetSize))
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.createSource(ctx.contentResolver, selectedImage).also {
                bitmap = ImageDecoder.decodeBitmap(it) { decoder, info, _ ->
                    decoder.setTargetSampleSize(
                        scaleResolution(
                            info.size.width,
                            info.size.height
                        )
                    )
                }
            }

        } else {
            val bmOptions = BitmapFactory.Options().apply {
                inJustDecodeBounds = true
                inJustDecodeBounds = false
                inSampleSize = scaleResolution(outWidth, outHeight)
                inPurgeable = true
            }
            ctx.contentResolver.openInputStream(selectedImage)?.also {
                bitmap = BitmapFactory.decodeStream(it, null, bmOptions)
                it.close()
            }
        }
        return bitmap
    }

    @Throws(IOException::class)
    private fun rotateImageIfRequired(
        context: Context,
        img: Bitmap,
        selectedImage: Uri
    ): Bitmap? {
        val path = selectedImage.path ?: return img
        val input: InputStream =
            context.contentResolver.openInputStream(selectedImage) ?: return img
        val ei: ExifInterface
        ei = if (Build.VERSION.SDK_INT > 23) ExifInterface(input) else ExifInterface(path)
        val orientation: Int =
            ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(img, 90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(img, 180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(img, 270)
            else -> img
        }
    }

    fun rotateImage(imageToOrient: Bitmap, degreesToRotate: Int): Bitmap? {
        var result: Bitmap? = imageToOrient
        try {
            if (degreesToRotate != 0) {
                val matrix = Matrix()
                matrix.setRotate(degreesToRotate.toFloat())
                result = Bitmap.createBitmap(
                    imageToOrient,
                    0,
                    0,
                    imageToOrient.width,
                    imageToOrient.height,
                    matrix,
                    true /*filter*/
                )
            }
        } catch (e: Exception) {

        }
        return result
    }

    fun onPictureError(e: Throwable?)
}