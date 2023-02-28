package com.team.seacondlife.codescanner

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.common.MlKitException
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import com.team.UserDataBase.ScannerSQLiteHelper
import com.team.UserDataBase.UserSQLiteHelper
import com.team.seacondlife.R

/** Demonstrates the code scanner powered by Google Play Services. */
class CodeScanner : AppCompatActivity() {

    private var allowManualInput = true
    private var barcodeResultView: TextView? = null
    private val dbhelper=UserSQLiteHelper(this)
    val scandbhelp=ScannerSQLiteHelper(this)
    var code = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_scanner)
        barcodeResultView = findViewById(R.id.barcode_result_view)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if(scandbhelp.verifyItem("8413402990503") == true) {
            scandbhelp.addSampleData()
        }
    }
    //to back main
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }
    /*
    fun onAllowManualInputCheckboxClicked(view: View) {
        allowManualInput = (view as CheckBox).isChecked
    }
     */

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_simple, menu)

        return true
    }

    fun onScanButtonClicked(view: View) {
        val optionsBuilder = GmsBarcodeScannerOptions.Builder()

        if (allowManualInput) {
            optionsBuilder.allowManualInput()
        }
        val gmsBarcodeScanner = GmsBarcodeScanning.getClient(this, optionsBuilder.build())
        gmsBarcodeScanner
            .startScan()
            .addOnSuccessListener { barcode: Barcode ->
                val text: String = getSuccessfulMessage(barcode)
                val names=intent?.extras!!.getString("user").toString()
                val passw=intent?.extras!!.getString("passw").toString()
                val intent: Intent
                if (text == "SORRY"){
                    intent = Intent(this, Sorry::class.java)
                    intent.putExtra("CODE", code)
                }
                else{
                    dbhelper.UpdateUserPoints(names,passw,dbhelper.getUserPoints(names,passw)+1)
                    Toast.makeText(this,R.string.addpoint,Toast.LENGTH_LONG).show()
                    intent = Intent(this, ScannerResult::class.java)
                    intent.putExtra("TEXT", text)
                }
                startActivity(intent)
            }
            .addOnFailureListener { e: Exception -> barcodeResultView!!.text = getErrorMessage(e) }
            .addOnCanceledListener {
                barcodeResultView!!.text = getString(R.string.error_scanner_cancelled)
            }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putBoolean(KEY_ALLOW_MANUAL_INPUT, allowManualInput)
        super.onSaveInstanceState(savedInstanceState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        allowManualInput = savedInstanceState.getBoolean(KEY_ALLOW_MANUAL_INPUT)
    }

    private fun getSuccessfulMessage(barcode: Barcode): String {
        /*
        val barcodeValue =
            String.format(
                Locale.US,
                "Display Value: %s\nRaw Value: %s\nFormat: %s\nValue Type: %s",
                barcode.displayValue,
                barcode.rawValue,
                barcode.format,
                barcode.valueType
            )

         */

        var text = ""
        code = barcode.displayValue!!

        if(scandbhelp.verifyItem(code) == true){
            text = scandbhelp.getName(code) + "\nCONTENEDOR: "+scandbhelp.getType(code)
        }
        else{
            text = "SORRY"
        }

        return text
    }

    private fun getErrorMessage(e: Exception): String? {
        return if (e is MlKitException) {
            when (e.errorCode) {
                MlKitException.CODE_SCANNER_CAMERA_PERMISSION_NOT_GRANTED ->
                    getString(R.string.error_camera_permission_not_granted)
                MlKitException.CODE_SCANNER_APP_NAME_UNAVAILABLE ->
                    getString(R.string.error_app_name_unavailable)
                else -> getString(R.string.error_default_message, e)
            }
        } else {
            e.message
        }
    }

    companion object {
        private const val KEY_ALLOW_MANUAL_INPUT = "allow_manual_input"
    }
}
