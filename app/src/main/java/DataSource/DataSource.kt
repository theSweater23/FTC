package DataSource

import DataModel.Currency
import android.content.Context
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class DataSource {

    companion object {

        var Currencies: MutableList<Currency> = mutableListOf()

        private const val URL = "https://www.cbr-xml-daily.ru/daily_json.js"

        fun getData(context: Context) {

            Currencies = mutableListOf()

            val requestQueue: RequestQueue = Volley.newRequestQueue(context)

            val request: JsonObjectRequest = JsonObjectRequest(Request.Method.GET,URL,null,
                Response.Listener { response ->
                    val info = response.getJSONObject("Valute")
                    info.keys().forEach {
                        val currency = info.getJSONObject(it)
                        Currencies.add(Currency(
                            currency.getString("Name"),
                            currency.getString("CharCode"),
                            currency.getInt("Nominal"),
                            currency.getDouble("Value"),
                            currency.getDouble("Previous")
                        ))
                    }
            }) {
                Toast.makeText(context, "Ошибка: нет интернета", Toast.LENGTH_SHORT).show()
                it.printStackTrace()
            }

            requestQueue.add(request)
        }
    }
}