package esprit.exam.datacenter.controller;

import esprit.exam.datacenter.entities.DataCenter;
import esprit.exam.datacenter.entities.Utilisateur;
import esprit.exam.datacenter.entities.VirtualMachine;
import esprit.exam.datacenter.repository.DataCenterRepository;
import esprit.exam.datacenter.service.IExamService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/exam")
public class examController {

    private final IExamService iExamService;
    private final DataCenterRepository dataCenterRepository;

    @PostMapping("/addUser")
    public void addUser(Utilisateur utilisateur){
        iExamService.ajouterUser(utilisateur);
    }

    @PostMapping("/addDC")
    public void addDC(DataCenter dataCenter){
        iExamService.ajouterDc(dataCenter);
    }

    @PostMapping("/addVM")
    public int addVm(VirtualMachine virtualMachine){
       return iExamService.ajouterVm(virtualMachine);
    }
    @PostMapping("/affecter-vm-user/{id-vm}/{id-user}")
    public void affecterVmUser(@PathVariable("id-vm") int idVm, @PathVariable("id-user") int idUser){
        iExamService.affecterVmuser(idVm, idUser);
    }
    @PostMapping("/affecter-vm/{id-vm}")
    public void affecterVm(@PathVariable("id-vm") int idVm){
        iExamService.affecterVm(idVm);
    }

    @PutMapping("/demarrer-vm/{id-vm}")
    public void demarrerVm(@PathVariable("id-vm") int idVm){
        iExamService.demarrerInstanceUser(idVm);;
    }
    @PutMapping("/arreterVm/{id-vm}")
    public void arreterVm(@PathVariable("id-vm") int idVm){
        iExamService.arreterInstanceUser(idVm);;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void listerDatacenter(){
      List<DataCenter>  dataCenters = dataCenterRepository.findAll();
        for (DataCenter dataCenter : dataCenters){
          if (dataCenter.getDateFabrication().isAfter(LocalDate.parse("2019-11-01"))) {
          log.info("data center: "+dataCenter.getRegion());
          }
          for (VirtualMachine virtualMachine : dataCenter.getVms()){
              log.info("OS: "+virtualMachine.getOs());
          }
      }
    }
}
