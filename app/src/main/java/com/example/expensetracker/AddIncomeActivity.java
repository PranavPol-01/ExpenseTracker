package com.example.expensetracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddIncomeActivity extends AppCompatActivity {

    private EditText etIncomeAmount, etIncomeDetail;
    private Button btnAddIncome;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);

        etIncomeAmount = findViewById(R.id.et_income_amount);
        etIncomeDetail = findViewById(R.id.et_income_detail);
        btnAddIncome = findViewById(R.id.btn_add_income);
        db = new DatabaseHelper(this);

        btnAddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amountStr = etIncomeAmount.getText().toString().trim();
                String detail = etIncomeDetail.getText().toString().trim();

                if (!amountStr.isEmpty() && !detail.isEmpty()) {
                    double amount = Double.parseDouble(amountStr);
                    db.addIncome(amount, detail);
                    Toast.makeText(AddIncomeActivity.this, "Income added", Toast.LENGTH_SHORT).show();
                    etIncomeAmount.setText("");
                    etIncomeDetail.setText("");
                } else {
                    Toast.makeText(AddIncomeActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}