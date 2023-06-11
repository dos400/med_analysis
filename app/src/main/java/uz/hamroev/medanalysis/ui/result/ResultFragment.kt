package uz.hamroev.medanalysis.ui.result

import android.annotation.SuppressLint
import android.content.Intent
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

        var ab = ""
        if (Cache.sex == "erkak"){
            ab = resources.getString(R.string.man)
        } else {
            ab = resources.getString(R.string.woman)
        }

        binding.countTv.text = "${activity?.resources!!.getString(R.string.sum_ball)} - ${Cache.countAll}"
        binding.userInfoTv.text = "${activity?.resources!!.getString(R.string.born)}: - ${Cache.birth}\n" +
                "${activity?.resources!!.getString(R.string.sex)}: - ${ab}\n" +
                "${activity?.resources!!.getString(R.string.address)}: - ${Cache.address}"

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
        if (Cache.sex == "erkak"){
            resultEntity.sex = resources.getString(R.string.man)
        } else {
            resultEntity.sex = resources.getString(R.string.woman)
        }
        resultEntity.birth = Cache.birth
        resultEntity.address = Cache.address
        resultEntity.countBall = "${activity?.resources!!.getString(R.string.sum_ball)} ${Cache.countAll}"
        resultEntity.diagnos = diagnosText
        resultEntity.date = getCurrentDateAndTime()

        resultDatabase.resultDao().addResult(resultEntity)


        binding.shareLayout.setOnClickListener {
            var message: String = ""
            var shareMessage: String =
                "https://play.google.com/store/apps/details?id="
            shareMessage = "https://play.google.com/store/apps/details?id=" + activity?.packageName
            val name = "Med - Analysis"
            message = "$name\n\n" +
                    "* * * * * * *\n" +
                    "${activity?.resources!!.getString(R.string.user)}: ${Cache.name}\n" +
                    "${activity?.resources!!.getString(R.string.sex)}: ${ab}\n" +
                    "${activity?.resources!!.getString(R.string.born)}: ${Cache.birth}\n" +
                    "${activity?.resources!!.getString(R.string.address)}: ${Cache.address}\n" +
                    "${activity?.resources!!.getString(R.string.diagnoss)}: ${diagnosText}\n" +
                    "${activity?.resources!!.getString(R.string.time)}: ${getCurrentDateAndTime()}" +
                    "\n" +
                    "\n* * * * * * *\n" +
                    "$shareMessage"

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, message)
            val chooser = Intent.createChooser(intent, "Share using...")
            startActivity(chooser)
        }


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