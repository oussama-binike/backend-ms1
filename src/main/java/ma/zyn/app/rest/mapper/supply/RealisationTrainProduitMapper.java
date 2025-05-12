package  ma.zyn.app.rest.mapper.supply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.supply.RealisationTrainMapper;
import ma.zyn.app.bean.core.supply.RealisationTrain;
import ma.zyn.app.ws.mapper.referentiel.ProduitMapper;
import ma.zyn.app.bean.core.referentiel.Produit;

import ma.zyn.app.bean.core.supply.RealisationTrain;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.supply.RealisationTrainProduit;
import ma.zyn.app.ws.dto.supply.RealisationTrainProduitDto;

@Component
public class RealisationTrainProduitMapper {

    @Autowired
    private RealisationTrainMapper realisationTrainMapper ;
    @Autowired
    private ProduitMapper produitMapper ;
    private boolean produit;
    private boolean realisationTrain;

    public  RealisationTrainProduitMapper() {
        initObject(true);
    }

    public RealisationTrainProduit toItem(RealisationTrainProduitDto dto) {
        if (dto == null) {
            return null;
        } else {
        RealisationTrainProduit item = new RealisationTrainProduit();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getVolume()))
                item.setVolume(dto.getVolume());
            if(StringUtil.isNotEmpty(dto.getTsm()))
                item.setTsm(dto.getTsm());
            if(this.produit && dto.getProduit()!=null)
                item.setProduit(produitMapper.toItem(dto.getProduit())) ;

            if(dto.getRealisationTrain() != null && dto.getRealisationTrain().getId() != null){
                item.setRealisationTrain(new RealisationTrain());
                item.getRealisationTrain().setId(dto.getRealisationTrain().getId());
                item.getRealisationTrain().setLibelle(dto.getRealisationTrain().getLibelle());
            }




        return item;
        }
    }


    public RealisationTrainProduitDto toDto(RealisationTrainProduit item) {
        if (item == null) {
            return null;
        } else {
            RealisationTrainProduitDto dto = new RealisationTrainProduitDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getVolume()))
                dto.setVolume(item.getVolume());
            if(StringUtil.isNotEmpty(item.getTsm()))
                dto.setTsm(item.getTsm());
            if(this.produit && item.getProduit()!=null) {
                dto.setProduit(produitMapper.toDto(item.getProduit())) ;

            }
            if(this.realisationTrain && item.getRealisationTrain()!=null) {
                dto.setRealisationTrain(realisationTrainMapper.toDto(item.getRealisationTrain())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.produit = value;
        this.realisationTrain = value;
    }

    public List<RealisationTrainProduit> toItem(List<RealisationTrainProduitDto> dtos) {
        List<RealisationTrainProduit> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (RealisationTrainProduitDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<RealisationTrainProduitDto> toDto(List<RealisationTrainProduit> items) {
        List<RealisationTrainProduitDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (RealisationTrainProduit item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(RealisationTrainProduitDto dto, RealisationTrainProduit t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getProduit() == null  && dto.getProduit() != null){
            t.setProduit(new Produit());
        }else if (t.getProduit() != null  && dto.getProduit() != null){
            t.setProduit(null);
            t.setProduit(new Produit());
        }
        if(t.getRealisationTrain() == null  && dto.getRealisationTrain() != null){
            t.setRealisationTrain(new RealisationTrain());
        }else if (t.getRealisationTrain() != null  && dto.getRealisationTrain() != null){
            t.setRealisationTrain(null);
            t.setRealisationTrain(new RealisationTrain());
        }
        if (dto.getProduit() != null)
        produitMapper.copy(dto.getProduit(), t.getProduit());
        if (dto.getRealisationTrain() != null)
        realisationTrainMapper.copy(dto.getRealisationTrain(), t.getRealisationTrain());
    }

    public List<RealisationTrainProduit> copy(List<RealisationTrainProduitDto> dtos) {
        List<RealisationTrainProduit> result = new ArrayList<>();
        if (dtos != null) {
            for (RealisationTrainProduitDto dto : dtos) {
                RealisationTrainProduit instance = new RealisationTrainProduit();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public RealisationTrainMapper getRealisationTrainMapper(){
        return this.realisationTrainMapper;
    }
    public void setRealisationTrainMapper(RealisationTrainMapper realisationTrainMapper ){
        this.realisationTrainMapper = realisationTrainMapper;
    }
    public ProduitMapper getProduitMapper(){
        return this.produitMapper;
    }
    public void setProduitMapper(ProduitMapper produitMapper ){
        this.produitMapper = produitMapper;
    }
    public boolean  isProduit(){
        return this.produit;
    }
    public void  setProduit(boolean produit){
        this.produit = produit;
    }
    public boolean  isRealisationTrain(){
        return this.realisationTrain;
    }
    public void  setRealisationTrain(boolean realisationTrain){
        this.realisationTrain = realisationTrain;
    }
}
