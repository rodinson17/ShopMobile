package com.shop.shopmobile.core;

import android.app.Application;

import com.google.gson.Gson;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class AppClientRealm {

    public static Realm realm;
    public static Gson gson;

    public static void init(Application application) {

        Realm.init(application);
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getDefaultInstance();
        gson = new Gson();
    }


    // region insert or Update
    public static <T extends RealmObject> void insertSingleData(T data){

        realm.executeTransaction(r -> {
            r.copyToRealmOrUpdate(data);
        });
    }


    public <T extends RealmModel>  void  writeListData(List<T> entl, Class<T> clazz) {
        String datassss= gson.toJson(entl);
        realm.beginTransaction();
        realm.createOrUpdateAllFromJson(clazz,datassss);
        realm.commitTransaction();
    }

    public <T extends RealmModel> void writeSingleData(Object json, Class<T> anyClass) {
        realm.beginTransaction();

        //serializo los datos de la entidad
        String data = gson.toJson(json);

        //guardo los datos en el local storage
        realm.copyToRealmOrUpdate(gson.fromJson(data, anyClass));
        realm.commitTransaction();
    }
/*realm.executeTransaction(new Realm.Transaction() {
    @Override
    public void execute(Realm realm) {
        realm.createObjectFromJson(City.class, "{ city: \"Copenhagen\", id: 1 }");
    }
});



List<User> users = Arrays.asList(new User("John"), new User("Jane"));

realm.beginTransaction();
realm.insert(users);
realm.commitTransaction();


RealmResults<User> result = realm.where(User.class).sort("age").findAll();
*/

    // endregion

    // region consult
    public static <T extends RealmObject> Number getMaxID(Class<T> tClass, String field) {
        return realm.where(tClass).max(field);
    }

    public static <T extends RealmObject> T getUser(Class<T> tClass, String username, String password) {
        return realm.where(tClass).equalTo("userName", username).equalTo("password", password).findFirst();
    }

    public static <T extends RealmObject> T getDataForID(Class<T> tClass, String field, int idData) {
        return realm.where(tClass).equalTo(field, idData).findFirst();
    }

    public static  <T extends RealmObject> RealmResults<T> getAllData(Class<T> anyClass) {
        RealmResults<T> results = realm.where(anyClass).findAll();
        return results;
    }

    public static <T extends RealmObject> RealmResults<T> getAllDataForID(Class<T> tClass, String field, int idClass) {
        return realm.where(tClass).equalTo(field, idClass).findAll();
        //RealmResults<Person> teensWithPups = realm.where(Person.class).between("age", 13, 20).equalTo("dogs.age", 1).findAll();
    }


    /*RealmResults<Person> r1 = realm.where(Person.class)
                             .equalTo("dogs.name", "Fluffy")
                             .equalTo("dogs.color", "Brown")
                             .findAll();

// r2 => [U2]
RealmResults<Person> r2 = realm.where(Person.class)
                             .equalTo("dogs.name", "Fluffy")
                             .findAll()
                             .where()
                             .equalTo("dogs.color", "Brown")
                             .findAll()
                             .where()
                             .equalTo("dogs.color", "Yellow")
                             .findAll();


                             RealmResults<Dog> dogs = realm.where(Dog.class).equalTo("persons.name", "Jane").findAll();

                             */

    // endregion



    public <T extends RealmObject> RealmResults<T> getAllFilter(Class<T> anyClass, String field, int value){
        return realm.where(anyClass).equalTo(field, value).findAll();
    }

    public <T extends RealmObject> RealmObject getEntityById(Class<T> anyClass, int id) {
        return realm.where(anyClass).equalTo("id", id).findFirst();
    }

    public <T extends RealmObject> boolean setEntityData(Class<T> anyClass, String data) {
        realm.beginTransaction();
        realm.copyToRealm(gson.fromJson(data, anyClass));
        realm.commitTransaction();
        return true;
    }

    //clear all objects from generic class
    public <T extends RealmObject> boolean  clearAll(Class<T> clazz) {
        realm.beginTransaction();
        realm.delete(clazz);
        realm.commitTransaction();
        return true;
    }



//    RealmResults<Person> r1 = realm.where(Person.class)
//            .equalTo("dogs.name", "Fluffy")
//            .equalTo("dogs.color", "Brown")
//            .findAll();



//    RealmResults<Person> r2 = realm.where(Person.class)
//            .equalTo("dogs.name", "Fluffy")
//            .findAll()
//            .where()
//            .equalTo("dogs.color", "Brown")
//            .findAll()
//            .where()
//            .equalTo("dogs.color", "Yellow")
//            .findAll();



//    Gson gson = new GsonBuilder().create();
//    String json = "{ name : 'John', email : 'john@corporation.com' }";
//    UserExample user = gson.fromJson(json, UserExample.class);



    // region remove

    // endregion

}
