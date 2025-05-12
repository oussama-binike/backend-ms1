package  ma.zyn.app.rest.mapper.referentiel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.mapper.referentiel.StockMapper;
import ma.zyn.app.bean.core.referentiel.Stock;
import ma.zyn.app.ws.mapper.referentiel.EnginMapper;
import ma.zyn.app.bean.core.referentiel.Engin;
import ma.zyn.app.ws.mapper.referentiel.OperationStadeOperatoireMapper;
import ma.zyn.app.bean.core.referentiel.OperationStadeOperatoire;
import ma.zyn.app.ws.mapper.referentiel.ProduitMapper;
import ma.zyn.app.bean.core.referentiel.Produit;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.referentiel.Liaison;
import ma.zyn.app.ws.dto.referentiel.LiaisonDto;

@Component
public class LiaisonMapper {

    @Autowired
    private StockMapper stockMapper ;
    @Autowired
    private EnginMapper enginMapper ;
    @Autowired
    private OperationStadeOperatoireMapper operationStadeOperatoireMapper ;
    @Autowired
    private ProduitMapper produitMapper ;
    private boolean stockSource;
    private boolean stockDestination;
    private boolean engin;
    private boolean operationStadeOperatoire;
    private boolean prodduit;

    public  LiaisonMapper() {
        initObject(true);
    }

    public Liaison toItem(LiaisonDto dto) {
        if (dto == null) {
            return null;
        } else {
        Liaison item = new Liaison();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(this.stockSource && dto.getStockSource()!=null)
                item.setStockSource(stockMapper.toItem(dto.getStockSource())) ;

            if(this.stockDestination && dto.getStockDestination()!=null)
                item.setStockDestination(stockMapper.toItem(dto.getStockDestination())) ;

            if(this.engin && dto.getEngin()!=null)
                item.setEngin(enginMapper.toItem(dto.getEngin())) ;

            if(this.operationStadeOperatoire && dto.getOperationStadeOperatoire()!=null)
                item.setOperationStadeOperatoire(operationStadeOperatoireMapper.toItem(dto.getOperationStadeOperatoire())) ;

            if(this.prodduit && dto.getProdduit()!=null)
                item.setProdduit(produitMapper.toItem(dto.getProdduit())) ;




        return item;
        }
    }


