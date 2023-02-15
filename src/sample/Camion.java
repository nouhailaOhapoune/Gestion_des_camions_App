package sample;

import java.time.LocalDate;
import java.util.Date;

public class Camion {
    protected  int idCamion;
    protected String Immatriculation;
    protected  String Model;
    protected  int Tar;
    protected String Workplace ;
    protected LocalDate Date_vidange;
    public Camion(int id, String Registration, String Model , LocalDate date, int Tar, String Workplace){
        this.idCamion=id;
        this.Immatriculation=Registration;
        this.Model=Model;
        this.Date_vidange=date;
        this.Tar=Tar;
        this.Workplace=Workplace;

    }

    public void setIdCamion(int idCamion) {
        this.idCamion = idCamion;
    }

    public int getIdCamion() {
        return idCamion;
    }

    public String getImmatriculation() {
        return Immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        Immatriculation = immatriculation;
    }


    public void setModel(String model) {
        Model = model;
    }

    public String getModel() {
        return Model;
    }


    public void setTar(int tar) {
        Tar = tar;
    }

    public int getTar() {
        return Tar;
    }

    public void setDate_vidange(LocalDate date_vidange) {
        Date_vidange = date_vidange;
    }

    public LocalDate getDate_vidange() {
        return Date_vidange;
    }

    public String getWorkplace() {
        return Workplace;
    }

    public void setWorkplace(String workplace) {
        Workplace = workplace;
    }
}
