package com.devmasterteam.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devmasterteam.tasks.service.constants.TaskConstants
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.PersonModel
import com.devmasterteam.tasks.service.model.ValidationModel
import com.devmasterteam.tasks.service.repository.PersonRepository
import com.devmasterteam.tasks.service.repository.SecurityPreferences
import com.devmasterteam.tasks.service.repository.remote.PersonService
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val personRepository = PersonRepository(application)
    private val securityPreferences = SecurityPreferences(application.applicationContext)

    private val _login = MutableLiveData<ValidationModel>()
    val login: LiveData<ValidationModel> = _login

    private val _loggedUser = MutableLiveData<Boolean>()
    val loggedUser: LiveData<Boolean> = _loggedUser


    fun doLogin(email: String, password: String) {
        personRepository.login(email,password, object : APIListener<PersonModel>{
            override fun onSucess(model: PersonModel) {

                securityPreferences.store(TaskConstants.SHARED.PERSON_KEY, model.personKey )
                securityPreferences.store(TaskConstants.SHARED.TOKEN_KEY,model.token)
                securityPreferences.store(TaskConstants.SHARED.PERSON_NAME,model.name)

                RetrofitClient.addHeaders(model.token,model.personKey)

                _login.value = ValidationModel()

            }

            override fun onFailue(message: String) {
                _login.value = ValidationModel(message)

            }

        })
    }

    /**
     * Verifica se usuário está logado
     */
    fun verifyLoggedUser() {
        val token = securityPreferences.get(TaskConstants.SHARED.TOKEN_KEY)
        val personKey = securityPreferences.get(TaskConstants.SHARED.PERSON_KEY)

        _loggedUser.value = (!token.isNullOrEmpty() && !personKey.isNullOrEmpty())


    }

}