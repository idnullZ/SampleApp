package kz.zunun.myapplication

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class SecondFragment : Fragment(R.layout.fragment) {

    private val title by lazy {
        requireArguments().getString("key")!!
    }

    companion object {
        fun newInstance(string: String) = SecondFragment().apply {
            arguments = bundleOf("key" to string)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<TextView>(R.id.text).text = title
    }


}