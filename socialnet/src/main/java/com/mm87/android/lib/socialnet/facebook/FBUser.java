package com.mm87.android.lib.socialnet.facebook;

/**
 * Created by marco.mendoza on 14/10/2016.
 */

public class FBUser {
    String id;
    String name;
    String email;

    public FBUser() {
    }

    public FBUser(String id, String name, String emal) {
        this.id = id;
        this.name = name;
        this.email = emal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
