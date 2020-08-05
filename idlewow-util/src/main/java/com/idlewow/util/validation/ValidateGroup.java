package com.idlewow.util.validation;

import javax.validation.groups.Default;
import java.io.Serializable;

public class ValidateGroup implements Serializable {
    public interface Create extends Default {
    }

    public interface Update extends Default {

    }
}
