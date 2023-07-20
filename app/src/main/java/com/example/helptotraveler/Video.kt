package com.example.helptotraveler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_video.*

class Video : AppCompatActivity() {
    private var mediaController: MediaController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        startVideo()
    }
    //Função responsável por definir e começar o video
    private fun startVideo(){
        videoView.setVideoPath("https://r2---sn-bg0ezn7y.googlevideo.com/videoplayback?expire=1623807828&ei=9ALJYJTsFITx0wWj2p-YDQ&ip=198.0.198.132&id=o-AI-wcG7wMHwsyVrYAljVhW2nq03_JOf6t-pkZsirHwn1&itag=18&source=youtube&requiressl=yes&vprv=1&mime=video%2Fmp4&ns=LHvLVnUjeBiprtoa6nRh1osF&gir=yes&clen=170656&ratebypass=yes&dur=3.691&lmt=1576691039201302&fexp=24001373,24007246&c=WEB&txp=1311222&n=Un3dEUZOI9r1GG&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRAIgGO7Sa7EwoW3JZT6WRBT-Augzwu3pIi3GM2PlMMK9xRECIFMgYg-ix3OGagaLE58lLhQJUeq1uVhv2WtSNwCF_F1Q&rm=sn-n4ves7l&req_id=1c16eaa65c23a3ee&cm2rm=sn-gjcg-btoe7l,sn-pmcg-bg067l&redirect_counter=3&cms_redirect=yes&ipbypass=yes&mh=nn&mip=143.208.72.9&mm=30&mn=sn-bg0ezn7y&ms=nxu&mt=1623785545&mv=m&mvi=2&pl=23&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRQIgTMfQpmWtZPYIoSTOdNSG-JFGM5sGWBjGnu19m6qa1JYCIQD1Q0w4lCdiwXYikxhnGS_wmv8u-shzT3U9FwsM9sUOKg%3D%3D")
        mediaController = MediaController(this)
        mediaController?.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.start()
    }
}