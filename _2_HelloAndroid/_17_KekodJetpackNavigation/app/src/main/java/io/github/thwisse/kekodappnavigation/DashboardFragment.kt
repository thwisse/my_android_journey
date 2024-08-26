package io.github.thwisse.kekodappnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class DashboardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnOpenProfile = view.findViewById<Button>(R.id.btnOpenProfile)
        val btnOpenSettingsDialog = view.findViewById<Button>(R.id.btnOpenSettingsDialog)
        val btnOpenSecondActivity = view.findViewById<Button>(R.id.btnOpenSecondActivity)

        btnOpenProfile.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_profileFragment)
            // burada navigate icine action id yerine
            // generate edilmis class hali ya da gidilen fragmentin fragment id'si de yazilabilir.
            //findNavController().navigate(R.id.profileFragment)
            // programmaticly olarak graph eklemen gerektiginde action tanimlayamayacagin icin
            // navigasyon icin fragment id'si kullanman gerekir.
            // ancak bu mevcut kullanim yani action id kullanimi daha verimli.
            // route'lar falan da varmis da onlari daha sonra gorecekmisiz.
        }

        btnOpenSettingsDialog.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_settingsDialogFragment)
        }

        btnOpenSecondActivity.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_secondActivity)
        }
    }
}