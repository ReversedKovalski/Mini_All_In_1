package com.example.drrrtesting.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drrrtesting.R;
import com.example.drrrtesting.room.objects.User;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements View.OnClickListener, MainActView {
    private Button btnAdd, btnUpdate, btnDelete;
    private EditText edtName, edtEmail, edtPassword;
    protected RecyclerView recViewData;
    protected DataRecViewAdapter adapter;

    @Inject
    MainActPresenterImpl mPresenter;

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
                    showError("Empty fields");
                } else {
                    mPresenter.addUser(name, email, password);
                }
                break;
            case R.id.btnDelete:
                if (name.isEmpty()) {
                    showError("Empty name field");
                } else {
                    mPresenter.deleteUserByName(name);
                }
                break;
        }
    }


    @Override
    public void updateAdapter(List<User> users) {
        //Возможно надо было закинуть recViewAdapter в презентер, дабы дергать его без посредника
        adapter.updateUsers(users);
    }

    @Override
    public void clearEdts() {
        edtName.setText("");
        edtEmail.setText("");
        edtPassword.setText("");
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.disposeAll();
        mPresenter.detached();
    }
}