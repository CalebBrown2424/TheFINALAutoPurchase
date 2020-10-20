package com.example.therealautopurchase;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class PurchaseActivity extends AppCompatActivity {

    Auto auto1;

    String loanReport;
    String monthlyPayment;

    private EditText carPriceET;
    private EditText downPayET;
    private EditText aprAmountET;
    private RadioGroup loanTermRG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_purchase);

        carPriceET = (EditText)findViewById(R.id.editText);
        downPayET = (EditText)findViewById(R.id.editText2);
        aprAmountET = (EditText)findViewById(R.id.editText3);
        loanTermRG = (RadioGroup)findViewById(R.id.radioGroup);

        auto1 = new Auto();

    }

    private void collectAutoInputData(){

        auto1.setPrice((double) Integer.valueOf(carPriceET.getText().toString()));

        auto1.setDownPayment((double) Integer.valueOf(downPayET.getText().toString()));
        auto1.setAPRamount((double) Double.valueOf(aprAmountET.getText().toString()));

        Integer radioId = loanTermRG.getCheckedRadioButtonId();
        RadioButton term = (RadioButton)findViewById(radioId);
        auto1.setLoanTerm(term.getText().toString());
    }

    private void buildLoanReport(){

        Resources res = getResources();
        monthlyPayment = res.getString(R.string.report_line1)
                + String.format("%.02f", auto1.monthlyPayment());

        loanReport =
                String.format("%-20s %8s %.02f\n", res.getString(R.string.report_line6), " $", auto1.getPrice());
        loanReport +=
                String.format("%-20s %5s %.02f\n", res.getString(R.string.report_line7), " $", auto1.getDownPayment());
        loanReport +=
                String.format("%-20s %6s %.02f\n", res.getString(R.string.report_line9), " $", auto1.taxAmount());
        loanReport +=
                String.format("%-20s %12s %.02f\n", res.getString(R.string.report_line10), " $", auto1.totalCost());
        loanReport +=
                String.format("%-20s %s %.02f\n", res.getString(R.string.report_line11), " $", auto1.borrowedAmount());
        loanReport +=
                String.format("%-20s %6s %.02f\n", res.getString(R.string.report_line12), " $", auto1.interestAmount());

        loanReport += "\n" + res.getString(R.string.report_line8)
                + " " + auto1.getLoanTerm() + " years.";
        loanReport += "\n" + res.getString(R.string.report_line2);
        loanReport += res.getString(R.string.report_line3);
        loanReport += res.getString(R.string.report_line4);
        loanReport += res.getString(R.string.report_line5);

    }

    public void activateLoanSummary(View view){

        collectAutoInputData();
        buildLoanReport();

        Intent launchReport = new Intent(this, LoanSumActivity.class);

        launchReport.putExtra("LoanReport", loanReport);
        launchReport.putExtra("MonthlyPayment", monthlyPayment);

        startActivity(launchReport);
    }
}
