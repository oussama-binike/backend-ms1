package  ma.zyn.app.rest.mapper.referentiel;

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

import ma.zyn.app.bean.core.referentiel.StadeOperatoire;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.CoutStade;
import ma.zyn.app.ws.dto.referentiel.CoutStadeDto;

@Component
public class CoutStadeMapper {

    @Autowired
    private UniteMapper uniteMapper ;
    @Autowired
    private StadeOperatoireMapper stadeOperatoireMapper ;
    private boolean stadeOperatoire;
    private boolean unite;

    public  CoutStadeMapper() {
        initObject(true);
    }

    public CoutStade toItem(CoutStadeDto dto) {
        if (dto == null) {
            return null;
        } else {
        CoutStade item = new CoutStade();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(StringUtil.isNotEmpty(dto.getCoutUnitaire()))
                item.setCoutUnitaire(dto.getCoutUnitaire());
            if(dto.getStadeOperatoire() != null && dto.getStadeOperatoire().getId() != null){
                item.setStadeOperatoire(new StadeOperatoire());
                item.getStadeOperatoire().setId(dto.getStadeOperatoire().getId());
                item.getStadeOperatoire().setLibelle(dto.getStadeOperatoire().getLibelle());
            }

            if(this.unite && dto.getUnite()!=null)
                item.setUnite(uniteMapper.toItem(dto.getUnite())) ;




        return item;
        }
    }


    public CoutStadeDto toDto(CoutStade item) {
        if (item == null) {
            return null;
        } else {
            CoutStadeDto dto = new CoutStadeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(StringUtil.isNotEmpty(item.getCoutUnitaire()))
                dto.setCoutUnitaire(item.getCoutUnitaire());
            if(this.stadeOperatoire && item.getStadeOperatoire()!=null) {
                dto.setStadeOperatoire(stadeOperatoireMapper.toDto(item.getStadeOperatoire())) ;

            }
            if(this.unite && item.getUnite()!=null) {
                dto.setUnite(uniteMapper.toDto(item.getUnite())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.stadeOperatoire = value;
        this.unite = value;
    }

    public List<CoutStade> toItem(List<CoutStadeDto> dtos) {
        List<CoutStade> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CoutStadeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CoutStadeDto> toDto(List<CoutStade> items) {
        List<CoutStadeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CoutStade item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CoutStadeDto dto, CoutStade t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
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
        if (dto.getStadeOperatoire() != null)
        stadeOperatoireMapper.copy(dto.getStadeOperatoire(), t.getStadeOperatoire());
        if (dto.getUnite() != null)
        uniteMapper.copy(dto.getUnite(), t.getUnite());
    }

    public List<CoutStade> copy(List<CoutStadeDto> dtos) {
        List<CoutStade> result = new ArrayList<>();
        if (dtos != null) {
            for (CoutStadeDto dto : dtos) {
                CoutStade instance = new CoutStade();
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
}
