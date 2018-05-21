package com.shop.shopmobile.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shop.shopmobile.R;
import com.shop.shopmobile.annotation.In;
import com.shop.shopmobile.core.AppClientRealm;
import com.shop.shopmobile.core.emuns.Roles;
import com.shop.shopmobile.core.entities.Person;
import com.shop.shopmobile.core.entities.User;
import com.shop.shopmobile.utilities.Constant;
import com.shop.shopmobile.utilities.GeneralApp;
import com.shop.shopmobile.utilities.MyTextWatcher;
import java.util.List;

public class LoginActivity extends GeneralApp {

    // region Attributes of Activity
    @In(R.id.tv_login_message)
    private TextView tvMessage;

    @In(R.id.et_user_name)
    private EditText etUser;

    @In(R.id.et_password)
    private EditText etPassword;

    @In(R.id.btn_login)
    private Button btnLogin;

    private List<User> listUser;
    // endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin.setOnClickListener(this::onClickLogin);
        etUser.addTextChangedListener(new MyTextWatcher(tvMessage));
        etPassword.addTextChangedListener(new MyTextWatcher(tvMessage));

        AppClientRealm.init(getApplication());
        consultUsers();
    }

    private void consultUsers() {

        listUser = AppClientRealm.getAllData(User.class);

        if (listUser.isEmpty()) { // create first user

            AppClientRealm.realm.executeTransaction(r -> {

                User firstUser = r.createObject(User.class, 1);
                firstUser.setUserName("rodinson");
                firstUser.setPassword("rodinson");
                firstUser.setRole(Roles.ROLE_ADMIN.getRole());

                Person firstPerson = r.createObject(Person.class, 1);
                firstPerson.setIdentification("1061531482");
                firstPerson.setFirstName("Rodinson Samuel");
                firstPerson.setLastName("Tombe");
                firstPerson.setEmail("rodinson17@hotmail.com");
                firstPerson.setPhone("3156283627");
                firstPerson.setPhoto(R.drawable.foto);

                firstUser.setPerson(firstPerson);
            });
        }

//        UserExample user = new UserExample();
//        user.setId(1);
//        user.setName("rodinson");
//
//        AppClientRealm.insertSingleData(user);
//        List<UserExample> list = AppClientRealm.getAllData(UserExample.class);
//        System.out.println("list: "+list.size());

    }


    public void onClickLogin(View view) {

        //String userName = etUser.getText().toString();
        String userName = "rodinson";
        //String password = etPassword.getText().toString();
        String password = "rodinson";

        if (userName.equals(Constant.EMPTY_STRING) || password.equals(Constant.EMPTY_STRING)) {
            tvMessage.setText(getResources().getString(R.string.msg_error));
        } else {

            User user = AppClientRealm.getUser(User.class, userName, password);

            if (user == null) {
                tvMessage.setText("this user not exist");
                etPassword.setText(Constant.EMPTY_STRING);
                return;
            }

            Intent intentHome = new Intent(LoginActivity.this, HomeActivity.class);
            intentHome.putExtra("idUser", user.getIdUser());
            startActivity(intentHome);
            tvMessage.setText(Constant.EMPTY_STRING);
            etUser.setText(Constant.EMPTY_STRING);
            etPassword.setText(Constant.EMPTY_STRING);
        }
    }

}
