package uz.hamroev.medanalysis.ui.result

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.hamroev.medanalysis.R
import uz.hamroev.medanalysis.cache.Cache
import uz.hamroev.medanalysis.database.ResultDatabase
import uz.hamroev.medanalysis.database.ResultEntity
import uz.hamroev.medanalysis.databinding.FragmentResultBinding
import java.text.SimpleDateFormat
import java.util.*

class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    lateinit var resultDatabase: ResultDatabase


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentResultBinding.inflate(layoutInflater)


        resultDatabase = ResultDatabase.getInstance(binding.root.context)

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.nameTv.text = Cache.name
        binding.timeTv.text = getCurrentDateAndTime()

        binding.countTv.text = "${activity?.resources!!.getString(R.string.sum_ball)} - ${Cache.countAll}"

        val sum = Cache.countAll
        var diagnosText = ""
        if ((sum!! <= 16)){
            binding.diagnosResult.text = activity?.resources!!.getString(R.string.tavsifa_past)
            binding.countAboutTv.text = activity?.resources!!.getString(R.string.xavf_past)
            diagnosText = activity?.resources!!.getString(R.string.tavsifa_past)
        } else if (sum in 17..31){
            binding.diagnosResult.text = activity?.resources!!.getString(R.string.tavsifa_orta)
            binding.countAboutTv.text = activity?.resources!!.getString(R.string.xavf_orta)
            diagnosText = activity?.resources!!.getString(R.string.tavsifa_orta)
        } else {
            if (sum >= 32){
                binding.diagnosResult.text = activity?.resources!!.getString(R.string.tavsifa_yuqori)
                binding.countAboutTv.text = activity?.resources!!.getString(R.string.xavf_yuqori)
                diagnosText = activity?.resources!!.getString(R.string.tavsifa_yuqori)
            }
        }

        val resultEntity = ResultEntity()
        resultEntity.fio = Cache.name
        resultEntity.countBall = "${activity?.resources!!.getString(R.string.sum_ball)} ${Cache.countAll}"
        resultEntity.diagnos = diagnosText
        resultEntity.date = getCurrentDateAndTime()

        resultDatabase.resultDao().addResult(resultEntity)




        return binding.root
    }

    private fun getCurrentDateAndTime(): String {
        val dateAndTime: Date = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val date = dateFormat.format(dateAndTime)
        val time = timeFormat.format(dateAndTime)
        val vaqt: String = "$date - $time"
        //Toast.makeText(this, "$vaqt", Toast.LENGTH_SHORT).show()
        return vaqt

    }


}