package  ma.zyn.app.api.facade.admin.camion;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.camion.ProvennanceCamion;
import ma.zyn.app.service.facade.admin.camion.ProvennanceCamionAdminService;
import ma.zyn.app.ws.mapper.camion.ProvennanceCamionMapper;
import ma.zyn.app.ws.dto.camion.ProvennanceCamionDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/provennanceCamion/")
public class ProvennanceCamionRestAdmin {




    @Operation(summary = "Finds a list of all provennanceCamions")
    @GetMapping("")
    public ResponseEntity<List<ProvennanceCamionDto>> findAll(){
        ResponseEntity<List<ProvennanceCamionDto>> res = null;
        List<ProvennanceCamion> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProvennanceCamionDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all provennanceCamions")
    @GetMapping("optimized")
    public ResponseEntity<List<ProvennanceCamionDto>> findAllOptimized(){
        ResponseEntity<List<ProvennanceCamionDto>> res = null;
        List<ProvennanceCamion> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProvennanceCamionDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a provennanceCamion by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProvennanceCamionDto> findById(@PathVariable Long id) {
        ProvennanceCamion t = service.findById(id);
        if (t != null) {
            ProvennanceCamionDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a provennanceCamion by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ProvennanceCamionDto> findByLibelle(@PathVariable String libelle) {
	    ProvennanceCamion t = service.findByReferenceEntity(new ProvennanceCamion(libelle));
        if (t != null) {
            ProvennanceCamionDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  provennanceCamion")
    @PostMapping("")
    public ResponseEntity<ProvennanceCamionDto> save(@RequestBody ProvennanceCamionDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            ProvennanceCamion myT = mapper.toItem(dto);
            ProvennanceCamion t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                ProvennanceCamionDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  provennanceCamion")
    @PutMapping("")
    public ResponseEntity<ProvennanceCamionDto> update(@RequestBody ProvennanceCamionDto dto){
        ResponseEntity<ProvennanceCamionDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ProvennanceCamion t = service.findById(dto.getId());
            mapper.copy(dto,t);
            ProvennanceCamion updated = service.update(t);
            ProvennanceCamionDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of provennanceCamion")
    @PostMapping("multiple")
    public ResponseEntity<List<ProvennanceCamionDto>> delete(@RequestBody List<ProvennanceCamionDto> dtos){
        ResponseEntity<List<ProvennanceCamionDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<ProvennanceCamion> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified provennanceCamion")
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


    @Operation(summary = "Finds a provennanceCamion and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProvennanceCamionDto> findWithAssociatedLists(@PathVariable Long id) {
        ProvennanceCamion loaded =  service.findWithAssociatedLists(id);
        ProvennanceCamionDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<ProvennanceCamionDto> findDtos(List<ProvennanceCamion> list){
        List<ProvennanceCamionDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProvennanceCamionDto> getDtoResponseEntity(ProvennanceCamionDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public ProvennanceCamionRestAdmin(ProvennanceCamionAdminService service, ProvennanceCamionMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final ProvennanceCamionAdminService service;
    private final ProvennanceCamionMapper mapper;





}
