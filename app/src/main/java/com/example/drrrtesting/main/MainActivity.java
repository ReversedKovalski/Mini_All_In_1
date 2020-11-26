package com.example.drrrtesting.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drrrtesting.R;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Button btnAdd, btnUpdate, btnDelete;
    private EditText edtName, edtEmail, edtPassword;
    protected RecyclerView recViewData;
    protected DataRecViewAdapter adapter;
    @Inject
    MainActPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter.attached(this);
        initWidgets();
    }

    private void initWidgets() {
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        recViewData = findViewById(R.id.recViewDataMA);
        adapter = new DataRecViewAdapter(this);

        btnAdd.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        showData();
        completeAdapter();
    }

    private void completeAdapter(){
        recViewData.setAdapter(adapter);
        recViewData.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void showData() {
        mPresenter.getAllUsers();
    }

    @Override
    public void onClick(View v) {
        String name = edtName.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        switch (v.getId()) {
            case R.id.btnAdd:
                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    makeToast("Empty fields");
                } else {
                    mPresenter.addUser(name, email, password);
                }
                break;
            case R.id.btnDelete:
                if (name.isEmpty()) {
                    makeToast("Empty name field");
                } else {
                    mPresenter.deleteUserByName(name);
                }
                break;
        }
    }

    protected void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void clearEdts() {
        edtName.setText("");
        edtEmail.setText("");
        edtPassword.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.disposeAll();
        mPresenter.detached();
    }
}