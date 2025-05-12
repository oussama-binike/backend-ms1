package  ma.zyn.app.rest.mapper.navire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.navire.RealisationNavireMapper;
import ma.zyn.app.bean.core.navire.RealisationNavire;
import ma.zyn.app.ws.mapper.referentiel.ProduitMapper;
import ma.zyn.app.bean.core.referentiel.Produit;

import ma.zyn.app.bean.core.navire.RealisationNavire;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.navire.RealisationNavireProduit;
import ma.zyn.app.ws.dto.navire.RealisationNavireProduitDto;

@Component
public class RealisationNavireProduitMapper {

    @Autowired
    private RealisationNavireMapper realisationNavireMapper ;
    @Autowired
    private ProduitMapper produitMapper ;
    private boolean produit;
    private boolean realisationNavire;

    public  RealisationNavireProduitMapper() {
        initObject(true);
    }

    public RealisationNavireProduit toItem(RealisationNavireProduitDto dto) {
        if (dto == null) {
            return null;
        } else {
        RealisationNavireProduit item = new RealisationNavireProduit();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getTsm()))
                item.setTsm(dto.getTsm());
            if(StringUtil.isNotEmpty(dto.getVolume()))
                item.setVolume(dto.getVolume());
            if(this.produit && dto.getProduit()!=null)
                item.setProduit(produitMapper.toItem(dto.getProduit())) ;

            if(dto.getRealisationNavire() != null && dto.getRealisationNavire().getId() != null){
                item.setRealisationNavire(new RealisationNavire());
                item.getRealisationNavire().setId(dto.getRealisationNavire().getId());
                item.getRealisationNavire().setLibelle(dto.getRealisationNavire().getLibelle());
            }




        return item;
        }
    }


    public RealisationNavireProduitDto toDto(RealisationNavireProduit item) {
        if (item == null) {
            return null;
        } else {
            RealisationNavireProduitDto dto = new RealisationNavireProduitDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getTsm()))
                dto.setTsm(item.getTsm());
            if(StringUtil.isNotEmpty(item.getVolume()))
                dto.setVolume(item.getVolume());
            if(this.produit && item.getProduit()!=null) {
                dto.setProduit(produitMapper.toDto(item.getProduit())) ;

            }
            if(this.realisationNavire && item.getRealisationNavire()!=null) {
                dto.setRealisationNavire(realisationNavireMapper.toDto(item.getRealisationNavire())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.produit = value;
        this.realisationNavire = value;
    }

    public List<RealisationNavireProduit> toItem(List<RealisationNavireProduitDto> dtos) {
        List<RealisationNavireProduit> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (RealisationNavireProduitDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<RealisationNavireProduitDto> toDto(List<RealisationNavireProduit> items) {
        List<RealisationNavireProduitDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (RealisationNavireProduit item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(RealisationNavireProduitDto dto, RealisationNavireProduit t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getProduit() == null  && dto.getProduit() != null){
            t.setProduit(new Produit());
        }else if (t.getProduit() != null  && dto.getProduit() != null){
            t.setProduit(null);
            t.setProduit(new Produit());
        }
        if(t.getRealisationNavire() == null  && dto.getRealisationNavire() != null){
            t.setRealisationNavire(new RealisationNavire());
        }else if (t.getRealisationNavire() != null  && dto.getRealisationNavire() != null){
            t.setRealisationNavire(null);
            t.setRealisationNavire(new RealisationNavire());
        }
        if (dto.getProduit() != null)
        produitMapper.copy(dto.getProduit(), t.getProduit());
        if (dto.getRealisationNavire() != null)
        realisationNavireMapper.copy(dto.getRealisationNavire(), t.getRealisationNavire());
    }

    public List<RealisationNavireProduit> copy(List<RealisationNavireProduitDto> dtos) {
        List<RealisationNavireProduit> result = new ArrayList<>();
        if (dtos != null) {
            for (RealisationNavireProduitDto dto : dtos) {
                RealisationNavireProduit instance = new RealisationNavireProduit();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public RealisationNavireMapper getRealisationNavireMapper(){
        return this.realisationNavireMapper;
    }
    public void setRealisationNavireMapper(RealisationNavireMapper realisationNavireMapper ){
        this.realisationNavireMapper = realisationNavireMapper;
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
    public boolean  isRealisationNavire(){
        return this.realisationNavire;
    }
    public void  setRealisationNavire(boolean realisationNavire){
        this.realisationNavire = realisationNavire;
    }
}
