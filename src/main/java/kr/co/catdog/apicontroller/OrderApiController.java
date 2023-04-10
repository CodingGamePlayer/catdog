package kr.co.catdog.apicontroller;

import kr.co.catdog.dto.OrderDTO;
import kr.co.catdog.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderApiController {
    @Autowired
    OrderService orderService;

    @DeleteMapping("api/user/order/delete")
    public ResponseEntity delete(@RequestParam int order_no){
        int result = orderService.delete(order_no);
        if(!(result>0)){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("api/user/order/list")
    public List<OrderDTO> findById(@RequestParam("user_id") String user_id){
       return orderService.findById(user_id);
    }
}
