import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerview.R

class video_Activity : AppCompatActivity() {
    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        val videoId = intent.getIntExtra("videoId", -1)
        videoView = findViewById(R.id.vv_video)

        val videoPath = "android.resource://${packageName}/${videoId}"
        videoView.setVideoURI(Uri.parse(videoPath))

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        videoView.setOnErrorListener { _, _, _ ->
            // Handle errors here
            return@setOnErrorListener true
        }

        videoView.start()
    }

    override fun onPause() {
        super.onPause()
        stopVideo()
    }

    private fun stopVideo() {
        if (videoView.isPlaying) {
            videoView.stopPlayback()
        }
    }
}
