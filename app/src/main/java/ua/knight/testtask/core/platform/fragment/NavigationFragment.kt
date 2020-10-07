package ua.knight.testtask.core.platform.fragment

import androidx.navigation.NavDirections

interface NavigationFragment {
    fun openScreen(actionId: Int)
    fun openScreen(navDirections: NavDirections)
}