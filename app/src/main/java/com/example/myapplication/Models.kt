package com.example.myapplication

data class ScreenData(
    val accountName: String,
    val accountType: Type,
    val income: Int,
    val jobName: String
)

enum class Type {
    SAVINGS,CHECKING
}

data class TestScreen(
    val header: TestHeader? = null,
    val body: TestBody? = null,
    val footer: TestFooter? = null
)

sealed class TestHeader {
    data class HeaderA(
        val accountName: String
    ) : TestHeader()
    data class HeaderB(
        val jobName: String,
        val income: Int
    ) : TestHeader()
}

sealed class TestBody {
    object DefaultTestBody : TestBody()
}

sealed class TestFooter {
    object DefaultTestFooter : TestFooter()
}