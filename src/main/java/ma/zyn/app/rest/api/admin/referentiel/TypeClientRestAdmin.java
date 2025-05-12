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

import ma.zyn.app.bean.core.referentiel.TypeClient;
import ma.zyn.app.service.facade.admin.referentiel.TypeClientAdminService;
import ma.zyn.app.ws.mapper.referentiel.TypeClientMapper;
import ma.zyn.app.ws.dto.referentiel.TypeClientDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/typeClient/")
public class TypeClientRestAdmin {




    @Operation(summary = "Finds a list of all typeClients")
    @GetMapping("")
    public ResponseEntity<List<TypeClientDto>> findAll(){
        ResponseEntity<List<TypeClientDto>> res = null;
        List<TypeClient> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeClientDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all typeClients")
    @GetMapping("optimized")
    public ResponseEntity<List<TypeClientDto>> findAllOptimized(){
        ResponseEntity<List<TypeClientDto>> res = null;
        List<TypeClient> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeClientDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a typeClient by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeClientDto> findById(@PathVariable Long id) {
        TypeClient t = service.findById(id);
        if (t != null) {
            TypeClientDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a typeClient by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<TypeClientDto> findByLibelle(@PathVariable String libelle) {
	    TypeClient t = service.findByReferenceEntity(new TypeClient(libelle));
        if (t != null) {
            TypeClientDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  typeClient")
    @PostMapping("")
    public ResponseEntity<TypeClientDto> save(@RequestBody TypeClientDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            TypeClient myT = mapper.toItem(dto);
            TypeClient t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                TypeClientDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  typeClient")
    @PutMapping("")
    public ResponseEntity<TypeClientDto> update(@RequestBody TypeClientDto dto){
        ResponseEntity<TypeClientDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeClient t = service.findById(dto.getId());
            mapper.copy(dto,t);
            TypeClient updated = service.update(t);
            TypeClientDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeClient")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeClientDto>> delete(@RequestBody List<TypeClientDto> dtos){
        ResponseEntity<List<TypeClientDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeClient> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeClient")
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


    @Operation(summary = "Finds a typeClient and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeClientDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeClient loaded =  service.findWithAssociatedLists(id);
        TypeClientDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<TypeClientDto> findDtos(List<TypeClient> list){
        List<TypeClientDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeClientDto> getDtoResponseEntity(TypeClientDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public TypeClientRestAdmin(TypeClientAdminService service, TypeClientMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final TypeClientAdminService service;
    private final TypeClientMapper mapper;





}
