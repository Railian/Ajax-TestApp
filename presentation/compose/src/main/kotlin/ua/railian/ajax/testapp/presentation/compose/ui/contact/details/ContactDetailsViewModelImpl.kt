package ua.railian.ajax.testapp.presentation.compose.ui.contact.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ua.railian.ajax.testapp.domain.contract.entity.Contact
import ua.railian.ajax.testapp.domain.contract.extension.fullName
import ua.railian.ajax.testapp.domain.contract.repository.ContactRepository
import javax.inject.Inject

@HiltViewModel
class ContactDetailsViewModelImpl @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val contactRepository: ContactRepository
) : ContactDetailsViewModel() {

    private val contactId: Long = requireNotNull(savedStateHandle["id"])

    override val title: Flow<String>
        get() = contact.map { it.fullName }

    private val _contact = MutableStateFlow<Contact?>(null)
    override val contact: Flow<Contact> = contactRepository
        .contact(contactId)
        .flatMapLatest { _contact.onStart { emit(it) }.filterNotNull() }

    override fun onContactChanged(contact: Contact) {
        _contact.value = contact
    }

    override fun saveContactAsync(): Job = viewModelScope.launch {
        _contact.value?.let { contactRepository.updateContacts(it) }
    }

    override fun deleteContactAsync(): Job = viewModelScope.launch {
        contactRepository.deleteContacts(contactId)
    }
}