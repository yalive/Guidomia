package ca.bell.guidomia.data.remote

import android.content.Context
import ca.bell.guidomia.data.Car
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.IOException
import java.nio.charset.Charset

class CarRemoteDataSource(
    private val gson: Gson,
    private val appContext: Context
) {

    suspend fun getCarList(): List<Car> {
        delay(3_000)
        val json = loadStringJSONFromAsset("car_list.json")
        return gson.from<List<CarRS>>(json).map(CarRS::toCar)
    }

    private suspend fun loadStringJSONFromAsset(
        assetFileName: String
    ): String = withContext(Dispatchers.IO) {
        return@withContext try {
            val inputStream = appContext.assets.open(assetFileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (ignored: IOException) {
            "{}"
        }
    }
}

internal inline fun <reified T> Gson.from(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)