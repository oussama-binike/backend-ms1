package  ma.zyn.app.rest.mapper.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.referentiel.StadeOperatoireMapper;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;

import ma.zyn.app.bean.core.referentiel.StadeOperatoire;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.ChaineStadeOperatoire;
import ma.zyn.app.ws.dto.referentiel.ChaineStadeOperatoireDto;

@Component
public class ChaineStadeOperatoireMapper {

    @Autowired
    private StadeOperatoireMapper stadeOperatoireMapper ;
    private boolean stadeOperatoire;

    public  ChaineStadeOperatoireMapper() {
        initObject(true);
    }

    public ChaineStadeOperatoire toItem(ChaineStadeOperatoireDto dto) {
        if (dto == null) {
            return null;
        } else {
        ChaineStadeOperatoire item = new ChaineStadeOperatoire();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(dto.getStadeOperatoire() != null && dto.getStadeOperatoire().getId() != null){
                item.setStadeOperatoire(new StadeOperatoire());
                item.getStadeOperatoire().setId(dto.getStadeOperatoire().getId());
                item.getStadeOperatoire().setLibelle(dto.getStadeOperatoire().getLibelle());
            }




        return item;
        }
    }


    public ChaineStadeOperatoireDto toDto(ChaineStadeOperatoire item) {
        if (item == null) {
            return null;
        } else {
            ChaineStadeOperatoireDto dto = new ChaineStadeOperatoireDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.stadeOperatoire && item.getStadeOperatoire()!=null) {
                dto.setStadeOperatoire(stadeOperatoireMapper.toDto(item.getStadeOperatoire())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.stadeOperatoire = value;
    }

    public List<ChaineStadeOperatoire> toItem(List<ChaineStadeOperatoireDto> dtos) {
        List<ChaineStadeOperatoire> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ChaineStadeOperatoireDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ChaineStadeOperatoireDto> toDto(List<ChaineStadeOperatoire> items) {
        List<ChaineStadeOperatoireDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ChaineStadeOperatoire item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ChaineStadeOperatoireDto dto, ChaineStadeOperatoire t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getStadeOperatoire() == null  && dto.getStadeOperatoire() != null){
            t.setStadeOperatoire(new StadeOperatoire());
        }else if (t.getStadeOperatoire() != null  && dto.getStadeOperatoire() != null){
            t.setStadeOperatoire(null);
            t.setStadeOperatoire(new StadeOperatoire());
        }
        if (dto.getStadeOperatoire() != null)
        stadeOperatoireMapper.copy(dto.getStadeOperatoire(), t.getStadeOperatoire());
    }

    public List<ChaineStadeOperatoire> copy(List<ChaineStadeOperatoireDto> dtos) {
        List<ChaineStadeOperatoire> result = new ArrayList<>();
        if (dtos != null) {
            for (ChaineStadeOperatoireDto dto : dtos) {
                ChaineStadeOperatoire instance = new ChaineStadeOperatoire();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
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
}
