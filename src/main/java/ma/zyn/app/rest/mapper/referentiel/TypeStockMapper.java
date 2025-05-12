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
import ma.zyn.app.bean.core.referentiel.TypeStock;
import ma.zyn.app.ws.dto.referentiel.TypeStockDto;

@Component
public class TypeStockMapper {



    public TypeStock toItem(TypeStockDto dto) {
        if (dto == null) {
            return null;
        } else {
        TypeStock item = new TypeStock();
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


    public TypeStockDto toDto(TypeStock item) {
        if (item == null) {
            return null;
        } else {
            TypeStockDto dto = new TypeStockDto();
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



    public List<TypeStock> toItem(List<TypeStockDto> dtos) {
        List<TypeStock> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TypeStockDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TypeStockDto> toDto(List<TypeStock> items) {
        List<TypeStockDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TypeStock item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TypeStockDto dto, TypeStock t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<TypeStock> copy(List<TypeStockDto> dtos) {
        List<TypeStock> result = new ArrayList<>();
        if (dtos != null) {
            for (TypeStockDto dto : dtos) {
                TypeStock instance = new TypeStock();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
