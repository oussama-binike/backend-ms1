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

import ma.zyn.app.bean.core.referentiel.OperationStadeOperatoire;
import ma.zyn.app.service.facade.admin.referentiel.OperationStadeOperatoireAdminService;
import ma.zyn.app.ws.mapper.referentiel.OperationStadeOperatoireMapper;
import ma.zyn.app.ws.dto.referentiel.OperationStadeOperatoireDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/operationStadeOperatoire/")
public class OperationStadeOperatoireRestAdmin {




    @Operation(summary = "Finds a list of all operationStadeOperatoires")
    @GetMapping("")
    public ResponseEntity<List<OperationStadeOperatoireDto>> findAll(){
        ResponseEntity<List<OperationStadeOperatoireDto>> res = null;
        List<OperationStadeOperatoire> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<OperationStadeOperatoireDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all operationStadeOperatoires")
    @GetMapping("optimized")
    public ResponseEntity<List<OperationStadeOperatoireDto>> findAllOptimized(){
        ResponseEntity<List<OperationStadeOperatoireDto>> res = null;
        List<OperationStadeOperatoire> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<OperationStadeOperatoireDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a operationStadeOperatoire by id")
    @GetMapping("id/{id}")
    public ResponseEntity<OperationStadeOperatoireDto> findById(@PathVariable Long id) {
        OperationStadeOperatoire t = service.findById(id);
        if (t != null) {
            OperationStadeOperatoireDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a operationStadeOperatoire by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<OperationStadeOperatoireDto> findByLibelle(@PathVariable String libelle) {
	    OperationStadeOperatoire t = service.findByReferenceEntity(new OperationStadeOperatoire(libelle));
        if (t != null) {
            OperationStadeOperatoireDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  operationStadeOperatoire")
    @PostMapping("")
    public ResponseEntity<OperationStadeOperatoireDto> save(@RequestBody OperationStadeOperatoireDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            OperationStadeOperatoire myT = mapper.toItem(dto);
            OperationStadeOperatoire t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                OperationStadeOperatoireDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  operationStadeOperatoire")
    @PutMapping("")
    public ResponseEntity<OperationStadeOperatoireDto> update(@RequestBody OperationStadeOperatoireDto dto){
        ResponseEntity<OperationStadeOperatoireDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            OperationStadeOperatoire t = service.findById(dto.getId());
            mapper.copy(dto,t);
            OperationStadeOperatoire updated = service.update(t);
            OperationStadeOperatoireDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of operationStadeOperatoire")
    @PostMapping("multiple")
    public ResponseEntity<List<OperationStadeOperatoireDto>> delete(@RequestBody List<OperationStadeOperatoireDto> dtos){
        ResponseEntity<List<OperationStadeOperatoireDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<OperationStadeOperatoire> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified operationStadeOperatoire")
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


    @Operation(summary = "Finds a operationStadeOperatoire and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<OperationStadeOperatoireDto> findWithAssociatedLists(@PathVariable Long id) {
        OperationStadeOperatoire loaded =  service.findWithAssociatedLists(id);
        OperationStadeOperatoireDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<OperationStadeOperatoireDto> findDtos(List<OperationStadeOperatoire> list){
        List<OperationStadeOperatoireDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<OperationStadeOperatoireDto> getDtoResponseEntity(OperationStadeOperatoireDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public OperationStadeOperatoireRestAdmin(OperationStadeOperatoireAdminService service, OperationStadeOperatoireMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final OperationStadeOperatoireAdminService service;
    private final OperationStadeOperatoireMapper mapper;





}
