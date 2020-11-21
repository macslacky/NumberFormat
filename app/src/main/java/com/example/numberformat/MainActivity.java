package com.example.numberformat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnEsegui;
    TextView tDispNumIn, tDispNumOut, tDispCifre; // tDispOutInt, tDispOutDec, tDispDepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //start activity
        // call the parent class costruction
        super.onCreate(savedInstanceState);
        // set the XML
        setContentView(R.layout.activity_main);

        // retrieve the reference to the interface objects
        tDispNumIn = findViewById(R.id.txtNumIn);
        tDispNumOut = findViewById(R.id.txtNumOut);
        tDispCifre = findViewById(R.id.txtCifre);
        btnEsegui = findViewById(R.id.btmEsegui);
        btnEsegui.setOnClickListener(this);
    }

    public void onClick(View v) { // click event
        NumberFormat numIn;
        double depoNum;
        int carPunto, maxDigit;
        String strDepo1, strError = "ERROR";
        boolean flagErr = true;

        // reads the maximum number of digits that can be displayed
        maxDigit = Integer.parseInt(tDispCifre.getText().toString());

        // the number entered is stored in the local variable
        strDepo1 = tDispNumIn.getText().toString();

        if (!strDepo1.contains(".")) {
            // the number does not contain the period
            if (strDepo1.length() <= maxDigit) {
                // the number of digits are not higher than expected
                flagErr = false;
            }
        }  else if (strDepo1.indexOf(".") <= maxDigit) {
            // the number of digits of the integer part is not more than expected
            flagErr = false;
        }

        if (!flagErr) {// the number is viewable
            // maximum number of digits allowed in the fraction part
            carPunto = maxDigit - strDepo1.indexOf(".");
            // returns the numeric value of the string
            depoNum = Double.parseDouble(tDispNumIn.getText().toString());
            // format the number
            numIn = NumberFormat.getNumberInstance();
            // sets the maximum number of digits allowed in the fraction part
            numIn.setMaximumFractionDigits(carPunto);
            // sets the grouping to use
            numIn.setGroupingUsed(false);
            // displays the formatted number
            tDispNumOut.setText(numIn.format(depoNum));
        } else {
            tDispNumOut.setText(strError);
        }
    }
}
