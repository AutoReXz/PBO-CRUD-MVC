package controller;
import java.util.List;
import DAOdatamv.datamvDAO;
import DAOImplement.datamvimplement;
import model.*;
import view.MainView;

public class Datamvcontroller {
    MainView frame;
    datamvimplement impldatamv;
    List<datamv> dp;
    
    public Datamvcontroller(MainView frame){
        this.frame = frame;
        impldatamv = new datamvDAO();
        dp = impldatamv.getAll();
    }
    public void isitabel(){
        dp = impldatamv.getAll();
        modeltabeldatamv mp = new modeltabeldatamv(dp);
        frame.getTabelDatamv().setModel(mp);
    }
    
    public void insert(){
        try {
            datamv dp = new datamv();
            double alur = Double.parseDouble(frame.getJTxtalur().getText());
            double penokohan = Double.parseDouble(frame.getJtxtpenokohan().getText());
            double akting = Double.parseDouble(frame.getJtxtakting().getText());
            double nilai = (dp.getAlur() + dp.getPenokohan() + dp.getAkting()) / 3;
            dp.setJudul(frame.getJTxtjudul().getText());
            dp.setAlur(alur);
            dp.setPenokohan(penokohan);
            dp.setAkting(akting);
            dp.setNilai(nilai);
            impldatamv.insert(dp);
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Pastikan Anda memasukkan angka untuk Alur, Penokohan, dan Akting.");
        }
    }

    public void update(){
        try {
            datamv dp = new datamv();
            double alur = Double.parseDouble(frame.getJTxtalur().getText());
            double penokohan = Double.parseDouble(frame.getJtxtpenokohan().getText());
            double akting = Double.parseDouble(frame.getJtxtakting().getText());
             double nilai = (alur + penokohan + akting) / 3;
            dp.setJudul(frame.getJTxtjudul().getText());
            dp.setAlur(alur);
            dp.setPenokohan(penokohan);
            dp.setAkting(akting);
            dp.setNilai(nilai);
            impldatamv.update(dp);
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid. Pastikan Anda memasukkan angka untuk Alur, Penokohan, dan Akting.");
        }
    }

    
    public void delete(){
    String judul = frame.getJTxtjudul().getText();
    impldatamv.delete(judul);
    }
}
