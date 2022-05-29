package ua.railian.ajax.testapp.presentation.compose.ui.contact.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import ua.railian.ajax.testapp.domain.contract.entity.Contact

abstract class ContactListViewModel : ViewModel() {
    abstract val title: String
    abstract val contacts: Flow<List<Contact>>
    abstract val areContactsRefreshing: StateFlow<Boolean>
    abstract fun refreshContactsAsync(): Job
    abstract fun deleteContactAsync(contactId: Long): Job
}