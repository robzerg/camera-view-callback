package eu.uvita.cameratest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.scanbot.sdk.ScanbotSDKInitializer
import kotlinx.android.synthetic.main.activity_main.*
import net.doo.snap.camera.PictureCallback

class MainActivity : AppCompatActivity(), PictureCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ScanbotSDKInitializer()
            .license(application, BuildConfig.SCANBOT_LICENSE_KEY)
            .initialize(application)

        setContentView(R.layout.activity_main)

        cameraView.setCameraOpenCallback {
            cameraView.post {
                cameraView.continuousFocus()
            }
        }
        cameraView.addPictureCallback(this)

        button.setOnClickListener {
            cameraView.takePicture(true)
            //cameraView.post { cameraView.startPreview() }
        }
    }

    public override fun onResume() {
        super.onResume()
        cameraView.onResume()
    }

    public override fun onPause() {
        super.onPause()
        cameraView.onPause()
    }

    override fun onPictureTaken(image: ByteArray?, imageOrientation: Int) {
        Log.i("!!!", "Picture taken!")
    }
}
