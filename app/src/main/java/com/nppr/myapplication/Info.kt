package com.nppr.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager

class Info : Fragment(R.layout.fragment_info) {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString("key")

        }
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val name:TextView=itemView.findViewById(R.id.name)
        val info:TextView=itemView.findViewById(R.id.info)
        val range:TextView=itemView.findViewById(R.id.range)
        val image:ImageView=itemView.findViewById(R.id.img)
        val backk:TextView=itemView.findViewById(R.id.back)

        backk.setOnClickListener {

            val destinationFragment = RealFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_home, destinationFragment).disallowAddToBackStack().commit()
                //.addToBackStack(null) // If you want to add the fragment to the back stack
 //               .commit()

        }
        name.text=param1.toString()
        if(param1=="O3"){
            name.text= "OZONE (O3)"
            info.text="Ozone is formed when heat and sunlight cause chemical reactions between oxides of nitrogen (NOX ) and Volatile Organic Compounds (VOC). Breathing ground-level ozone can trigger a variety of health problems including chest pain, coughing, throat irritation, and congestion."
            range.text="Suitable Range: 0-150"
            image.setImageResource(R.drawable.o3)
        }
        else if(param1=="PM10"){
            name.text= "PARTICULATE MATTER 10 (PM10)"
            info.text="PM10 describes inhalable particles, with diameters that are generally 10 micrometers and smaller. Exposure to high concentrations of PM10 can result in a number of health impacts ranging from coughing to asthma attacks and bronchitis to high BP, heart attack, strokes and death."
            range.text="Suitable Range: 0-40"
            image.setImageResource(R.drawable.pm10)

        }
        else if(param1=="PM25"){

            name.text= "PARTICULATE MATTER 2.5 (PM2.5)"
            info.text="PM2.5 describes inhalable particles, with diameters that are generally 2.5 micrometers and smaller. Breathing in unhealthy levels of PM2.5 can increase the risk of health problems like heart disease, asthma, and low birth weight. Unhealthy levels can also reduce visibility and cause the air to appear hazy."
            range.text="Suitable Range: 0-12.5"
            image.setImageResource(R.drawable.pm10)

        }
        else if(param1=="CO"){

            name.text= "CARBON MONOXIDE (CO)"

            info.text="The greatest sources of CO to outdoor air are cars, trucks and other vehicles or machinery that burn fossil fuels. The most common symptoms of CO poisoning are headache, dizziness, weakness, chest pain, and confusion. CO symptoms are often described as “flu-like.” If you breathe in a lot of CO it might kill you."
            range.text="Suitable Range: 0-9"
            image.setImageResource(R.drawable.co)
        }
        else if(param1=="SO2"){

            name.text= "SULPHUR DIOXIDE (SO2)"
            info.text="SO2  a colorless gas or liquid with a strong, choking odor. It is produced from the burning of fossil fuels. Sulphur dioxide can cause respiratory problems such as bronchitis, and can irritate your nose, throat and lungs. It may cause coughing, wheezing, phlegm and asthma attacks."
            range.text="Suitable Range: 0-15"
            image.setImageResource(R.drawable.so2)
        }
        else if(param1=="NO2"){

            name.text= "NITROGEN DIOXIDE (NO2)"

            info.text="The main source of nitrogen dioxide resulting from human activities is the combustion of fossil fuels (coal, gas and oil) especially fuel used in cars. Longer exposures to elevated concentrations of NO2 may contribute to the development of asthma and potentially increase susceptibility to respiratory infections."
            range.text="Suitable Range: 0-1.8"
            image.setImageResource(R.drawable.no2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }
}