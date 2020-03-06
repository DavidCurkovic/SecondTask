package com.example.drugizadatak

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.*
import com.example.drugizadatak.R.layout
import kotlinx.android.synthetic.main.fizzbuzz_dialog.view.*
import kotlin.collections.ArrayList

open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //load and set language from Shared Preferences
        loadLocale()
        setContentView(layout.activity_main)

        //change actionbar title, if you don't change it will be according to your system default language
        changeActionBarTitle()

        val langBtn = findViewById<Button>(R.id.languageButton)
        val messBtn = findViewById<Button>(R.id.messageButton)
        val inBtn = findViewById<Button>(R.id.fizzBuzzButton)

        langBtn.setOnClickListener() {
            Log.i("MainActivity", "LanguageButton was clicked!")
            //show Dialog to display a list of languages, one can be selected
            showChangeLanguageDialog()
        }

        messBtn.setOnClickListener() {
            Log.i("MainActivity", "MessageButton was clicked!")
            //show Toast to display a message
            val toast = Toast.makeText(applicationContext, resources.getString(R.string.languageToast1), Toast.LENGTH_LONG)
            toast.show()
        }

        inBtn.setOnClickListener() {
            Log.i("MainActivity", "InputButton was clicked!")
            //showFizzBuzzDialog to display TexView + Start Button
            showFizzBuzzDialog()
        }
    }

    // set Items from ListView to be clicked + setLocale function
    private fun showChangeLanguageDialog() {
        //getting strings array from xml file to be displayed in AlertDialog
        val langList: Array<String> = resources.getStringArray(R.array.languageList)
        val builder = AlertDialog.Builder(this)

        builder.setItems(langList) { dialog, i ->
            when (i) {
                0 -> {
                    //English
                    setLocale("en")
                    recreate()
                }
                1 -> {
                    //Croatian
                    setLocale("hr")
                    recreate()
                }
                2 -> {
                    //Slovak
                    setLocale("sk")
                    recreate()
                }
                3 -> {
                    //Czech
                    setLocale("cs")
                    recreate()
                }
                4 -> {
                    //Chinese
                    setLocale("zh")
                    recreate()
                }
            }
            //dismiss AlertDialog when language was selected
            dialog.dismiss()
        }
        val dialog = builder.create()
        //show AlertDialog
        dialog.show()
    }

    //configuration + save data to SharedPreferences
    private fun setLocale(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        val configuration = resources.configuration
        configuration.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        //save data to SharedPreferences
        val sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("My_Lang", lang)
        editor.apply()
    }

    //load language saved in SharedPreferences
    private fun loadLocale() {
        val sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "")
        if (language != null)
            setLocale(language)
    }

    //create FizzBuzz dialog
    private fun showFizzBuzzDialog(){
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.fizzbuzz_dialog, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
        //show Dialog
        val mDialog = mBuilder.show()

        mDialogView.fizzbuzzButton.setOnClickListener {
        val arrayList = ArrayList<String>()
        //call FizzBuzz class with method to check FizzBuzz
        FizzBuzz(this,arrayList).checkFizzBuzz()
        //set FizzBuzz array to TextView
        mDialogView.FizzBuzzDialogText.text = arrayList.toString()
        }
        //close Dialog by click on Img
        mDialogView.closeImg.setOnClickListener {
        mDialog.dismiss();
        }
        //set Dialog to not be able for closing outside of window
        mDialog.setCanceledOnTouchOutside(false);
    }

    //change actionbar title
    private fun changeActionBarTitle(){
        val actionBar = supportActionBar
        if (actionBar != null)
            actionBar.title = resources.getString(R.string.app_name)
    }
}
