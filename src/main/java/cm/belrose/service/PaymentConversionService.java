package cm.belrose.service;

import cm.belrose.model.PaymentRequest;
import org.springframework.web.multipart.MultipartFile;

public interface PaymentConversionService {
         String convertToMT103(MultipartFile file);
    }
