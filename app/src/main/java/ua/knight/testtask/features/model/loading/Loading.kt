package ua.knight.testtask.features.model.loading

import androidx.databinding.ObservableField

class Loading(initialState : State = State.LOADING) {
    val state = ObservableField(initialState)
}