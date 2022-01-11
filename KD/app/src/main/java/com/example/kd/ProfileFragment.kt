package com.example.kd

import android.os.Bundle
import com.example.kd.ProfileFragment
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.kd.R
import android.widget.LinearLayout
import android.content.Intent
import android.view.View
import com.example.kd.Feedback
import com.example.kd.ResetPassword
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.kd.Login
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = requireArguments().getString(ARG_PARAM1)
            mParam2 = requireArguments().getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val callHelp = view.findViewById<View>(R.id.call_Help) as LinearLayout
        callHelp.setOnClickListener {
            val intent = Intent(activity, Help::class.java)
            startActivity(intent)
        }
        val callFeedback = view.findViewById<View>(R.id.call_Feedback) as LinearLayout
        callFeedback.setOnClickListener {
            val intent = Intent(activity, Feedback::class.java)
            startActivity(intent)
        }
        val callResetPassword = view.findViewById<View>(R.id.call_Reset_Password) as LinearLayout
        callResetPassword.setOnClickListener {
            val intent = Intent(activity, ResetPassword::class.java)
            startActivity(intent)
        }
        val callLogin = view.findViewById<View>(R.id.logout) as AppCompatButton
        callLogin.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(activity, Login::class.java)
            startActivity(intent)
        }
        return view
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): ProfileFragment {
            val fragment = ProfileFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}