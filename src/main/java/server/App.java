package server;

import server.controllers.CiudadController;
import server.model.JsonTransformer;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        get(API.Routes.ALL_CITIES,CiudadController::getCities, new JsonTransformer<>());
        post(API.Routes.NEW_CITY,CiudadController::addCity, new JsonTransformer<>());

        // Oracle
       // post(API.Routes.AUTHENTICATE, EmpleadoController::authenticate, new JsonTransformer<>());
    }
}


