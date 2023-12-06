package esprit.exam.datacenter.service;

import esprit.exam.datacenter.entities.DataCenter;
import esprit.exam.datacenter.entities.Utilisateur;
import esprit.exam.datacenter.entities.VirtualMachine;

public interface IExamService {
    public void ajouterUser(Utilisateur utilisateur);
    public void ajouterDc(DataCenter datacenter);
    public int ajouterVm(VirtualMachine vm);
    public void affecterVmuser(int idvm, int iduser);
    public void affecterVm(int idvm);
    public void demarrerInstanceUser(int idvm);
    public void arreterInstanceUser(int idvm);
}
