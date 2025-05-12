package  ma.zyn.app.rest.mapper.reclamation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.reclamation.EtatReclamation;
import ma.zyn.app.ws.dto.reclamation.EtatReclamationDto;

@Component
public class EtatReclamationMapper {



    public EtatReclamation toItem(EtatReclamationDto dto) {
        if (dto == null) {
            return null;
        } else {
        EtatReclamation item = new EtatReclamation();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getStyle()))
                item.setStyle(dto.getStyle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());



        return item;
        }
    }


    public EtatReclamationDto toDto(EtatReclamation item) {
        if (item == null) {
            return null;
        } else {
            EtatReclamationDto dto = new EtatReclamationDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getStyle()))
                dto.setStyle(item.getStyle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());


        return dto;
        }
    }



    public List<EtatReclamation> toItem(List<EtatReclamationDto> dtos) {
        List<EtatReclamation> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EtatReclamationDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EtatReclamationDto> toDto(List<EtatReclamation> items) {
        List<EtatReclamationDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (EtatReclamation item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EtatReclamationDto dto, EtatReclamation t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<EtatReclamation> copy(List<EtatReclamationDto> dtos) {
        List<EtatReclamation> result = new ArrayList<>();
        if (dtos != null) {
            for (EtatReclamationDto dto : dtos) {
                EtatReclamation instance = new EtatReclamation();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
