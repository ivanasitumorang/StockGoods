package com.azuka.stockgoods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController


/**
 * Created by ivanaazuka on 06/03/21.
 * Android Engineer
 */

abstract class BaseFragment : Fragment() {

    protected lateinit var navController: NavController

    abstract val viewLayout: Int

    abstract fun onFragmentReady(view: View, savedInstanceState: Bundle?)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(viewLayout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        onFragmentReady(view, savedInstanceState)
    }
}