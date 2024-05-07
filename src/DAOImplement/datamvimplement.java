package DAOImplement;

import java.util.List;
import model.*;
public interface datamvimplement {

    public void insert(datamv p);
    public void update(datamv p);
    public void delete(String judul);
    public List<datamv> getAll();
    
}
