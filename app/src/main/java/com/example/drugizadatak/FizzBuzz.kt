package com.example.drugizadatak

import android.app.Activity
import android.content.Context

// FizzBuzz class with checkFizzBuzz method
class FizzBuzz(context: Context,arrayList: ArrayList<String>) :Activity() {
    private val array = arrayList
    private val myContext = context

    //check FizzBuzz
     fun checkFizzBuzz(){
        for (number in 1..15) {
            if (number % 3 == 0 && number % 5 == 0)
                array.add(myContext.resources.getString(R.string.FizzBuzz))
            else if (number % 3 == 0)
                array.add(myContext.resources.getString(R.string.Fizz))
            else if (number % 5 == 0)
                array.add(myContext.resources.getString(R.string.Buzz))
            else
                array.add(number.toString())
        }
    }
}