package entity.profiles;

import entity.datafiles.Email;
import entity.datafiles.Name;
import entity.datafiles.Phone;

public abstract class Memento {

    public abstract String getName();
    public abstract String getPhone();
    public abstract String getEmail();
    public abstract String getUsername();

}
