package com.g3c1.aide.feature_account.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.g3c1.aide.feature_account.presentation.ui.components.AccountButton
import com.g3c1.aide.feature_account.presentation.ui.components.InputField
import com.g3c1.aide.feature_account.presentation.ui.components.OnClickText
import com.g3c1.aide.feature_account.presentation.utils.Utils.checkIdPattern
import com.g3c1.aide.feature_account.presentation.utils.Utils.checkPasswordIsSame
import com.g3c1.aide.feature_account.presentation.utils.Utils.checkPasswordPattern
import com.g3c1.aide.feature_account.presentation.viewmodel.AccountViewModel
import com.g3c1.aide.ui.theme.PretendardText

@Composable
fun SignUpScreen(
    viewModel: AccountViewModel,
    goLoginScreen: () -> Unit,
    goRealNameScreen: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val id = remember {
            mutableStateOf("")
        }
        val password = remember {
            mutableStateOf("")
        }
        val confirmationPassword = remember {
            mutableStateOf("")
        }
        val idProtocol = remember {
            mutableStateOf(false)
        }
        val passwordProtocol = remember {
            mutableStateOf(false)
        }
        val passwordIsSame = remember {
            mutableStateOf(false)
        }
        PretendardText(
            text = "회원가입",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.size(12.dp))
        PretendardText(
            text = "사용하실 아이디와 비밀번호를 \n" +
                    "입력해주세요.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
        Spacer(modifier = Modifier.size(33.dp))
        InputField(
            text = id.value,
            hint = "아이디를 입력해주세요.",
            isError = idProtocol.value,
            onValueChange = {
                id.value = it
                idProtocol.value = checkIdPattern(id.value)
            },
            errorMsg = "아이디는 8글자 이상이여야 합니다."
        )
        InputField(
            text = password.value,
            hint = "비밀번호를 입력해주세요.",
            isError = passwordProtocol.value,
            onValueChange = {
                password.value = it
                passwordProtocol.value = checkPasswordPattern(password.value)
            },
            errorMsg = "비밀번호는 영문,숫자,특수문자포함 8~20글자여야 합니다."
        )
        InputField(
            text = confirmationPassword.value,
            hint = "입력하신 비밀번호를 한번 더 입력해주세요.",
            isError = passwordIsSame.value,
            onValueChange = {
                confirmationPassword.value = it
                passwordIsSame.value =
                    checkPasswordIsSame(password.value, confirmationPassword.value)
            },
            errorMsg = "비밀번호가 일치하지 않습니다."
        )
        Spacer(modifier = Modifier.size(16.dp))
        OnClickText(
            firstText = "기존 회원이신가요? ",
            orangeText = "로그인",
            lastText = "하러가기",
            onClick = goLoginScreen
        )
    }
    AccountButton(
        onClick = goRealNameScreen,
        text = "다음"
    )
}