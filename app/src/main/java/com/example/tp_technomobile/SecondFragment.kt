package com.example.tp_technomobile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.zxing.integration.android.IntentIntegrator

class SecondFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logOutButton(view)
        openOpenium(view)
        openQrCodeScanner(view)
        openTODoListFragment(view)
    }

    private fun logOutButton(view: View) {
        val button = view.findViewById<Button>(R.id.button2)
        button?.setOnClickListener {
            Snackbar.make(view, "Log out Successfully", Snackbar.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_secondFragment_to_activityFragment)
        }
    }

    private fun openQrCodeScanner(view: View) {
        //Bouton Scan QurCode
        val scan = view.findViewById<Button>(R.id.scanQrCodeButton)
        scan?.setOnClickListener {
            //Première méthode
            val integrator = IntentIntegrator(activity)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
            integrator.setPrompt("Scan a QR code")
            integrator.setOrientationLocked(true)
            integrator.setCameraId(0)  // Utilisation du caméra
            integrator.setBeepEnabled(false)
            integrator.setBarcodeImageEnabled(true)
            integrator.initiateScan()


        }
    }

    private fun openOpenium(view: View) {
        //Afficher la page web de Openium
        val openiumButton = view.findViewById<Button>(R.id.openiumButton)
        openiumButton?.setOnClickListener {
            val url = "https://www.openium.fr"
            val browSerIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browSerIntent);
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(activity, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(activity, "Scanned: ${result.contents}", Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)

        }
    }

    private fun openTODoListFragment(view: View) {
        val button = view.findViewById<Button>(R.id.todoButton)
        button.setOnClickListener {
            findNavController().navigate(R.id.action_secondFragment_to_fragmentListTodo)
        }
    }

}