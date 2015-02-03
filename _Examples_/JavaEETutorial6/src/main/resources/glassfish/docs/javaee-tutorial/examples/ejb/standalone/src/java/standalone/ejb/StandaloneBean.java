/*
 * Copyright 2010 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package standalone.ejb;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;


/**
 *
 * @author ian
 */
@Stateless
@LocalBean
public class StandaloneBean {
    private static final String message = "Greetings!";

    public String returnMessage() {
        return message;
    }
}
