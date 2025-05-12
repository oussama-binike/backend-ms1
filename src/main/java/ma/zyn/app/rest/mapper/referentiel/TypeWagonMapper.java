package  ma.zyn.app.rest.mapper.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.TypeWagon;
import ma.zyn.app.ws.dto.referentiel.TypeWagonDto;

@Component
public class TypeWagonMapper {



    public TypeWagon toItem(TypeWagonDto dto) {
        if (dto == null) {
            return null;
        } else {
        TypeWagon item = new TypeWagon();
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



        return item;
        }
    }


    public TypeWagonDto toDto(TypeWagon item) {
        if (item == null) {
            return null;
        } else {
            TypeWagonDto dto = new TypeWagonDto();
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


        return dto;
        }
    }



    public List<TypeWagon> toItem(List<TypeWagonDto> dtos) {
        List<TypeWagon> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TypeWagonDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TypeWagonDto> toDto(List<TypeWagon> items) {
        List<TypeWagonDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TypeWagon item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TypeWagonDto dto, TypeWagon t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<TypeWagon> copy(List<TypeWagonDto> dtos) {
        List<TypeWagon> result = new ArrayList<>();
        if (dtos != null) {
            for (TypeWagonDto dto : dtos) {
                TypeWagon instance = new TypeWagon();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
