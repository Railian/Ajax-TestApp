package ua.railian.ajax.testapp.domain.contract.datasource

import ua.railian.ajax.testapp.domain.contract.entity.Contact

interface RemoteContactDataSource {

    suspend fun getContacts(
        page: Int = 1,
        limit: Int = 30
    ): List<Contact>
}