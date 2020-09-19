package com.silwek.tools.picturepicker

import android.content.Intent
import android.graphics.Bitmap
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

interface PicturePickerHandler : CameraHandler, GalleryHandler {

    fun onWantPickPicture(
        fragment: Fragment,
        @StringRes title: Int,
        cameraLabel: String,
        galleryLabel: String
    ) {
        val ctx = getSourceContext() ?: return
        val items = arrayOf(cameraLabel, galleryLabel)
        val builder: AlertDialog.Builder = AlertDialog.Builder(ctx)
        builder.setTitle(title)
        builder.setItems(items) { _, which ->
            when (which) {
                0 -> {
                    takePicture(ctx, fragment)
                }
                1 -> {
                    pickPicture(ctx, fragment)
                }
            }
        }
        builder.show()
    }

    fun handlePictureResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val ctx = getSourceContext() ?: return
        handleCameraResult(ctx, requestCode, resultCode, data) { bitmap ->
            onPictureReady(bitmap)
        }
        handlePickResult(ctx, requestCode, resultCode, data) { bitmap ->
            onPictureReady(bitmap)
        }
    }

    fun onPictureReady(bitmap: Bitmap)
}