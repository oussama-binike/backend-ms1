package  ma.zyn.app.rest.mapper.supply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.referentiel.UniteMapper;
import ma.zyn.app.bean.core.referentiel.Unite;
import ma.zyn.app.ws.mapper.referentiel.StadeOperatoireMapper;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.ws.mapper.referentiel.ProduitMapper;
import ma.zyn.app.bean.core.referentiel.Produit;
import ma.zyn.app.ws.mapper.referentiel.MoyenMapper;
import ma.zyn.app.bean.core.referentiel.Moyen;

import ma.zyn.app.bean.core.referentiel.StadeOperatoire;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.supply.SuiviProduction;
import ma.zyn.app.ws.dto.supply.SuiviProductionDto;

@Component
public class SuiviProductionMapper {

    @Autowired
    private UniteMapper uniteMapper ;
    @Autowired
    private StadeOperatoireMapper stadeOperatoireMapper ;
    @Autowired
    private ProduitMapper produitMapper ;
    @Autowired
    private MoyenMapper moyenMapper ;
    private boolean produit;
    private boolean stadeOperatoire;
    private boolean unite;
    private boolean moyen;

    public  SuiviProductionMapper() {
        initObject(true);
    }

    public SuiviProduction toItem(SuiviProductionDto dto) {
        if (dto == null) {
            return null;
        } else {
        SuiviProduction item = new SuiviProduction();
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
            if(StringUtil.isNotEmpty(dto.getVolume()))
                item.setVolume(dto.getVolume());
            if(StringUtil.isNotEmpty(dto.getTsm()))
                item.setTsm(dto.getTsm());
            if(this.produit && dto.getProduit()!=null)
                item.setProduit(produitMapper.toItem(dto.getProduit())) ;

            if(dto.getStadeOperatoire() != null && dto.getStadeOperatoire().getId() != null){
                item.setStadeOperatoire(new StadeOperatoire());
                item.getStadeOperatoire().setId(dto.getStadeOperatoire().getId());
                item.getStadeOperatoire().setLibelle(dto.getStadeOperatoire().getLibelle());
            }

            if(this.unite && dto.getUnite()!=null)
                item.setUnite(uniteMapper.toItem(dto.getUnite())) ;

            if(this.moyen && dto.getMoyen()!=null)
                item.setMoyen(moyenMapper.toItem(dto.getMoyen())) ;




        return item;
        }
    }


    public SuiviProductionDto toDto(SuiviProduction item) {
        if (item == null) {
            return null;
        } else {
            SuiviProductionDto dto = new SuiviProductionDto();
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
            if(StringUtil.isNotEmpty(item.getVolume()))
                dto.setVolume(item.getVolume());
            if(StringUtil.isNotEmpty(item.getTsm()))
                dto.setTsm(item.getTsm());
            if(this.produit && item.getProduit()!=null) {
                dto.setProduit(produitMapper.toDto(item.getProduit())) ;

            }
            if(this.stadeOperatoire && item.getStadeOperatoire()!=null) {
                dto.setStadeOperatoire(stadeOperatoireMapper.toDto(item.getStadeOperatoire())) ;

            }
            if(this.unite && item.getUnite()!=null) {
                dto.setUnite(uniteMapper.toDto(item.getUnite())) ;

            }
            if(this.moyen && item.getMoyen()!=null) {
                dto.setMoyen(moyenMapper.toDto(item.getMoyen())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.produit = value;
        this.stadeOperatoire = value;
        this.unite = value;
        this.moyen = value;
    }

    public List<SuiviProduction> toItem(List<SuiviProductionDto> dtos) {
        List<SuiviProduction> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (SuiviProductionDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<SuiviProductionDto> toDto(List<SuiviProduction> items) {
        List<SuiviProductionDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (SuiviProduction item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(SuiviProductionDto dto, SuiviProduction t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getProduit() == null  && dto.getProduit() != null){
            t.setProduit(new Produit());
        }else if (t.getProduit() != null  && dto.getProduit() != null){
            t.setProduit(null);
            t.setProduit(new Produit());
        }
        if(t.getStadeOperatoire() == null  && dto.getStadeOperatoire() != null){
            t.setStadeOperatoire(new StadeOperatoire());
        }else if (t.getStadeOperatoire() != null  && dto.getStadeOperatoire() != null){
            t.setStadeOperatoire(null);
            t.setStadeOperatoire(new StadeOperatoire());
        }
        if(t.getUnite() == null  && dto.getUnite() != null){
            t.setUnite(new Unite());
        }else if (t.getUnite() != null  && dto.getUnite() != null){
            t.setUnite(null);
            t.setUnite(new Unite());
        }
        if(t.getMoyen() == null  && dto.getMoyen() != null){
            t.setMoyen(new Moyen());
        }else if (t.getMoyen() != null  && dto.getMoyen() != null){
            t.setMoyen(null);
            t.setMoyen(new Moyen());
        }
        if (dto.getProduit() != null)
        produitMapper.copy(dto.getProduit(), t.getProduit());
        if (dto.getStadeOperatoire() != null)
        stadeOperatoireMapper.copy(dto.getStadeOperatoire(), t.getStadeOperatoire());
        if (dto.getUnite() != null)
        uniteMapper.copy(dto.getUnite(), t.getUnite());
        if (dto.getMoyen() != null)
        moyenMapper.copy(dto.getMoyen(), t.getMoyen());
    }

    public List<SuiviProduction> copy(List<SuiviProductionDto> dtos) {
        List<SuiviProduction> result = new ArrayList<>();
        if (dtos != null) {
            for (SuiviProductionDto dto : dtos) {
                SuiviProduction instance = new SuiviProduction();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public UniteMapper getUniteMapper(){
        return this.uniteMapper;
    }
    public void setUniteMapper(UniteMapper uniteMapper ){
        this.uniteMapper = uniteMapper;
    }
    public StadeOperatoireMapper getStadeOperatoireMapper(){
        return this.stadeOperatoireMapper;
    }
    public void setStadeOperatoireMapper(StadeOperatoireMapper stadeOperatoireMapper ){
        this.stadeOperatoireMapper = stadeOperatoireMapper;
    }
    public ProduitMapper getProduitMapper(){
        return this.produitMapper;
    }
    public void setProduitMapper(ProduitMapper produitMapper ){
        this.produitMapper = produitMapper;
    }
    public MoyenMapper getMoyenMapper(){
        return this.moyenMapper;
    }
    public void setMoyenMapper(MoyenMapper moyenMapper ){
        this.moyenMapper = moyenMapper;
    }
    public boolean  isProduit(){
        return this.produit;
    }
    public void  setProduit(boolean produit){
        this.produit = produit;
    }
    public boolean  isStadeOperatoire(){
        return this.stadeOperatoire;
    }
    public void  setStadeOperatoire(boolean stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
    public boolean  isUnite(){
        return this.unite;
    }
    public void  setUnite(boolean unite){
        this.unite = unite;
    }
    public boolean  isMoyen(){
        return this.moyen;
    }
    public void  setMoyen(boolean moyen){
        this.moyen = moyen;
    }
}
