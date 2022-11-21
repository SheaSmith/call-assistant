package nz.shea.callassistant

import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.telecom.CallRedirectionService
import android.telecom.PhoneAccountHandle

class GoogleAssistantService :  CallRedirectionService() {
    override fun onPlaceCall(
        handle: Uri,
        initialPhoneAccount: PhoneAccountHandle,
        allowInteractiveResponse: Boolean
    ) {
        if (handle.toString().equals("tel:25555158", true)) {
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this@GoogleAssistantService, StartGoogleAssistantActivity::class.java);
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                cancelCall()
            }, 2000)

//            Handler(Looper.getMainLooper()).postDelayed({
//            }, 2000)
        }
        else {
            placeCallUnmodified()
        }
    }

}