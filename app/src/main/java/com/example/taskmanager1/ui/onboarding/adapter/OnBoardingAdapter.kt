package com.example.taskmanager1.ui.onboarding.adapter

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewpager2.widget.ViewPager2
import com.example.taskmanager1.databinding.ItemOnboardingBinding
import com.example.taskmanager1.model.OnBoarding
import com.example.taskmanager1.utils.loadImage
import me.relex.circleindicator.CircleIndicator3


class OnBoardingAdapter(private val onClick: () -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val list = arrayListOf<OnBoarding>(
        OnBoarding(
            "King Julian",
            "narcissistic, eccentric partylover",
            "https://i.pinimg.com/564x/09/56/17/0956174ddd8e5d3e92df648d8d338d13.jpg"
        ),
        OnBoarding(
            "Alex",
            "simple, popular and good guy",
            "https://static.miraheze.org/greatcharacterswiki/thumb/b/bc/Dyhdfhrthftdty.jpg/640px-Dyhdfhrthftdty.jpg"
        ),
        OnBoarding(
            "Melman",
            "the kindest and smartest one",
            "https://i.pinimg.com/originals/c6/a7/8e/c6a78ecd61e60eb23d000ff58d8bdd57.png"
        )
    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list[position])
    }


    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        ViewHolder(binding.root) {

        fun bind(onBoarding: OnBoarding) = with(binding) {
            tvTitle.text = onBoarding.title
            tvDesc.text = onBoarding.desc
            tvSkip.isVisible = adapterPosition != list.lastIndex
            btnStart.isVisible = adapterPosition == list.lastIndex

            btnStart.setOnClickListener {
                onClick()
            }
            tvSkip.setOnClickListener {
                onClick()
            }
            ivBoard.loadImage(onBoarding.image.toString())
        }
    }
}