package esprit.exam.datacenter.service;

import esprit.exam.datacenter.entities.DataCenter;
import esprit.exam.datacenter.entities.Utilisateur;
import esprit.exam.datacenter.entities.VirtualMachine;
import esprit.exam.datacenter.entities.enums.Etat;
import esprit.exam.datacenter.repository.DataCenterRepository;
import esprit.exam.datacenter.repository.UtilisateurRepository;
import esprit.exam.datacenter.repository.VirtualMachineRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService implements IExamService {

    private final UtilisateurRepository utilisateurRepository;
    private final DataCenterRepository dataCenterRepository;
    private final VirtualMachineRepository virtualMachineRepository;

    @Override
    public void ajouterUser(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
    }

    @Override
    public void ajouterDc(DataCenter datacenter) {
       dataCenterRepository.save(datacenter);
    }

    @Override
    public int ajouterVm(VirtualMachine vm) {
        return virtualMachineRepository.save(vm).getIdVm();
    }

    @Override
    public void affecterVmuser(int idvm, int iduser) {
        Utilisateur utilisateur = utilisateurRepository.findById(iduser).orElse(null);
        VirtualMachine virtualMachine = virtualMachineRepository.findById(idvm).orElse(null);
        utilisateur.getVirtualMachines().add(virtualMachine);
       utilisateurRepository.save(utilisateur);

    }
    @Transactional
    @Override
    public void affecterVm(int idvm) {
      VirtualMachine virtualMachine = virtualMachineRepository.findById(idvm).orElse(null);

      for (DataCenter dataCenter : dataCenterRepository.findAll()){
          if (dataCenter.getEspaceLibreDisque() > virtualMachine.getTailleDisque()){


              int espaceLibreAncienne= dataCenter.getEspaceLibreDisque();
              int tailleDiqueVM = virtualMachine.getTailleDisque();
              dataCenter.setEspaceLibreDisque(espaceLibreAncienne - tailleDiqueVM);
              virtualMachine.setDatacenter(dataCenter);
          }
      }

    }

    @Override
    public void demarrerInstanceUser(int idvm) {
        VirtualMachine virtualMachine = virtualMachineRepository.findById(idvm).orElse(null);
        if (virtualMachine != null){
            virtualMachine.setEtat(Etat.Running);
            virtualMachineRepository.save(virtualMachine);
        }
    }
    @Transactional
    @Override
    public void arreterInstanceUser(int idvm) {
        VirtualMachine virtualMachine = virtualMachineRepository.findById(idvm).orElse(null);
        if (virtualMachine != null){
            virtualMachine.setEtat(Etat.Stopped);
            virtualMachineRepository.save(virtualMachine);
        }
    }
}
