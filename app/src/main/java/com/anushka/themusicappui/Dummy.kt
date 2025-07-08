package com.anushka.themusicappui

import androidx.annotation.DrawableRes
import org.junit.experimental.categories.Category

data class Lib(@DrawableRes val icon:Int,val name:String)

val libraries=listOf<Lib>(
    Lib(R.drawable.baseline_playlist_add_24,"Playlist"),
    Lib(R.drawable._318047_audio_conference_mic_microphone_record_icon,"Artists"),
    Lib(R.drawable.baseline_album_24,"Album"),
    Lib(R.drawable.baseline_music_note_24,"Songs"),
    Lib(R.drawable.baseline_tune_24,"Genre")
)



