package server.controllers;


import model.Ciudad;
import model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.model.ICityService;
import server.model.ImpCityService;
import server.model.JsonTransformer;
import spark.Request;
import spark.Response;

import java.util.List;

public class PersonController {

    static Logger logger = LoggerFactory.getLogger(PersonController.class);

    private static ICityService service = new ImpCityService();
    private static JsonTransformer<Ciudad> jsonTransformer = new JsonTransformer<>();

    public static List<Ciudad> getPersons(Request req, Response res){
        logger.info("Receiving request for all persons");
        return service.getAll();
    }
    public static Result<Ciudad> getCity(Request req, Response res){
        // http://localhost:4567/person?dni=1111
        String name = req.queryParams("name");
        logger.info("Get city with name= " + name);
        Result result = service.get(name);
        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }
        return result;
    }

    public static Result<Ciudad> addCity(Request request, Response res) {
        logger.info("Add new city");

        String body = request.body();
        Ciudad c = jsonTransformer.getObjet(body,Ciudad.class);
        Result<Ciudad> result = service.add(c);

        if(result instanceof Result.Success)
            res.status(200);
        else {
            Result.Error error = (Result.Error)result;
            res.status(error.getCode());
        }

        return result;
    }
}
