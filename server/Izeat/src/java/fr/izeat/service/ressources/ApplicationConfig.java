/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.izeat.service.ressources;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(fr.izeat.service.ressources.ProductResource.class);
        resources.add(fr.izeat.service.ressources.ProductSearchResource.class);
        resources.add(fr.izeat.service.ressources.ServiceResource.class);
        resources.add(fr.izeat.service.ressources.UserLogin.class);
        resources.add(fr.izeat.service.ressources.UserResource.class);
        resources.add(fr.izeat.service.ressources.UserSignUpResource.class);
    }
    
}
