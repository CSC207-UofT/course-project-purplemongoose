package entity.profiles;

import java.io.Serializable;

/**
 * Defines how Profiles are implemented.
 */
public interface ProfileType extends Serializable {
    String getName();
    String getPhone();
    String getEmail();
    String getUsername();
}
