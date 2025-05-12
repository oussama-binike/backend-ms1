package  ma.zyn.app.rest.mapper.supply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.mapper.referentiel.TrainMapper;
import ma.zyn.app.bean.core.referentiel.Train;
import ma.zyn.app.ws.mapper.referentiel.StadeOperatoireMapper;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.ws.mapper.referentiel.ProvennanceTrainMapper;
import ma.zyn.app.bean.core.referentiel.ProvennanceTrain;
import ma.zyn.app.ws.mapper.referentiel.DestinationTrainMapper;
import ma.zyn.app.bean.core.referentiel.DestinationTrain;
import ma.zyn.app.ws.mapper.supply.RealisationTrainProduitMapper;
import ma.zyn.app.bean.core.supply.RealisationTrainProduit;
import ma.zyn.app.ws.mapper.referentiel.TypeWagonMapper;
import ma.zyn.app.bean.core.referentiel.TypeWagon;
import ma.zyn.app.ws.mapper.referentiel.ProduitMapper;
import ma.zyn.app.bean.core.referentiel.Produit;

import ma.zyn.app.bean.core.referentiel.StadeOperatoire;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.supply.RealisationTrain;
import ma.zyn.app.ws.dto.supply.RealisationTrainDto;

@Component
public class RealisationTrainMapper {

    @Autowired
    private TrainMapper trainMapper ;
    @Autowired
    private StadeOperatoireMapper stadeOperatoireMapper ;
    @Autowired
    private ProvennanceTrainMapper provennanceTrainMapper ;
    @Autowired
    private DestinationTrainMapper destinationTrainMapper ;
    @Autowired
    private RealisationTrainProduitMapper realisationTrainProduitMapper ;
    @Autowired
    private TypeWagonMapper typeWagonMapper ;
    @Autowired
    private ProduitMapper produitMapper ;
    private boolean provennanceTrain;
    private boolean destinationTrain;
    private boolean train;
    private boolean typeWagon;
    private boolean stadeOperatoire;
    private boolean realisationTrainProduits;

    public  RealisationTrainMapper() {
        init(true);
    }

    public RealisationTrain toItem(RealisationTrainDto dto) {
        if (dto == null) {
            return null;
        } else {
        RealisationTrain item = new RealisationTrain();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getJour()))
                item.setJour(DateUtil.stringEnToDate(dto.getJour()));
            if(StringUtil.isNotEmpty(dto.getTauxCompletude()))
                item.setTauxCompletude(dto.getTauxCompletude());
            if(StringUtil.isNotEmpty(dto.getTauxRemplissage()))
                item.setTauxRemplissage(dto.getTauxRemplissage());
            if(StringUtil.isNotEmpty(dto.getTauxChargement()))
                item.setTauxChargement(dto.getTauxChargement());
            if(StringUtil.isNotEmpty(dto.getTempsChargement()))
                item.setTempsChargement(dto.getTempsChargement());
            if(StringUtil.isNotEmpty(dto.getTempsDechargement()))
                item.setTempsDechargement(dto.getTempsDechargement());
            if(StringUtil.isNotEmpty(dto.getTempsTransite()))
                item.setTempsTransite(dto.getTempsTransite());
            if(dto.getExpedie() != null)
                item.setExpedie(dto.getExpedie());
            if(dto.getPlanifie() != null)
                item.setPlanifie(dto.getPlanifie());
            if(this.provennanceTrain && dto.getProvennanceTrain()!=null)
                item.setProvennanceTrain(provennanceTrainMapper.toItem(dto.getProvennanceTrain())) ;

            if(this.destinationTrain && dto.getDestinationTrain()!=null)
                item.setDestinationTrain(destinationTrainMapper.toItem(dto.getDestinationTrain())) ;

            if(this.train && dto.getTrain()!=null)
                item.setTrain(trainMapper.toItem(dto.getTrain())) ;

            if(this.typeWagon && dto.getTypeWagon()!=null)
                item.setTypeWagon(typeWagonMapper.toItem(dto.getTypeWagon())) ;

            if(dto.getStadeOperatoire() != null && dto.getStadeOperatoire().getId() != null){
                item.setStadeOperatoire(new StadeOperatoire());
                item.getStadeOperatoire().setId(dto.getStadeOperatoire().getId());
                item.getStadeOperatoire().setLibelle(dto.getStadeOperatoire().getLibelle());
            }


