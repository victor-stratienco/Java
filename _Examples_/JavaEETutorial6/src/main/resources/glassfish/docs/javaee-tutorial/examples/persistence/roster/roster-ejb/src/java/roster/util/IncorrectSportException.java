/*
 * Copyright 2010 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package roster.util;

public class IncorrectSportException extends Exception {
    /**
     * Creates a new instance of <code>IncorrectSportException</code> without detail message.
     */
    public IncorrectSportException() {
    }

    /**
     * Constructs an instance of <code>IncorrectSportException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public IncorrectSportException(String msg) {
        super(msg);
    }
}
