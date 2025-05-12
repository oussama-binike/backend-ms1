package  ma.zyn.app.rest.mapper.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.referentiel.PanneauMapper;
import ma.zyn.app.bean.core.referentiel.Panneau;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.Tranchee;
import ma.zyn.app.ws.dto.referentiel.TrancheeDto;

@Component
public class TrancheeMapper {

    @Autowired
    private PanneauMapper panneauMapper ;
    private boolean panneau;

    public  TrancheeMapper() {
        initObject(true);
    }

    public Tranchee toItem(TrancheeDto dto) {
        if (dto == null) {
            return null;
        } else {
        Tranchee item = new Tranchee();
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
            if(this.panneau && dto.getPanneau()!=null)
                item.setPanneau(panneauMapper.toItem(dto.getPanneau())) ;




        return item;
        }
    }


    public TrancheeDto toDto(Tranchee item) {
        if (item == null) {
            return null;
        } else {
            TrancheeDto dto = new TrancheeDto();
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
            if(this.panneau && item.getPanneau()!=null) {
                dto.setPanneau(panneauMapper.toDto(item.getPanneau())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.panneau = value;
    }

    public List<Tranchee> toItem(List<TrancheeDto> dtos) {
        List<Tranchee> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TrancheeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TrancheeDto> toDto(List<Tranchee> items) {
        List<TrancheeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Tranchee item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TrancheeDto dto, Tranchee t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getPanneau() == null  && dto.getPanneau() != null){
            t.setPanneau(new Panneau());
        }else if (t.getPanneau() != null  && dto.getPanneau() != null){
            t.setPanneau(null);
            t.setPanneau(new Panneau());
        }
        if (dto.getPanneau() != null)
        panneauMapper.copy(dto.getPanneau(), t.getPanneau());
    }

    public List<Tranchee> copy(List<TrancheeDto> dtos) {
        List<Tranchee> result = new ArrayList<>();
        if (dtos != null) {
            for (TrancheeDto dto : dtos) {
                Tranchee instance = new Tranchee();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public PanneauMapper getPanneauMapper(){
        return this.panneauMapper;
    }
    public void setPanneauMapper(PanneauMapper panneauMapper ){
        this.panneauMapper = panneauMapper;
    }
    public boolean  isPanneau(){
        return this.panneau;
    }
    public void  setPanneau(boolean panneau){
        this.panneau = panneau;
    }
}
