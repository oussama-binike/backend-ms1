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

import ma.zyn.app.bean.core.referentiel.CategorieStock;
import ma.zyn.app.service.facade.admin.referentiel.CategorieStockAdminService;
import ma.zyn.app.ws.mapper.referentiel.CategorieStockMapper;
import ma.zyn.app.ws.dto.referentiel.CategorieStockDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/categorieStock/")
public class CategorieStockRestAdmin {




    @Operation(summary = "Finds a list of all categorieStocks")
    @GetMapping("")
    public ResponseEntity<List<CategorieStockDto>> findAll(){
        ResponseEntity<List<CategorieStockDto>> res = null;
        List<CategorieStock> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CategorieStockDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all categorieStocks")
    @GetMapping("optimized")
    public ResponseEntity<List<CategorieStockDto>> findAllOptimized(){
        ResponseEntity<List<CategorieStockDto>> res = null;
        List<CategorieStock> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CategorieStockDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a categorieStock by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CategorieStockDto> findById(@PathVariable Long id) {
        CategorieStock t = service.findById(id);
        if (t != null) {
            CategorieStockDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a categorieStock by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<CategorieStockDto> findByLibelle(@PathVariable String libelle) {
	    CategorieStock t = service.findByReferenceEntity(new CategorieStock(libelle));
        if (t != null) {
            CategorieStockDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  categorieStock")
    @PostMapping("")
    public ResponseEntity<CategorieStockDto> save(@RequestBody CategorieStockDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            CategorieStock myT = mapper.toItem(dto);
            CategorieStock t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                CategorieStockDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  categorieStock")
    @PutMapping("")
    public ResponseEntity<CategorieStockDto> update(@RequestBody CategorieStockDto dto){
        ResponseEntity<CategorieStockDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CategorieStock t = service.findById(dto.getId());
            mapper.copy(dto,t);
            CategorieStock updated = service.update(t);
            CategorieStockDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of categorieStock")
    @PostMapping("multiple")
    public ResponseEntity<List<CategorieStockDto>> delete(@RequestBody List<CategorieStockDto> dtos){
        ResponseEntity<List<CategorieStockDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<CategorieStock> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified categorieStock")
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


    @Operation(summary = "Finds a categorieStock and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CategorieStockDto> findWithAssociatedLists(@PathVariable Long id) {
        CategorieStock loaded =  service.findWithAssociatedLists(id);
        CategorieStockDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<CategorieStockDto> findDtos(List<CategorieStock> list){
        List<CategorieStockDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<CategorieStockDto> getDtoResponseEntity(CategorieStockDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public CategorieStockRestAdmin(CategorieStockAdminService service, CategorieStockMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final CategorieStockAdminService service;
    private final CategorieStockMapper mapper;





}
