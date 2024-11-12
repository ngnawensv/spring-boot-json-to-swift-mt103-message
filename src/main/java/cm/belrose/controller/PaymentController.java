package cm.belrose.controller;

import cm.belrose.service.PaymentConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentConversionService paymentConversionService;

    @PostMapping("/convert")
    public ResponseEntity<String> convertJsonToMT103(@RequestParam("file") MultipartFile file) {
            String mt103Message = paymentConversionService.convertToMT103(file);
            return ResponseEntity.ok(mt103Message);
    }
}
