package com.potato.nckuppp.callAPI

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import retrofit2.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap


data class Course (@JsonProperty("y") val y: String, @JsonProperty("dn") val dn: String,
				   @JsonProperty("sn") val sn: Int?, @JsonProperty("ca") val ca: String,
				   @JsonProperty("sc") val sc: String, @JsonProperty("g") val g: Int,
				   @JsonProperty("fc") val fc: String?, @JsonProperty("fg") val fg: String?,
				   @JsonProperty("ct") val ct: String, @JsonProperty("cn") val cn: String,
				   @JsonProperty("ci") val ci: String?, @JsonProperty("cl") val cl: String?,
				   @JsonProperty("tg") val tg: List<String>?, @JsonProperty("c") val c: Float,
				   @JsonProperty("r") val r: Boolean, @JsonProperty("i") val i: List<String>?,
				   @JsonProperty("s") val s: Int?, @JsonProperty("a") val a: Int,
				   @JsonProperty("t") val t: List<String>?)

data class CourseSearchResponse(@JsonProperty("success") val success: Boolean,
								@JsonProperty("data") val data: List<Course>?,
								@JsonProperty("msg") val msg: String? = null, @JsonProperty("code") val code: Int,
								@JsonProperty("err") val err: List<String>? = null,
								@JsonProperty("warn") val warn: List<String>? = null)

data class ErrorResponse(
	@JsonProperty("message") val message: String
)

object RetrofitClient {
	private const val BASEURL = "https://api.wavjaby.nckuctf.org/api/v0/"

	val okHttpClient = OkHttpClient()
		.newBuilder()
		.addInterceptor(RequestInterceptor)
		.build()

	fun getClient() : Retrofit = Retrofit.Builder()
									.baseUrl(BASEURL)
									.addConverterFactory(JacksonConverterFactory.create())
									.build()
}

object RequestInterceptor : Interceptor {
	override fun intercept(chain: Interceptor.Chain): Response {
		val request = chain.request()
		println("Outgoing request ${request.url}")
		return chain.proceed(request)
	}
}

interface SearchAPI {
	@GET("historySearch")
	fun getSearch(@QueryMap map: Map<String, String>): Call<CourseSearchResponse>
}

fun getCourseResult(query: Map<String, String>) : CourseSearchResponse? {
	val retrofit = RetrofitClient.getClient()
	val userAPI = retrofit.create(SearchAPI::class.java)

	val res = userAPI.getSearch(query).execute()

	return res.body() ?: res.errorBody().let {
		it?.let { notNullErrorBody ->
			ObjectMapper().readValue(
				notNullErrorBody.string(),
				CourseSearchResponse::class.java)
		}
	}
}


fun main() {
	val query = mapOf<String, String>("dept" to "F7")
	println(getCourseResult(query))
}








