package ItemAdapter

import DataModel.Currency
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.ftc.ConverterActivity
import com.example.ftc.R

class ItemAdapter(private val dataSource: List<Currency>, private val context: Context) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val Name: TextView = view.findViewById(R.id.name)
        val CharCode: TextView = view.findViewById(R.id.charCode)
        val present_value: TextView = view.findViewById(R.id.value)
        val previous_value: TextView = view.findViewById(R.id.previous)
        val Nominal: TextView = view.findViewById(R.id.nominal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)

        adapterLayout.setOnClickListener {
            val converterIntent = Intent(context, ConverterActivity::class.java)
            val options = Bundle()

            val charCode = adapterLayout.findViewById<TextView>(R.id.charCode).text.toString()
            val currencyValue = adapterLayout.findViewById<TextView>(R.id.value).text.toString()
            val nominal = adapterLayout.findViewById<TextView>(R.id.nominal).text.toString()

            options.putString("CHAR_CODE", charCode)
            options.putString("VALUE", currencyValue.split(" ")[1])
            options.putString("NOMINAL", nominal.split(" ")[1])

            converterIntent.putExtras(options)

            startActivity(context, converterIntent, options)
        }

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currency = dataSource[position]

        holder.Name.text = currency.Name.replace("\\s".toRegex(), "\n")
        holder.CharCode.text = currency.CharCode
        holder.Nominal.text = "Номинал: " + currency.Nominal.toString()
        holder.present_value.text = "Новое: " + currency.PresentValue.toString()
        holder.previous_value.text = "Старое: " + currency.PreviousValue.toString()
    }

    override fun getItemCount(): Int = dataSource.size
}