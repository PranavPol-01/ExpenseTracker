package com.example.expensetracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddExpenseActivity extends AppCompatActivity {

    private EditText etExpenseAmount, etExpenseDetail;
    private Button btnAddExpense;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        etExpenseAmount = findViewById(R.id.et_expense_amount);
        etExpenseDetail = findViewById(R.id.et_expense_detail);
        btnAddExpense = findViewById(R.id.btn_add_expense);
        db = new DatabaseHelper(this);

        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amountStr = etExpenseAmount.getText().toString().trim();
                String detail = etExpenseDetail.getText().toString().trim();

                if (!amountStr.isEmpty() && !detail.isEmpty()) {
                    double amount = Double.parseDouble(amountStr);
                    db.addExpense(amount, detail);
                    Toast.makeText(AddExpenseActivity.this, "Expense added", Toast.LENGTH_SHORT).show();
                    etExpenseAmount.setText("");
                    etExpenseDetail.setText("");
                } else {
                    Toast.makeText(AddExpenseActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}