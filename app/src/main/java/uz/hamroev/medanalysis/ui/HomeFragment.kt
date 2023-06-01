package uz.hamroev.medanalysis.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.hamroev.imagebywavelet.utils.toast
import uz.hamroev.medanalysis.R
import uz.hamroev.medanalysis.adapters.NavAdapter
import uz.hamroev.medanalysis.cache.Cache
import uz.hamroev.medanalysis.databinding.DialogLanguageBinding
import uz.hamroev.medanalysis.databinding.FragmentHomeBinding
import uz.hamroev.medanalysis.model.Nav
import java.util.*

class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding

    var question1 = 0
    var question2 = 0
    var question3 = 0
    var question4 = 0
    var question5 = 0
    var question6 = 0
    var question7 = 0
    var question8 = 0
    var question9 = 0
    var question10 = 0
    var question11 = 0
    var question12 = 0
    var question13 = 0
    var question14 = 0
    var question15 = 0
    var question16 = 0
    var question17 = 0
    var question18 = 0
    var question19 = 0
    var question20 = 0
    var question21 = 0

    //nav menu list and navAdapter to down
    private lateinit var listNav: ArrayList<Nav>
    private lateinit var navAdapter: NavAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)



        binding.menuButton.setOnClickListener {
            binding.drawerLayout.open()
        }


        solve()




        loadNav()
        navAdapter = NavAdapter(requireContext(), listNav, object : NavAdapter.OnNavClickListener {
            override fun onCLick(nav: Nav, position: Int) {
                when (position) {
                    0 -> {
                        binding.drawerLayout.close()
                    }
                    1 -> {
                        binding.drawerLayout.close()
                        findNavController().navigate(R.id.savedFragment)
                    }
                    2 -> {
                        binding.drawerLayout.close()
                        findNavController().navigate(R.id.aboutAppFragment)
                    }
                    3 -> {
                        binding.drawerLayout.closeDrawers()
                        val alertDialog = android.app.AlertDialog.Builder(binding.root.context)
                        val dialog = alertDialog.create()
                        val bindingLanguage =
                            DialogLanguageBinding.inflate(LayoutInflater.from(requireContext()))
                        dialog.setView(bindingLanguage.root)
                        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                        dialog.setCancelable(true)


                        bindingLanguage.russian.setOnClickListener {
                            Cache.language = "ru"
                            setAppLocale(requireContext(), "ru")
                            findNavController().popBackStack()
                            findNavController().navigate(R.id.homeFragment)
                            dialog.dismiss()
                        }
//                        bindingLanguage.uk.setOnClickListener {
//                            Cache.language = "en"
//                            setAppLocale(requireContext(), "en")
//                            findNavController().popBackStack()
//                            findNavController().navigate(R.id.homeFragment)
//                            dialog.dismiss()
//                        }
                        bindingLanguage.uzb.setOnClickListener {
                            Cache.language = "uz"
                            setAppLocale(requireContext(), "en")
                            findNavController().popBackStack()
                            findNavController().navigate(R.id.homeFragment)
                            dialog.dismiss()
                        }


                        dialog.show()


                    }
                    4 -> {
                        binding.drawerLayout.close()
                        findNavController().navigate(R.id.authorsFragment)
                    }
                    5 -> {
                        try {
                            val intent = Intent(Intent.ACTION_SEND)
                            intent.type = "text/plain"
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Med Analysis")
                            val shareMessage =
                                "https://play.google.com/store/apps/details?id=${activity!!.packageName}"
                            intent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                            startActivity(Intent.createChooser(intent, "share by iAndroid.uz"))
                        } catch (e: Exception) {
                        }
                        binding.drawerLayout.close()
                    }
                    6 -> {
                        try {
                            val uri: Uri =
                                Uri.parse("market://details?id=${activity!!.packageName}")
                            val intent = Intent(Intent.ACTION_VIEW, uri)
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        } catch (e: ActivityNotFoundException) {
                            val uri: Uri =
                                Uri.parse("http://play.google.com/store/apps/details?id=${activity!!.packageName}")
                            val intent = Intent(Intent.ACTION_VIEW, uri)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                        }
                        binding.drawerLayout.close()
                    }
                    7 -> {
                        activity?.finish()
                    }
                }
            }
        })
        binding.rvNav.adapter = navAdapter

        binding.resultButton.setOnClickListener {
            if (binding.fioEt.text.isNotEmpty()) {
                Cache.name = binding.fioEt.text.toString().trim()
                count()
                findNavController().navigate(R.id.resultFragment)
                clearCheck()
            } else {
                count()
                toast(activity?.resources!!.getString(R.string.input_fio))
            }
        }




        return binding.root
    }

    private fun clearCheck() {
        binding.fioEt.setText("")
        binding.apply {
            includeQuestion1.radioGroup.clearCheck()
            includeQuestion2.radioGroup.clearCheck()
            includeQuestion3.radioGroup.clearCheck()
            includeQuestion4.radioGroup1.clearCheck()
            includeQuestion4.radioGroup2.clearCheck()
            includeQuestion5.radioGroup.clearCheck()
            includeQuestion6.radioGroup.clearCheck()
            includeQuestion7.radioGroup1.clearCheck()
            includeQuestion7.radioGroup2.clearCheck()
            includeQuestion8.radioGroup.clearCheck()
            includeQuestion9.radioGroup.clearCheck()
            includeQuestion10.radioGroup.clearCheck()
            includeQuestion11.radioGroup1.clearCheck()
            includeQuestion11.radioGroup2.clearCheck()
            includeQuestion12.radioGroup.clearCheck()
            includeQuestion13.radioGroup.clearCheck()
            includeQuestion14.radioGroup.clearCheck()
            includeQuestion16.radioGroup.clearCheck()
            includeQuestion17.radioGroup.clearCheck()
            includeQuestion18.radioGroup.clearCheck()
            includeQuestion19.radioGroup.clearCheck()
            includeQuestion20.radioGroup.clearCheck()
            includeQuestion21.radioGroup.clearCheck()

            question1 = 0
            question2 = 0
            question3 = 0
            question4 = 0
            question5 = 0
            question6 = 0
            question7 = 0
            question8 = 0
            question9 = 0
            question10 = 0
            question11 = 0
            question12 = 0
            question13 = 0
            question14 = 0
            question15 = 0
            question16 = 0
            question17 = 0
            question18 = 0
            question19 = 0
            question20 = 0
            question21 = 0

        }
    }

    private fun solve() {
        //question 1
        binding.includeQuestion1.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button1 -> {
                    question1 = 0
                }
                R.id.radio_button2 -> {
                    question1 = 1
                }
            }
        }

        //question 2
        binding.includeQuestion2.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button1 -> {
                    question2 = 0
                }
                R.id.radio_button2 -> {
                    question2 = 1
                }
            }
        }

        //question 3
        binding.includeQuestion3.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button1 -> {
                    question3 = 0
                }
                R.id.radio_button2 -> {
                    question3 = 1
                }
            }
        }

        //question 4
        binding.includeQuestion4.radioGroup1.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button_no -> {
                    question4 = 0
                    binding.includeQuestion4.radioGroup2.clearCheck()
                }

            }
        }

        binding.includeQuestion4.radioGroup2.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button1 -> {
                    question4 = 1
                    binding.includeQuestion4.radioGroup1.clearCheck()
                }
                R.id.radio_button2 -> {
                    question4 = 2
                    binding.includeQuestion4.radioGroup1.clearCheck()
                }
                R.id.radio_button3 -> {
                    question4 = 3
                    binding.includeQuestion4.radioGroup1.clearCheck()
                }

            }
        }

        //question 5
        binding.includeQuestion5.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button1 -> {
                    question5 = 0
                }
                R.id.radio_button2 -> {
                    question5 = 1
                }
            }
        }


        //question 6
        binding.includeQuestion6.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button1 -> {
                    question6 = 0
                }
                R.id.radio_button2 -> {
                    question6 = 1
                }
            }
        }

        //question 7
        binding.includeQuestion7.radioGroup1.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button_no -> {
                    question7 = 0
                    binding.includeQuestion7.radioGroup2.clearCheck()
                }

            }
        }

        binding.includeQuestion7.radioGroup2.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button1 -> {
                    question7 = 1
                    binding.includeQuestion7.radioGroup1.clearCheck()
                }
                R.id.radio_button2 -> {
                    question7 = 2
                    binding.includeQuestion7.radioGroup1.clearCheck()
                }
                R.id.radio_button3 -> {
                    question7 = 3
                    binding.includeQuestion7.radioGroup1.clearCheck()
                }

            }
        }

        // question 8
        binding.includeQuestion8.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button1 -> {
                    question8 = 0
                }
                R.id.radio_button2 -> {
                    question8 = 1
                }
                R.id.radio_button3 -> {
                    question8 = 2
                }
                R.id.radio_button4 -> {
                    question8 = 3
                }

            }
        }

        // question 9
        binding.includeQuestion9.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button1 -> {
                    question9 = 0
                }
                R.id.radio_button2 -> {
                    question9 = 1
                }
                R.id.radio_button3 -> {
                    question9 = 2
                }
                R.id.radio_button4 -> {
                    question9 = 3
                }
                R.id.radio_button5 -> {
                    question9 = 4
                }

            }
        }

        // question 10
        binding.includeQuestion10.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button1 -> {
                    question10 = 0
                }
                R.id.radio_button2 -> {
                    question10 = 1
                }
                R.id.radio_button3 -> {
                    question10 = 2
                }
                R.id.radio_button4 -> {
                    question10 = 3
                }
                R.id.radio_button5 -> {
                    question10 = 4
                }

            }
        }

        //question 11
        binding.includeQuestion11.radioGroup1.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button_no -> {
                    question11 = 0
                    binding.includeQuestion11.radioGroup2.clearCheck()
                }

            }
        }

        binding.includeQuestion11.radioGroup2.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button1 -> {
                    question11 = 1
                    binding.includeQuestion11.radioGroup1.clearCheck()
                }
                R.id.radio_button2 -> {
                    question11 = 2
                    binding.includeQuestion11.radioGroup1.clearCheck()
                }
                R.id.radio_button3 -> {
                    question11 = 3
                    binding.includeQuestion11.radioGroup1.clearCheck()
                }

            }
        }

        //question 12
        binding.includeQuestion12.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button1 -> {
                    question12 = 0
                }
                R.id.radio_button2 -> {
                    question12 = 1
                }
            }
        }

        // question 13
        binding.includeQuestion13.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button1 -> {
                    question13 = 1
                }
                R.id.radio_button2 -> {
                    question13 = 2
                }
                R.id.radio_button3 -> {
                    question13 = 3
                }
                R.id.radio_button4 -> {
                    question13 = 4
                }
            }
        }


        //question 14
        binding.includeQuestion14.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button1 -> {
                    question14 = 0
                }
                R.id.radio_button2 -> {
                    question14 = 1
                }
            }
        }

        // question 16 -----------15
        binding.includeQuestion16.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button1 -> {
                    question16 = 1
                }
                R.id.radio_button2 -> {
                    question16 = 2
                }
                R.id.radio_button3 -> {
                    question16 = 3
                }
                R.id.radio_button4 -> {
                    question16 = 4
                }
            }
        }

        //question 17 -------16
        binding.includeQuestion17.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button1 -> {
                    question17 = 0
                }
                R.id.radio_button2 -> {
                    question17 = 1
                }
            }
        }


        //question 18 -------17
        binding.includeQuestion18.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button1 -> {
                    question18 = 0
                }
                R.id.radio_button2 -> {
                    question18 = 1
                }
            }
        }

        //question 19 -------18
        binding.includeQuestion19.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button1 -> {
                    question19 = 0
                }
                R.id.radio_button2 -> {
                    question19 = 1
                }
            }
        }

        // question 20 -----------19
        binding.includeQuestion20.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button1 -> {
                    question20 = 1
                }
                R.id.radio_button2 -> {
                    question20 = 2
                }
                R.id.radio_button3 -> {
                    question20 = 3
                }
                R.id.radio_button4 -> {
                    question20 = 4
                }
            }
        }

        // question 21 -----------20
        binding.includeQuestion21.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_button1 -> {
                    question21 = 1
                }
                R.id.radio_button2 -> {
                    question21 = 2
                }
                R.id.radio_button3 -> {
                    question21 = 3
                }
                R.id.radio_button4 -> {
                    question21 = 4
                }
                R.id.radio_button5 -> {
                    question21 = 5
                }
            }
        }


    }

    private fun count() {
        val sum = question1 + question2 + question3 + question4 + question5 + question6 +
                question7 + question8 + question9 + question10 + question11 + question12 +
                question13 + question14 + question15 + question16 + question17 + question18 +
                question19 + question20 + question21
        Cache.countAll = sum
//        toast(Cache.countAll.toString())
    }

    private fun loadNav() {
        listNav = ArrayList()
        listNav.clear()
        listNav.add(Nav(requireActivity().resources.getString(R.string.main), R.drawable.fi_home))
        listNav.add(
            Nav(
                requireActivity().resources.getString(R.string.save),
                R.drawable.fi_bookmark
            )
        )
        listNav.add(Nav(requireActivity().resources.getString(R.string.info), R.drawable.fi_info))
        listNav.add(
            Nav(
                requireActivity().resources.getString(R.string.language),
                R.drawable.fi_globe
            )
        )
        listNav.add(
            Nav(
                requireActivity().resources.getString(R.string.authors),
                R.drawable.fi_users
            )
        )
        listNav.add(
            Nav(
                requireActivity().resources.getString(R.string.share),
                R.drawable.fi_share_2
            )
        )
        listNav.add(Nav(requireActivity().resources.getString(R.string.rate), R.drawable.fi_star))
        listNav.add(
            Nav(
                requireActivity().resources.getString(R.string.exit),
                R.drawable.fi_log_out
            )
        )
    }

    fun setAppLocale(context: Context, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = context.resources.configuration
        config.setLocale(locale)
        context.createConfigurationContext(config)
        context.resources.updateConfiguration(
            config,
            context.resources.displayMetrics
        )
    }
}