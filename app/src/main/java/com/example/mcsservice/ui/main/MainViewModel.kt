package com.example.mcsservice.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mcsservice.repository.SubjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import timber.log.Timber

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = SubjectRepository(getApplication<Application>().applicationContext)

    fun getSubjectList() = repo.getSubjectList().catch { Timber.e(it) }
        .asLiveData(Dispatchers.IO + viewModelScope.coroutineContext)
}