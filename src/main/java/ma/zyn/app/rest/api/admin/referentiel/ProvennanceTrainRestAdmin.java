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

import ma.zyn.app.bean.core.referentiel.ProvennanceTrain;
import ma.zyn.app.service.facade.admin.referentiel.ProvennanceTrainAdminService;
import ma.zyn.app.ws.mapper.referentiel.ProvennanceTrainMapper;
import ma.zyn.app.ws.dto.referentiel.ProvennanceTrainDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/provennanceTrain/")
public class ProvennanceTrainRestAdmin {




    @Operation(summary = "Finds a list of all provennanceTrains")
    @GetMapping("")
    public ResponseEntity<List<ProvennanceTrainDto>> findAll(){
        ResponseEntity<List<ProvennanceTrainDto>> res = null;
        List<ProvennanceTrain> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProvennanceTrainDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all provennanceTrains")
    @GetMapping("optimized")
    public ResponseEntity<List<ProvennanceTrainDto>> findAllOptimized(){
        ResponseEntity<List<ProvennanceTrainDto>> res = null;
        List<ProvennanceTrain> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProvennanceTrainDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a provennanceTrain by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProvennanceTrainDto> findById(@PathVariable Long id) {
        ProvennanceTrain t = service.findById(id);
        if (t != null) {
            ProvennanceTrainDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a provennanceTrain by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ProvennanceTrainDto> findByLibelle(@PathVariable String libelle) {
	    ProvennanceTrain t = service.findByReferenceEntity(new ProvennanceTrain(libelle));
        if (t != null) {
            ProvennanceTrainDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  provennanceTrain")
    @PostMapping("")
    public ResponseEntity<ProvennanceTrainDto> save(@RequestBody ProvennanceTrainDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            ProvennanceTrain myT = mapper.toItem(dto);
            ProvennanceTrain t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                ProvennanceTrainDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  provennanceTrain")
    @PutMapping("")
    public ResponseEntity<ProvennanceTrainDto> update(@RequestBody ProvennanceTrainDto dto){
        ResponseEntity<ProvennanceTrainDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ProvennanceTrain t = service.findById(dto.getId());
            mapper.copy(dto,t);
            ProvennanceTrain updated = service.update(t);
            ProvennanceTrainDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of provennanceTrain")
    @PostMapping("multiple")
    public ResponseEntity<List<ProvennanceTrainDto>> delete(@RequestBody List<ProvennanceTrainDto> dtos){
        ResponseEntity<List<ProvennanceTrainDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<ProvennanceTrain> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified provennanceTrain")
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


    @Operation(summary = "Finds a provennanceTrain and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProvennanceTrainDto> findWithAssociatedLists(@PathVariable Long id) {
        ProvennanceTrain loaded =  service.findWithAssociatedLists(id);
        ProvennanceTrainDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<ProvennanceTrainDto> findDtos(List<ProvennanceTrain> list){
        List<ProvennanceTrainDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProvennanceTrainDto> getDtoResponseEntity(ProvennanceTrainDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public ProvennanceTrainRestAdmin(ProvennanceTrainAdminService service, ProvennanceTrainMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final ProvennanceTrainAdminService service;
    private final ProvennanceTrainMapper mapper;





}
