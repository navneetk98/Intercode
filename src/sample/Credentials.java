package sample;

import java.io.Serializable;

public class Credentials implements Serializable {
    private String uid;
    private String password;
    private String toggle;
    private String name;
    private String branch;
    private String cpi;
    private String rol;

    public Credentials(String uid, String password, String toggle, String rol)
    {
        this.uid=uid;
        this.password=password;
        this.toggle=toggle;
        this.rol=rol;
    }

    public Credentials(String uid, String password, String toggle, String name, String branch, String cpi, String rol)
    {
        this.password=(password);
        this.toggle=(toggle);
        this.uid=(uid);
        this.name = name;
        this.branch=branch;
        this.cpi=cpi;
        this.rol=rol;
    }

    public  String getUid() {
        return uid;
    }


    public String getPassword() {
        return password;
    }


    public String getToggle() {
        return toggle;
    }


    public String getBranch() {
        return branch;
    }

    public String getCpi() {
        return cpi;
    }

    public String getName() {
        return name;
    }

    public String getRol() {
        return rol;
    }

    @Override
    public String toString()
    {
      return String.format("%s,%s,%s",uid,password,toggle);
}
}