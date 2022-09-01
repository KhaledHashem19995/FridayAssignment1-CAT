package com.example.friday1assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.friday1assignment.databinding.ActivityMainBinding

const val TAG  = "MainActivity"

class MainActivity : AppCompatActivity() {

    // Initializing viewBinding (2nd step after buildFeatures in griddles)
    private val binding by lazy {
        //activity_main is what will be inflated
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setting the content of what will be inflated, root refers to the activity_main
        //but this is the "binding" way of doing it, which we will be using
        //after this step now we have access to the layout (activity_main),
        //we can access its elements by referring to their id's
        setContentView(binding.root)

        //accessing a specific item in layout and performing an action
        // upon clicking on it(setOnClickListener)
        binding.imageButton.setOnClickListener {

            /* VERY USEFUL PIECE OF CODE
        //right here you have the click action

            //Builder Pattern, we are building the AlertDialog
            AlertDialog.Builder(this)
                .setTitle("Button has been clicked")
                .setMessage("You have clicked the Login Button")

                 //usually  it can be something like
                //.setPositiveButton("OK"){ dialog, id ->
                // but the "_" refers that we only have one parameter to be used
                // in this case
                .setPositiveButton("OK"){ dialog, _ ->
                    dialog.dismiss()
                }
                .setNegativeButton("DISMISS") { dialog, _ ->
                    dialog.dismiss()
                }

                .create() //create the AlertDialog
                .show() // show the AlertDialog

        // baseContext -> it is the activity CONTEXT that will be alive when the activity is active
        //applicationContext -> this will be alive when app is running */

            //INTENT (EXPLICIT) Example
            //second term in bracket is the reference for the item we want to open
            //"apply" this to the object we have created

            startActivity(Intent(baseContext, MainActivity2::class.java))/*.apply {

                //Passing data to the next activity
                putExtra("MY_DATA","This data is coming from activity one")
                putExtra("DATA_OBJECT", MyData("Android training"))
                  startActivity(this) //start activity 2

            } */

//Implicit Intent EXAMPLE DIRECTING TO URL
/*
            val mUri = Uri.parse("https://www.google.com")
            Intent(ACTION_VIEW).apply { //standard action to open a url
                startActivity(this)
            }  */

            //Activity is not visible to the user
//here you are inflating the UI of the layout and creating an instance of the activity to be used
            Log.d(TAG, "OnCreate: Activity created")
        }
    }
    override fun onStart() {
        super.onStart()
        // ACTIVITY WILL BE VISIBLE TO THE USER BUT CANNOT INTERACT
        //This is used to start services, broadcast receivers, listening to events
        //This will be called only once
        Log.d(TAG, "onStart: Activity started" )
    }

    override fun onResume() {
        super.onResume()
        //THIS IS WHERE THE USER IS ABLE TO START INTERACTING WITH THE ACTIVITY
        Log.d(TAG, "onResume: Activity resumed" )
    }

    //SCREEN ROTATION
    // a Bundle object allows you to put data inside
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState: Rotation of screen " + 12)
        outState.putInt("Age", 12)
    }
    override fun  onRestoreInstanceState(savedInstanceState: Bundle){
        super.onRestoreInstanceState(savedInstanceState)
        val data = savedInstanceState.getInt("Age")
        Log.d(TAG, "onRestoreInstanceState: Restoring rotation  $data")
    }


    override fun onPause(){
        super.onPause()
        // WHEN THE ACTIVITY IS VISIBLE TO THE USER BUT CANNOT INTERACT ANYMORE
        Log.d(TAG, "onPause: Activity paused" )
    }

    override fun onStop() {
        super.onStop()
        // WHEN ACTIVITY IS NOT VISIBLE TO THE USER
        //here you will disconnect services, broadcast receivers, stop listening events
        Log.d(TAG, "onStop: Activity stopped" )
        finish()// to call on destroy
    }

    override fun onRestart(){
        super.onRestart()
        //pulling the activity instance from the backstack
        Log.d(TAG, "onRestart: Activity restarted" )
    }

    override fun onDestroy(){
        super.onDestroy()
        //THIS IS WHERE THE ACTIVITY GETS DESTROYED FROM BACKSTACK AND INSTANCE IS DISPOSED
        //this method is not granted to be called unless you execute the finish or killing the app
        Log.d(TAG, "onDestroy: Activity destroyed" )
    }

}

