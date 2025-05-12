package  ma.zyn.app.rest.mapper.navire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.referentiel.ProduitMarchandMapper;
import ma.zyn.app.bean.core.referentiel.ProduitMarchand;
import ma.zyn.app.ws.mapper.navire.RealisationNavireMapper;
import ma.zyn.app.bean.core.navire.RealisationNavire;

import ma.zyn.app.bean.core.navire.RealisationNavire;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.navire.RealisationNavireQualite;
import ma.zyn.app.ws.dto.navire.RealisationNavireQualiteDto;

@Component
public class RealisationNavireQualiteMapper {

    @Autowired
    private ProduitMarchandMapper produitMarchandMapper ;
    @Autowired
    private RealisationNavireMapper realisationNavireMapper ;
    private boolean produitMarchand;
    private boolean realisationNavire;

    public  RealisationNavireQualiteMapper() {
        initObject(true);
    }

    public RealisationNavireQualite toItem(RealisationNavireQualiteDto dto) {
        if (dto == null) {
            return null;
        } else {
        RealisationNavireQualite item = new RealisationNavireQualite();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getTsm()))
                item.setTsm(dto.getTsm());
            if(StringUtil.isNotEmpty(dto.getVolume()))
                item.setVolume(dto.getVolume());
            if(this.produitMarchand && dto.getProduitMarchand()!=null)
                item.setProduitMarchand(produitMarchandMapper.toItem(dto.getProduitMarchand())) ;

            if(dto.getRealisationNavire() != null && dto.getRealisationNavire().getId() != null){
                item.setRealisationNavire(new RealisationNavire());
                item.getRealisationNavire().setId(dto.getRealisationNavire().getId());
                item.getRealisationNavire().setLibelle(dto.getRealisationNavire().getLibelle());
            }




        return item;
        }
    }


    public RealisationNavireQualiteDto toDto(RealisationNavireQualite item) {
        if (item == null) {
            return null;
        } else {
            RealisationNavireQualiteDto dto = new RealisationNavireQualiteDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getTsm()))
                dto.setTsm(item.getTsm());
            if(StringUtil.isNotEmpty(item.getVolume()))
                dto.setVolume(item.getVolume());
            if(this.produitMarchand && item.getProduitMarchand()!=null) {
                dto.setProduitMarchand(produitMarchandMapper.toDto(item.getProduitMarchand())) ;

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
        this.produitMarchand = value;
        this.realisationNavire = value;
    }

    public List<RealisationNavireQualite> toItem(List<RealisationNavireQualiteDto> dtos) {
        List<RealisationNavireQualite> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (RealisationNavireQualiteDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<RealisationNavireQualiteDto> toDto(List<RealisationNavireQualite> items) {
        List<RealisationNavireQualiteDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (RealisationNavireQualite item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(RealisationNavireQualiteDto dto, RealisationNavireQualite t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getProduitMarchand() == null  && dto.getProduitMarchand() != null){
            t.setProduitMarchand(new ProduitMarchand());
        }else if (t.getProduitMarchand() != null  && dto.getProduitMarchand() != null){
            t.setProduitMarchand(null);
            t.setProduitMarchand(new ProduitMarchand());
        }
        if(t.getRealisationNavire() == null  && dto.getRealisationNavire() != null){
            t.setRealisationNavire(new RealisationNavire());
        }else if (t.getRealisationNavire() != null  && dto.getRealisationNavire() != null){
            t.setRealisationNavire(null);
            t.setRealisationNavire(new RealisationNavire());
        }
        if (dto.getProduitMarchand() != null)
        produitMarchandMapper.copy(dto.getProduitMarchand(), t.getProduitMarchand());
        if (dto.getRealisationNavire() != null)
        realisationNavireMapper.copy(dto.getRealisationNavire(), t.getRealisationNavire());
    }

    public List<RealisationNavireQualite> copy(List<RealisationNavireQualiteDto> dtos) {
        List<RealisationNavireQualite> result = new ArrayList<>();
        if (dtos != null) {
            for (RealisationNavireQualiteDto dto : dtos) {
                RealisationNavireQualite instance = new RealisationNavireQualite();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ProduitMarchandMapper getProduitMarchandMapper(){
        return this.produitMarchandMapper;
    }
    public void setProduitMarchandMapper(ProduitMarchandMapper produitMarchandMapper ){
        this.produitMarchandMapper = produitMarchandMapper;
    }
    public RealisationNavireMapper getRealisationNavireMapper(){
        return this.realisationNavireMapper;
    }
    public void setRealisationNavireMapper(RealisationNavireMapper realisationNavireMapper ){
        this.realisationNavireMapper = realisationNavireMapper;
    }
    public boolean  isProduitMarchand(){
        return this.produitMarchand;
    }
    public void  setProduitMarchand(boolean produitMarchand){
        this.produitMarchand = produitMarchand;
    }
    public boolean  isRealisationNavire(){
        return this.realisationNavire;
    }
    public void  setRealisationNavire(boolean realisationNavire){
        this.realisationNavire = realisationNavire;
    }
}
