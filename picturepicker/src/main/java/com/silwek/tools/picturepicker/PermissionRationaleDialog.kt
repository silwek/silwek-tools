package com.silwek.tools.picturepicker

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AlertDialog

interface PermissionRationaleDialog {
    fun getSourceContext(): Context?

    fun showRationaleDialog(
        titleRes: Int,
        messageRes: Int,
        permission: String,
        requestCode: Int
    ) {
        val ctx = getSourceContext() ?: return
        val pkg = ctx.packageName
        val builder: AlertDialog.Builder = AlertDialog.Builder(ctx)
        builder.setTitle(titleRes)
            .setMessage(messageRes)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri: Uri = Uri.fromParts("package", pkg, null)
                intent.data = uri
                startSettingActivity(intent)
            }
            .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                dialog.cancel()
            }
        builder.create().show()
    }

    fun startSettingActivity(intent: Intent)
}