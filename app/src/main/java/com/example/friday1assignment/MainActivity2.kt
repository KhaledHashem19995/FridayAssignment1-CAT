package com.example.friday1assignment
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.friday1assignment.databinding.ActivityMain2Binding

const val TAG2 = "Activity2"

class MainActivity2 : AppCompatActivity() {

    private val binding by lazy {
        ActivityMain2Binding.inflate(layoutInflater)
    }

    //to store email in local data by share preferences (name of share file, mode)
    //let it be in private mode, better security, only your app can access it
    private val sharedPreferences: SharedPreferences by lazy{
        baseContext.getSharedPreferences("MY_SHARE_PREFS", MODE_PRIVATE)
        //editing = sharePreferences.edit()
    }

        private var email = ""
        private var password = ""
        private var password2 = ""


//--

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            //Starting activity1 when pressing the back button
            startActivity(Intent(baseContext, MainActivity::class.java))
        }



    }


    override fun onStart() {
        super.onStart()
        // ACTIVITY WILL BE VISIBLE TO THE USER BUT CANNOT INTERACT
        //This is used to start services, broadcast receivers, listening to events
        //This will be called only once
        Log.d(TAG2, "onStart: Activity started" )
    }

    override fun onResume() {
        super.onResume()
        //THIS IS WHERE THE USER IS ABLE TO START INTERACTING WITH THE ACTIVITY
        Log.d(TAG2, "onResume: Activity resumed")



        //val emailStored = sharedPreferences.edit().apply{"MY_EMAIL"
         //   putString("email", binding.emailTv.text.toString())
       // }
        //binding.emailTv.setText(emailStored)

        binding.imageButton2.setOnClickListener {

            validateData()

            //getting the data from the INTENT passed by previous activity
            /* intent.extras?.let {
            binding.secondTv.text = String.format(it.getString("MY_DATA") +
            it.getParcelable<MyData>("DATA_OBJECT")?.name) //parcelable type MyData
        } */


            }

        }


    private fun validateData() {

        email = binding.emailTv.text.toString().trim()
        password = binding.passwordTv.text.toString().trim()
        password2 = binding.passwordTv2.text.toString().trim()


        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            Toast.makeText(applicationContext, "Wrong email Format !", Toast.LENGTH_LONG)
                .show()
            binding.Instruction.visibility = View.VISIBLE
            binding.Instruction1.visibility = View.INVISIBLE
            binding.imageButton3.visibility = View.INVISIBLE
            binding.imageView12.visibility = View.INVISIBLE
        }

        else  if (TextUtils.isEmpty(password)){
            Toast.makeText(applicationContext, "Please enter a password !", Toast.LENGTH_LONG)
                .show()
            binding.Instruction.visibility = View.VISIBLE
            binding.Instruction1.visibility = View.INVISIBLE
            binding.imageButton3.visibility = View.INVISIBLE
            binding.imageView12.visibility = View.INVISIBLE
        }

        else if ((password.length > 8) && (password == password2) && (password.matches("^(?=.{8,})(?=.*[a-z])(?=.*[A-Z]).*\$".toRegex()))) {

            if (!sharedPreferences.getString(email,"").equals(email)) {
                Toast.makeText(
                    applicationContext,
                    "Registered successfully",
                    Toast.LENGTH_LONG
                )
                    .show()
                binding.Instruction.visibility = View.INVISIBLE
                binding.Instruction1.visibility = View.INVISIBLE
                binding.imageButton3.visibility = View.INVISIBLE
                binding.imageView12.visibility = View.VISIBLE

                sharedPreferences.edit().apply {
                    this.putString( binding.emailTv.text.toString(),email)
                    //apply will perform the operation without returning any value
                    //commit will return the boolean value whenever its done
                    //commit()
                }.apply()
            }

            else {

                Toast.makeText(
                    applicationContext,
                    "Email is already registered !",
                    Toast.LENGTH_LONG
                )
                    .show()

            } }
            //calling my shared preferences object, and editor object that allows you
            //to write something on shared preferences


        else if ((password.length > 8) && (password == password2) && (password.matches("^(?=.{8,})(?=.*[a-z])(?=.*[A-Z]).*\$".toRegex()))) {


            Toast.makeText(
                applicationContext,
                "Registered successfully",
                Toast.LENGTH_LONG
            )
                .show()
            binding.Instruction.visibility = View.INVISIBLE
            binding.Instruction1.visibility = View.INVISIBLE
            binding.imageButton3.visibility = View.INVISIBLE
            binding.imageView12.visibility = View.VISIBLE

        }


        else if ((password.length > 8) && (password != password2) && (password.matches("^(?=.{8,})(?=.*[a-z])(?=.*[A-Z]).*\$".toRegex()))){

            Toast.makeText(applicationContext, "Passwords don't match !", Toast.LENGTH_LONG)
                .show()
            binding.Instruction.visibility = View.INVISIBLE
            binding.Instruction1.visibility = View.VISIBLE
            binding.imageButton3.visibility = View.VISIBLE
            binding.imageView12.visibility = View.VISIBLE
            binding.imageView12.visibility = View.INVISIBLE
        }

            else{
            Toast.makeText(applicationContext, "Passwords format doesn't meet requirements mentioned above !", Toast.LENGTH_LONG)
             .show()
            binding.Instruction.visibility = View.VISIBLE
            binding.Instruction1.visibility = View.INVISIBLE
            binding.imageButton3.visibility = View.INVISIBLE
            binding.imageView12.visibility = View.INVISIBLE
        }
    }



    //SCREEN ROTATION
    // a Bundle object allows you to put data inside
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG2, "onSaveInstanceState: Rotation of screen " + 12)
        outState.putInt("Age", 12)
    }
    override fun  onRestoreInstanceState(savedInstanceState: Bundle){
        super.onRestoreInstanceState(savedInstanceState)
        val data = savedInstanceState.getInt("Age")
        Log.d(TAG2, "onRestoreInstanceState: Restoring rotation  $data")
    }


    override fun onPause(){
        super.onPause()
        // WHEN THE ACTIVITY IS VISIBLE TO THE USER BUT CANNOT INTERACT ANYMORE
        Log.d(TAG2, "onPause: Activity paused" )
    }

    override fun onStop() {
        super.onStop()
        // WHEN ACTIVITY IS NOT VISIBLE TO THE USER
        //here you will disconnect services, broadcast receivers, stop listening events
        Log.d(TAG2, "onStop: Activity stopped" )
        finish()// to call on destroy
    }

    override fun onRestart(){
        super.onRestart()
        //pulling the activity instance from the backstack
        Log.d(TAG2, "onRestart: Activity restarted" )
    }

    override fun onDestroy(){
        super.onDestroy()
        //THIS IS WHERE THE ACTIVITY GETS DESTROYED FROM BACKSTACK AND INSTANCE IS DISPOSED
        //this method is not granted to be called unless you execute the finish or killing the app
        Log.d(TAG2, "onDestroy: Activity destroyed" )
    }


    /*
    private fun inputFocusScanner() {
        binding.passwordTv.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.tvPasswordER.text = passwordValidation()
            }
        }
    }
*/
   /* private fun passwordValidation():String? {
        val passText = binding.passwordTv.text.toString()
        return if(!passText.matches("^(?=.{8,})(?+.*[a-z])(?=.*[A-Z]).*\$".toRegex()))
            togglePasswordError(true)
        binding.passwordTv.background = R.drawable.error_box_id
        "
        */
    }



