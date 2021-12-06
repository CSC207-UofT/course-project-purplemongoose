package entity.profiles;

/**
 * An abstract class which represents mementos of Profiles
 */
public abstract class Memento implements ProfileType{
    public abstract String getName();
    public abstract String getPhone();
    public abstract String getEmail();
    public abstract String getUsername();

}
