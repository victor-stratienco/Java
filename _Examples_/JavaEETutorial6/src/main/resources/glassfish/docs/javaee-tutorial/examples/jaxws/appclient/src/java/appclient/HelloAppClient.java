/*
 * Copyright 2010 Oracle and/or its affiliates.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package appclient;

import helloservice.endpoint.HelloService;
import javax.xml.ws.WebServiceRef;


public class HelloAppClient {
    @WebServiceRef(wsdlLocation = "META-INF/wsdl/localhost_8080/helloservice/HelloService.wsdl")
    private static HelloService service;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(sayHello("world"));
    }

    private static String sayHello(String arg0) {
        helloservice.endpoint.Hello port = service.getHelloPort();

        return port.sayHello(arg0);
    }
}
