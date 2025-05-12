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
import ma.zyn.app.bean.core.referentiel.ProvennanceTrain;
import ma.zyn.app.ws.dto.referentiel.ProvennanceTrainDto;

@Component
public class ProvennanceTrainMapper {



    public ProvennanceTrain toItem(ProvennanceTrainDto dto) {
        if (dto == null) {
            return null;
        } else {
        ProvennanceTrain item = new ProvennanceTrain();
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


    public ProvennanceTrainDto toDto(ProvennanceTrain item) {
        if (item == null) {
            return null;
        } else {
            ProvennanceTrainDto dto = new ProvennanceTrainDto();
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



    public List<ProvennanceTrain> toItem(List<ProvennanceTrainDto> dtos) {
        List<ProvennanceTrain> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProvennanceTrainDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProvennanceTrainDto> toDto(List<ProvennanceTrain> items) {
        List<ProvennanceTrainDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ProvennanceTrain item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProvennanceTrainDto dto, ProvennanceTrain t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<ProvennanceTrain> copy(List<ProvennanceTrainDto> dtos) {
        List<ProvennanceTrain> result = new ArrayList<>();
        if (dtos != null) {
            for (ProvennanceTrainDto dto : dtos) {
                ProvennanceTrain instance = new ProvennanceTrain();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
