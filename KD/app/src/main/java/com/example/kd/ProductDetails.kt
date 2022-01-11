package com.example.kd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import org.json.JSONObject

class ProductDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        val chipset = findViewById<TextView>(R.id.chipset)
        val cpu = findViewById<TextView>(R.id.cpu)
        val gpu = findViewById<TextView>(R.id.gpu)

        val builtin = findViewById<TextView>(R.id.builtin)
        val card_support = findViewById<TextView>(R.id.card_support)

        val capacity = findViewById<TextView>(R.id.capacity)
        val battery_other = findViewById<TextView>(R.id.battery_other)

        val camera_features = findViewById<TextView>(R.id.camera_features)
        val camera_front = findViewById<TextView>(R.id.camera_front)
        val camera_main = findViewById<TextView>(R.id.camera_main)

        val bluetooth = findViewById<TextView>(R.id.bluetooth)
        val data = findViewById<TextView>(R.id.data)
        val gps = findViewById<TextView>(R.id.gps)
        val nfc = findViewById<TextView>(R.id.nfc)
        val radio = findViewById<TextView>(R.id.radio)
        val usb = findViewById<TextView>(R.id.usb)
        val wlan = findViewById<TextView>(R.id.wlan)

        val colors = findViewById<TextView>(R.id.colors)
        val dimensions = findViewById<TextView>(R.id.dimensions)
        val osText = findViewById<TextView>(R.id.os_text)
        val sim = findViewById<TextView>(R.id.sim)
        val weightText = findViewById<TextView>(R.id.weight_text)

        val audio = findViewById<TextView>(R.id.audio)
        val browser = findViewById<TextView>(R.id.browser)
        val extra = findViewById<TextView>(R.id.extra)
        val games = findViewById<TextView>(R.id.games)
        val messaging = findViewById<TextView>(R.id.messaging)
        val sensors = findViewById<TextView>(R.id.sensors)
        val torch = findViewById<TextView>(R.id.torch)

        val productHeadingName = findViewById<TextView>(R.id.product_heading_name)
        val productHeadingImage = findViewById<ImageView>(R.id.product_heading_image)

        val mobileName: String? = intent.getStringExtra("mobile_name")
        val brandName: String? = intent.getStringExtra("brand_name")
        val image: String? = intent.getStringExtra("image")

        val database = Firebase.database.reference

        productHeadingName.text = mobileName

        Picasso.get()
            .load(image)
            .placeholder(R.drawable.sample_phone)
            .error(R.drawable.sample_phone)
            .into(productHeadingImage)

        if (brandName != null && mobileName != null) {
            database.child("Mobile").child("BrandName").child(brandName).child(mobileName).orderByKey().limitToFirst(1).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val ds = dataSnapshot.children
                    val ids = ds.iterator()
                    val item = ids.next()
                    print(item)

                    chipset.text = item.child("Processor").child("Chipset").value.toString()
                    cpu.text = item.child("Processor").child("CPU").value.toString()
                    gpu.text = item.child("Processor").child("GPU").value.toString()

                    builtin.text = item.child("Memory").child("Built-in").value.toString()
                    card_support.text = item.child("Memory").child("Card").value.toString()

                    capacity.text = item.child("Battery").child("Capacity").value.toString()
                    battery_other.text = item.child("Battery").child("nan").value.toString()

                    camera_features.text = item.child("Camera").child("Features").value.toString()
                    camera_front.text = item.child("Camera").child("Front").value.toString()
                    camera_main.text = item.child("Camera").child("Main").value.toString()

                    bluetooth.text = item.child("Connectivity").child("Bluetooth").value.toString()
                    data.text = item.child("Connectivity").child("Data").value.toString()
                    gps.text = item.child("Connectivity").child("GPS").value.toString()
                    nfc.text = item.child("Connectivity").child("NFC").value.toString()
                    radio.text = item.child("Connectivity").child("Radio").value.toString()
                    usb.text = item.child("Connectivity").child("USB").value.toString()
                    wlan.text = item.child("Connectivity").child("WLAN").value.toString()

                    colors.text = item.child("Build").child("Colors").value.toString()
                    dimensions.text = item.child("Build").child("Dimensions").value.toString()
                    osText.text = item.child("Build").child("OS").value.toString()
                    sim.text = item.child("Build").child("SIM").value.toString()
                    weightText.text = item.child("Build").child("Weight").value.toString()

                    audio.text = item.child("Features").child("Audio").value.toString()
                    browser.text = item.child("Features").child("Browser").value.toString()
                    extra.text = item.child("Features").child("Extra").value.toString()
                    games.text = item.child("Features").child("Games").value.toString()
                    messaging.text = item.child("Features").child("Messaging").value.toString()
                    sensors.text = item.child("Features").child("Sensors").value.toString()
                    torch.text = item.child("Features").child("Torch").value.toString()

                }
                override fun onCancelled(databaseError: DatabaseError) {}
            })
        }
    }

    fun call_Product(view: View?) {
        finish()
    }
}