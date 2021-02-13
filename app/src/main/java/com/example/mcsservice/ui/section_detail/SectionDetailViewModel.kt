package com.example.mcsservice.ui.section_detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mcsservice.model.database.DbSection
import com.example.mcsservice.repository.SubjectRepository
import com.example.mcsservice.ui.section_detail.validator.Validator
import com.example.mcsservice.util.AES
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class SectionDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = SubjectRepository(getApplication<Application>().applicationContext)

    fun getSectionDetailList(sectionId: Int) =
        repo.getSectionDetailList(sectionId).catch { Timber.e(it) }
            .asLiveData(Dispatchers.IO + viewModelScope.coroutineContext)

    fun getSection(sectionId: Int) = getSectionFlow(sectionId)
        .asLiveData(Dispatchers.IO + viewModelScope.coroutineContext)

    fun updateSectionPass(section: DbSection, pass: String) {

        val updatedSection = section.copy(
            sectionPass = pass
        )
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repo.updateSection(updatedSection)
            }
        }
    }


    //TODO update section password when decryption password is true
    fun decryptSection(section: DbSection, validator: Validator) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                var decryptCounter = 0
                getEncryptedTasks(section.id).collect {
                    it.forEach { dbTask ->
                        val encryptedDesc = AES.decrypt(dbTask.description, section.sectionPass)

                        //TODO find why validator gives false sometimes !!!!!!!!!
                        if (encryptedDesc.isNotEmpty() && validator.isValid(encryptedDesc)) {
                            val updateTask =
                                dbTask.copy(
                                    description = encryptedDesc,
                                    isDescriptionDecrypted = true
                                )
                            repo.updateTask(updateTask)
                            decryptCounter++
                            Timber.i("Success decrypting: ${dbTask.name}")
                        } else {
                            Timber.e("Fail decrypting: ${dbTask.name} with pass ${section.sectionPass}")
                            updateSectionPass(section, "")
                        }
                    }

                    Timber.i("$decryptCounter / ${it.size} task decrypted")
                }
            }
        }
    }

    private fun getEncryptedTasks(sectionId: Int) =
        repo.getEncryptedTasks(sectionId).catch { Timber.e(it) }

    private fun getSectionFlow(sectionId: Int) =
        repo.getSectionById(sectionId).catch { Timber.e(it) }

}