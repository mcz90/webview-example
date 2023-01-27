package com.example.myapplication

import android.os.Bundle
import android.webkit.JavascriptInterface
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding.webView) {
            settings.javaScriptEnabled = true
            addJavascriptInterface(ExampleJSInterface(), "exampleJSInterface")
            loadUrl("file:///android_res/raw/example.html");
        }

        binding.fab.setOnClickListener { view ->
            binding.webView.evaluateJavascript("getPosts('2')", null)
        }
    }

    private inner class ExampleJSInterface {

        @JavascriptInterface
        fun exampleJSMethod(returnedValue: String) {
            Toast.makeText(this@MainActivity, returnedValue, Toast.LENGTH_SHORT).show()
        }
    }

}