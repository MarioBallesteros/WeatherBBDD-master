package server.model;


import model.Ciudad;
import model.MyDataSource;
import model.Result;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpCityService implements ICityService {

 

    @Override
    public List<Ciudad> getAll() {

        List<Ciudad> ciudadList = new ArrayList<>();
        DataSource dataSource = MyDataSource.getMyMariaDBDataSource();

        try(Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from ciudad")){

            String name;
            double lat;
            double lon;
            String img;

            while(resultSet.next()){
                name = resultSet.getString("nombre");
                lat = resultSet.getDouble("lat");
                lon = resultSet.getDouble("lon");
                img = resultSet.getString("img");

                ciudadList.add(new Ciudad(name,lat,lon,img));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return ciudadList;
    }

    @Override
    public Result<Ciudad> get(String name) {


        DataSource dataSource = MyDataSource.getMyMariaDBDataSource();

        try(Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from ciudad where name='"+name+"'")){

            double lat;
            double lon;
            String img;

            if(resultSet.next()){

                name = resultSet.getString("name");
                lat = resultSet.getDouble("lat");
                lon = resultSet.getDouble("lon");
                img = resultSet.getString("img");

                Ciudad ciudad = new Ciudad(name,lat,lon,img);

                return new Result.Success<>(ciudad);

            } else  {
                return new Result.Error(404,"No existe ninguna ciudad con el nombre " + name);
            }


        } catch (SQLSyntaxErrorException sqlee) {
            return new Result.Error(sqlee.getErrorCode(),"Error de acceso a la BD. Consulte con el administrador.");
        }catch (SQLException throwables) {
            return new Result.Error(throwables.getErrorCode(),throwables.getMessage());
        }catch (Exception e) {
            return new Result.Error(1,e.getMessage());
        }
    }

    @Override
    public boolean update(Ciudad person) {
        return false;
    }

    @Override
    public Result<Ciudad> add(Ciudad ciudad) {
        DataSource ds = MyDataSource.getMyMariaDBDataSource();

        try(Connection con = ds.getConnection();
            Statement statement = con.createStatement();) {
            String sql = "INSERT INTO " +
                    "ciudad VALUES ('"+ciudad.getName()+"','"+ciudad.getLat()+"','"+ciudad.getLon()+"',"+ciudad.getImg()+")";

            int count = statement.executeUpdate(sql);
            if(count==1){
                return new Result.Success<Ciudad>(ciudad);
            }else{
                return new Result.Error(404,"No se ha podido añadir la ciudad");
            }
        } catch (SQLException throwables) {
            return new Result.Error(404,throwables.getMessage());
        }
    }
}
