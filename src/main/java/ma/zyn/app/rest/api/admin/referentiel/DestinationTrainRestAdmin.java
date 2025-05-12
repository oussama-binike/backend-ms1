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

import ma.zyn.app.bean.core.referentiel.DestinationTrain;
import ma.zyn.app.service.facade.admin.referentiel.DestinationTrainAdminService;
import ma.zyn.app.ws.mapper.referentiel.DestinationTrainMapper;
import ma.zyn.app.ws.dto.referentiel.DestinationTrainDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/destinationTrain/")
public class DestinationTrainRestAdmin {




    @Operation(summary = "Finds a list of all destinationTrains")
    @GetMapping("")
    public ResponseEntity<List<DestinationTrainDto>> findAll(){
        ResponseEntity<List<DestinationTrainDto>> res = null;
        List<DestinationTrain> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DestinationTrainDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all destinationTrains")
    @GetMapping("optimized")
    public ResponseEntity<List<DestinationTrainDto>> findAllOptimized(){
        ResponseEntity<List<DestinationTrainDto>> res = null;
        List<DestinationTrain> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DestinationTrainDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a destinationTrain by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DestinationTrainDto> findById(@PathVariable Long id) {
        DestinationTrain t = service.findById(id);
        if (t != null) {
            DestinationTrainDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a destinationTrain by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<DestinationTrainDto> findByLibelle(@PathVariable String libelle) {
	    DestinationTrain t = service.findByReferenceEntity(new DestinationTrain(libelle));
        if (t != null) {
            DestinationTrainDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  destinationTrain")
    @PostMapping("")
    public ResponseEntity<DestinationTrainDto> save(@RequestBody DestinationTrainDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            DestinationTrain myT = mapper.toItem(dto);
            DestinationTrain t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                DestinationTrainDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  destinationTrain")
    @PutMapping("")
    public ResponseEntity<DestinationTrainDto> update(@RequestBody DestinationTrainDto dto){
        ResponseEntity<DestinationTrainDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DestinationTrain t = service.findById(dto.getId());
            mapper.copy(dto,t);
            DestinationTrain updated = service.update(t);
            DestinationTrainDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of destinationTrain")
    @PostMapping("multiple")
    public ResponseEntity<List<DestinationTrainDto>> delete(@RequestBody List<DestinationTrainDto> dtos){
        ResponseEntity<List<DestinationTrainDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<DestinationTrain> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified destinationTrain")
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


    @Operation(summary = "Finds a destinationTrain and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DestinationTrainDto> findWithAssociatedLists(@PathVariable Long id) {
        DestinationTrain loaded =  service.findWithAssociatedLists(id);
        DestinationTrainDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<DestinationTrainDto> findDtos(List<DestinationTrain> list){
        List<DestinationTrainDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<DestinationTrainDto> getDtoResponseEntity(DestinationTrainDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public DestinationTrainRestAdmin(DestinationTrainAdminService service, DestinationTrainMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final DestinationTrainAdminService service;
    private final DestinationTrainMapper mapper;





}
