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
import ma.zyn.app.bean.core.referentiel.DestinationTrain;
import ma.zyn.app.ws.dto.referentiel.DestinationTrainDto;

@Component
public class DestinationTrainMapper {



    public DestinationTrain toItem(DestinationTrainDto dto) {
        if (dto == null) {
            return null;
        } else {
        DestinationTrain item = new DestinationTrain();
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


    public DestinationTrainDto toDto(DestinationTrain item) {
        if (item == null) {
            return null;
        } else {
            DestinationTrainDto dto = new DestinationTrainDto();
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



    public List<DestinationTrain> toItem(List<DestinationTrainDto> dtos) {
        List<DestinationTrain> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DestinationTrainDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DestinationTrainDto> toDto(List<DestinationTrain> items) {
        List<DestinationTrainDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DestinationTrain item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DestinationTrainDto dto, DestinationTrain t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<DestinationTrain> copy(List<DestinationTrainDto> dtos) {
        List<DestinationTrain> result = new ArrayList<>();
        if (dtos != null) {
            for (DestinationTrainDto dto : dtos) {
                DestinationTrain instance = new DestinationTrain();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
