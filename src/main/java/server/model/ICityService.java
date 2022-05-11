package server.model;


import model.Ciudad;
import model.Result;

import java.util.List;

public interface ICityService {

    List<Ciudad> getAll();
    Result<Ciudad> get(String nombre);
    boolean update(Ciudad nombre);
    Result<Ciudad> add(Ciudad ciudad);

}
