/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Dariusz
 */
@Path("newwebservice2port")
public class NewWebService2Port {
    private web_client.NewWebService2 port;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of NewWebService2Port
     */
    public NewWebService2Port() {
        port = getPort();
    }

    /**
     * Invokes the SOAP method updateProduktyOddzialy
     * @param oddzialID resource URI parameter
     * @param produktID resource URI parameter
     * @param ilosc resource URI parameter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    @Path("updateproduktyoddzialy/")
    public String getUpdateProduktyOddzialy(@QueryParam("oddzialID")
            @DefaultValue("0") int oddzialID, @QueryParam("produktID")
            @DefaultValue("0") int produktID, @QueryParam("ilosc")
            @DefaultValue("0") int ilosc) {
        try {
            // Call Web Service Operation
            if (port != null) {
                java.lang.String result = port.updateProduktyOddzialy(oddzialID, produktID, ilosc);
                return result;
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

    /**
     *
     */
    private web_client.NewWebService2 getPort() {
        try {
            // Call Web Service Operation
            web_client.NewWebService2_Service service = new web_client.NewWebService2_Service();
            web_client.NewWebService2 p = service.getNewWebService2Port();
            return p;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }
}
