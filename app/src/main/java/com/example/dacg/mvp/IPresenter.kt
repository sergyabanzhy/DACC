package com.example.dacg.mvp

interface IPresenter<V> {
    fun attach(view: V)
    fun detach()
}