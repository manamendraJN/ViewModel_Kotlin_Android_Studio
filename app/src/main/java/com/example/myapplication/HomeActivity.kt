package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {

    lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        imageView = findViewById(R.id.imageView)
        val button2:Button = findViewById(R.id.button2)
        val button3:Button = findViewById(R.id.button3)
        val button4:Button = findViewById(R.id.button4)
        val button5:Button = findViewById(R.id.button5)
        val button6:Button = findViewById(R.id.button6)
        val button7:Button = findViewById(R.id.button7)

        val thumbnailLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){

            if (it.resultCode == RESULT_OK){
                val data = it.data
                val imageBitmap = data?.extras?.get("data")as? Bitmap
                imageView.setImageBitmap(imageBitmap)
            }
        }

        button2.setOnClickListener(){

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        button3.setOnClickListener(){

            val number = "0785698745"
            val uri = Uri.parse(String.format("tel:$number"))
            val intent = Intent()
            intent.action = Intent.ACTION_DIAL
            intent.data = uri
            startActivity(intent)
        }
        button4.setOnClickListener(){

            var number = "078569874"
            val smsText = "Why are you"
            val uri = Uri.parse(String.format("smsto:$number"))
            val intent = Intent()
            intent.action = Intent.ACTION_SENDTO
            intent.data = uri
            intent.putExtra("sms_body", smsText)
            startActivity(intent)
        }
        button5.setOnClickListener(){

            val url = "https://www.sliit.lk"
            val uri = Uri.parse(url)
            val intent = Intent()
            intent.action=Intent.ACTION_VIEW;
            intent.data = uri
            startActivity(intent)

        }
        button6.setOnClickListener(){

            val mailTo = arrayOf("abc@email.com")
            val subject = "Test Email"
            val mailBody = "This the test email body"

            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.type = "message/rfc822"
            intent.putExtra(Intent.EXTRA_EMAIL,mailTo)
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(Intent.EXTRA_TEXT, mailBody)
            startActivity(intent)

        }
        button7.setOnClickListener(){

            val intent = Intent()
            intent.action = MediaStore.ACTION_IMAGE_CAPTURE
            thumbnailLauncher.launch(intent)
        }

    }


}