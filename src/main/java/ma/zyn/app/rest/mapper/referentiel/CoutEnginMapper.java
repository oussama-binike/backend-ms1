package  ma.zyn.app.rest.mapper.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.referentiel.UniteMapper;
import ma.zyn.app.bean.core.referentiel.Unite;
import ma.zyn.app.ws.mapper.referentiel.EnginMapper;
import ma.zyn.app.bean.core.referentiel.Engin;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.CoutEngin;
import ma.zyn.app.ws.dto.referentiel.CoutEnginDto;

@Component
public class CoutEnginMapper {

    @Autowired
    private UniteMapper uniteMapper ;
    @Autowired
    private EnginMapper enginMapper ;
    private boolean engin;
    private boolean unite;

    public  CoutEnginMapper() {
        initObject(true);
    }

    public CoutEngin toItem(CoutEnginDto dto) {
        if (dto == null) {
            return null;
        } else {
        CoutEngin item = new CoutEngin();
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
            if(this.engin && dto.getEngin()!=null)
                item.setEngin(enginMapper.toItem(dto.getEngin())) ;

            if(this.unite && dto.getUnite()!=null)
                item.setUnite(uniteMapper.toItem(dto.getUnite())) ;




        return item;
        }
    }


    public CoutEnginDto toDto(CoutEngin item) {
        if (item == null) {
            return null;
        } else {
            CoutEnginDto dto = new CoutEnginDto();
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
            if(this.engin && item.getEngin()!=null) {
                dto.setEngin(enginMapper.toDto(item.getEngin())) ;

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
        this.engin = value;
        this.unite = value;
    }

    public List<CoutEngin> toItem(List<CoutEnginDto> dtos) {
        List<CoutEngin> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CoutEnginDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CoutEnginDto> toDto(List<CoutEngin> items) {
        List<CoutEnginDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CoutEngin item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CoutEnginDto dto, CoutEngin t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getEngin() == null  && dto.getEngin() != null){
            t.setEngin(new Engin());
        }else if (t.getEngin() != null  && dto.getEngin() != null){
            t.setEngin(null);
            t.setEngin(new Engin());
        }
        if(t.getUnite() == null  && dto.getUnite() != null){
            t.setUnite(new Unite());
        }else if (t.getUnite() != null  && dto.getUnite() != null){
            t.setUnite(null);
            t.setUnite(new Unite());
        }
        if (dto.getEngin() != null)
        enginMapper.copy(dto.getEngin(), t.getEngin());
        if (dto.getUnite() != null)
        uniteMapper.copy(dto.getUnite(), t.getUnite());
    }

    public List<CoutEngin> copy(List<CoutEnginDto> dtos) {
        List<CoutEngin> result = new ArrayList<>();
        if (dtos != null) {
            for (CoutEnginDto dto : dtos) {
                CoutEngin instance = new CoutEngin();
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
    public EnginMapper getEnginMapper(){
        return this.enginMapper;
    }
    public void setEnginMapper(EnginMapper enginMapper ){
        this.enginMapper = enginMapper;
    }
    public boolean  isEngin(){
        return this.engin;
    }
    public void  setEngin(boolean engin){
        this.engin = engin;
    }
    public boolean  isUnite(){
        return this.unite;
    }
    public void  setUnite(boolean unite){
        this.unite = unite;
    }
}
