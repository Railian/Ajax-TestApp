package ua.railian.ajax.testapp.presentation.classicview

import android.app.Activity
import android.os.Bundle
import ua.railian.ajax.testapp.presentation.classicview.databinding.PostDetailsBinding

class PostDetailsActivity : Activity() {

    private lateinit var viewBinding: PostDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = PostDetailsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}