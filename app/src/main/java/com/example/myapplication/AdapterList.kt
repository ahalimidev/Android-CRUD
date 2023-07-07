package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CostumListBinding
import com.example.myapplication.model.ModelUser

class AdapterList (val dataList: ArrayList<ModelUser>, val listener: OnAdapterListener): RecyclerView.Adapter<AdapterList.ViewHolder>() {

    private var Items = ArrayList<ModelUser>()
    private var mAdapterCallback: OnAdapterListener? = null

    init {
        this.Items = dataList
        this.mAdapterCallback = listener

    }

    class ViewHolder (val bindHolder : CostumListBinding) : RecyclerView.ViewHolder(bindHolder.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CostumListBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(dataList[position]){
                bindHolder.tvNamaLengkap.text = this.nama
                bindHolder.tvNomorHp.text = this.nomor_hp
                bindHolder.btEdit.setOnClickListener {
                    listener.onEdit(this.id_crud.toString())
                }
                bindHolder.btDetail.setOnClickListener {
                    listener.onDetail(this.id_crud.toString())
                }
                bindHolder.btHapus.setOnClickListener {
                    listener.onDelete(this.id_crud.toString())
                }
            }
        }
    }

    interface OnAdapterListener{
        fun onEdit(id : String)
        fun onDelete(id : String)
        fun onDetail(id : String)
    }
}