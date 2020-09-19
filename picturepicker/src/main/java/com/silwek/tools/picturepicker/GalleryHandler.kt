package com.silwek.tools.picturepicker

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment


interface GalleryHandler : PermissionRationaleDialog, PictureLoaderHandler {

    companion object {
        private const val REQUEST_GALLERY = 1128
        private const val REQUEST_PERMISSIONS = 1118
    }

    var readStoragePermissionAsked: Boolean

    val maxPictureSize: Int
    val minPictureSize: Int

    fun pickPicture(ctx: Context, fragment: Fragment) {
        if (isReadStoragePermissionGranted(ctx, fragment)) {
            val pickPictureIntent = getPictureFromGallery(ctx)
            pickPictureIntent?.let {
                fragment.startActivityForResult(
                    pickPictureIntent,
                    REQUEST_GALLERY
                )
            }
        }
    }

    private fun isReadStoragePermissionGranted(ctx: Context, fragment: Fragment): Boolean {
        val perm = Manifest.permission.READ_EXTERNAL_STORAGE
        return if (Build.VERSION.SDK_INT >= 23) {
            if (ctx.checkSelfPermission(perm)
                == PackageManager.PERMISSION_GRANTED
            ) {
                true
            } else {
                if (fragment.shouldShowRequestPermissionRationale(perm)) {
                    showRequestReadStoragePermissionRationale(perm)
                } else {
                    if (!readStoragePermissionAsked) {
                        readStoragePermissionAsked = true
                        fragment.requestPermissions(
                            arrayOf(perm),
                            REQUEST_PERMISSIONS
                        )
                    } else {
                        showRequestReadStoragePermissionRationale(perm)
                    }
                }
                false
            }
        } else {
            true
        }
    }

    fun showRequestReadStoragePermissionRationale(perm: String) {
        showRationaleDialog(
            getReadStoragePermissionDialogTitle(),
            getReadStoragePermissionDialogContent(),
            perm,
            REQUEST_PERMISSIONS
        )
    }

    @StringRes
    fun getReadStoragePermissionDialogContent(): Int

    @StringRes
    fun getReadStoragePermissionDialogTitle(): Int

    fun handlePickPermissionResult(
        ctx: Context,
        fragment: Fragment,
        requestCode: Int,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_PERMISSIONS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickPicture(ctx, fragment)
            }
        }
    }

    private fun getPictureFromGallery(ctx: Context): Intent? {
        Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        ).also { pickPictureIntent ->
            pickPictureIntent.type = "image/*"
            val mimeTypes =
                arrayOf("image/jpeg", "image/png")
            pickPictureIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            pickPictureIntent.resolveActivity(ctx.packageManager)?.also {
                return pickPictureIntent
            }
        }
        return null
    }

    fun handlePickResult(
        ctx: Context,
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        onLoaded: (Bitmap) -> Unit
    ) {
        if (requestCode == REQUEST_GALLERY && resultCode == Activity.RESULT_OK) {
            val selectedImage = data?.data ?: return
            loadPicture(ctx, selectedImage, onLoaded)
        }
    }

    fun loadPicture(
        ctx: Context,
        selectedImage: Uri,
        onLoaded: (Bitmap) -> Unit
    ) {
        val bitmap: Bitmap? = getSafePicture(
            ctx,
            uri = selectedImage,
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