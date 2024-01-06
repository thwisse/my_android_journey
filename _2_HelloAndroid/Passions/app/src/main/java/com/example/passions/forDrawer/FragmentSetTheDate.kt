package com.example.passions.forDrawer

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import com.example.passions.R
import com.example.passions.databinding.FragmentSetTheDateBinding
import java.util.Calendar

class FragmentSetTheDate : Fragment() {

    private lateinit var binding: FragmentSetTheDateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSetTheDateBinding.inflate(inflater,container, false)

        // bir saat ve bir tarih bilgisi alacagiz.
        // editTextlere bu degerleri girecekler. bu yuzden edittext'e tiklandigi an timepicker ve
        // datePicker'lar calismali. bunu ayarlayacagiz.

        // timePicker

        binding.editTextTimeInput.setOnClickListener {
            // picker calistiginda o andaki saati ve dakikayi picker'a aktararak kullanicinin
            // isini kolaylastirmak amacli sunlari yaziyoruz:
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY) // 24 saatlik dilime gore
            // HOUR = 12 saatlik dilim icin
            val minute = calendar.get(Calendar.MINUTE)

            val time_picker = TimePickerDialog(
                context, TimePickerDialog.OnTimeSetListener{ view: TimePicker?, hourOfDay: Int, minute: Int ->
                //TODO hoca burada context icin this kullaniyor ama bende o hata veriyor. neden? ben context kullaniyorum.

                if (minute < 10) {
                    binding.editTextTimeInput.setText("$hourOfDay:0$minute")
                }
                else {
                    binding.editTextTimeInput.setText("$hourOfDay:$minute")
                }

            }, hour, minute, true) //TODO false yapsam 12 saatlik dilime gore olur? tam olmuyor. neden?
            // o anin saatini ve dakikasini timepicker icine aktardik.

            // simdi timePicker'in basligini girecegiz. bazi surumlerde bu gozukmuyor ama bazilarinda
            // gozukuyormus. o sebeple gozukurse istedigimiz sey gozuksun diye yaziyoruz.
            //time_picker.setTitle("Saati belirle")
            // cirkin gozuktugu icin iptal ettim.

            // timePicker uzerindeki butonlari ayarlayalim
            time_picker.setButton(DialogInterface.BUTTON_POSITIVE, "Ayarla", time_picker)
            time_picker.setButton(DialogInterface.BUTTON_NEGATIVE, "Iptal et", time_picker)

            // o kadar sey yaptik. su show'u yazmasan hicbirinin onemi yok.
            time_picker.show()
        }

        // datePicker

        binding.editTextDateInput.setOnClickListener {
            // yine saat gibi burada da o gunun tarihini kolaylik olmasi acisindan datePicker'a
            // yansitacagiz. kodlar:
            val calendar = Calendar.getInstance()

            val yeaR = calendar.get(Calendar.YEAR)
            val montH = calendar.get(Calendar.MONTH)
            val daY = calendar.get(Calendar.DAY_OF_MONTH)

            //TODO burada context bile kullanamadim. yukarda kullanabilmistim. requireContext() kullandim
            // tamam calisti ama neden bu context mezvusu fragmentlarda sikinti oluyor anlamadim.
            val date_picker = DatePickerDialog(
                requireContext(), DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
                binding.editTextDateInput.setText("$dayOfMonth/${month+1}/$year")
                // gariptir ay'i -1 veriyor. ocaktayken 0 yazdi mesela. subat olsa 1 yazacak.
                //TODO  o yuzden +1 ekledim. neden bilmiyorum
            }, yeaR, montH, daY)

            // datePicker uzerindeki butonlar
            date_picker.setButton(DialogInterface.BUTTON_POSITIVE, "Ayarla", date_picker)
            date_picker.setButton(DialogInterface.BUTTON_NEGATIVE, "Iptal et", date_picker)
            // ve son nokta:
            date_picker.show()
        }

        // her ikisinde de editText'e bastigimda ilk once klavye aciliyor, ondan sonra bir daha
        // basinda picker aciliyordu. bunu duzeltmek icin xml code'una gidiyoruz.
        // her iki edittextin xml koduna da
        //android:focusableInTouchMode="false"
        // seklinde ozellik girdim. bu sekilde tiklayinca direkt pickerlar acilacak.

        return binding.root
    }
}