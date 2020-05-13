package com.xiangxue.googleproject.data.binding;

import android.text.Editable;
import android.text.Selection;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.databinding.BindingAdapter;

public class PasswordAdapter {

    @BindingAdapter(value = {"customChecked", "mUserPasswordEt"}, requireAll = false)
    public static void customChecked(CheckBox mCheckBox, boolean checked, EditText mUserPasswordEt) {
        // compoundButton 代表的是当前  CheckBox    checked 代表当前是否选中
        if(checked){
            // 显示密码
            mUserPasswordEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }else{
            // 隐藏密码
            mUserPasswordEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }

        // 把光标移动到最后
        Editable etext = mUserPasswordEt.getText();
        Selection.setSelection(etext, etext.length());
    }

}
