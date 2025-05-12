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
import ma.zyn.app.bean.core.reclamation.MotifReclamation;
import ma.zyn.app.ws.dto.reclamation.MotifReclamationDto;

@Component
public class MotifReclamationMapper {



    public MotifReclamation toItem(MotifReclamationDto dto) {
        if (dto == null) {
            return null;
        } else {
        MotifReclamation item = new MotifReclamation();
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


    public MotifReclamationDto toDto(MotifReclamation item) {
        if (item == null) {
            return null;
        } else {
            MotifReclamationDto dto = new MotifReclamationDto();
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



    public List<MotifReclamation> toItem(List<MotifReclamationDto> dtos) {
        List<MotifReclamation> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (MotifReclamationDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<MotifReclamationDto> toDto(List<MotifReclamation> items) {
        List<MotifReclamationDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (MotifReclamation item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(MotifReclamationDto dto, MotifReclamation t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<MotifReclamation> copy(List<MotifReclamationDto> dtos) {
        List<MotifReclamation> result = new ArrayList<>();
        if (dtos != null) {
            for (MotifReclamationDto dto : dtos) {
                MotifReclamation instance = new MotifReclamation();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
