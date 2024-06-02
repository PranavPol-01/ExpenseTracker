package com.example.expensetracker;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnAddExpense, btnAddIncome;
    private TextView tvTotalExpense, tvTotalIncome;
    private ListView lvExpenses, lvIncome;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddExpense = findViewById(R.id.btn_add_expense);
        btnAddIncome = findViewById(R.id.btn_add_income);
        tvTotalExpense = findViewById(R.id.tv_total_expense);
        tvTotalIncome = findViewById(R.id.tv_total_income);
        lvExpenses = findViewById(R.id.lv_expenses);
        lvIncome = findViewById(R.id.lv_income);
        db = new DatabaseHelper(this);

        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddExpenseActivity.class));
            }
        });

        btnAddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddIncomeActivity.class));
            }
        });

        loadSummary();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSummary();
    }

    private void loadSummary() {
        double totalExpense = db.getTotalExpense();
        double totalIncome = db.getTotalIncome();

        tvTotalExpense.setText("Total Expense: $" + totalExpense);
        tvTotalIncome.setText("Total Income: $" + totalIncome);

        Cursor expenseCursor = db.getAllExpenses();
        String[] fromExpense = { "amount", "detail", "timestamp" };
        int[] toExpense = { R.id.tv_amount, R.id.tv_detail, R.id.tv_timestamp };
        SimpleCursorAdapter expenseAdapter = new SimpleCursorAdapter(this, R.layout.item_record, expenseCursor, fromExpense, toExpense, 0);
        lvExpenses.setAdapter(expenseAdapter);

        Cursor incomeCursor = db.getAllIncome();
        String[] fromIncome = { "amount", "detail", "timestamp" };
        int[] toIncome = { R.id.tv_amount, R.id.tv_detail, R.id.tv_timestamp };
        SimpleCursorAdapter incomeAdapter = new SimpleCursorAdapter(this, R.layout.item_record, incomeCursor, fromIncome, toIncome, 0);
        lvIncome.setAdapter(incomeAdapter);
    }
}