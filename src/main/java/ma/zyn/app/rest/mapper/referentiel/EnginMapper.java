package  ma.zyn.app.rest.mapper.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.referentiel.StadeOperatoireMapper;
import ma.zyn.app.bean.core.referentiel.StadeOperatoire;
import ma.zyn.app.ws.mapper.referentiel.TypeEnginMapper;
import ma.zyn.app.bean.core.referentiel.TypeEngin;
import ma.zyn.app.ws.mapper.referentiel.OperationStadeOperatoireMapper;
import ma.zyn.app.bean.core.referentiel.OperationStadeOperatoire;

import ma.zyn.app.bean.core.referentiel.StadeOperatoire;


import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.Engin;
import ma.zyn.app.ws.dto.referentiel.EnginDto;

@Component
public class EnginMapper {

    @Autowired
    private StadeOperatoireMapper stadeOperatoireMapper ;
    @Autowired
    private TypeEnginMapper typeEnginMapper ;
    @Autowired
    private OperationStadeOperatoireMapper operationStadeOperatoireMapper ;
    private boolean typeEngin;
    private boolean operationStadeOperatoire;
    private boolean stadeOperatoire;

    public  EnginMapper() {
        initObject(true);
    }

    public Engin toItem(EnginDto dto) {
        if (dto == null) {
            return null;
        } else {
        Engin item = new Engin();
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
            if(StringUtil.isNotEmpty(dto.getCapacite()))
                item.setCapacite(dto.getCapacite());
            if(StringUtil.isNotEmpty(dto.getRendement()))
                item.setRendement(dto.getRendement());
            if(this.typeEngin && dto.getTypeEngin()!=null)
                item.setTypeEngin(typeEnginMapper.toItem(dto.getTypeEngin())) ;

            if(this.operationStadeOperatoire && dto.getOperationStadeOperatoire()!=null)
                item.setOperationStadeOperatoire(operationStadeOperatoireMapper.toItem(dto.getOperationStadeOperatoire())) ;

            if(dto.getStadeOperatoire() != null && dto.getStadeOperatoire().getId() != null){
                item.setStadeOperatoire(new StadeOperatoire());
                item.getStadeOperatoire().setId(dto.getStadeOperatoire().getId());
                item.getStadeOperatoire().setLibelle(dto.getStadeOperatoire().getLibelle());
            }




        return item;
        }
    }


    public EnginDto toDto(Engin item) {
        if (item == null) {
            return null;
        } else {
            EnginDto dto = new EnginDto();
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
            if(StringUtil.isNotEmpty(item.getCapacite()))
                dto.setCapacite(item.getCapacite());
            if(StringUtil.isNotEmpty(item.getRendement()))
                dto.setRendement(item.getRendement());
            if(this.typeEngin && item.getTypeEngin()!=null) {
                dto.setTypeEngin(typeEnginMapper.toDto(item.getTypeEngin())) ;

            }
            if(this.operationStadeOperatoire && item.getOperationStadeOperatoire()!=null) {
                dto.setOperationStadeOperatoire(operationStadeOperatoireMapper.toDto(item.getOperationStadeOperatoire())) ;

            }
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
        this.typeEngin = value;
        this.operationStadeOperatoire = value;
        this.stadeOperatoire = value;
    }

    public List<Engin> toItem(List<EnginDto> dtos) {
        List<Engin> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EnginDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EnginDto> toDto(List<Engin> items) {
        List<EnginDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Engin item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EnginDto dto, Engin t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getTypeEngin() == null  && dto.getTypeEngin() != null){
            t.setTypeEngin(new TypeEngin());
        }else if (t.getTypeEngin() != null  && dto.getTypeEngin() != null){
            t.setTypeEngin(null);
            t.setTypeEngin(new TypeEngin());
        }
        if(t.getOperationStadeOperatoire() == null  && dto.getOperationStadeOperatoire() != null){
            t.setOperationStadeOperatoire(new OperationStadeOperatoire());
        }else if (t.getOperationStadeOperatoire() != null  && dto.getOperationStadeOperatoire() != null){
            t.setOperationStadeOperatoire(null);
            t.setOperationStadeOperatoire(new OperationStadeOperatoire());
        }
        if(t.getStadeOperatoire() == null  && dto.getStadeOperatoire() != null){
            t.setStadeOperatoire(new StadeOperatoire());
        }else if (t.getStadeOperatoire() != null  && dto.getStadeOperatoire() != null){
            t.setStadeOperatoire(null);
            t.setStadeOperatoire(new StadeOperatoire());
        }
        if (dto.getTypeEngin() != null)
        typeEnginMapper.copy(dto.getTypeEngin(), t.getTypeEngin());
        if (dto.getOperationStadeOperatoire() != null)
        operationStadeOperatoireMapper.copy(dto.getOperationStadeOperatoire(), t.getOperationStadeOperatoire());
        if (dto.getStadeOperatoire() != null)
        stadeOperatoireMapper.copy(dto.getStadeOperatoire(), t.getStadeOperatoire());
    }

    public List<Engin> copy(List<EnginDto> dtos) {
        List<Engin> result = new ArrayList<>();
        if (dtos != null) {
            for (EnginDto dto : dtos) {
                Engin instance = new Engin();
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
    public TypeEnginMapper getTypeEnginMapper(){
        return this.typeEnginMapper;
    }
    public void setTypeEnginMapper(TypeEnginMapper typeEnginMapper ){
        this.typeEnginMapper = typeEnginMapper;
    }
    public OperationStadeOperatoireMapper getOperationStadeOperatoireMapper(){
        return this.operationStadeOperatoireMapper;
    }
    public void setOperationStadeOperatoireMapper(OperationStadeOperatoireMapper operationStadeOperatoireMapper ){
        this.operationStadeOperatoireMapper = operationStadeOperatoireMapper;
    }
    public boolean  isTypeEngin(){
        return this.typeEngin;
    }
    public void  setTypeEngin(boolean typeEngin){
        this.typeEngin = typeEngin;
    }
    public boolean  isOperationStadeOperatoire(){
        return this.operationStadeOperatoire;
    }
    public void  setOperationStadeOperatoire(boolean operationStadeOperatoire){
        this.operationStadeOperatoire = operationStadeOperatoire;
    }
    public boolean  isStadeOperatoire(){
        return this.stadeOperatoire;
    }
    public void  setStadeOperatoire(boolean stadeOperatoire){
        this.stadeOperatoire = stadeOperatoire;
    }
}
