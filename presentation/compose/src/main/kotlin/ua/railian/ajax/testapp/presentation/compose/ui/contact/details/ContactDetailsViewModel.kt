package ua.railian.ajax.testapp.presentation.compose.ui.contact.details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import ua.railian.ajax.testapp.domain.contract.entity.Contact

abstract class ContactDetailsViewModel : ViewModel() {
    abstract val title: Flow<String>
    abstract val contact: Flow<Contact>
    abstract fun onContactChanged(contact: Contact)
    abstract fun saveContactAsync(): Job
    abstract fun deleteContactAsync(): Job
}