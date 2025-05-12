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

import ma.zyn.app.bean.core.referentiel.TypeStock;
import ma.zyn.app.service.facade.admin.referentiel.TypeStockAdminService;
import ma.zyn.app.ws.mapper.referentiel.TypeStockMapper;
import ma.zyn.app.ws.dto.referentiel.TypeStockDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/typeStock/")
public class TypeStockRestAdmin {




    @Operation(summary = "Finds a list of all typeStocks")
    @GetMapping("")
    public ResponseEntity<List<TypeStockDto>> findAll(){
        ResponseEntity<List<TypeStockDto>> res = null;
        List<TypeStock> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeStockDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all typeStocks")
    @GetMapping("optimized")
    public ResponseEntity<List<TypeStockDto>> findAllOptimized(){
        ResponseEntity<List<TypeStockDto>> res = null;
        List<TypeStock> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeStockDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a typeStock by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeStockDto> findById(@PathVariable Long id) {
        TypeStock t = service.findById(id);
        if (t != null) {
            TypeStockDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a typeStock by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<TypeStockDto> findByLibelle(@PathVariable String libelle) {
	    TypeStock t = service.findByReferenceEntity(new TypeStock(libelle));
        if (t != null) {
            TypeStockDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  typeStock")
    @PostMapping("")
    public ResponseEntity<TypeStockDto> save(@RequestBody TypeStockDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            TypeStock myT = mapper.toItem(dto);
            TypeStock t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                TypeStockDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  typeStock")
    @PutMapping("")
    public ResponseEntity<TypeStockDto> update(@RequestBody TypeStockDto dto){
        ResponseEntity<TypeStockDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeStock t = service.findById(dto.getId());
            mapper.copy(dto,t);
            TypeStock updated = service.update(t);
            TypeStockDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeStock")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeStockDto>> delete(@RequestBody List<TypeStockDto> dtos){
        ResponseEntity<List<TypeStockDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeStock> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeStock")
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


    @Operation(summary = "Finds a typeStock and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeStockDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeStock loaded =  service.findWithAssociatedLists(id);
        TypeStockDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<TypeStockDto> findDtos(List<TypeStock> list){
        List<TypeStockDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeStockDto> getDtoResponseEntity(TypeStockDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public TypeStockRestAdmin(TypeStockAdminService service, TypeStockMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final TypeStockAdminService service;
    private final TypeStockMapper mapper;





}
