package kr.co.catdog.apicontroller;

import kr.co.catdog.dto.CartDTO;
import kr.co.catdog.dto.ProductDTO;
import kr.co.catdog.service.CartService;
import kr.co.catdog.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ShopApiController {
    private final ShopService shopService;
    @PostMapping("/api/user/shop")
    public ResponseEntity<ProductDTO> register(@RequestBody ProductDTO productDTO){

        log.info(String.valueOf(productDTO));
        int result = shopService.insert(productDTO);

        if(result == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PutMapping("/api/user/shop")
    public ResponseEntity<ProductDTO> edit(@RequestBody ProductDTO productDTO) {
        log.info(String.valueOf(productDTO));
        int result = shopService.update(productDTO);

        if(result == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/api/user/shop")
    public ResponseEntity<List<ProductDTO>> list(@RequestBody ProductDTO productDTO) {
        log.info("ddddddddddd"+String.valueOf(productDTO));
//        List<ProductDTO> productDTOList = shopService.selectAll(productDTO);

//        if(){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//
        return ResponseEntity.status(HttpStatus.OK).build();
    }

//    @PostMapping(value = "/misu", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<MisuDTO> register(@RequestBody MisuDTO misuDTO) {
//
//        if(misuDTO.getMisu_uuid() == null){
//            misuDTO.setMisu_uuid("");
//            misuDTO.setMisu_filename("");
//        }
//    }
}
