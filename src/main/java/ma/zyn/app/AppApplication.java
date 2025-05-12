package ma.zyn.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;


import ma.zyn.app.zynerator.security.bean.*;
import ma.zyn.app.zynerator.security.common.AuthoritiesConstants;
import ma.zyn.app.zynerator.security.service.facade.*;

import ma.zyn.app.bean.core.reclamation.TypeReclamation;
import ma.zyn.app.service.facade.admin.reclamation.TypeReclamationAdminService;
import ma.zyn.app.bean.core.referentiel.Axe;
import ma.zyn.app.service.facade.admin.referentiel.AxeAdminService;
import ma.zyn.app.bean.core.referentiel.EtatDemande;
import ma.zyn.app.service.facade.admin.referentiel.EtatDemandeAdminService;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import ma.zyn.app.service.facade.admin.referentiel.ProduitMarchandAdminService;
import ma.zyn.app.bean.core.camion.DestinationCamion;
import ma.zyn.app.service.facade.admin.camion.DestinationCamionAdminService;
import ma.zyn.app.bean.core.referentiel.TypeWagon;
import ma.zyn.app.service.facade.admin.referentiel.TypeWagonAdminService;
import ma.zyn.app.bean.core.referentiel.ProvennanceTrain;
import ma.zyn.app.service.facade.admin.referentiel.ProvennanceTrainAdminService;
import ma.zyn.app.bean.core.referentiel.Moyen;
import ma.zyn.app.service.facade.admin.referentiel.MoyenAdminService;
import ma.zyn.app.bean.core.navire.DestinationNavire;
import ma.zyn.app.service.facade.admin.navire.DestinationNavireAdminService;
import ma.zyn.app.bean.core.referentiel.StatusScenarioFlux;
import ma.zyn.app.service.facade.admin.referentiel.StatusScenarioFluxAdminService;
import ma.zyn.app.bean.core.aleas.CauseArret;
import ma.zyn.app.service.facade.admin.aleas.CauseArretAdminService;
import ma.zyn.app.bean.core.camion.ProvennanceCamion;
import ma.zyn.app.service.facade.admin.camion.ProvennanceCamionAdminService;
import ma.zyn.app.bean.core.referentiel.ElementChimique;
import ma.zyn.app.service.facade.admin.referentiel.ElementChimiqueAdminService;
import ma.zyn.app.bean.core.referentiel.ProduitSource;
import ma.zyn.app.service.facade.admin.referentiel.ProduitSourceAdminService;
import ma.zyn.app.bean.core.referentiel.StatusExercice;
import ma.zyn.app.service.facade.admin.referentiel.StatusExerciceAdminService;
import ma.zyn.app.bean.core.referentiel.Site;
import ma.zyn.app.service.facade.admin.referentiel.SiteAdminService;
import ma.zyn.app.bean.core.referentiel.TypeDemande;
import ma.zyn.app.service.facade.admin.referentiel.TypeDemandeAdminService;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.service.facade.admin.referentiel.StadeOperatoireAdminService;
import ma.zyn.app.bean.core.referentiel.OperationStadeOperatoire;
import ma.zyn.app.service.facade.admin.referentiel.OperationStadeOperatoireAdminService;
import ma.zyn.app.bean.core.referentiel.TypeExpedition;
import ma.zyn.app.service.facade.admin.referentiel.TypeExpeditionAdminService;
import ma.zyn.app.bean.core.reclamation.MotifReclamation;
import ma.zyn.app.service.facade.admin.reclamation.MotifReclamationAdminService;
import ma.zyn.app.bean.core.referentiel.CategorieStock;
import ma.zyn.app.service.facade.admin.referentiel.CategorieStockAdminService;
import ma.zyn.app.bean.core.reclamation.EtatReclamation;
import ma.zyn.app.service.facade.admin.reclamation.EtatReclamationAdminService;
import ma.zyn.app.bean.core.referentiel.Entite;
import ma.zyn.app.service.facade.admin.referentiel.EntiteAdminService;
import ma.zyn.app.bean.core.referentiel.Unite;
import ma.zyn.app.service.facade.admin.referentiel.UniteAdminService;
import ma.zyn.app.bean.core.reclamation.ActionEntreprise;
import ma.zyn.app.service.facade.admin.reclamation.ActionEntrepriseAdminService;
import ma.zyn.app.bean.core.referentiel.TypeStock;
import ma.zyn.app.service.facade.admin.referentiel.TypeStockAdminService;
import ma.zyn.app.bean.core.referentiel.DestinationTrain;
import ma.zyn.app.service.facade.admin.referentiel.DestinationTrainAdminService;
import ma.zyn.app.bean.core.referentiel.TypeEngin;
import ma.zyn.app.service.facade.admin.referentiel.TypeEnginAdminService;
import ma.zyn.app.bean.core.referentiel.CategorieUnite;
import ma.zyn.app.service.facade.admin.referentiel.CategorieUniteAdminService;
import ma.zyn.app.bean.core.referentiel.Consommable;
import ma.zyn.app.service.facade.admin.referentiel.ConsommableAdminService;
import ma.zyn.app.bean.core.referentiel.ConsommationSpecifique;
import ma.zyn.app.service.facade.admin.referentiel.ConsommationSpecifiqueAdminService;
import ma.zyn.app.bean.core.referentiel.Panneau;
import ma.zyn.app.service.facade.admin.referentiel.PanneauAdminService;
import ma.zyn.app.bean.core.referentiel.Tranchee;
import ma.zyn.app.service.facade.admin.referentiel.TrancheeAdminService;
import ma.zyn.app.bean.core.referentiel.TypeClient;
import ma.zyn.app.service.facade.admin.referentiel.TypeClientAdminService;

