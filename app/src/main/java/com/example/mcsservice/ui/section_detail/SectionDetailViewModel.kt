package com.example.mcsservice.ui.section_detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mcsservice.repository.SubjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import timber.log.Timber

class SectionDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = SubjectRepository(getApplication<Application>().applicationContext)

    fun getSectionDetailList(sectionId: Int) =
        repo.getSectionDetailList(sectionId).catch { Timber.e(it) }
            .asLiveData(Dispatchers.IO + viewModelScope.coroutineContext)
}