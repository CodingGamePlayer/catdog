package kr.co.catdog.apicontroller;

import kr.co.catdog.dto.CartDTO;
import kr.co.catdog.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CartApiController {
    private final CartService cartService;
    @PutMapping("/api/user/shop/cart")
    public ResponseEntity<CartDTO> edit(@RequestBody CartDTO cartDTO){
        int result = cartService.update(cartDTO);
        if(result == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("api/user/shop/cart/delete")
    public ResponseEntity<CartDTO> deleteAll(@RequestParam("user_id") String user_id){
        log.info("카트 delete user_id : "+user_id);
        int result = cartService.deleteAll(user_id);
        if(result == 0){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
