package com.example.mytictactoe.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.mytictactoe.R

fun FragmentTransaction.tictactoeNav(
        fragment: Fragment,
        tag: String,
        backStackTag: String?
): FragmentTransaction {
    backStackTag?.apply {
        this@tictactoeNav.replace(R.id.fragmentContainer, fragment, tag)
                .addToBackStack(this)
    } ?: run {
        this@tictactoeNav.replace(R.id.fragmentContainer, fragment, tag)
    }
    return this
}