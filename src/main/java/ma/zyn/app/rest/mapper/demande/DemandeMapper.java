package  ma.zyn.app.rest.mapper.demande;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.scenario.ScenarioFluxMapper;
import ma.zyn.app.bean.core.scenario.ScenarioFlux;
import ma.zyn.app.ws.mapper.referentiel.EtatDemandeMapper;
import ma.zyn.app.bean.core.referentiel.EtatDemande;
import ma.zyn.app.ws.mapper.referentiel.ProduitMarchandMapper;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import ma.zyn.app.ws.mapper.referentiel.TypeDemandeMapper;
import ma.zyn.app.bean.core.referentiel.TypeDemande;
import ma.zyn.app.ws.mapper.scenario.ExerciceMapper;
import ma.zyn.app.bean.core.scenario.Exercice;
import ma.zyn.app.ws.mapper.referentiel.ClientMapper;
import ma.zyn.app.bean.core.referentiel.Client;

import ma.zyn.app.bean.core.scenario.ScenarioFlux;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.demande.Demande;
import ma.zyn.app.ws.dto.demande.DemandeDto;

@Component
public class DemandeMapper {

    @Autowired
    private ScenarioFluxMapper scenarioFluxMapper ;
    @Autowired
    private EtatDemandeMapper etatDemandeMapper ;
    @Autowired
    private ProduitMarchandMapper produitMarchandMapper ;
    @Autowired
    private TypeDemandeMapper typeDemandeMapper ;
    @Autowired
    private ExerciceMapper exerciceMapper ;
    @Autowired
    private ClientMapper clientMapper ;
    private boolean produitMarchand;
    private boolean client;
    private boolean typeDemande;
    private boolean etatDemande;
    private boolean scenarioFlux;
    private boolean exercice;

    public  DemandeMapper() {
        initObject(true);
    }

    public Demande toItem(DemandeDto dto) {
        if (dto == null) {
            return null;
        } else {
        Demande item = new Demande();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getDateDemande()))
                item.setDateDemande(DateUtil.stringEnToDate(dto.getDateDemande()));
            if(StringUtil.isNotEmpty(dto.getDateExpedition()))
                item.setDateExpedition(DateUtil.stringEnToDate(dto.getDateExpedition()));
            if(StringUtil.isNotEmpty(dto.getVolume()))
                item.setVolume(dto.getVolume());
            if(StringUtil.isNotEmpty(dto.getActionEntreprise()))
                item.setActionEntreprise(dto.getActionEntreprise());
            if(StringUtil.isNotEmpty(dto.getTrg()))
                item.setTrg(dto.getTrg());
            if(StringUtil.isNotEmpty(dto.getCause()))
                item.setCause(dto.getCause());
            if(StringUtil.isNotEmpty(dto.getCommentaire()))
                item.setCommentaire(dto.getCommentaire());
            if(this.produitMarchand && dto.getProduitMarchand()!=null)
                item.setProduitMarchand(produitMarchandMapper.toItem(dto.getProduitMarchand())) ;

            if(this.client && dto.getClient()!=null)
                item.setClient(clientMapper.toItem(dto.getClient())) ;

            if(this.typeDemande && dto.getTypeDemande()!=null)
                item.setTypeDemande(typeDemandeMapper.toItem(dto.getTypeDemande())) ;

            if(this.etatDemande && dto.getEtatDemande()!=null)
                item.setEtatDemande(etatDemandeMapper.toItem(dto.getEtatDemande())) ;

            if(dto.getScenarioFlux() != null && dto.getScenarioFlux().getId() != null){
                item.setScenarioFlux(new ScenarioFlux());
                item.getScenarioFlux().setId(dto.getScenarioFlux().getId());
                item.getScenarioFlux().setLibelle(dto.getScenarioFlux().getLibelle());
            }

            if(this.exercice && dto.getExercice()!=null)
                item.setExercice(exerciceMapper.toItem(dto.getExercice())) ;




