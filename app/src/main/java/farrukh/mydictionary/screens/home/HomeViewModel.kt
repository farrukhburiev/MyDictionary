package farrukh.mydictionary.screens.home

import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import farrukh.mydictionary.screens.add_edit.AddEditModel
import uz.ictschool.mywords.localDatabse.entity.Word

class HomeViewModel(val homeModel: HomeModel, val addEditModel: AddEditModel) {

    private var _list: MutableLiveData<List<Word>> = MutableLiveData(mutableListOf())
    var list: LiveData<List<Word>> = _list

    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    private var _state: MutableLiveData<Boolean> = MutableLiveData(false)
    var state: LiveData<Boolean> = _state

    var searchList = mutableListOf<Word>()


    fun filterByName():List<Word>{
       _list.value = _list.value!!.sortedBy { it.name }
        _state.value = true
        return _list.value!!
    }

    fun filterById():List<Word>{
        _list.value = _list.value!!.sortedBy { it.id }
        return _list.value!!
    }





    fun loadList() {
        _list.value = homeModel.getAllWords()
    }

    fun delete(word: Word) {
        addEditModel.deleteWord(word)
        loadList()
    }

    fun searchText(newtext: String) {
        _name.value = newtext
    }

    fun filterSearch():MutableList<Word>{
        searchList.clear()
        for (i in _list.value!!){
            if (i.name.trim().toLowerCase().contains(_name.value!!.trim().toLowerCase())){
                searchList.add(i)
            }
        }
        if (searchList.isEmpty()){
            searchList = (_list.value as MutableList<Word>?)!!
        }
        return searchList
    }

}