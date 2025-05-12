package  ma.zyn.app.api.facade.admin.referentiel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.referentiel.Stock;
import ma.zyn.app.service.facade.admin.referentiel.StockAdminService;
import ma.zyn.app.ws.mapper.referentiel.StockMapper;
import ma.zyn.app.ws.dto.referentiel.StockDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/stock/")
public class StockRestAdmin {




    @Operation(summary = "Finds a list of all stocks")
    @GetMapping("")
    public ResponseEntity<List<StockDto>> findAll(){
        ResponseEntity<List<StockDto>> res = null;
        List<Stock> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<StockDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all stocks")
    @GetMapping("optimized")
    public ResponseEntity<List<StockDto>> findAllOptimized(){
        ResponseEntity<List<StockDto>> res = null;
        List<Stock> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<StockDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a stock by id")
    @GetMapping("id/{id}")
    public ResponseEntity<StockDto> findById(@PathVariable Long id) {
        Stock t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            StockDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a stock by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<StockDto> findByLibelle(@PathVariable String libelle) {
	    Stock t = service.findByReferenceEntity(new Stock(libelle));
        if (t != null) {
            mapper.init(true);
            StockDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  stock")
    @PostMapping("")
    public ResponseEntity<StockDto> save(@RequestBody StockDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            Stock myT = mapper.toItem(dto);
            Stock t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                StockDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  stock")
    @PutMapping("")
    public ResponseEntity<StockDto> update(@RequestBody StockDto dto){
        ResponseEntity<StockDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Stock t = service.findById(dto.getId());
            mapper.copy(dto,t);
            Stock updated = service.update(t);
            StockDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of stock")
    @PostMapping("multiple")
    public ResponseEntity<List<StockDto>> delete(@RequestBody List<StockDto> dtos){
        ResponseEntity<List<StockDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<Stock> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified stock")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id){
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }

    @Operation(summary = "find by stadeOperatoire code")
    @GetMapping("stadeOperatoire/code/{code}")
    public List<StockDto> findByStadeOperatoireCode(@PathVariable String code){
        return findDtos(service.findByStadeOperatoireCode(code));
    }
    @Operation(summary = "delete by stadeOperatoire code")
    @DeleteMapping("stadeOperatoire/code/{code}")
    public int deleteByStadeOperatoireCode(@PathVariable String code){
        return service.deleteByStadeOperatoireCode(code);
    }
    @Operation(summary = "find by categorieStock code")
    @GetMapping("categorieStock/code/{code}")
    public List<StockDto> findByCategorieStockCode(@PathVariable String code){
        return findDtos(service.findByCategorieStockCode(code));
    }
    @Operation(summary = "delete by categorieStock code")
    @DeleteMapping("categorieStock/code/{code}")
    public int deleteByCategorieStockCode(@PathVariable String code){
        return service.deleteByCategorieStockCode(code);
    }

    @Operation(summary = "Finds a stock and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<StockDto> findWithAssociatedLists(@PathVariable Long id) {
        Stock loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        StockDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<StockDto> findDtos(List<Stock> list){
        mapper.initObject(true);
        List<StockDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<StockDto> getDtoResponseEntity(StockDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public StockRestAdmin(StockAdminService service, StockMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final StockAdminService service;
    private final StockMapper mapper;





}
