function fn() {

    
    var config = {};
    
   
    const rootUrlDev = 'http://localhost:8036/';
    const adminCredentialsDev = {"username": "admin","password": "123"};
    const urlDev = 'jdbc:mysql://localhost:3306/operationsmanagement';

    const usernameDev = 'root';
    const passwordDev = '';
    const driverDev = 'com.mysql.cj.jdbc.Driver';


    const rootUrlInit = 'https://stage-host/';
    const adminCredentialsInit = {"username": "admin","password": "123"};
    const urlInit = 'jdbc:mysql://localhost:8036/peps-order';
    const usernameInit = 'root';
    const passwordInit = '';
    const driverInit = 'com.mysql.cj.jdbc.Driver';
    
    
    const rootUrlE2e = 'https://e2e-host/';
    const adminCredentialsE2e = {"username": "admin","password": "123"};
    const urlE2e = 'jdbc:mysql://localhost:8036/peps-order';
    const usernameE2e = 'root';
    const passwordE2e = '';
    const driverE2e = 'com.mysql.cj.jdbc.Driver';
    
    var env = karate.env; // get java system property 'karate.env'
    if (!env || env=='dev') {
        env = 'dev';
        config.rootUrl = rootUrlDev;
        config.adminCredentials = adminCredentialsDev;
        config.datasource = { username: usernameDev, password: passwordDev, url: urlDev, driverClassName: driverDev }
    }
    if (env == 'int') {
        // over-ride only those that need to be
        config.rootUrl = rootUrlInit;
        config.adminCredentials = adminCredentialsInit;
        config.datasource = { username: usernameInit, password: passwordInit, url: urlInit, driverClassName: driverInit }
    } else if (env == 'e2e') {
        config.rootUrl = rootUrlE2e;
        config.adminCredentials = adminCredentialsE2e;
        config.datasource = { username: usernameE2e, password: passwordE2e, url: urlE2e, driverClassName: driverE2e }
    }

    config.actuatorUri = config.rootUrl + 'actuator/';
    config.adminUri = config.rootUrl + 'api/admin/';

    config.typeReclamationUrl = config.adminUri + 'typeReclamation/';
    config.axeUrl = config.adminUri + 'axe/';
    config.etatDemandeUrl = config.adminUri + 'etatDemande/';
    config.produitMarchandUrl = config.adminUri + 'produitMarchand/';
    config.realisationTrainProduitUrl = config.adminUri + 'realisationTrainProduit/';
    config.destinationCamionUrl = config.adminUri + 'destinationCamion/';
    config.tauxRendementStadeOperatoireUrl = config.adminUri + 'tauxRendementStadeOperatoire/';
    config.realisationNavireUrl = config.adminUri + 'realisationNavire/';
    config.typeWagonUrl = config.adminUri + 'typeWagon/';
    config.arretNonPlanifieUrl = config.adminUri + 'arretNonPlanifie/';
    config.provennanceTrainUrl = config.adminUri + 'provennanceTrain/';
    config.moyenUrl = config.adminUri + 'moyen/';
    config.destinationNavireUrl = config.adminUri + 'destinationNavire/';
    config.statusScenarioFluxUrl = config.adminUri + 'statusScenarioFlux/';
    config.suiviStockUrl = config.adminUri + 'suiviStock/';
    config.causeArretUrl = config.adminUri + 'causeArret/';
    config.realisationCamionProduitUrl = config.adminUri + 'realisationCamionProduit/';
    config.provennanceCamionUrl = config.adminUri + 'provennanceCamion/';
    config.chartePhysiqueUrl = config.adminUri + 'chartePhysique/';
    config.elementChimiqueUrl = config.adminUri + 'elementChimique/';
    config.coutStadeUrl = config.adminUri + 'coutStade/';
    config.produitSourceUrl = config.adminUri + 'produitSource/';
    config.realisationTrainUrl = config.adminUri + 'realisationTrain/';
    config.statusExerciceUrl = config.adminUri + 'statusExercice/';
    config.siteUrl = config.adminUri + 'site/';
    config.niveauUrl = config.adminUri + 'niveau/';
    config.clientUrl = config.adminUri + 'client/';
    config.modelUrl = config.adminUri + 'model/';
    config.planDisponibiliteUrl = config.adminUri + 'planDisponibilite/';
    config.typeDemandeUrl = config.adminUri + 'typeDemande/';
    config.stadeOperatoireProduitUrl = config.adminUri + 'stadeOperatoireProduit/';
    config.exerciceUrl = config.adminUri + 'exercice/';
    config.coutConsommableUrl = config.adminUri + 'coutConsommable/';
    config.stadeOperatoireUrl = config.adminUri + 'stadeOperatoire/';
    config.operationStadeOperatoireUrl = config.adminUri + 'operationStadeOperatoire/';
    config.consommableStadeOperatoireUrl = config.adminUri + 'consommableStadeOperatoire/';
    config.liaisonUrl = config.adminUri + 'liaison/';
    config.typeExpeditionUrl = config.adminUri + 'typeExpedition/';
    config.reclamationElementChimiqueUrl = config.adminUri + 'reclamationElementChimique/';
    config.motifReclamationUrl = config.adminUri + 'motifReclamation/';
    config.realisationCamionUrl = config.adminUri + 'realisationCamion/';
    config.categorieStockUrl = config.adminUri + 'categorieStock/';
    config.etatReclamationUrl = config.adminUri + 'etatReclamation/';
    config.coutEnginUrl = config.adminUri + 'coutEngin/';
    config.ratioUniteUrl = config.adminUri + 'ratioUnite/';
    config.entiteUrl = config.adminUri + 'entite/';
    config.uniteUrl = config.adminUri + 'unite/';
    config.stockUrl = config.adminUri + 'stock/';
    config.realisationNavireQualiteUrl = config.adminUri + 'realisationNavireQualite/';
    config.charteChimiqueUrl = config.adminUri + 'charteChimique/';
    config.realisationNavireProduitUrl = config.adminUri + 'realisationNavireProduit/';
    config.actionEntrepriseUrl = config.adminUri + 'actionEntreprise/';
    config.chaineStadeOperatoireUrl = config.adminUri + 'chaineStadeOperatoire/';
    config.pointControleUrl = config.adminUri + 'pointControle/';
    config.typeStockUrl = config.adminUri + 'typeStock/';
    config.destinationTrainUrl = config.adminUri + 'destinationTrain/';
    config.typeEnginUrl = config.adminUri + 'typeEngin/';
    config.categorieUniteUrl = config.adminUri + 'categorieUnite/';
    config.consommableUrl = config.adminUri + 'consommable/';
    config.produitUrl = config.adminUri + 'produit/';
    config.trainUrl = config.adminUri + 'train/';
    config.consommationSpecifiqueUrl = config.adminUri + 'consommationSpecifique/';
    config.scenarioFluxUrl = config.adminUri + 'scenarioFlux/';
    config.enginUrl = config.adminUri + 'engin/';
    config.demandeUrl = config.adminUri + 'demande/';
    config.reclamationUrl = config.adminUri + 'reclamation/';
    config.panneauUrl = config.adminUri + 'panneau/';
    config.trancheeUrl = config.adminUri + 'tranchee/';
    config.suiviProductionUrl = config.adminUri + 'suiviProduction/';
    config.typeClientUrl = config.adminUri + 'typeClient/';

    common = karate.callSingle('classpath:common.feature', config);
    config.uniqueId = common.uniqueId
    config.db = common.db
    config.adminToken = common.adminToken
    config.env = env;

    karate.log('karate.env =', karate.env);
    karate.log('config =', config);
    // don't waste time waiting for a connection or if servers don't respond within 5 seconds
    karate.configure('connectTimeout', 5000);
    karate.configure('readTimeout', 5000);
    return config;
}
