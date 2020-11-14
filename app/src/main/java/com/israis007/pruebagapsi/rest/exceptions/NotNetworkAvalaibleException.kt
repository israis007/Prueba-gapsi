package com.israis007.pruebagapsi.rest.exceptions

import java.lang.RuntimeException

class NotNetworkAvalaibleException(private val errorMessage: String = "Not network available") : RuntimeException(errorMessage)