package oguzhan.ornek.moviebook.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import oguzhan.ornek.moviebook.util.Constants.IMAGE_URL

@BindingAdapter("bindUrlImage")
fun ImageView.bindUrlImage(url:String?){
    url?.let {
        Glide.with(this.context).load(IMAGE_URL+it).into(this)
    }

}
@BindingAdapter("visibleIf")
fun View.visibleIf(state:Boolean){
    visibility = if(state)
        View.VISIBLE
    else
        View.GONE
}