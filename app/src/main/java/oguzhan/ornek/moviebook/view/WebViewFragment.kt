package oguzhan.ornek.moviebook.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import oguzhan.ornek.moviebook.R
import oguzhan.ornek.moviebook.databinding.FragmentWebViewBinding

@AndroidEntryPoint
class WebViewFragment : Fragment() {

    private lateinit var binding: FragmentWebViewBinding
    private val args: WebViewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_web_view, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webViewSetup()
    }

    fun webViewSetup() {
        binding.webView.webViewClient = WebViewClient()
        binding.webView.apply {
            loadUrl(args.link)
        }
    }

}