import ma.zyn.app.zynerator.security.bean.User;
import ma.zyn.app.zynerator.security.bean.Role;

@SpringBootApplication
//@EnableFeignClients
public class AppApplication {
    public static ConfigurableApplicationContext ctx;


    //state: primary success info secondary warning danger contrast
    //_STATE(Pending=warning,Rejeted=danger,Validated=success)
    public static void main(String[] args) {
        ctx=SpringApplication.run(AppApplication.class, args);
    }


    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }

    @Bean
    public CommandLineRunner demo(UserService userService, RoleService roleService, ModelPermissionService modelPermissionService, ActionPermissionService actionPermissionService, ModelPermissionUserService modelPermissionUserService ) {
    return (args) -> {
        if(true){

            createTypeReclamation();
            createAxe();
            createEtatDemande();
            createProduitMarchand();
            createDestinationCamion();
            createTypeWagon();
            createProvennanceTrain();
            createMoyen();
            createDestinationNavire();
            createStatusScenarioFlux();
            createCauseArret();
            createProvennanceCamion();
            createElementChimique();
            createProduitSource();
            createStatusExercice();
            createSite();
            createTypeDemande();
            createStadeOperatoire();
            createOperationStadeOperatoire();
            createTypeExpedition();
            createMotifReclamation();
            createCategorieStock();
            createEtatReclamation();
            createEntite();
            createUnite();
            createActionEntreprise();
            createTypeStock();
            createDestinationTrain();
            createTypeEngin();
            createCategorieUnite();
            createConsommable();
            createConsommationSpecifique();
            createPanneau();
            createTranchee();
            createTypeClient();

        /*
        List<ModelPermission> modelPermissions = new ArrayList<>();
        addPermission(modelPermissions);
        modelPermissions.forEach(e -> modelPermissionService.create(e));
        List<ActionPermission> actionPermissions = new ArrayList<>();
        addActionPermission(actionPermissions);
        actionPermissions.forEach(e -> actionPermissionService.create(e));
        */

		// User Admin
        User userForAdmin = new User("admin");
		userForAdmin.setPassword("123");
		// Role Admin
		Role roleForAdmin = new Role();
		roleForAdmin.setAuthority(AuthoritiesConstants.ADMIN);
		roleForAdmin.setCreatedAt(LocalDateTime.now());
		Role roleForAdminSaved = roleService.create(roleForAdmin);
		RoleUser roleUserForAdmin = new RoleUser();
		roleUserForAdmin.setRole(roleForAdminSaved);
		if (userForAdmin.getRoleUsers() == null)
			userForAdmin.setRoleUsers(new ArrayList<>());

		userForAdmin.getRoleUsers().add(roleUserForAdmin);


        userForAdmin.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        userService.create(userForAdmin);

            }
        };
    }



    private void createTypeReclamation(){
            TypeReclamation itemPrimary = new TypeReclamation();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Fondé");
            itemPrimary.setCode("Fondé");
            typeReclamationService.create(itemPrimary);

    }
    private void createAxe(){
            Axe itemSecondary = new Axe();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("BG");
            itemSecondary.setCode("BG");
            axeService.create(itemSecondary);
            Axe itemPrimary = new Axe();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("YFIA");
            itemPrimary.setCode("YFIA");
            axeService.create(itemPrimary);

    }
    private void createEtatDemande(){
            EtatDemande itemPrimary = new EtatDemande();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("En cours");
            itemPrimary.setCode("En cours");
            etatDemandeService.create(itemPrimary);
            EtatDemande itemSecondary = new EtatDemande();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("Cloture");
            itemSecondary.setCode("Cloture");
            etatDemandeService.create(itemSecondary);
            EtatDemande itemDanger = new EtatDemande();
            itemDanger.setStyle("danger");
            itemDanger.setLibelle("Accepte");
            itemDanger.setCode("Accepte");
            etatDemandeService.create(itemDanger);

    }
    private void createProduitMarchand(){
            ProduitMarchand itemPrimary = new ProduitMarchand();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("G10");
            itemPrimary.setCode("G10");
            produitMarchandService.create(itemPrimary);
            ProduitMarchand itemDanger = new ProduitMarchand();
            itemDanger.setStyle("danger");
            itemDanger.setLibelle("BG22");
            itemDanger.setCode("BG22");
            produitMarchandService.create(itemDanger);
            ProduitMarchand itemInfo = new ProduitMarchand();
            itemInfo.setStyle("info");
            itemInfo.setLibelle("BG10");
            itemInfo.setCode("BG10");
            produitMarchandService.create(itemInfo);
            ProduitMarchand itemSuccess = new ProduitMarchand();
            itemSuccess.setStyle("success");
            itemSuccess.setLibelle("Ycc01");
            itemSuccess.setCode("Ycc01");
            produitMarchandService.create(itemSuccess);

    }
    private void createDestinationCamion(){
            DestinationCamion itemPink = new DestinationCamion();
            itemPink.setStyle("pink");
            itemPink.setLibelle("Port-Safi");
            itemPink.setCode("Port-Safi");
            destinationCamionService.create(itemPink);
            DestinationCamion itemSuccess = new DestinationCamion();
            itemSuccess.setStyle("success");
            itemSuccess.setLibelle("Safi-MPI");
            itemSuccess.setCode("Safi-MPI");
            destinationCamionService.create(itemSuccess);
            DestinationCamion itemSecondary = new DestinationCamion();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("Youssoufia-Calcination");
            itemSecondary.setCode("Youssoufia-Calcination");
            destinationCamionService.create(itemSecondary);
            DestinationCamion itemInfo = new DestinationCamion();
            itemInfo.setStyle("info");
            itemInfo.setLibelle("Safi-MPII");
            itemInfo.setCode("Safi-MPII");
            destinationCamionService.create(itemInfo);
            DestinationCamion itemBlack = new DestinationCamion();
            itemBlack.setStyle("black");
            itemBlack.setLibelle("Jorf-Lasfar");
            itemBlack.setCode("Jorf-Lasfar");
            destinationCamionService.create(itemBlack);
            DestinationCamion itemDark = new DestinationCamion();
            itemDark.setStyle("dark");
            itemDark.setLibelle("Safi-MC");
            itemDark.setCode("Safi-MC");
            destinationCamionService.create(itemDark);
            DestinationCamion itemPrimary = new DestinationCamion();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Youssoufia-Laverie");
            itemPrimary.setCode("Youssoufia-Laverie");
            destinationCamionService.create(itemPrimary);
            DestinationCamion itemGreen = new DestinationCamion();
            itemGreen.setStyle("green");
            itemGreen.setLibelle("Port-Jorf");
            itemGreen.setCode("Port-Jorf");
            destinationCamionService.create(itemGreen);

    }
    private void createTypeWagon(){
            TypeWagon itemPrimary = new TypeWagon();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("UADS");
            itemPrimary.setCode("UADS");
            typeWagonService.create(itemPrimary);
            TypeWagon itemSecondary = new TypeWagon();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("TAOUS");
            itemSecondary.setCode("TAOUS");
            typeWagonService.create(itemSecondary);

    }
    private void createProvennanceTrain(){
            ProvennanceTrain itemDark = new ProvennanceTrain();
            itemDark.setStyle("dark");
            itemDark.setLibelle("Benguerir");
            itemDark.setCode("Benguerir");
            provennanceTrainService.create(itemDark);
            ProvennanceTrain itemPrimary = new ProvennanceTrain();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Youssoufia-Sechage");
            itemPrimary.setCode("Youssoufia-Sechage");
            provennanceTrainService.create(itemPrimary);
            ProvennanceTrain itemSecondary = new ProvennanceTrain();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("Youssoufia-Calcination");
            itemSecondary.setCode("Youssoufia-Calcination");
            provennanceTrainService.create(itemSecondary);

    }
    private void createMoyen(){
            Moyen itemDanger = new Moyen();
            itemDanger.setStyle("danger");
            itemDanger.setLibelle("EE");
            itemDanger.setCode("EE");
            moyenService.create(itemDanger);
            Moyen itemPrimary = new Moyen();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("OCP");
            itemPrimary.setCode("OCP");
            moyenService.create(itemPrimary);

    }
    private void createDestinationNavire(){
            DestinationNavire itemPink = new DestinationNavire();
            itemPink.setStyle("pink");
            itemPink.setLibelle("Port-Safi");
            itemPink.setCode("Port-Safi");
            destinationNavireService.create(itemPink);
            DestinationNavire itemSuccess = new DestinationNavire();
            itemSuccess.setStyle("success");
            itemSuccess.setLibelle("Safi-MPI");
            itemSuccess.setCode("Safi-MPI");
            destinationNavireService.create(itemSuccess);
            DestinationNavire itemSecondary = new DestinationNavire();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("Youssoufia-Calcination");
            itemSecondary.setCode("Youssoufia-Calcination");
            destinationNavireService.create(itemSecondary);
            DestinationNavire itemInfo = new DestinationNavire();
            itemInfo.setStyle("info");
            itemInfo.setLibelle("Safi-MPII");
            itemInfo.setCode("Safi-MPII");
            destinationNavireService.create(itemInfo);
            DestinationNavire itemBlack = new DestinationNavire();
            itemBlack.setStyle("black");
            itemBlack.setLibelle("Jorf-Lasfar");
            itemBlack.setCode("Jorf-Lasfar");
            destinationNavireService.create(itemBlack);
            DestinationNavire itemDark = new DestinationNavire();
            itemDark.setStyle("dark");
            itemDark.setLibelle("Safi-MC");
            itemDark.setCode("Safi-MC");
            destinationNavireService.create(itemDark);
            DestinationNavire itemPrimary = new DestinationNavire();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Youssoufia-Laverie");
            itemPrimary.setCode("Youssoufia-Laverie");
            destinationNavireService.create(itemPrimary);
            DestinationNavire itemGreen = new DestinationNavire();
            itemGreen.setStyle("green");
            itemGreen.setLibelle("Port-Jorf");
            itemGreen.setCode("Port-Jorf");
            destinationNavireService.create(itemGreen);

    }
    private void createStatusScenarioFlux(){
            StatusScenarioFlux itemPrimary = new StatusScenarioFlux();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Valide");
            itemPrimary.setCode("Valide");
            statusScenarioFluxService.create(itemPrimary);
            StatusScenarioFlux itemWarnning = new StatusScenarioFlux();
            itemWarnning.setStyle("warnning");
            itemWarnning.setLibelle("En cours analyse");
            itemWarnning.setCode("En cours analyse");
            statusScenarioFluxService.create(itemWarnning);
            StatusScenarioFlux itemDanger = new StatusScenarioFlux();
            itemDanger.setStyle("danger");
            itemDanger.setLibelle("Actualise");
            itemDanger.setCode("Actualise");
            statusScenarioFluxService.create(itemDanger);
            StatusScenarioFlux itemSecondry = new StatusScenarioFlux();
            itemSecondry.setStyle("secondry");
            itemSecondry.setLibelle("Enregistre");
            itemSecondry.setCode("Enregistre");
            statusScenarioFluxService.create(itemSecondry);

    }
    private void createCauseArret(){
            CauseArret itemPrimary = new CauseArret();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Panne decanteur");
            itemPrimary.setCode("Panne decanteur");
            causeArretService.create(itemPrimary);

    }
    private void createProvennanceCamion(){
            ProvennanceCamion itemDark = new ProvennanceCamion();
            itemDark.setStyle("dark");
            itemDark.setLibelle("Benguerir");
            itemDark.setCode("Benguerir");
            provennanceCamionService.create(itemDark);
            ProvennanceCamion itemPrimary = new ProvennanceCamion();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Youssoufia-Sechage");
            itemPrimary.setCode("Youssoufia-Sechage");
            provennanceCamionService.create(itemPrimary);
            ProvennanceCamion itemSecondary = new ProvennanceCamion();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("Youssoufia-Calcination");
            itemSecondary.setCode("Youssoufia-Calcination");
            provennanceCamionService.create(itemSecondary);

    }
    private void createElementChimique(){
            ElementChimique itemPrimary = new ElementChimique();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Phosphate");
            itemPrimary.setCode("Phosphate");
            elementChimiqueService.create(itemPrimary);
            ElementChimique itemSecondary = new ElementChimique();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("Nitrogene");
            itemSecondary.setCode("Nitrogene");
            elementChimiqueService.create(itemSecondary);

    }
    private void createProduitSource(){
            ProduitSource itemInfo = new ProduitSource();
            itemInfo.setStyle("info");
            itemInfo.setLibelle("BT");
            itemInfo.setCode("BT");
            produitSourceService.create(itemInfo);
            ProduitSource itemPrimary = new ProduitSource();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("TBT");
            itemPrimary.setCode("TBT");
            produitSourceService.create(itemPrimary);
            ProduitSource itemDanger = new ProduitSource();
            itemDanger.setStyle("danger");
            itemDanger.setLibelle("MT");
            itemDanger.setCode("MT");
            produitSourceService.create(itemDanger);

    }
    private void createStatusExercice(){
            StatusExercice itemPrimary = new StatusExercice();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("En cours");
            itemPrimary.setCode("En cours");
            statusExerciceService.create(itemPrimary);
            StatusExercice itemDanger = new StatusExercice();
            itemDanger.setStyle("danger");
            itemDanger.setLibelle("Cloture");
            itemDanger.setCode("Cloture");
            statusExerciceService.create(itemDanger);

    }
    private void createSite(){
            Site itemPrimary = new Site();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Ben Geurir");
            itemPrimary.setCode("Ben Geurir");
            siteService.create(itemPrimary);
            Site itemInfo = new Site();
            itemInfo.setStyle("info");
            itemInfo.setLibelle("Youssefia");
            itemInfo.setCode("Youssefia");
            siteService.create(itemInfo);

    }
    private void createTypeDemande(){
            TypeDemande itemDanger = new TypeDemande();
            itemDanger.setStyle("danger");
            itemDanger.setLibelle("Imprevue");
            itemDanger.setCode("Imprevue");
            typeDemandeService.create(itemDanger);
            TypeDemande itemPrimary = new TypeDemande();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Normale");
            itemPrimary.setCode("Normale");
            typeDemandeService.create(itemPrimary);

    }
    private void createStadeOperatoire(){
            StadeOperatoire itemSecondary = new StadeOperatoire();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("Lavage");
            itemSecondary.setCode("Lavage");
            stadeOperatoireService.create(itemSecondary);
            StadeOperatoire itemDark = new StadeOperatoire();
            itemDark.setStyle("dark");
            itemDark.setLibelle("Criblage");
            itemDark.setCode("Criblage");
            stadeOperatoireService.create(itemDark);
            StadeOperatoire itemPrimary = new StadeOperatoire();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Defruitage");
            itemPrimary.setCode("Defruitage");
            stadeOperatoireService.create(itemPrimary);

    }
    private void createOperationStadeOperatoire(){
            OperationStadeOperatoire itemSecondary = new OperationStadeOperatoire();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("Chargement");
            itemSecondary.setCode("Chargement");
            operationStadeOperatoireService.create(itemSecondary);
            OperationStadeOperatoire itemPrimary = new OperationStadeOperatoire();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Traitement");
            itemPrimary.setCode("Traitement");
            operationStadeOperatoireService.create(itemPrimary);
            OperationStadeOperatoire itemDanger = new OperationStadeOperatoire();
            itemDanger.setStyle("danger");
            itemDanger.setLibelle("Transport");
            itemDanger.setCode("Transport");
            operationStadeOperatoireService.create(itemDanger);

    }
    private void createTypeExpedition(){
            TypeExpedition itemPrimary = new TypeExpedition();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Locale");
            itemPrimary.setCode("Locale");
            typeExpeditionService.create(itemPrimary);
            TypeExpedition itemDanger = new TypeExpedition();
            itemDanger.setStyle("danger");
            itemDanger.setLibelle("Port");
            itemDanger.setCode("Port");
            typeExpeditionService.create(itemDanger);

    }
    private void createMotifReclamation(){
            MotifReclamation itemPrimary = new MotifReclamation();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("MGO eleve");
            itemPrimary.setCode("MGO eleve");
            motifReclamationService.create(itemPrimary);

    }
    private void createCategorieStock(){
            CategorieStock itemPrimary = new CategorieStock();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Mort");
            itemPrimary.setCode("Mort");
            categorieStockService.create(itemPrimary);
            CategorieStock itemSecondary = new CategorieStock();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("Vif");
            itemSecondary.setCode("Vif");
            categorieStockService.create(itemSecondary);

    }
    private void createEtatReclamation(){
            EtatReclamation itemPrimary = new EtatReclamation();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Traité");
            itemPrimary.setCode("Traité");
            etatReclamationService.create(itemPrimary);

    }
    private void createEntite(){
            Entite itemPrimary = new Entite();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Mine Mzinda");
            itemPrimary.setCode("Mine Mzinda");
            entiteService.create(itemPrimary);
            Entite itemSecondary = new Entite();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("Mine Bouchane");
            itemSecondary.setCode("Mine Bouchane");
            entiteService.create(itemSecondary);
            Entite itemDanger = new Entite();
            itemDanger.setStyle("danger");
            itemDanger.setLibelle("Laverie Benguerir");
            itemDanger.setCode("Laverie Benguerir");
            entiteService.create(itemDanger);
            Entite itemDark = new Entite();
            itemDark.setStyle("dark");
            itemDark.setLibelle("Calcination");
            itemDark.setCode("Calcination");
            entiteService.create(itemDark);
            Entite itemInfo = new Entite();
            itemInfo.setStyle("info");
            itemInfo.setLibelle("Sechage");
            itemInfo.setCode("Sechage");
            entiteService.create(itemInfo);
            Entite itemSuccess = new Entite();
            itemSuccess.setStyle("success");
            itemSuccess.setLibelle("Mine Youssoufia");
            itemSuccess.setCode("Mine Youssoufia");
            entiteService.create(itemSuccess);
            Entite itemWarning = new Entite();
            itemWarning.setStyle("warning");
            itemWarning.setLibelle("Laverie Youssoufia");
            itemWarning.setCode("Laverie Youssoufia");
            entiteService.create(itemWarning);

    }
    private void createUnite(){
            Unite itemPrimary = new Unite();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Metre cube");
            itemPrimary.setCode("Metre cube");
            uniteService.create(itemPrimary);
            Unite itemSecondary = new Unite();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("KW");
            itemSecondary.setCode("KW");
            uniteService.create(itemSecondary);

    }
    private void createActionEntreprise(){
            ActionEntreprise itemPrimary = new ActionEntreprise();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Mise a jour Planing");
            itemPrimary.setCode("Mise a jour Planing");
            actionEntrepriseService.create(itemPrimary);
            ActionEntreprise itemDanger = new ActionEntreprise();
            itemDanger.setStyle("danger");
            itemDanger.setLibelle("Reclassement");
            itemDanger.setCode("Reclassement");
            actionEntrepriseService.create(itemDanger);

    }
    private void createTypeStock(){
            TypeStock itemPrimary = new TypeStock();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Mort");
            itemPrimary.setCode("Mort");
            typeStockService.create(itemPrimary);
            TypeStock itemSecondary = new TypeStock();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("Vif");
            itemSecondary.setCode("Vif");
            typeStockService.create(itemSecondary);

    }
    private void createDestinationTrain(){
            DestinationTrain itemPink = new DestinationTrain();
            itemPink.setStyle("pink");
            itemPink.setLibelle("Port-Safi");
            itemPink.setCode("Port-Safi");
            destinationTrainService.create(itemPink);
            DestinationTrain itemSuccess = new DestinationTrain();
            itemSuccess.setStyle("success");
            itemSuccess.setLibelle("Safi-MPI");
            itemSuccess.setCode("Safi-MPI");
            destinationTrainService.create(itemSuccess);
            DestinationTrain itemSecondary = new DestinationTrain();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("Youssoufia-Calcination");
            itemSecondary.setCode("Youssoufia-Calcination");
            destinationTrainService.create(itemSecondary);
            DestinationTrain itemInfo = new DestinationTrain();
            itemInfo.setStyle("info");
            itemInfo.setLibelle("Safi-MPII");
            itemInfo.setCode("Safi-MPII");
            destinationTrainService.create(itemInfo);
            DestinationTrain itemBlack = new DestinationTrain();
            itemBlack.setStyle("black");
            itemBlack.setLibelle("Jorf-Lasfar");
            itemBlack.setCode("Jorf-Lasfar");
            destinationTrainService.create(itemBlack);
            DestinationTrain itemDark = new DestinationTrain();
            itemDark.setStyle("dark");
            itemDark.setLibelle("Safi-MC");
            itemDark.setCode("Safi-MC");
            destinationTrainService.create(itemDark);
            DestinationTrain itemPrimary = new DestinationTrain();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Youssoufia-Laverie");
            itemPrimary.setCode("Youssoufia-Laverie");
            destinationTrainService.create(itemPrimary);
            DestinationTrain itemGreen = new DestinationTrain();
            itemGreen.setStyle("green");
            itemGreen.setLibelle("Port-Jorf");
            itemGreen.setCode("Port-Jorf");
            destinationTrainService.create(itemGreen);

    }
    private void createTypeEngin(){
            TypeEngin itemPrimary = new TypeEngin();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Chargeuse");
            itemPrimary.setCode("Chargeuse");
            typeEnginService.create(itemPrimary);
            TypeEngin itemSecondary = new TypeEngin();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("Roue-pelle");
            itemSecondary.setCode("Roue-pelle");
            typeEnginService.create(itemSecondary);
            TypeEngin itemPink = new TypeEngin();
            itemPink.setStyle("pink");
            itemPink.setLibelle("Camion");
            itemPink.setCode("Camion");
            typeEnginService.create(itemPink);
            TypeEngin itemInfo = new TypeEngin();
            itemInfo.setStyle("info");
            itemInfo.setLibelle("Convoyeur");
            itemInfo.setCode("Convoyeur");
            typeEnginService.create(itemInfo);
            TypeEngin itemDark = new TypeEngin();
            itemDark.setStyle("dark");
            itemDark.setLibelle("Train");
            itemDark.setCode("Train");
            typeEnginService.create(itemDark);

    }
    private void createCategorieUnite(){
            CategorieUnite itemPrimary = new CategorieUnite();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Volume");
            itemPrimary.setCode("Volume");
            categorieUniteService.create(itemPrimary);
            CategorieUnite itemDanger = new CategorieUnite();
            itemDanger.setStyle("danger");
            itemDanger.setLibelle("Qualité");
            itemDanger.setCode("Qualité");
            categorieUniteService.create(itemDanger);
            CategorieUnite itemSecondary = new CategorieUnite();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("Cout");
            itemSecondary.setCode("Cout");
            categorieUniteService.create(itemSecondary);

    }
    private void createConsommable(){
            Consommable itemPrimary = new Consommable();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Ester");
            itemPrimary.setCode("Ester");
            consommableService.create(itemPrimary);
            Consommable itemSecondary = new Consommable();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("Amine");
            itemSecondary.setCode("Amine");
            consommableService.create(itemSecondary);

    }
    private void createConsommationSpecifique(){
            ConsommationSpecifique itemPrimary = new ConsommationSpecifique();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Ester");
            itemPrimary.setCode("Ester");
            consommationSpecifiqueService.create(itemPrimary);
            ConsommationSpecifique itemSecondary = new ConsommationSpecifique();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("Amine");
            itemSecondary.setCode("Amine");
            consommationSpecifiqueService.create(itemSecondary);

    }
    private void createPanneau(){
            Panneau itemSecondary = new Panneau();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("Lavage");
            itemSecondary.setCode("Lavage");
            panneauService.create(itemSecondary);
            Panneau itemPrimary = new Panneau();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Criblage");
            itemPrimary.setCode("Criblage");
            panneauService.create(itemPrimary);

    }
    private void createTranchee(){
            Tranchee itemSecondary = new Tranchee();
            itemSecondary.setStyle("secondary");
            itemSecondary.setLibelle("Lavage");
            itemSecondary.setCode("Lavage");
            trancheeService.create(itemSecondary);
            Tranchee itemPrimary = new Tranchee();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Criblage");
            itemPrimary.setCode("Criblage");
            trancheeService.create(itemPrimary);

    }
    private void createTypeClient(){
            TypeClient itemDanger = new TypeClient();
            itemDanger.setStyle("danger");
            itemDanger.setLibelle("Externe");
            itemDanger.setCode("Externe");
            typeClientService.create(itemDanger);
            TypeClient itemPrimary = new TypeClient();
            itemPrimary.setStyle("primary");
            itemPrimary.setLibelle("Interne");
            itemPrimary.setCode("Interne");
            typeClientService.create(itemPrimary);

    }

    private static String fakeString(String attributeName, int i) {
        return attributeName + i;
    }

    private static Long fakeLong(String attributeName, int i) {
        return  10L * i;
    }
    private static Integer fakeInteger(String attributeName, int i) {
        return  10 * i;
    }

    private static Double fakeDouble(String attributeName, int i) {
        return 10D * i;
    }

    private static BigDecimal fakeBigDecimal(String attributeName, int i) {
        return  BigDecimal.valueOf(i*1L*10);
    }

    private static Boolean fakeBoolean(String attributeName, int i) {
        return i % 2 == 0 ? true : false;
    }
    private static LocalDateTime fakeLocalDateTime(String attributeName, int i) {
        return LocalDateTime.now().plusDays(i);
    }


    private static void addPermission(List<ModelPermission> modelPermissions) {
        modelPermissions.add(new ModelPermission("TypeReclamation"));
        modelPermissions.add(new ModelPermission("Axe"));
        modelPermissions.add(new ModelPermission("EtatDemande"));
        modelPermissions.add(new ModelPermission("ProduitMarchand"));
        modelPermissions.add(new ModelPermission("RealisationTrainProduit"));
        modelPermissions.add(new ModelPermission("DestinationCamion"));
        modelPermissions.add(new ModelPermission("TauxRendementStadeOperatoire"));
        modelPermissions.add(new ModelPermission("RealisationNavire"));
        modelPermissions.add(new ModelPermission("TypeWagon"));
        modelPermissions.add(new ModelPermission("ArretNonPlanifie"));
        modelPermissions.add(new ModelPermission("ProvennanceTrain"));
        modelPermissions.add(new ModelPermission("Moyen"));
        modelPermissions.add(new ModelPermission("DestinationNavire"));
        modelPermissions.add(new ModelPermission("StatusScenarioFlux"));
        modelPermissions.add(new ModelPermission("SuiviStock"));
        modelPermissions.add(new ModelPermission("CauseArret"));
        modelPermissions.add(new ModelPermission("RealisationCamionProduit"));
        modelPermissions.add(new ModelPermission("ProvennanceCamion"));
        modelPermissions.add(new ModelPermission("ChartePhysique"));
        modelPermissions.add(new ModelPermission("ElementChimique"));
        modelPermissions.add(new ModelPermission("CoutStade"));
        modelPermissions.add(new ModelPermission("ProduitSource"));
        modelPermissions.add(new ModelPermission("RealisationTrain"));
        modelPermissions.add(new ModelPermission("StatusExercice"));
        modelPermissions.add(new ModelPermission("Site"));
        modelPermissions.add(new ModelPermission("Niveau"));
        modelPermissions.add(new ModelPermission("Client"));
        modelPermissions.add(new ModelPermission("Model"));
        modelPermissions.add(new ModelPermission("PlanDisponibilite"));
        modelPermissions.add(new ModelPermission("TypeDemande"));
        modelPermissions.add(new ModelPermission("StadeOperatoireProduit"));
        modelPermissions.add(new ModelPermission("Exercice"));
        modelPermissions.add(new ModelPermission("CoutConsommable"));
        modelPermissions.add(new ModelPermission("StadeOperatoire"));
        modelPermissions.add(new ModelPermission("OperationStadeOperatoire"));
        modelPermissions.add(new ModelPermission("ConsommableStadeOperatoire"));
        modelPermissions.add(new ModelPermission("Liaison"));
        modelPermissions.add(new ModelPermission("TypeExpedition"));
        modelPermissions.add(new ModelPermission("ReclamationElementChimique"));
        modelPermissions.add(new ModelPermission("MotifReclamation"));
        modelPermissions.add(new ModelPermission("RealisationCamion"));
        modelPermissions.add(new ModelPermission("CategorieStock"));
        modelPermissions.add(new ModelPermission("EtatReclamation"));
        modelPermissions.add(new ModelPermission("CoutEngin"));
        modelPermissions.add(new ModelPermission("RatioUnite"));
        modelPermissions.add(new ModelPermission("Entite"));
        modelPermissions.add(new ModelPermission("Unite"));
        modelPermissions.add(new ModelPermission("Stock"));
        modelPermissions.add(new ModelPermission("RealisationNavireQualite"));
        modelPermissions.add(new ModelPermission("CharteChimique"));
        modelPermissions.add(new ModelPermission("RealisationNavireProduit"));
        modelPermissions.add(new ModelPermission("ActionEntreprise"));
        modelPermissions.add(new ModelPermission("ChaineStadeOperatoire"));
        modelPermissions.add(new ModelPermission("PointControle"));
        modelPermissions.add(new ModelPermission("TypeStock"));
        modelPermissions.add(new ModelPermission("DestinationTrain"));
        modelPermissions.add(new ModelPermission("TypeEngin"));
        modelPermissions.add(new ModelPermission("CategorieUnite"));
        modelPermissions.add(new ModelPermission("Consommable"));
        modelPermissions.add(new ModelPermission("Produit"));
        modelPermissions.add(new ModelPermission("Train"));
        modelPermissions.add(new ModelPermission("ConsommationSpecifique"));
        modelPermissions.add(new ModelPermission("ScenarioFlux"));
        modelPermissions.add(new ModelPermission("Engin"));
        modelPermissions.add(new ModelPermission("Demande"));
        modelPermissions.add(new ModelPermission("Reclamation"));
        modelPermissions.add(new ModelPermission("Panneau"));
        modelPermissions.add(new ModelPermission("Tranchee"));
        modelPermissions.add(new ModelPermission("SuiviProduction"));
        modelPermissions.add(new ModelPermission("TypeClient"));
        modelPermissions.add(new ModelPermission("User"));
        modelPermissions.add(new ModelPermission("ModelPermission"));
        modelPermissions.add(new ModelPermission("ActionPermission"));
    }

    private static void addActionPermission(List<ActionPermission> actionPermissions) {
        actionPermissions.add(new ActionPermission("list"));
        actionPermissions.add(new ActionPermission("create"));
        actionPermissions.add(new ActionPermission("delete"));
        actionPermissions.add(new ActionPermission("edit"));
        actionPermissions.add(new ActionPermission("view"));
        actionPermissions.add(new ActionPermission("duplicate"));
    }


    @Autowired
    TypeReclamationAdminService typeReclamationService;
    @Autowired
    AxeAdminService axeService;
    @Autowired
    EtatDemandeAdminService etatDemandeService;
    @Autowired
    ProduitMarchandAdminService produitMarchandService;
    @Autowired
    DestinationCamionAdminService destinationCamionService;
    @Autowired
    TypeWagonAdminService typeWagonService;
    @Autowired
    ProvennanceTrainAdminService provennanceTrainService;
    @Autowired
    MoyenAdminService moyenService;
    @Autowired
    DestinationNavireAdminService destinationNavireService;
    @Autowired
    StatusScenarioFluxAdminService statusScenarioFluxService;
    @Autowired
    CauseArretAdminService causeArretService;
    @Autowired
    ProvennanceCamionAdminService provennanceCamionService;
    @Autowired
    ElementChimiqueAdminService elementChimiqueService;
    @Autowired
    ProduitSourceAdminService produitSourceService;
    @Autowired
    StatusExerciceAdminService statusExerciceService;
    @Autowired
    SiteAdminService siteService;
    @Autowired
    TypeDemandeAdminService typeDemandeService;
    @Autowired
    StadeOperatoireAdminService stadeOperatoireService;
    @Autowired
    OperationStadeOperatoireAdminService operationStadeOperatoireService;
    @Autowired
    TypeExpeditionAdminService typeExpeditionService;
    @Autowired
    MotifReclamationAdminService motifReclamationService;
    @Autowired
    CategorieStockAdminService categorieStockService;
    @Autowired
    EtatReclamationAdminService etatReclamationService;
    @Autowired
    EntiteAdminService entiteService;
    @Autowired
    UniteAdminService uniteService;
    @Autowired
    ActionEntrepriseAdminService actionEntrepriseService;
    @Autowired
    TypeStockAdminService typeStockService;
    @Autowired
    DestinationTrainAdminService destinationTrainService;
    @Autowired
    TypeEnginAdminService typeEnginService;
    @Autowired
    CategorieUniteAdminService categorieUniteService;
    @Autowired
    ConsommableAdminService consommableService;
    @Autowired
    ConsommationSpecifiqueAdminService consommationSpecifiqueService;
    @Autowired
    PanneauAdminService panneauService;
    @Autowired
    TrancheeAdminService trancheeService;
    @Autowired
    TypeClientAdminService typeClientService;
}


