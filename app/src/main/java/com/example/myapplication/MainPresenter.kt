package com.example.myapplication

class MainPresenter {

    fun getScreen(data: ScreenData) : TestScreen {
        return when(data.accountType) {
            Type.SAVINGS -> TestScreen(
                header = TestHeader.HeaderA(
                    accountName = data.accountName
                ),
                body = TestBody.DefaultTestBody,
                footer = TestFooter.DefaultTestFooter
            )
            Type.CHECKING -> TestScreen(
                header = TestHeader.HeaderB(
                    jobName = data.jobName,
                    income = data.income
                )
            )
        }
    }
}