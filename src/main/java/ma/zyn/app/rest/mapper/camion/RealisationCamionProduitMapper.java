package  ma.zyn.app.rest.mapper.camion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.camion.RealisationCamionMapper;
import ma.zyn.app.bean.core.camion.RealisationCamion;
import ma.zyn.app.ws.mapper.referentiel.ProduitMapper;
import ma.zyn.app.bean.core.referentiel.Produit;

import ma.zyn.app.bean.core.camion.RealisationCamion;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.camion.RealisationCamionProduit;
import ma.zyn.app.ws.dto.camion.RealisationCamionProduitDto;

@Component
public class RealisationCamionProduitMapper {

    @Autowired
    private RealisationCamionMapper realisationCamionMapper ;
    @Autowired
    private ProduitMapper produitMapper ;
    private boolean produit;
    private boolean realisationCamion;

    public  RealisationCamionProduitMapper() {
        initObject(true);
    }

    public RealisationCamionProduit toItem(RealisationCamionProduitDto dto) {
        if (dto == null) {
            return null;
        } else {
        RealisationCamionProduit item = new RealisationCamionProduit();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getTsm()))
                item.setTsm(dto.getTsm());
            if(this.produit && dto.getProduit()!=null)
                item.setProduit(produitMapper.toItem(dto.getProduit())) ;

            if(dto.getRealisationCamion() != null && dto.getRealisationCamion().getId() != null){
                item.setRealisationCamion(new RealisationCamion());
                item.getRealisationCamion().setId(dto.getRealisationCamion().getId());
                item.getRealisationCamion().setLibelle(dto.getRealisationCamion().getLibelle());
            }




        return item;
        }
    }


    public RealisationCamionProduitDto toDto(RealisationCamionProduit item) {
        if (item == null) {
            return null;
        } else {
            RealisationCamionProduitDto dto = new RealisationCamionProduitDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getTsm()))
                dto.setTsm(item.getTsm());
            if(this.produit && item.getProduit()!=null) {
                dto.setProduit(produitMapper.toDto(item.getProduit())) ;

            }
            if(this.realisationCamion && item.getRealisationCamion()!=null) {
                dto.setRealisationCamion(realisationCamionMapper.toDto(item.getRealisationCamion())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.produit = value;
        this.realisationCamion = value;
    }

    public List<RealisationCamionProduit> toItem(List<RealisationCamionProduitDto> dtos) {
        List<RealisationCamionProduit> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (RealisationCamionProduitDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<RealisationCamionProduitDto> toDto(List<RealisationCamionProduit> items) {
        List<RealisationCamionProduitDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (RealisationCamionProduit item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(RealisationCamionProduitDto dto, RealisationCamionProduit t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getProduit() == null  && dto.getProduit() != null){
            t.setProduit(new Produit());
        }else if (t.getProduit() != null  && dto.getProduit() != null){
            t.setProduit(null);
            t.setProduit(new Produit());
        }
        if(t.getRealisationCamion() == null  && dto.getRealisationCamion() != null){
            t.setRealisationCamion(new RealisationCamion());
        }else if (t.getRealisationCamion() != null  && dto.getRealisationCamion() != null){
            t.setRealisationCamion(null);
            t.setRealisationCamion(new RealisationCamion());
        }
        if (dto.getProduit() != null)
        produitMapper.copy(dto.getProduit(), t.getProduit());
        if (dto.getRealisationCamion() != null)
        realisationCamionMapper.copy(dto.getRealisationCamion(), t.getRealisationCamion());
    }

    public List<RealisationCamionProduit> copy(List<RealisationCamionProduitDto> dtos) {
        List<RealisationCamionProduit> result = new ArrayList<>();
        if (dtos != null) {
            for (RealisationCamionProduitDto dto : dtos) {
                RealisationCamionProduit instance = new RealisationCamionProduit();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public RealisationCamionMapper getRealisationCamionMapper(){
        return this.realisationCamionMapper;
    }
    public void setRealisationCamionMapper(RealisationCamionMapper realisationCamionMapper ){
        this.realisationCamionMapper = realisationCamionMapper;
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
    public boolean  isRealisationCamion(){
        return this.realisationCamion;
    }
    public void  setRealisationCamion(boolean realisationCamion){
        this.realisationCamion = realisationCamion;
    }
}
