package entity.profiles;

import java.io.Serializable;

/**
 * Defines how Person and Organization and their subclasses
 * are implemented.
 */

public interface ProfileType extends Serializable {
    String getName();
    String getPhone();
    String getEmail();
    String getUsername();
    Memento createMemento();
}
