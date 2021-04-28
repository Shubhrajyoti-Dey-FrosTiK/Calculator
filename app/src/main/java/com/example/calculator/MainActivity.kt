package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var s = ""
        var num = 0
        var IntegerStore = mutableListOf<Double>()
        var indicator = 0

        val one: Button = findViewById(R.id.one)
        val two: Button = findViewById(R.id.two)
        val three: Button = findViewById(R.id.three)
        val four: Button = findViewById(R.id.four)
        val five: Button = findViewById(R.id.five)
        val six: Button = findViewById(R.id.six)
        val seven: Button = findViewById(R.id.seven)
        val eight: Button = findViewById(R.id.eight)
        val nine: Button = findViewById(R.id.nine)
        val zero: Button = findViewById(R.id.zero)
        val textShown: TextView = findViewById(R.id.TextShown)
        val addition: Button = findViewById(R.id.addition)
        val subtraction: Button= findViewById(R.id.subtraction)
        val product: Button = findViewById(R.id.product)
        val division: Button= findViewById(R.id.division)
        val res: Button = findViewById(R.id.result)
        val clear: Button = findViewById(R.id.clear)

        one.setOnClickListener {
            s = s + "1"
            num = (num * 10) + 1

            textShown.text = s
        }
        two.setOnClickListener {
            s = s + "2"
            num = (num * 10) + 2
            textShown.text = s
        }
        three.setOnClickListener {
            s = s + "3"
            num = (num * 10) + 3
            textShown.text = s
        }
        four.setOnClickListener {
            s = s + "4"
            num = (num * 10) + 4
            textShown.text = s
        }
        five.setOnClickListener {
            s = s + "5"
            num = (num * 10) + 5
            textShown.text = s
        }
        six.setOnClickListener {
            s = s + "6"
            num = (num * 10) + 6
            textShown.text = s
        }
        seven.setOnClickListener {
            s = s + "7"
            num = (num * 10) + 7
            textShown.text = s
        }
        eight.setOnClickListener {
            s = s + "8"
            num = (num * 10) + 8
            textShown.text = s
        }
        nine.setOnClickListener {
            s = s + "9"
            num = (num * 10) + 9
            textShown.text = s
        }
        zero.setOnClickListener {
            s = s + "0"
            num = (num * 10)
            textShown.text = s
        }
        addition.setOnClickListener {
            s = s + "+"
            IntegerStore.add(num.toDouble())
            num = 0
            textShown.text = s
        }
        subtraction.setOnClickListener {
            s = s + "-"
            IntegerStore.add(num.toDouble())
            num = 0
            textShown.text = s
        }
        product.setOnClickListener {
            s = s + "x"
            IntegerStore.add(num.toDouble())
            num = 0
            textShown.text = s
        }
        division.setOnClickListener {
            s = s + "/"
            IntegerStore.add(num.toDouble())
            num = 0
            textShown.text = s
        }
        res.setOnClickListener {

             if(IntegerStore.isNotEmpty())
             {
                 IntegerStore.add(num.toDouble())
//                 if(indicator>0){IntegerStore.removeAt(0)}

                 Calculate(s, IntegerStore, textShown)
//                 textShown.text = IntegerStore.toString()
//                 s = IntegerStore[0].toString()
                 s=""
                 num = 0
                 IntegerStore.clear()
                 indicator=indicator+1
             }

        }
        clear.setOnClickListener {
            textShown.text = ""
            s=""
            num = 0
            IntegerStore.clear()
            indicator=indicator+1
        }
    }

    private fun Calculate(s: String, integerStore: MutableList<Double>, textShown: TextView) {


        var OperationStore = mutableListOf<Char>()
        var result=0

        for(i in 0..s.length-1)
        {
            if(s[i]=='+')
            {OperationStore.add('+')}
            if(s[i]=='-')
            {OperationStore.add('-')}
            if(s[i]=='x')
            {OperationStore.add('*')}
            if(s[i]=='/')
            {OperationStore.add('/')}
        }

        var flag=1
        if(OperationStore.size == integerStore.size-1){
            flag=2
        }


        while(flag==1) {
            flag = 0
            for (i in 0..OperationStore.size - 1) {
                if (OperationStore[i] == '/') {
                    if(integerStore[i+1]==0.0){
                        textShown.text = "Division by 0 not possible"
                        flag=2
                        break
                    }
                    var product = integerStore[i] / integerStore[i + 1]
                    textShown.text = "DONE FINE"
                    OperationStore.removeAt(i)
                    integerStore.removeAt(i + 1)
                    integerStore.set(i, product)
                    flag = 1
                    break
                }
            }
        }
        if(flag==0){flag=1}
        while(flag==1) {
            flag = 0
            for (i in 0..OperationStore.size - 1) {
                if (OperationStore[i] == '*') {
                    var product = integerStore[i] * integerStore[i + 1]
                    textShown.text = "DONE FINE"
                    OperationStore.removeAt(i)
                    integerStore.removeAt(i + 1)
                    integerStore.set(i, product)
                    flag = 1
                    break
                }
            }
        }
        if(flag==0){flag=1}
        while(flag==1)
        {
            flag=0
            for(i in 0..OperationStore.size-1)
            {
                if(OperationStore[i]=='+')
                {
                    var sum=integerStore[i]+integerStore[i+1]
//                    textShown.text = "DONE FINE"
                    OperationStore.removeAt(i)
                    integerStore.removeAt(i+1)
                    integerStore.set(i,sum)
                    flag=1
                    break
                }
                if(OperationStore[i]=='-')
                {
                    var diff=integerStore[i]-integerStore[i+1]
//                    textShown.text = "DONE FINE"
                    OperationStore.removeAt(i)
                    integerStore.removeAt(i+1)
                    integerStore.set(i,diff)
                    flag=1
                    break
                }
            }
        }
        if(flag!=2) {
            textShown.text = integerStore[0].toString()
        }
    }
}
