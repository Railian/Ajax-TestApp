package ua.railian.ajax.testapp.presentation.compose.ui.contact.list

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ua.railian.ajax.testapp.domain.contract.entity.Contact
import ua.railian.ajax.testapp.domain.contract.repository.ContactRepository
import ua.railian.ajax.testapp.presentation.compose.ui.contact.list.ContactListViewModel
//import kotlinx.coroutines.flow.*
//import ua.railian.ajax.testapp.domain.contract.entity.Post
//import ua.railian.ajax.testapp.domain.contract.entity.User
//import ua.railian.ajax.testapp.domain.contract.repository.PostRepository
//import ua.railian.ajax.testapp.domain.contract.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class ContactListViewModelImpl @Inject constructor(
    private val contactRepository: ContactRepository
) : ContactListViewModel() {

    override val title: String
        get() = "Ajax Test Application"

    override val contacts: Flow<List<Contact>>
        get() = contactRepository.contacts

    private val _areContactsRefreshing = MutableStateFlow(false)
    override val areContactsRefreshing: StateFlow<Boolean> = _areContactsRefreshing

    override fun refreshContactsAsync(): Job = viewModelScope.launch {
        try {
            _areContactsRefreshing.value = true
            contactRepository.refreshContacts()
        } finally {
            _areContactsRefreshing.value = false
        }
    }

    override fun deleteContactAsync(contactId: Long): Job = viewModelScope.launch {
        contactRepository.deleteContacts(contactId)
    }
}