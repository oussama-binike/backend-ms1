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
import ma.zyn.app.bean.core.referentiel.Train;
import ma.zyn.app.ws.dto.referentiel.TrainDto;

@Component
public class TrainMapper {



    public Train toItem(TrainDto dto) {
        if (dto == null) {
            return null;
        } else {
        Train item = new Train();
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


    public TrainDto toDto(Train item) {
        if (item == null) {
            return null;
        } else {
            TrainDto dto = new TrainDto();
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



    public List<Train> toItem(List<TrainDto> dtos) {
        List<Train> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TrainDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TrainDto> toDto(List<Train> items) {
        List<TrainDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Train item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TrainDto dto, Train t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Train> copy(List<TrainDto> dtos) {
        List<Train> result = new ArrayList<>();
        if (dtos != null) {
            for (TrainDto dto : dtos) {
                Train instance = new Train();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
