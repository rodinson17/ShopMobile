package com.shop.shopmobile.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.shop.shopmobile.R;
import com.shop.shopmobile.annotation.In;
import com.shop.shopmobile.core.AppClientRealm;
import com.shop.shopmobile.core.entities.Person;
import com.shop.shopmobile.core.entities.User;
import com.shop.shopmobile.utilities.GeneralApp;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends GeneralApp {

    // region Attributes of Class
    @In(R.id.civ_photo_profile)
    private CircleImageView civPhoto;

    @In(R.id.til_first_name_profile)
    private TextInputLayout tilName;

    @In(R.id.til_last_name_profile)
    private TextInputLayout tilLastName;

    @In(R.id.til_identification_profile)
    private TextInputLayout tilIdentification;

    @In(R.id.til_email_profile)
    private TextInputLayout tilEmail;

    @In(R.id.til_phone_profile)
    private TextInputLayout tilPhone;

    @In(R.id.til_password)
    private TextInputLayout tilPassword;

    @In(R.id.til_confirm_password)
    private TextInputLayout tilConfirmPassword;

    @In(R.id.et_first_name_profile)
    private EditText etName;

    @In(R.id.et_last_name_profile)
    private EditText etLastName;

    @In(R.id.et_identification_profile)
    private EditText etIdentification;

    @In(R.id.et_email_profile)
    private EditText etEmail;

    @In(R.id.et_phone_profile)
    private EditText etPhone;

    @In(R.id.et_password)
    private EditText etPassword;

    @In(R.id.et_confirm_password)
    private EditText etConfirmPassword;

    @In(R.id.rg_genero)
    private RadioGroup rgGender;

    @In(R.id.rb_male)
    private RadioButton rbMale;

    @In(R.id.rb_female)
    private RadioButton rbFemale;

    private User currentUser;
    private boolean gender;
    // endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Perfil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(view -> { onBackPressed(); });

        int idUser = getIntent().getIntExtra("idUser", 0);
        currentUser = AppClientRealm.getDataForID(User.class, "idUser", idUser);

        setInfoUser();
        enableViews(false);

        rgGender.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == R.id.rb_male) {
                gender = true;
            } else if (i == R.id.rb_female){
                gender = false;
            }
        });
    }

    private void setInfoUser() {
        Person person = currentUser.getPerson();

        Picasso.get().load(currentUser.getPerson().getPhoto()).into(civPhoto);
        etName.setText(person.getFirstName());
        etLastName.setText(person.getLastName());
        etIdentification.setText(person.getIdentification());
        etEmail.setText(person.getEmail());
        etPhone.setText(person.getPhone());
        etPassword.setText(currentUser.getPassword());
        etConfirmPassword.setText(currentUser.getPassword());

        if (person.getGender() == null) return;

        if (person.getGender().equals("Male")){
            rbMale.setChecked(true);
            rbFemale.setChecked(false);
            gender = true;
        } else {
            rbMale.setChecked(false);
            rbFemale.setChecked(true);
        }
    }

    private void enableViews(boolean enable) {
        etName.setEnabled(enable);
        etLastName.setEnabled(enable);
        etIdentification.setEnabled(enable);
        etEmail.setEnabled(enable);
        etPhone.setEnabled(enable);
        etPassword.setEnabled(enable);
        etConfirmPassword.setEnabled(enable);
        rbMale.setEnabled(enable);
        rbFemale.setEnabled(enable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    boolean update;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_save) {

            if (update) {
                item.setTitle("update");
                update = false;
                enableViews(false);
                updateProfileUser();
            } else {
                item.setTitle("save");
                update = true;
                enableViews(true);
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateProfileUser() {
        User user = new User();
        Person person = new Person();

        // validar campos

        // actualizar datos de la persona
        //Person person = currentUser.getPerson();

        person.setIdPerson(currentUser.getPerson().getIdPerson());
        person.setIdentification(etIdentification.getText().toString());
        person.setFirstName(etName.getText().toString());
        person.setLastName(etLastName.getText().toString());
        person.setEmail(etEmail.getText().toString());
        person.setPhone(etPhone.getText().toString());

        if (gender) {
            person.setGender("Male");
        } else {
            person.setGender("Female");
        }
        person.setPhoto(currentUser.getPerson().getPhoto());

        // conparar password
        if (etPassword.getText().toString().equalsIgnoreCase(etConfirmPassword.getText().toString())) {
            user.setPassword(etPassword.getText().toString());
        } else {
            //validar
        }
        user.setIdUser(currentUser.getIdUser());
        user.setUserName(currentUser.getUserName());
        user.setPassword(currentUser.getPassword());
        user.setPerson(person);
        user.setRole(currentUser.getRole());

        // update db
        AppClientRealm.insertSingleData(user);
        finish();
    }

}
