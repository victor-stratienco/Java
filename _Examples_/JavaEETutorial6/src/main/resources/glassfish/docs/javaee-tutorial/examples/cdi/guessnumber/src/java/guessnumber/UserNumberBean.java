/*
 * Copyright 2010 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package guessnumber;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;


@SessionScoped
@Named
public class UserNumberBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    @Random
    Instance<Integer> randomInt;
    private Integer userNumber;
    @Inject
    @MaxNumber
    private int maxNumber;
    private int maximum;
    private int minimum;
    private int number;
    private int remainingGuesses;

    public UserNumberBean() {
    }

    public int getNumber() {
        return number;
    }

    public void setUserNumber(Integer user_number) {
        userNumber = user_number;
    }

    public Integer getUserNumber() {
        return userNumber;
    }

    public int getMaximum() {
        return (this.maximum);
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public int getMinimum() {
        return (this.minimum);
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    public String check() throws InterruptedException {
        if (userNumber > number) {
            maximum = userNumber - 1;
        }

        if (userNumber < number) {
            minimum = userNumber + 1;
        }

        if (userNumber == number) {
            FacesContext.getCurrentInstance()
                        .addMessage(
                    null,
                    new FacesMessage("Correct!"));
        }

        remainingGuesses--;

        return null;
    }

    @PostConstruct
    public void reset() {
        this.minimum = 0;
        this.userNumber = 0;
        this.remainingGuesses = 10;
        this.maximum = maxNumber;
        this.number = randomInt.get();
    }

    public void validateNumberRange(
        FacesContext context,
        UIComponent toValidate,
        Object value) {
        if (remainingGuesses <= 0) {
            FacesMessage message = new FacesMessage("No guesses left!");
            context.addMessage(
                toValidate.getClientId(context),
                message);
            ((UIInput) toValidate).setValid(false);

            return;
        }

        int input = (Integer) value;

        if ((input < minimum) || (input > maximum)) {
            ((UIInput) toValidate).setValid(false);

            FacesMessage message = new FacesMessage("Invalid guess");
            context.addMessage(
                toValidate.getClientId(context),
                message);
        }
    }
}
