package com.silwek.tools.picturepicker

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


interface CameraHandler : PictureLoaderHandler {
    companion object {
        private const val REQUEST_CAMERA = 1133
    }

    var currentPhotoPath: String?
    var currentPhotoUri: Uri?
    val fileProviderAuthority: String
    val maxPictureSize: Int
    val minPictureSize: Int

    @SuppressLint("SimpleDateFormat")
    fun createImageFilePrefix(): String {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        return "JPEG_${timeStamp}_"
    }

    fun createImageFileSuffix(): String = ".jpg"

    fun takePicture(ctx: Context, fragment: Fragment) {
        val takePictureIntent = prepareTakePictureIntent(ctx)
        takePictureIntent?.let {
            fragment.startActivityForResult(
                takePictureIntent,
                REQUEST_CAMERA
            )
        }
    }

    private fun prepareTakePictureIntent(ctx: Context): Intent? {
        if (ctx.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(ctx.packageManager)?.also {
                    val photoFile: File? = try {
                        createImageFile(ctx)
                    } catch (ex: IOException) {
                        onPictureError(ex)
                        null
                    }
                    photoFile?.let {
                        val photoURI: Uri = FileProvider.getUriForFile(
                            ctx,
                            fileProviderAuthority,
                            it
                        )
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                        return takePictureIntent
                    }
                }
            }
        }
        return null
    }

    fun handleCameraResult(
        ctx: Context,
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        onLoaded: (Bitmap) -> Unit
    ) {
        if (requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK) {
            val bitmap: Bitmap? = getSafePicture(
                ctx,
                path = currentPhotoPath,
                uri = currentPhotoUri,
                targetSize = maxPictureSize,
                minTargetSize = minPictureSize
            )
            if (bitmap != null) {
                onLoaded(bitmap)
            } else {
                onPictureError(null)
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(context: Context): File? {
        val storageDir: File =
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) ?: return null
        return File.createTempFile(
            createImageFilePrefix(),
            createImageFileSuffix(),
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
            currentPhotoUri = toUri()
        }
    }
}