        return item;
        }
    }


    public DemandeDto toDto(Demande item) {
        if (item == null) {
            return null;
        } else {
            DemandeDto dto = new DemandeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(item.getDateDemande()!=null)
                dto.setDateDemande(DateUtil.dateTimeToString(item.getDateDemande()));
            if(item.getDateExpedition()!=null)
                dto.setDateExpedition(DateUtil.dateTimeToString(item.getDateExpedition()));
            if(StringUtil.isNotEmpty(item.getVolume()))
                dto.setVolume(item.getVolume());
            if(StringUtil.isNotEmpty(item.getActionEntreprise()))
                dto.setActionEntreprise(item.getActionEntreprise());
            if(StringUtil.isNotEmpty(item.getTrg()))
                dto.setTrg(item.getTrg());
            if(StringUtil.isNotEmpty(item.getCause()))
                dto.setCause(item.getCause());
            if(StringUtil.isNotEmpty(item.getCommentaire()))
                dto.setCommentaire(item.getCommentaire());
            if(this.produitMarchand && item.getProduitMarchand()!=null) {
                dto.setProduitMarchand(produitMarchandMapper.toDto(item.getProduitMarchand())) ;

            }
            if(this.client && item.getClient()!=null) {
                dto.setClient(clientMapper.toDto(item.getClient())) ;

            }
            if(this.typeDemande && item.getTypeDemande()!=null) {
                dto.setTypeDemande(typeDemandeMapper.toDto(item.getTypeDemande())) ;

            }
            if(this.etatDemande && item.getEtatDemande()!=null) {
                dto.setEtatDemande(etatDemandeMapper.toDto(item.getEtatDemande())) ;

            }
            if(this.scenarioFlux && item.getScenarioFlux()!=null) {
                dto.setScenarioFlux(scenarioFluxMapper.toDto(item.getScenarioFlux())) ;

            }
            if(this.exercice && item.getExercice()!=null) {
                dto.setExercice(exerciceMapper.toDto(item.getExercice())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.produitMarchand = value;
        this.client = value;
        this.typeDemande = value;
        this.etatDemande = value;
        this.scenarioFlux = value;
        this.exercice = value;
    }

    public List<Demande> toItem(List<DemandeDto> dtos) {
        List<Demande> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DemandeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DemandeDto> toDto(List<Demande> items) {
        List<DemandeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Demande item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DemandeDto dto, Demande t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getProduitMarchand() == null  && dto.getProduitMarchand() != null){
            t.setProduitMarchand(new ProduitMarchand());
        }else if (t.getProduitMarchand() != null  && dto.getProduitMarchand() != null){
            t.setProduitMarchand(null);
            t.setProduitMarchand(new ProduitMarchand());
        }
        if(t.getClient() == null  && dto.getClient() != null){
            t.setClient(new Client());
        }else if (t.getClient() != null  && dto.getClient() != null){
            t.setClient(null);
            t.setClient(new Client());
        }
        if(t.getTypeDemande() == null  && dto.getTypeDemande() != null){
            t.setTypeDemande(new TypeDemande());
        }else if (t.getTypeDemande() != null  && dto.getTypeDemande() != null){
            t.setTypeDemande(null);
            t.setTypeDemande(new TypeDemande());
        }
        if(t.getEtatDemande() == null  && dto.getEtatDemande() != null){
            t.setEtatDemande(new EtatDemande());
        }else if (t.getEtatDemande() != null  && dto.getEtatDemande() != null){
            t.setEtatDemande(null);
            t.setEtatDemande(new EtatDemande());
        }
        if(t.getScenarioFlux() == null  && dto.getScenarioFlux() != null){
            t.setScenarioFlux(new ScenarioFlux());
        }else if (t.getScenarioFlux() != null  && dto.getScenarioFlux() != null){
            t.setScenarioFlux(null);
            t.setScenarioFlux(new ScenarioFlux());
        }
        if(t.getExercice() == null  && dto.getExercice() != null){
            t.setExercice(new Exercice());
        }else if (t.getExercice() != null  && dto.getExercice() != null){
            t.setExercice(null);
            t.setExercice(new Exercice());
        }
        if (dto.getProduitMarchand() != null)
        produitMarchandMapper.copy(dto.getProduitMarchand(), t.getProduitMarchand());
        if (dto.getClient() != null)
        clientMapper.copy(dto.getClient(), t.getClient());
        if (dto.getTypeDemande() != null)
        typeDemandeMapper.copy(dto.getTypeDemande(), t.getTypeDemande());
        if (dto.getEtatDemande() != null)
        etatDemandeMapper.copy(dto.getEtatDemande(), t.getEtatDemande());
        if (dto.getScenarioFlux() != null)
        scenarioFluxMapper.copy(dto.getScenarioFlux(), t.getScenarioFlux());
        if (dto.getExercice() != null)
        exerciceMapper.copy(dto.getExercice(), t.getExercice());
    }

    public List<Demande> copy(List<DemandeDto> dtos) {
        List<Demande> result = new ArrayList<>();
        if (dtos != null) {
            for (DemandeDto dto : dtos) {
                Demande instance = new Demande();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ScenarioFluxMapper getScenarioFluxMapper(){
        return this.scenarioFluxMapper;
    }
    public void setScenarioFluxMapper(ScenarioFluxMapper scenarioFluxMapper ){
        this.scenarioFluxMapper = scenarioFluxMapper;
    }
    public EtatDemandeMapper getEtatDemandeMapper(){
        return this.etatDemandeMapper;
    }
    public void setEtatDemandeMapper(EtatDemandeMapper etatDemandeMapper ){
        this.etatDemandeMapper = etatDemandeMapper;
    }
    public ProduitMarchandMapper getProduitMarchandMapper(){
        return this.produitMarchandMapper;
    }
    public void setProduitMarchandMapper(ProduitMarchandMapper produitMarchandMapper ){
        this.produitMarchandMapper = produitMarchandMapper;
    }
    public TypeDemandeMapper getTypeDemandeMapper(){
        return this.typeDemandeMapper;
    }
    public void setTypeDemandeMapper(TypeDemandeMapper typeDemandeMapper ){
        this.typeDemandeMapper = typeDemandeMapper;
    }
    public ExerciceMapper getExerciceMapper(){
        return this.exerciceMapper;
    }
    public void setExerciceMapper(ExerciceMapper exerciceMapper ){
        this.exerciceMapper = exerciceMapper;
    }
    public ClientMapper getClientMapper(){
        return this.clientMapper;
    }
    public void setClientMapper(ClientMapper clientMapper ){
        this.clientMapper = clientMapper;
    }
    public boolean  isProduitMarchand(){
        return this.produitMarchand;
    }
    public void  setProduitMarchand(boolean produitMarchand){
        this.produitMarchand = produitMarchand;
    }
    public boolean  isClient(){
        return this.client;
    }
    public void  setClient(boolean client){
        this.client = client;
    }
    public boolean  isTypeDemande(){
        return this.typeDemande;
    }
    public void  setTypeDemande(boolean typeDemande){
        this.typeDemande = typeDemande;
    }
    public boolean  isEtatDemande(){
        return this.etatDemande;
    }
    public void  setEtatDemande(boolean etatDemande){
        this.etatDemande = etatDemande;
    }
    public boolean  isScenarioFlux(){
        return this.scenarioFlux;
    }
    public void  setScenarioFlux(boolean scenarioFlux){
        this.scenarioFlux = scenarioFlux;
    }
    public boolean  isExercice(){
        return this.exercice;
    }
    public void  setExercice(boolean exercice){
        this.exercice = exercice;
    }
}
