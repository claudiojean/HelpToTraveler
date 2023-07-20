package com.example.helptotraveler.db

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class HttApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { HelpToTravelerDataBase.getDatabase(this) }
    val repository by lazy { Repository(database.viagemDao(),database.eventoDao()) }
}