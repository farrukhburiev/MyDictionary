package farrukh.mydictionary.screens.add_edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.ictschool.mywords.localDatabse.entity.Word

class AddEditViewModel(val addEditModel: AddEditModel, val id: Int) {

    var word = Word(name = "", translation = "", defination = "")

    init {
        if (id != -1) {
            word = getWordById(id)
        }
    }

    private val _name = MutableLiveData(word.name)
    val name: LiveData<String> = _name

    private val _translation = MutableLiveData(word.translation)
    val translation: LiveData<String> = _translation

    private val _defination = MutableLiveData(word.defination)
    val defination: LiveData<String> = _defination

    fun getButtonText(): String {
        if (id == -1) {
            return "Add new word"
        }
        return "Edit and save"
    }

    fun getTopAppBarText(): String {
        if (id == -1) {
            return "Add word"
        }
        return "Edit word"
    }

    fun getWordById(id: Int): Word {
        return addEditModel.wordDao.getWordById(id)
    }

    fun updateWord(newtext: String) {
        _name.value = newtext
    }

    fun updateTranslate(newtext: String) {
        _translation.value = newtext
    }

    fun updateDefination(newtext: String) {
        _defination.value = newtext
    }

    fun enabledDisabled():Boolean{
        if (_defination.value!!.isNotEmpty() && _translation.value!!.isNotEmpty() && _name.value!!.isNotEmpty()){
            return true
        }
        return false
    }



}