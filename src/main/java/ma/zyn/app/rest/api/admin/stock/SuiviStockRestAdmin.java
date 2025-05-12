package  ma.zyn.app.api.facade.admin.stock;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.stock.SuiviStock;
import ma.zyn.app.service.facade.admin.stock.SuiviStockAdminService;
import ma.zyn.app.ws.mapper.stock.SuiviStockMapper;
import ma.zyn.app.ws.dto.stock.SuiviStockDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/suiviStock/")
public class SuiviStockRestAdmin {




    @Operation(summary = "Finds a list of all suiviStocks")
    @GetMapping("")
    public ResponseEntity<List<SuiviStockDto>> findAll(){
        ResponseEntity<List<SuiviStockDto>> res = null;
        List<SuiviStock> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<SuiviStockDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all suiviStocks")
    @GetMapping("optimized")
    public ResponseEntity<List<SuiviStockDto>> findAllOptimized(){
        ResponseEntity<List<SuiviStockDto>> res = null;
        List<SuiviStock> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        mapper.initObject(true);
        List<SuiviStockDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a suiviStock by id")
    @GetMapping("id/{id}")
    public ResponseEntity<SuiviStockDto> findById(@PathVariable Long id) {
        SuiviStock t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            SuiviStockDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a suiviStock by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<SuiviStockDto> findByLibelle(@PathVariable String libelle) {
	    SuiviStock t = service.findByReferenceEntity(new SuiviStock(libelle));
        if (t != null) {
            mapper.init(true);
            SuiviStockDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  suiviStock")
    @PostMapping("")
    public ResponseEntity<SuiviStockDto> save(@RequestBody SuiviStockDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            SuiviStock myT = mapper.toItem(dto);
            SuiviStock t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                SuiviStockDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  suiviStock")
    @PutMapping("")
    public ResponseEntity<SuiviStockDto> update(@RequestBody SuiviStockDto dto){
        ResponseEntity<SuiviStockDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            SuiviStock t = service.findById(dto.getId());
            mapper.copy(dto,t);
            SuiviStock updated = service.update(t);
            SuiviStockDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of suiviStock")
    @PostMapping("multiple")
    public ResponseEntity<List<SuiviStockDto>> delete(@RequestBody List<SuiviStockDto> dtos){
        ResponseEntity<List<SuiviStockDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<SuiviStock> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified suiviStock")
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


    @Operation(summary = "Finds a suiviStock and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<SuiviStockDto> findWithAssociatedLists(@PathVariable Long id) {
        SuiviStock loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        SuiviStockDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<SuiviStockDto> findDtos(List<SuiviStock> list){
        mapper.initObject(true);
        List<SuiviStockDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<SuiviStockDto> getDtoResponseEntity(SuiviStockDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public SuiviStockRestAdmin(SuiviStockAdminService service, SuiviStockMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final SuiviStockAdminService service;
    private final SuiviStockMapper mapper;





}