    public LiaisonDto toDto(Liaison item) {
        if (item == null) {
            return null;
        } else {
            LiaisonDto dto = new LiaisonDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.stockSource && item.getStockSource()!=null) {
                dto.setStockSource(stockMapper.toDto(item.getStockSource())) ;

            }
            if(this.stockDestination && item.getStockDestination()!=null) {
                dto.setStockDestination(stockMapper.toDto(item.getStockDestination())) ;

            }
            if(this.engin && item.getEngin()!=null) {
                dto.setEngin(enginMapper.toDto(item.getEngin())) ;

            }
            if(this.operationStadeOperatoire && item.getOperationStadeOperatoire()!=null) {
                dto.setOperationStadeOperatoire(operationStadeOperatoireMapper.toDto(item.getOperationStadeOperatoire())) ;

            }
            if(this.prodduit && item.getProdduit()!=null) {
                dto.setProdduit(produitMapper.toDto(item.getProdduit())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.stockSource = value;
        this.stockDestination = value;
        this.engin = value;
        this.operationStadeOperatoire = value;
        this.prodduit = value;
    }

    public List<Liaison> toItem(List<LiaisonDto> dtos) {
        List<Liaison> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (LiaisonDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<LiaisonDto> toDto(List<Liaison> items) {
        List<LiaisonDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Liaison item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(LiaisonDto dto, Liaison t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getStockSource() == null  && dto.getStockSource() != null){
            t.setStockSource(new Stock());
        }else if (t.getStockSource() != null  && dto.getStockSource() != null){
            t.setStockSource(null);
            t.setStockSource(new Stock());
        }
        if(t.getStockDestination() == null  && dto.getStockDestination() != null){
            t.setStockDestination(new Stock());
        }else if (t.getStockDestination() != null  && dto.getStockDestination() != null){
            t.setStockDestination(null);
            t.setStockDestination(new Stock());
        }
        if(t.getEngin() == null  && dto.getEngin() != null){
            t.setEngin(new Engin());
        }else if (t.getEngin() != null  && dto.getEngin() != null){
            t.setEngin(null);
            t.setEngin(new Engin());
        }
        if(t.getOperationStadeOperatoire() == null  && dto.getOperationStadeOperatoire() != null){
            t.setOperationStadeOperatoire(new OperationStadeOperatoire());
        }else if (t.getOperationStadeOperatoire() != null  && dto.getOperationStadeOperatoire() != null){
            t.setOperationStadeOperatoire(null);
            t.setOperationStadeOperatoire(new OperationStadeOperatoire());
        }
        if(t.getProdduit() == null  && dto.getProdduit() != null){
            t.setProdduit(new Produit());
        }else if (t.getProdduit() != null  && dto.getProdduit() != null){
            t.setProdduit(null);
            t.setProdduit(new Produit());
        }
        if (dto.getStockSource() != null)
        stockMapper.copy(dto.getStockSource(), t.getStockSource());
        if (dto.getStockDestination() != null)
        stockMapper.copy(dto.getStockDestination(), t.getStockDestination());
        if (dto.getEngin() != null)
        enginMapper.copy(dto.getEngin(), t.getEngin());
        if (dto.getOperationStadeOperatoire() != null)
        operationStadeOperatoireMapper.copy(dto.getOperationStadeOperatoire(), t.getOperationStadeOperatoire());
        if (dto.getProdduit() != null)
        produitMapper.copy(dto.getProdduit(), t.getProdduit());
    }

    public List<Liaison> copy(List<LiaisonDto> dtos) {
        List<Liaison> result = new ArrayList<>();
        if (dtos != null) {
            for (LiaisonDto dto : dtos) {
                Liaison instance = new Liaison();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public StockMapper getStockMapper(){
        return this.stockMapper;
    }
    public void setStockMapper(StockMapper stockMapper ){
        this.stockMapper = stockMapper;
    }
    public EnginMapper getEnginMapper(){
        return this.enginMapper;
    }
    public void setEnginMapper(EnginMapper enginMapper ){
        this.enginMapper = enginMapper;
    }
    public OperationStadeOperatoireMapper getOperationStadeOperatoireMapper(){
        return this.operationStadeOperatoireMapper;
    }
    public void setOperationStadeOperatoireMapper(OperationStadeOperatoireMapper operationStadeOperatoireMapper ){
        this.operationStadeOperatoireMapper = operationStadeOperatoireMapper;
    }
    public ProduitMapper getProduitMapper(){
        return this.produitMapper;
    }
    public void setProduitMapper(ProduitMapper produitMapper ){
        this.produitMapper = produitMapper;
    }
    public boolean  isStockSource(){
        return this.stockSource;
    }
    public void  setStockSource(boolean stockSource){
        this.stockSource = stockSource;
    }
    public boolean  isStockDestination(){
        return this.stockDestination;
    }
    public void  setStockDestination(boolean stockDestination){
        this.stockDestination = stockDestination;
    }
    public boolean  isEngin(){
        return this.engin;
    }
    public void  setEngin(boolean engin){
        this.engin = engin;
    }
    public boolean  isOperationStadeOperatoire(){
        return this.operationStadeOperatoire;
    }
    public void  setOperationStadeOperatoire(boolean operationStadeOperatoire){
        this.operationStadeOperatoire = operationStadeOperatoire;
    }
    public boolean  isProdduit(){
        return this.prodduit;
    }
    public void  setProdduit(boolean prodduit){
        this.prodduit = prodduit;
    }
}
