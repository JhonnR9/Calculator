package me.jhones.calculator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import me.jhones.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _mainViewModel: MainViewModel? = null
    private var _binding: ActivityMainBinding? = null

    private val binding get() = _binding!!
    private val mainViewModel get() = _mainViewModel!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        _mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        enter()
        observe()



        binding.btnAdd.setOnClickListener {
            mainViewModel.addOperator(Operation.PLUS)
        }
        binding.btnDivide.setOnClickListener {
            mainViewModel.addOperator(Operation.DIVISION)
        }
        binding.btnSubtract.setOnClickListener {
            mainViewModel.addOperator(Operation.MINUS)
        }
        binding.btnMultiply.setOnClickListener {
            mainViewModel.addOperator(Operation.MULTIPLICATION)
        }
        binding.btnClear.setOnClickListener {
            mainViewModel.clear()
        }
        binding.btnBack.setOnClickListener {
            mainViewModel.backspace()
        }

        binding.btnEquals.setOnClickListener {
            mainViewModel.equals()
            binding.txtInput.textSize = 25f
            binding.txtSolution.textSize = 35f

        }

        setContentView(binding.root)
    }

    private fun observe() {
        mainViewModel.enteredValues.observe(this) {
            binding.txtInput.textSize = 35f
            binding.txtSolution.textSize = 25f
            binding.txtInput.text = it
        }
        mainViewModel.result.observe(this) {
            binding.txtSolution.text = it
        }
    }

    private fun enter() {
        binding.btnZero.setOnClickListener() {
            mainViewModel.enter(0f)
        }
        binding.btnOne.setOnClickListener {
            mainViewModel.enter(1f)
        }
        binding.btnTwo.setOnClickListener {
            mainViewModel.enter(2f)
        }
        binding.btnThree.setOnClickListener {
            mainViewModel.enter(3f)
        }
        binding.btnFour.setOnClickListener {
            mainViewModel.enter(4f)
        }
        binding.btnFive.setOnClickListener {
            mainViewModel.enter(5f)
        }
        binding.btnSix.setOnClickListener() {
            mainViewModel.enter(6f)
        }
        binding.btnSeven.setOnClickListener {
            mainViewModel.enter(7f)
        }
        binding.btnEight.setOnClickListener {
            mainViewModel.enter(8f)
        }
        binding.btnNine.setOnClickListener {
            mainViewModel.enter(9f)
        }
        binding.btnDecimal.setOnClickListener {
             mainViewModel.enter(".")
        }
    }

    override fun onDestroy() {
        _mainViewModel = null
        _binding = null
        super.onDestroy()
    }
}