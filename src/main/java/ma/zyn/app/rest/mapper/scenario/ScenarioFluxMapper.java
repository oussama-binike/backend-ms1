package  ma.zyn.app.rest.mapper.scenario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.mapper.referentiel.LiaisonMapper;
import ma.zyn.app.bean.core.referentiel.Liaison;
import ma.zyn.app.ws.mapper.referentiel.EtatDemandeMapper;
import ma.zyn.app.bean.core.referentiel.EtatDemande;
import ma.zyn.app.ws.mapper.referentiel.ProduitMarchandMapper;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import ma.zyn.app.ws.mapper.referentiel.TypeDemandeMapper;
import ma.zyn.app.bean.core.referentiel.TypeDemande;
import ma.zyn.app.ws.mapper.scenario.ExerciceMapper;
import ma.zyn.app.bean.core.scenario.Exercice;
import ma.zyn.app.ws.mapper.referentiel.StadeOperatoireMapper;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.ws.mapper.planmaintenance.TauxRendementStadeOperatoireMapper;
import ma.zyn.app.bean.core.planmaintenance.TauxRendementStadeOperatoire;
import ma.zyn.app.ws.mapper.referentiel.StatusScenarioFluxMapper;
import ma.zyn.app.bean.core.referentiel.StatusScenarioFlux;
import ma.zyn.app.ws.mapper.demande.DemandeMapper;
import ma.zyn.app.bean.core.demande.Demande;
import ma.zyn.app.ws.mapper.planmaintenance.PlanDisponibiliteMapper;
import ma.zyn.app.bean.core.planmaintenance.PlanDisponibilite;
import ma.zyn.app.ws.mapper.referentiel.ClientMapper;
import ma.zyn.app.bean.core.referentiel.Client;
import ma.zyn.app.ws.mapper.stock.SuiviStockMapper;
import ma.zyn.app.bean.core.stock.SuiviStock;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.scenario.ScenarioFlux;
import ma.zyn.app.ws.dto.scenario.ScenarioFluxDto;

@Component
public class ScenarioFluxMapper {

    @Autowired
    private LiaisonMapper liaisonMapper ;
    @Autowired
    private EtatDemandeMapper etatDemandeMapper ;
    @Autowired
    private ProduitMarchandMapper produitMarchandMapper ;
    @Autowired
    private TypeDemandeMapper typeDemandeMapper ;
    @Autowired
    private ExerciceMapper exerciceMapper ;
    @Autowired
    private StadeOperatoireMapper stadeOperatoireMapper ;
    @Autowired
    private TauxRendementStadeOperatoireMapper tauxRendementStadeOperatoireMapper ;
    @Autowired
    private StatusScenarioFluxMapper statusScenarioFluxMapper ;
    @Autowired
    private DemandeMapper demandeMapper ;
    @Autowired
    private PlanDisponibiliteMapper planDisponibiliteMapper ;
    @Autowired
    private ClientMapper clientMapper ;
    @Autowired
    private SuiviStockMapper suiviStockMapper ;
    private boolean exercice;
    private boolean statusScenarioFlux;
    private boolean demandes;
    private boolean planDisponibilites;
    private boolean tauxRendementStadeOperatoires;
    private boolean suiviStocks;

    public  ScenarioFluxMapper() {
        init(true);
    }

    public ScenarioFlux toItem(ScenarioFluxDto dto) {
        if (dto == null) {
            return null;
        } else {
        ScenarioFlux item = new ScenarioFlux();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getDateEffet()))
                item.setDateEffet(DateUtil.stringEnToDate(dto.getDateEffet()));
            if(this.exercice && dto.getExercice()!=null)
                item.setExercice(exerciceMapper.toItem(dto.getExercice())) ;

            if(this.statusScenarioFlux && dto.getStatusScenarioFlux()!=null)
                item.setStatusScenarioFlux(statusScenarioFluxMapper.toItem(dto.getStatusScenarioFlux())) ;


