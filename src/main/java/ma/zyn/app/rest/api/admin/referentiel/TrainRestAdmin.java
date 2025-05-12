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

import ma.zyn.app.bean.core.referentiel.Train;
import ma.zyn.app.service.facade.admin.referentiel.TrainAdminService;
import ma.zyn.app.ws.mapper.referentiel.TrainMapper;
import ma.zyn.app.ws.dto.referentiel.TrainDto;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/train/")
public class TrainRestAdmin {




    @Operation(summary = "Finds a list of all trains")
    @GetMapping("")
    public ResponseEntity<List<TrainDto>> findAll(){
        ResponseEntity<List<TrainDto>> res = null;
        List<Train> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TrainDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all trains")
    @GetMapping("optimized")
    public ResponseEntity<List<TrainDto>> findAllOptimized(){
        ResponseEntity<List<TrainDto>> res = null;
        List<Train> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TrainDto> dtos  = mapper.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a train by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TrainDto> findById(@PathVariable Long id) {
        Train t = service.findById(id);
        if (t != null) {
            TrainDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a train by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<TrainDto> findByLibelle(@PathVariable String libelle) {
	    Train t = service.findByReferenceEntity(new Train(libelle));
        if (t != null) {
            TrainDto dto = mapper.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  train")
    @PostMapping("")
    public ResponseEntity<TrainDto> save(@RequestBody TrainDto dto){
        if (dto == null) {
            return ResponseEntity.noContent().build();
        }
            Train myT = mapper.toItem(dto);
            Train t = service.create(myT);
            if (t == null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			} else{
                TrainDto myDto = mapper.toDto(t);
				return ResponseEntity.status(HttpStatus.CREATED).body(myDto);
            }
    }

    @Operation(summary = "Updates the specified  train")
    @PutMapping("")
    public ResponseEntity<TrainDto> update(@RequestBody TrainDto dto){
        ResponseEntity<TrainDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Train t = service.findById(dto.getId());
            mapper.copy(dto,t);
            Train updated = service.update(t);
            TrainDto myDto = mapper.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of train")
    @PostMapping("multiple")
    public ResponseEntity<List<TrainDto>> delete(@RequestBody List<TrainDto> dtos){
        ResponseEntity<List<TrainDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Train> ts = mapper.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified train")
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


    @Operation(summary = "Finds a train and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TrainDto> findWithAssociatedLists(@PathVariable Long id) {
        Train loaded =  service.findWithAssociatedLists(id);
        TrainDto dto = mapper.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

	public List<TrainDto> findDtos(List<Train> list){
        List<TrainDto> dtos = mapper.toDto(list);
        return dtos;
    }

    private ResponseEntity<TrainDto> getDtoResponseEntity(TrainDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public TrainRestAdmin(TrainAdminService service, TrainMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    private final TrainAdminService service;
    private final TrainMapper mapper;





}
