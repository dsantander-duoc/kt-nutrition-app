package com.example.mobiledevelopment.accessibility

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import java.util.*

// TTS is intended to be used to read texts for the users
@Composable
fun rememberTTS(context: Context): TextToSpeech {
    var ready by remember { mutableStateOf(false) }

    val tts = remember {
        TextToSpeech(context) { status ->
            ready = (status == TextToSpeech.SUCCESS)
        }
    }

    LaunchedEffect(ready) {
        if (ready) {
            val cl = Locale("es", "CL")
            val es = Locale("es", "ES")
            val avail = tts.isLanguageAvailable(cl)
            tts.language = if (avail >= TextToSpeech.LANG_AVAILABLE) cl else es
            tts.setSpeechRate(1.0f)
        }
    }

    DisposableEffect(tts) {
        onDispose {
            tts.stop()
            tts.shutdown()
        }
    }
    return tts
}

fun TextToSpeech.speakPolite(text: String) {
    if (text.isNotBlank()) {
        speak(text, TextToSpeech.QUEUE_FLUSH, null, "nutrigo-utterance")
    }
}
