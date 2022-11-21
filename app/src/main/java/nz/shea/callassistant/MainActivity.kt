package nz.shea.callassistant

import android.app.role.RoleManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!isRedirection())
            roleAcquire(RoleManager.ROLE_CALL_REDIRECTION)
    }

    private fun isRedirection(): Boolean {
        return isRoleHeldByApp(RoleManager.ROLE_CALL_REDIRECTION)
    }

    private fun isRoleHeldByApp(roleName: String): Boolean {
        val roleManager: RoleManager? = getSystemService(RoleManager::class.java)
        return roleManager!!.isRoleHeld(roleName)

    }

    private fun roleAcquire(roleName: String) {
        val roleManager: RoleManager?
        if (roleAvailable(roleName)) {
            roleManager = getSystemService(RoleManager::class.java)
            val intent = roleManager.createRequestRoleIntent(roleName)
            startActivityForResult(intent, 1)
        } else {
            Toast.makeText(
                this,
                "Redirection call with role in not available",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun roleAvailable(roleName: String): Boolean {
        val roleManager: RoleManager? = getSystemService(RoleManager::class.java)
        return roleManager!!.isRoleAvailable(roleName)
    }
}