            if(this.realisationTrainProduits && ListUtil.isNotEmpty(dto.getRealisationTrainProduits()))
                item.setRealisationTrainProduits(realisationTrainProduitMapper.toItem(dto.getRealisationTrainProduits()));


        return item;
        }
    }


    public RealisationTrainDto toDto(RealisationTrain item) {
        if (item == null) {
            return null;
        } else {
            RealisationTrainDto dto = new RealisationTrainDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(item.getJour()!=null)
                dto.setJour(DateUtil.dateTimeToString(item.getJour()));
            if(StringUtil.isNotEmpty(item.getTauxCompletude()))
                dto.setTauxCompletude(item.getTauxCompletude());
            if(StringUtil.isNotEmpty(item.getTauxRemplissage()))
                dto.setTauxRemplissage(item.getTauxRemplissage());
            if(StringUtil.isNotEmpty(item.getTauxChargement()))
                dto.setTauxChargement(item.getTauxChargement());
            if(StringUtil.isNotEmpty(item.getTempsChargement()))
                dto.setTempsChargement(item.getTempsChargement());
            if(StringUtil.isNotEmpty(item.getTempsDechargement()))
                dto.setTempsDechargement(item.getTempsDechargement());
            if(StringUtil.isNotEmpty(item.getTempsTransite()))
                dto.setTempsTransite(item.getTempsTransite());
                dto.setExpedie(item.getExpedie());
                dto.setPlanifie(item.getPlanifie());
            if(this.provennanceTrain && item.getProvennanceTrain()!=null) {
                dto.setProvennanceTrain(provennanceTrainMapper.toDto(item.getProvennanceTrain())) ;

            }
            if(this.destinationTrain && item.getDestinationTrain()!=null) {
                dto.setDestinationTrain(destinationTrainMapper.toDto(item.getDestinationTrain())) ;

            }
            if(this.train && item.getTrain()!=null) {
                dto.setTrain(trainMapper.toDto(item.getTrain())) ;

            }
            if(this.typeWagon && item.getTypeWagon()!=null) {
                dto.setTypeWagon(typeWagonMapper.toDto(item.getTypeWagon())) ;

            }
            if(this.stadeOperatoire && item.getStadeOperatoire()!=null) {
                dto.setStadeOperatoire(stadeOperatoireMapper.toDto(item.getStadeOperatoire())) ;

            }
        if(this.realisationTrainProduits && ListUtil.isNotEmpty(item.getRealisationTrainProduits())){
            realisationTrainProduitMapper.init(true);
            realisationTrainProduitMapper.setRealisationTrain(false);
            dto.setRealisationTrainProduits(realisationTrainProduitMapper.toDto(item.getRealisationTrainProduits()));
            realisationTrainProduitMapper.setRealisationTrain(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.realisationTrainProduits = value;
    }
    public void initObject(boolean value) {
        this.provennanceTrain = value;
        this.destinationTrain = value;
        this.train = value;
        this.typeWagon = value;
        this.stadeOperatoire = value;
    }

    public List<RealisationTrain> toItem(List<RealisationTrainDto> dtos) {
        List<RealisationTrain> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (RealisationTrainDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<RealisationTrainDto> toDto(List<RealisationTrain> items) {
        List<RealisationTrainDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (RealisationTrain item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(RealisationTrainDto dto, RealisationTrain t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getProvennanceTrain() == null  && dto.getProvennanceTrain() != null){
            t.setProvennanceTrain(new ProvennanceTrain());
        }else if (t.getProvennanceTrain() != null  && dto.getProvennanceTrain() != null){
            t.setProvennanceTrain(null);
            t.setProvennanceTrain(new ProvennanceTrain());
        }
        if(t.getDestinationTrain() == null  && dto.getDestinationTrain() != null){
            t.setDestinationTrain(new DestinationTrain());
        }else if (t.getDestinationTrain() != null  && dto.getDestinationTrain() != null){
            t.setDestinationTrain(null);
            t.setDestinationTrain(new DestinationTrain());
        }
        if(t.getTrain() == null  && dto.getTrain() != null){
            t.setTrain(new Train());
        }else if (t.getTrain() != null  && dto.getTrain() != null){
            t.setTrain(null);
            t.setTrain(new Train());
        }
        if(t.getTypeWagon() == null  && dto.getTypeWagon() != null){
            t.setTypeWagon(new TypeWagon());
        }else if (t.getTypeWagon() != null  && dto.getTypeWagon() != null){
            t.setTypeWagon(null);
            t.setTypeWagon(new TypeWagon());
        }
        if(t.getStadeOperatoire() == null  && dto.getStadeOperatoire() != null){
            t.setStadeOperatoire(new StadeOperatoire());
        }else if (t.getStadeOperatoire() != null  && dto.getStadeOperatoire() != null){
            t.setStadeOperatoire(null);
            t.setStadeOperatoire(new StadeOperatoire());
        }
        if (dto.getProvennanceTrain() != null)
        provennanceTrainMapper.copy(dto.getProvennanceTrain(), t.getProvennanceTrain());
        if (dto.getDestinationTrain() != null)
        destinationTrainMapper.copy(dto.getDestinationTrain(), t.getDestinationTrain());
        if (dto.getTrain() != null)
        trainMapper.copy(dto.getTrain(), t.getTrain());
        if (dto.getTypeWagon() != null)
        typeWagonMapper.copy(dto.getTypeWagon(), t.getTypeWagon());
        if (dto.getStadeOperatoire() != null)
        stadeOperatoireMapper.copy(dto.getStadeOperatoire(), t.getStadeOperatoire());
        if (dto.getRealisationTrainProduits() != null)
            t.setRealisationTrainProduits(realisationTrainProduitMapper.copy(dto.getRealisationTrainProduits()));
    }

    public List<RealisationTrain> copy(List<RealisationTrainDto> dtos) {
        List<RealisationTrain> result = new ArrayList<>();
        if (dtos != null) {
            for (RealisationTrainDto dto : dtos) {
                RealisationTrain instance = new RealisationTrain();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public TrainMapper getTrainMapper(){
        return this.trainMapper;
    }
    public void setTrainMapper(TrainMapper trainMapper ){
        this.trainMapper = trainMapper;
    }
    public StadeOperatoireMapper getStadeOperatoireMapper(){
        return this.stadeOperatoireMapper;
    }
    public void setStadeOperatoireMapper(StadeOperatoireMapper stadeOperatoireMapper ){
        this.stadeOperatoireMapper = stadeOperatoireMapper;
    }
    public ProvennanceTrainMapper getProvennanceTrainMapper(){
        return this.provennanceTrainMapper;
    }
    public void setProvennanceTrainMapper(ProvennanceTrainMapper provennanceTrainMapper ){
        this.provennanceTrainMapper = provennanceTrainMapper;
    }
    public DestinationTrainMapper getDestinationTrainMapper(){
        return this.destinationTrainMapper;
    }
    public void setDestinationTrainMapper(DestinationTrainMapper destinationTrainMapper ){
        this.destinationTrainMapper = destinationTrainMapper;
    }
    public RealisationTrainProduitMapper getRealisationTrainProduitMapper(){
        return this.realisationTrainProduitMapper;
    }
    public void setRealisationTrainProduitMapper(RealisationTrainProduitMapper realisationTrainProduitMapper ){
        this.realisationTrainProduitMapper = realisationTrainProduitMapper;
    }
    public TypeWagonMapper getTypeWagonMapper(){
        return this.typeWagonMapper;
    }
    public void setTypeWagonMapper(TypeWagonMapper typeWagonMapper ){
        this.typeWagonMapper = typeWagonMapper;
    }
    public ProduitMapper getProduitMapper(){
        return this.produitMapper;
    }
    public void setProduitMapper(ProduitMapper produitMapper ){
        this.produitMapper = produitMapper;
    }
    public boolean  isProvennanceTrain(){
        return this.provennanceTrain;
    }
    public void  setProvennanceTrain(boolean provennanceTrain){
        this.provennanceTrain = provennanceTrain;
    }
    public boolean  isDestinationTrain(){
        return this.destinationTrain;
    }
    public void  setDestinationTrain(boolean destinationTrain){
        this.destinationTrain = destinationTrain;
    }
    public boolean  isTrain(){
        return this.train;
    }
    public void  setTrain(boolean train){
        this.train = train;
    }
    public boolean  isTypeWagon(){
        return this.typeWagon;
    }
    public void  setTypeWagon(boolean typeWagon){
        this.typeWagon = typeWagon;
    }
    public boolean  isStadeOperatoire(){
        return this.stadeOperatoire;
    }
    public void  setStadeOperatoire(boolean stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    public boolean  isRealisationTrainProduits(){
        return this.realisationTrainProduits ;
    }
    public void  setRealisationTrainProduits(boolean realisationTrainProduits ){
        this.realisationTrainProduits  = realisationTrainProduits ;
    }
}
