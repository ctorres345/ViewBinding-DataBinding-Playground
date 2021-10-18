package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentMainTestBinding
import com.example.myapplication.databinding.ViewABinding
import com.example.myapplication.databinding.ViewBBinding

class MainTestFragment : Fragment() {
    private lateinit var binding: FragmentMainTestBinding
    private val presenter = MainPresenter()
    private val testData = ScreenData(
        accountName = "John Doe",
        accountType = Type.SAVINGS,
        income = 100,
        jobName = "Software Engineer"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainTestBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initScreen(presenter.getScreen(testData))
    }

    private fun initScreen(screen: TestScreen) {
        screen.header?.let {
            initHeader(it)
        }
        screen.body?.let {
            initBody(it)
        }
        screen.footer?.let {
            initFooter(it)
        }
    }

    private fun initFooter(footer: TestFooter) {
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f)
        val testStub = ViewStub(requireContext(), R.layout.view_d)
        testStub.layoutParams = params
        binding.root.addView(testStub)
        testStub.inflate()
    }

    private fun initBody(body: TestBody) {
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f)
        val testStub = ViewStub(requireContext(), R.layout.view_c)
        testStub.layoutParams = params
        binding.root.addView(testStub)
        testStub.inflate()
    }

    private fun initHeader(header: TestHeader) {
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f)
        val testStub = when(header){
            is TestHeader.HeaderA -> {
                ViewStub(requireContext(), R.layout.view_a).apply {
                    setOnInflateListener { stub, inflated ->
                        run {
                            DataBindingUtil.bind<ViewABinding>(inflated)?.let {
                                it.data = header
                            }
                        }
                    }
                }
            }
            is TestHeader.HeaderB -> {
                ViewStub(requireContext(), R.layout.view_b).apply {
                    setOnInflateListener { stub, inflated ->
                        run {
                            DataBindingUtil.bind<ViewBBinding>(inflated)?.let {
                                it.data = header
                            }
                        }
                    }
                }
            }
        }
        testStub.layoutParams = params
        binding.root.addView(testStub)
        testStub.inflate()
    }
}