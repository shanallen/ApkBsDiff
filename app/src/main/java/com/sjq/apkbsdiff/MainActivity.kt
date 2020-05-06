package com.sjq.apkbsdiff

import android.Manifest
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_update.setOnClickListener {
            PermissionTool.getInstance().chekPermissions(this, arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE),object :PermissionTool.IPermissionsResult{
                override fun passPermissons() {
                    updateApk()
                }

                override fun forbitPermissons() {
                    Toast.makeText(this@MainActivity,"reject perssion apply",Toast.LENGTH_LONG).show()
                }

            })

        }
    }

    fun updateApk(){
        PatchTask().execute()
    }

    class PatchTask:AsyncTask<String,Int,String>(){

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            InstallApkUti.install(result!!,MyApplication.getApplication())
        }
        override fun doInBackground(vararg params: String?): String {

            //获取旧版本路径（正在运行的apk路径）
            //String oldApk = getApplicationInfo().sourceDir
            var patch = File(Environment.getExternalStorageDirectory(),"patch").absolutePath
            var oldApk = File(Environment.getExternalStorageDirectory(),"old.apk").absolutePath
            var output = InstallApkUti.createNewApk().absolutePath
            BsPatcher.bsPatch(oldApk,patch,output)


            return File(output).toString()
        }



        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }
    }

}
