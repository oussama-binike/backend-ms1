package  ma.zyn.app.api.facade.admin.reclamation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.reclamation.ReclamationElementChimique;
import ma.zyn.app.service.facade.admin.reclamation.ReclamationElementChimiqueAdminService;
import ma.zyn.app.ws.mapper.reclamation.ReclamationElementChimiqueMapper;
import ma.zyn.app.ws.dto.reclamation.ReclamationElementChimiqueDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/reclamationElementChimique/")
public class ReclamationElementChimiqueRestAdmin {




    @Operation(summary = "Finds a list of all reclamationElementChimiques")
    @GetMapping("")
    public ResponseEntity<List<ReclamationElementChimiqueDto>> findAll(){
        ResponseEntity<List<ReclamationElementChimiqueDto>> res = null;
        List<ReclamationElementChimique> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            mapper.initObject(true);
        List<ReclamationElementChimiqueDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a reclamationElementChimique by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ReclamationElementChimiqueDto> findById(@PathVariable Long id) {
        ReclamationElementChimique t = service.findById(id);
        if (t != null) {
            mapper.init(true);
            ReclamationElementChimiqueDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  reclamationElementChimique")
    @PostMapping("")
    public ResponseEntity<ReclamationElementChimiqueDto> save(@RequestBody ReclamationElementChimiqueDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            mapper.init(true);
            ReclamationElementChimique myT = mapper.toItem(dto);
            ReclamationElementChimique t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                ReclamationElementChimiqueDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  reclamationElementChimique")
    @PutMapping("")
    public ResponseEntity<ReclamationElementChimiqueDto> update(@RequestBody ReclamationElementChimiqueDto dto){
        ResponseEntity<ReclamationElementChimiqueDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ReclamationElementChimique t = service.findById(dto.getId());
            mapper.copy(dto,t);
            ReclamationElementChimique updated = service.update(t);
            ReclamationElementChimiqueDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of reclamationElementChimique")
    @PostMapping("multiple")
    public ResponseEntity<List<ReclamationElementChimiqueDto>> delete(@RequestBody List<ReclamationElementChimiqueDto> dtos){
        ResponseEntity<List<ReclamationElementChimiqueDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            mapper.init(false);
            List<ReclamationElementChimique> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified reclamationElementChimique")
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

    @Operation(summary = "find by elementChimique code")
    @GetMapping("elementChimique/code/{code}")
    public List<ReclamationElementChimiqueDto> findByElementChimiqueCode(@PathVariable String code){
        return findDtos(service.findByElementChimiqueCode(code));
    }
    @Operation(summary = "delete by elementChimique code")
    @DeleteMapping("elementChimique/code/{code}")
    public int deleteByElementChimiqueCode(@PathVariable String code){
        return service.deleteByElementChimiqueCode(code);
    }

    @Operation(summary = "Finds a reclamationElementChimique and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ReclamationElementChimiqueDto> findWithAssociatedLists(@PathVariable Long id) {
        ReclamationElementChimique loaded =  service.findWithAssociatedLists(id);
        mapper.init(true);
        ReclamationElementChimiqueDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<ReclamationElementChimiqueDto> findDtos(List<ReclamationElementChimique> list){
        mapper.initObject(true);
        List<ReclamationElementChimiqueDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<ReclamationElementChimiqueDto> getDtoResponseEntity(ReclamationElementChimiqueDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public ReclamationElementChimiqueRestAdmin(ReclamationElementChimiqueAdminService service, ReclamationElementChimiqueMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final ReclamationElementChimiqueAdminService service;
    private final ReclamationElementChimiqueMapper mapper;





}
