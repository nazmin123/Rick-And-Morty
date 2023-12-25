package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.pojo.Character
import com.example.myapplication.databinding.ItemCharacterBinding

class CharacterRecyclerAdapter(  private val listener: ItemClick): RecyclerView.Adapter<CharacterRecyclerAdapter.CharactorViewHolder>() {
    private var characterList:List<Character> = ArrayList()
    fun setCategoryList(characterList: List<Character>){
        this.characterList = characterList
        notifyDataSetChanged()
    }
    class CharactorViewHolder(val binding:ItemCharacterBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(character: Character) {
            binding.character= character
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharactorViewHolder {

        return CharactorViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }
    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(thumbs: ImageView, url: String) {
            Glide.with(thumbs)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(thumbs)
        }

    }

    override fun onBindViewHolder(
        holder: CharactorViewHolder,
        position: Int
    ) {
        holder.apply {
            bind(characterList[position])
        }
        holder.itemView.setOnClickListener {
            listener.onItemClick(characterList[position])
        }

    }

    override fun getItemCount(): Int {
        return characterList.size
    }
    interface ItemClick {
        fun onItemClick(character: Character)
    }
}