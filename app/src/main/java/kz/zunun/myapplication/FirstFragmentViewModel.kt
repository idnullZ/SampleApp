package kz.zunun.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

sealed class State {
    data class Succes(val data: List<RespondItem>) : State()
    data class Error(val error: Throwable) : State()
    object Loading : State()
}

class FirstFragmentViewModel : ViewModel() {

    private val api = createApi()

    val data = MutableStateFlow<State>(State.Loading)

    init {
        fetchData()
    }


    fun fetchData() {
        viewModelScope.launch {
            try {
                val result = api.getAll()
                data.emit(State.Succes(result))
            } catch (e: Exception) {
                data.emit(State.Error(e))
            }
        }
    }
}