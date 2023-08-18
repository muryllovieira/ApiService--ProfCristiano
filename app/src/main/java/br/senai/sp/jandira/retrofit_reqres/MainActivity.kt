package br.senai.sp.jandira.retrofit_reqres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)

        //BOTÃO DE GET
        findViewById<Button>(R.id.btnGET).setOnClickListener {
            //Log.e("GETTING-DATA", "TESTE DE BOTÃO GET")

            getUserById()

        }

        //BOTÃO DE PUT
        findViewById<Button>(R.id.btnPUT).setOnClickListener {
            //Log.e("PUTTING-DATA", "TESTE DE BOTÃO PUT")

            updateUser()

        }

        //BOTÃO DE DELETE
        findViewById<Button>(R.id.btnDELETE).setOnClickListener {
            //Log.e("DELETING-DATA", "TESTE DE BOTÃO DELETE")

            deleteUser()

        }

        //BOTÃO DE POST
        findViewById<Button>(R.id.btnPOST).setOnClickListener {
            //Log.e("POSTING-DATA", "TESTE DE BOTÃO POST")

            createUser()

        }

    }



    //LISTAGEM DE USUARIO
    private fun getUserById() {
        lifecycleScope.launch {
            val result = apiService.getUserById("2")

            if (result.isSuccessful) {
                Log.e("GETTING-DATA", "${result.body()?.data}")
            } else {
                Log.e("GETTING-DATA", "${result.message()}")
            }

        }
    }

    //INSERIR USUARIO
    private fun createUser() {
        lifecycleScope.launch {
            val body = JsonObject().apply{
                addProperty("name", "Muryllo Vieira")
                addProperty("job", "Desenvolvedor Full Stack")
            }

            val result = apiService.createUser(body)

            if (result.isSuccessful) {
                Log.i("CREATE-DATA", "${result.body()}")
            } else {
                Log.i("CREATE-DATA", "${result.message()}")
            }

        }
    }

    //ATUALIZAR USUARIO
    private fun updateUser() {
        lifecycleScope.launch {
            val body = JsonObject().apply{
                addProperty("name", "Muryllo Vieira")
                addProperty("job", "Desenvolvedor Full Stack")
            }

            val result = apiService.updateUser("10", body)

            if (result.isSuccessful) {
                Log.i("UPDATE-DATA", "${result.body()}")
            } else {
                Log.i("UPDATE-DATA", "${result.message()}")
            }

        }
    }

    //DELETAR USUARIO
    private fun deleteUser() {
        lifecycleScope.launch {

            val result = apiService.deleteUser("10")

            if (result.isSuccessful) {
                Log.e("DELETE-DATA", "${result}")
            } else {
                Log.e("DELETE-DATA", "${result.message()}")
            }

        }
    }

}