            if(this.demandes && ListUtil.isNotEmpty(dto.getDemandes()))
                item.setDemandes(demandeMapper.toItem(dto.getDemandes()));
            if(this.planDisponibilites && ListUtil.isNotEmpty(dto.getPlanDisponibilites()))
                item.setPlanDisponibilites(planDisponibiliteMapper.toItem(dto.getPlanDisponibilites()));
            if(this.tauxRendementStadeOperatoires && ListUtil.isNotEmpty(dto.getTauxRendementStadeOperatoires()))
                item.setTauxRendementStadeOperatoires(tauxRendementStadeOperatoireMapper.toItem(dto.getTauxRendementStadeOperatoires()));
            if(this.suiviStocks && ListUtil.isNotEmpty(dto.getSuiviStocks()))
                item.setSuiviStocks(suiviStockMapper.toItem(dto.getSuiviStocks()));


        return item;
        }
    }


    public ScenarioFluxDto toDto(ScenarioFlux item) {
        if (item == null) {
            return null;
        } else {
            ScenarioFluxDto dto = new ScenarioFluxDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(item.getDateEffet()!=null)
                dto.setDateEffet(DateUtil.dateTimeToString(item.getDateEffet()));
            if(this.exercice && item.getExercice()!=null) {
                dto.setExercice(exerciceMapper.toDto(item.getExercice())) ;

            }
            if(this.statusScenarioFlux && item.getStatusScenarioFlux()!=null) {
                dto.setStatusScenarioFlux(statusScenarioFluxMapper.toDto(item.getStatusScenarioFlux())) ;

            }
        if(this.demandes && ListUtil.isNotEmpty(item.getDemandes())){
            demandeMapper.init(true);
            demandeMapper.setScenarioFlux(false);
            dto.setDemandes(demandeMapper.toDto(item.getDemandes()));
            demandeMapper.setScenarioFlux(true);

        }
        if(this.planDisponibilites && ListUtil.isNotEmpty(item.getPlanDisponibilites())){
            planDisponibiliteMapper.init(true);
            planDisponibiliteMapper.setScenarioFlux(false);
            dto.setPlanDisponibilites(planDisponibiliteMapper.toDto(item.getPlanDisponibilites()));
            planDisponibiliteMapper.setScenarioFlux(true);

        }
        if(this.tauxRendementStadeOperatoires && ListUtil.isNotEmpty(item.getTauxRendementStadeOperatoires())){
            tauxRendementStadeOperatoireMapper.init(true);
            tauxRendementStadeOperatoireMapper.setScenarioFlux(false);
            dto.setTauxRendementStadeOperatoires(tauxRendementStadeOperatoireMapper.toDto(item.getTauxRendementStadeOperatoires()));
            tauxRendementStadeOperatoireMapper.setScenarioFlux(true);

        }
        if(this.suiviStocks && ListUtil.isNotEmpty(item.getSuiviStocks())){
            suiviStockMapper.init(true);
            suiviStockMapper.setScenarioFlux(false);
            dto.setSuiviStocks(suiviStockMapper.toDto(item.getSuiviStocks()));
            suiviStockMapper.setScenarioFlux(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.demandes = value;
        this.planDisponibilites = value;
        this.tauxRendementStadeOperatoires = value;
        this.suiviStocks = value;
    }
    public void initObject(boolean value) {
        this.exercice = value;
        this.statusScenarioFlux = value;
    }

    public List<ScenarioFlux> toItem(List<ScenarioFluxDto> dtos) {
        List<ScenarioFlux> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ScenarioFluxDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ScenarioFluxDto> toDto(List<ScenarioFlux> items) {
        List<ScenarioFluxDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ScenarioFlux item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ScenarioFluxDto dto, ScenarioFlux t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getExercice() == null  && dto.getExercice() != null){
            t.setExercice(new Exercice());
        }else if (t.getExercice() != null  && dto.getExercice() != null){
            t.setExercice(null);
            t.setExercice(new Exercice());
        }
        if(t.getStatusScenarioFlux() == null  && dto.getStatusScenarioFlux() != null){
            t.setStatusScenarioFlux(new StatusScenarioFlux());
        }else if (t.getStatusScenarioFlux() != null  && dto.getStatusScenarioFlux() != null){
            t.setStatusScenarioFlux(null);
            t.setStatusScenarioFlux(new StatusScenarioFlux());
        }
        if (dto.getExercice() != null)
        exerciceMapper.copy(dto.getExercice(), t.getExercice());
        if (dto.getStatusScenarioFlux() != null)
        statusScenarioFluxMapper.copy(dto.getStatusScenarioFlux(), t.getStatusScenarioFlux());
        if (dto.getDemandes() != null)
            t.setDemandes(demandeMapper.copy(dto.getDemandes()));
        if (dto.getPlanDisponibilites() != null)
            t.setPlanDisponibilites(planDisponibiliteMapper.copy(dto.getPlanDisponibilites()));
        if (dto.getTauxRendementStadeOperatoires() != null)
            t.setTauxRendementStadeOperatoires(tauxRendementStadeOperatoireMapper.copy(dto.getTauxRendementStadeOperatoires()));
        if (dto.getSuiviStocks() != null)
            t.setSuiviStocks(suiviStockMapper.copy(dto.getSuiviStocks()));
    }

    public List<ScenarioFlux> copy(List<ScenarioFluxDto> dtos) {
        List<ScenarioFlux> result = new ArrayList<>();
        if (dtos != null) {
            for (ScenarioFluxDto dto : dtos) {
                ScenarioFlux instance = new ScenarioFlux();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public LiaisonMapper getLiaisonMapper(){
        return this.liaisonMapper;
    }
    public void setLiaisonMapper(LiaisonMapper liaisonMapper ){
        this.liaisonMapper = liaisonMapper;
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
    public StadeOperatoireMapper getStadeOperatoireMapper(){
        return this.stadeOperatoireMapper;
    }
    public void setStadeOperatoireMapper(StadeOperatoireMapper stadeOperatoireMapper ){
        this.stadeOperatoireMapper = stadeOperatoireMapper;
    }
    public TauxRendementStadeOperatoireMapper getTauxRendementStadeOperatoireMapper(){
        return this.tauxRendementStadeOperatoireMapper;
    }
    public void setTauxRendementStadeOperatoireMapper(TauxRendementStadeOperatoireMapper tauxRendementStadeOperatoireMapper ){
        this.tauxRendementStadeOperatoireMapper = tauxRendementStadeOperatoireMapper;
    }
    public StatusScenarioFluxMapper getStatusScenarioFluxMapper(){
        return this.statusScenarioFluxMapper;
    }
    public void setStatusScenarioFluxMapper(StatusScenarioFluxMapper statusScenarioFluxMapper ){
        this.statusScenarioFluxMapper = statusScenarioFluxMapper;
    }
    public DemandeMapper getDemandeMapper(){
        return this.demandeMapper;
    }
    public void setDemandeMapper(DemandeMapper demandeMapper ){
        this.demandeMapper = demandeMapper;
    }
    public PlanDisponibiliteMapper getPlanDisponibiliteMapper(){
        return this.planDisponibiliteMapper;
    }
    public void setPlanDisponibiliteMapper(PlanDisponibiliteMapper planDisponibiliteMapper ){
        this.planDisponibiliteMapper = planDisponibiliteMapper;
    }
    public ClientMapper getClientMapper(){
        return this.clientMapper;
    }
    public void setClientMapper(ClientMapper clientMapper ){
        this.clientMapper = clientMapper;
    }
    public SuiviStockMapper getSuiviStockMapper(){
        return this.suiviStockMapper;
    }
    public void setSuiviStockMapper(SuiviStockMapper suiviStockMapper ){
        this.suiviStockMapper = suiviStockMapper;
    }
    public boolean  isExercice(){
        return this.exercice;
    }
    public void  setExercice(boolean exercice){
        this.exercice = exercice;
    }
    public boolean  isStatusScenarioFlux(){
        return this.statusScenarioFlux;
    }
    public void  setStatusScenarioFlux(boolean statusScenarioFlux){
        this.statusScenarioFlux = statusScenarioFlux;
    }
    public boolean  isDemandes(){
        return this.demandes ;
    }
    public void  setDemandes(boolean demandes ){
        this.demandes  = demandes ;
    }
    public boolean  isPlanDisponibilites(){
        return this.planDisponibilites ;
    }
    public void  setPlanDisponibilites(boolean planDisponibilites ){
        this.planDisponibilites  = planDisponibilites ;
    }
    public boolean  isTauxRendementStadeOperatoires(){
        return this.tauxRendementStadeOperatoires ;
    }
    public void  setTauxRendementStadeOperatoires(boolean tauxRendementStadeOperatoires ){
        this.tauxRendementStadeOperatoires  = tauxRendementStadeOperatoires ;
    }
    public boolean  isSuiviStocks(){
        return this.suiviStocks ;
    }
    public void  setSuiviStocks(boolean suiviStocks ){
        this.suiviStocks  = suiviStocks ;
    }
}
