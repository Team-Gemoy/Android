package com.synrgy.wefly.common

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

/**
 * Created by Yosua on 18/01/2024
 */
object UiUtils {

    fun TextInputEditText.getTextInputLayout() = this.parent.parent as TextInputLayout

}