package ua.railian.ajax.testapp.presentation.classicview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import ua.railian.ajax.testapp.presentation.classicview.databinding.PostDetailsBinding

class PostDetailsFragment : Fragment() {

    private var _viewBinding: PostDetailsBinding? = null
    private var viewBinding: PostDetailsBinding = requireNotNull(_viewBinding) {
        "viewBinding is only valid between onCreateView and onDestroyView."
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = PostDetailsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}