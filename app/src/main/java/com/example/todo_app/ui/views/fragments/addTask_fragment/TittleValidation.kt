package com.example.todo_app.ui.views.fragments.addTask_fragment

class TittleValidation : Validation() {
    var helperText: String? = null
    override fun valid(text: String): Boolean {
        var isValid = true
        if (text.isBlank()) {
            helperText = "tittle can not be empty"
            return false
        } else {
            helperText = null
        }
        return isValid
    }
}