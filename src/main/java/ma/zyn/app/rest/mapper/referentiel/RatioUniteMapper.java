package  ma.zyn.app.rest.mapper.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.referentiel.EntiteMapper;
import ma.zyn.app.bean.core.referentiel.Entite;
import ma.zyn.app.ws.mapper.referentiel.ProduitMapper;
import ma.zyn.app.bean.core.referentiel.Produit;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.RatioUnite;
import ma.zyn.app.ws.dto.referentiel.RatioUniteDto;

@Component
public class RatioUniteMapper {

    @Autowired
    private EntiteMapper entiteMapper ;
    @Autowired
    private ProduitMapper produitMapper ;
    private boolean entite;
    private boolean produit;

    public  RatioUniteMapper() {
        initObject(true);
    }

    public RatioUnite toItem(RatioUniteDto dto) {
        if (dto == null) {
            return null;
        } else {
        RatioUnite item = new RatioUnite();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getRatio()))
                item.setRatio(dto.getRatio());
            if(this.entite && dto.getEntite()!=null)
                item.setEntite(entiteMapper.toItem(dto.getEntite())) ;

            if(this.produit && dto.getProduit()!=null)
                item.setProduit(produitMapper.toItem(dto.getProduit())) ;




        return item;
        }
    }


    public RatioUniteDto toDto(RatioUnite item) {
        if (item == null) {
            return null;
        } else {
            RatioUniteDto dto = new RatioUniteDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getRatio()))
                dto.setRatio(item.getRatio());
            if(this.entite && item.getEntite()!=null) {
                dto.setEntite(entiteMapper.toDto(item.getEntite())) ;

            }
            if(this.produit && item.getProduit()!=null) {
                dto.setProduit(produitMapper.toDto(item.getProduit())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.entite = value;
        this.produit = value;
    }

    public List<RatioUnite> toItem(List<RatioUniteDto> dtos) {
        List<RatioUnite> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (RatioUniteDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<RatioUniteDto> toDto(List<RatioUnite> items) {
        List<RatioUniteDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (RatioUnite item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(RatioUniteDto dto, RatioUnite t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getEntite() == null  && dto.getEntite() != null){
            t.setEntite(new Entite());
        }else if (t.getEntite() != null  && dto.getEntite() != null){
            t.setEntite(null);
            t.setEntite(new Entite());
        }
        if(t.getProduit() == null  && dto.getProduit() != null){
            t.setProduit(new Produit());
        }else if (t.getProduit() != null  && dto.getProduit() != null){
            t.setProduit(null);
            t.setProduit(new Produit());
        }
        if (dto.getEntite() != null)
        entiteMapper.copy(dto.getEntite(), t.getEntite());
        if (dto.getProduit() != null)
        produitMapper.copy(dto.getProduit(), t.getProduit());
    }

    public List<RatioUnite> copy(List<RatioUniteDto> dtos) {
        List<RatioUnite> result = new ArrayList<>();
        if (dtos != null) {
            for (RatioUniteDto dto : dtos) {
                RatioUnite instance = new RatioUnite();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public EntiteMapper getEntiteMapper(){
        return this.entiteMapper;
    }
    public void setEntiteMapper(EntiteMapper entiteMapper ){
        this.entiteMapper = entiteMapper;
    }
    public ProduitMapper getProduitMapper(){
        return this.produitMapper;
    }
    public void setProduitMapper(ProduitMapper produitMapper ){
        this.produitMapper = produitMapper;
    }
    public boolean  isEntite(){
        return this.entite;
    }
    public void  setEntite(boolean entite){
        this.entite = entite;
    }
    public boolean  isProduit(){
        return this.produit;
    }
    public void  setProduit(boolean produit){
        this.produit = produit;
    }
}
