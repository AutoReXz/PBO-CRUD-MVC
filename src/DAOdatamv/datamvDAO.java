package DAOdatamv;
import java.sql.*;
import java.util.*;
import koneksi.connector;
import model.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import DAOImplement.datamvimplement;
public class datamvDAO implements datamvimplement{
    Connection connection;
    
    final String select = "select * from movie;";
    final String insert = "INSERT INTO movie (judul, alur, penokohan, akting, nilai) VALUES (?, ?, ?, ?, ?);";
    final String update = "update movie set judul=?, alur=?, penokohan=?, akting=?, nilai=? where judul=?";
    final String delete = "delete from movie where judul=?";
    public datamvDAO(){
        connection = connector.connection();
    }

    @Override
    public void insert(datamv p) {
        PreparedStatement statement = null;
        try{
            double nilai = (p.getAlur() + p.getPenokohan() + p.getAkting()) / 3;
            statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, p.getJudul());
            statement.setDouble(2, p.getAlur());
            statement.setDouble(3, p.getPenokohan());
            statement.setDouble(4, p.getAkting());
            statement.setDouble(5, nilai);
            statement.executeUpdate();
            /*ResultSet rs = statement.getGeneratedKeys();
            while(rs.next()){
                p.setId(rs.getInt(1));
            }*/
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void update(datamv p) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(update);
            statement.setString(1, p.getJudul());
            statement.setDouble(2, p.getAlur());
            statement.setDouble(3, p.getPenokohan());
            statement.setDouble(4, p.getAkting());
            statement.setDouble(5, p.getNilai());
            statement.setString(6, p.getJudul()); // Parameter untuk WHERE clause
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String judul) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(delete);
            
            statement.setString(1, judul);
            statement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                statement.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<datamv> getAll() {
        List<datamv> dp = null;
        try{
            dp = new ArrayList<datamv>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                datamv mv = new datamv();
                mv.setJudul(rs.getString("judul"));
                mv.setAlur(rs.getDouble("alur"));
                mv.setPenokohan(rs.getDouble("penokohan"));
                mv.setAkting(rs.getDouble("Akting"));
                mv.setNilai(rs.getDouble("Nilai"));
                dp.add(mv);
                
            }
        }catch(SQLException ex){
            Logger.getLogger(datamvDAO.class.getName()).log(Level.SEVERE, null,ex);
        }
        
        return dp;
    }
}
